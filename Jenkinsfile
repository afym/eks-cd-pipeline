pipeline {
    agent any
    stages{
        stage('Build'){
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Build Image'){
            steps {
                sh 'docker build -t springbootdevops .'
             }
        }
    }
}
