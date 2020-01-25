pipeline {
    agent any
    stages {
        stage('Compile') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('Unit Tests') {
            steps {
                sh 'mvn surefire:test'
            }
        }
         stage('Integration Tests') {
            steps {
                sh 'mvn failsafe:integration-test'
            }
        }
    }
}