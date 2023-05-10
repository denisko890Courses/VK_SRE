pipeline {
    agent any

    stages {
        stage('Dump MySQL database') {
            steps {
                script {
                    def now = new Date().format('yyyy-MM-dd_HH-mm-ss')
                    def dumpFile = "${now}-mydatabase.sql"
                    def dumpDir = "${env.WORKSPACE}/dumps"

                    sh "mkdir -p ${dumpDir}"
                    withCredentials([usernamePassword(credentialsId: 'db_jenkins_creds', passwordVariable: 'db_user_pass', usernameVariable: 'db_user')]) {
                        sh "mysqldump -u $db_user -p$db_user_pass -h ${params.DB_HOST} ${params.DB_NAME} > ${dumpDir}/${dumpFile}"
                    }
                    dir("${dumpDir}"){
                        archiveArtifacts "${dumpFile}"
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
