pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                // Checkout code from Github repository
                git branch: 'main', url: "https://github.com/denisko890Courses/VK_SRE" // используем встроенный в Jenkins плагин Git для скачивания проекта из бранча main
            }
        }
        stage('Run Ansible Playbook') {
            steps {
                // Run Ansible playbook using Ansible plugin
                dir('lamp_ubuntu2004') {
                    ansiblePlaybook(
                        inventory: 'inventory.yaml', // Path to your inventory file
                        playbook: 'playbook.yaml', // Path to your playbook
                        colorized: true // Enable colorized output
                    )
                }
            }
        }
        stage('Check database existence') {
            steps {
                // Check if database exists using shell command and store the result in a variable
                script {
                    withCredentials([usernamePassword(credentialsId: 'db_jenkins_creds', passwordVariable: 'db_user_pass', usernameVariable: 'db_user')]) {
                        def result = sh(returnStdout: true, script: "mysql -u $db_user -p$db_user_pass -h ${params.DB_HOST} -e 'use ${params.DB_NAME}' >/dev/null 2>&1 && echo 'exists' || true").trim()
                        env.DB_EXISTS = result
                    }
                }
            }
        }

        stage('Load database dump') {
            when {
                expression {
                    env.DB_EXISTS == 'exists'
                }
            }
            steps {
                script {
                    def dumpDir = "/var/jenkins_home/workspace/DB_dump/dumps"
                    dir("${dumpDir}") {
                        def dumpFile = sh(script: "ls -t *.sql | head -n 1", returnStdout: true).trim()
                        sh "echo ${dumpFile}"
                        withCredentials([usernamePassword(credentialsId: 'db_jenkins_creds', passwordVariable: 'db_user_pass', usernameVariable: 'db_user')]) {
                            sh "mysql -u $db_user -p$db_user_pass -h ${params.DB_HOST} ${params.DB_NAME} < ${dumpFile}"
                        }
                    }
                }
            }
        }
    }
    parameters {
        string(name: 'DB_NAME', defaultValue: 'mydatabase', description: 'MySQL database name')
        string(name: 'DB_HOST', defaultValue: '10.92.16.59', description: 'MySQL host')
    }
}
