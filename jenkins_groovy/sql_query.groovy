pipeline {
    agent any

    parameters {
        string(name: 'TABLE_NAME', defaultValue: 'Players', description: 'Name of the table to query')
        string(name: 'DB_HOST', defaultValue: '10.92.16.59', description: 'MySQL host')
        string(name: 'DB_PORT', defaultValue: '3306', description: 'MySQL port')
        string(name: 'DB_NAME', defaultValue: 'mydatabase', description: 'MySQL database name')
    }

    stages {
        stage('Query MySQL database') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'db_jenkins_creds', passwordVariable: 'db_user_pass', usernameVariable: 'db_user')]) {
                        def query = "SELECT * FROM ${params.TABLE_NAME};"
                        def mysql = "mysql -u $db_user -p$db_user_pass -h ${params.DB_HOST} -P ${params.DB_PORT} -D ${params.DB_NAME} -e '${query}'"
                        def result = sh(returnStdout: true, script: mysql).trim()
                        println(result)
                    }
                }
            }
        }
    }
}
