pipeline {
    agent any
    stages{
        stage('Test') {
            steps {
                sh "docker-compose -f docker-test.yml up"
            }
        }
        stage('Sonar') {
            steps {
                sh "docker-compose -f docker-sonar.yml up"
            }
        }
        stage('Build') {
            steps {
                sh "mvn clean package"
            }
        }
        stage('Build') {
            steps {
                sh "mvn clean package"
            }
        }

    # mvn liquibase:diff
    # docker tag 1.0 angelfym/springoboot
    # docker push angelfym/springoboot
}