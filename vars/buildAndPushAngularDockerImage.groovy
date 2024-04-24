// vars/buildAndPushAngularDockerImage.groovy

def call(Map config) {
    pipeline {
        agent any
        
        stages {
            stage('Build Angular Application') {
                steps {
                    script {
                        // Install dependencies and build Angular app
                      aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin 891376912626.dkr.ecr.us-east-1.amazonaws.com
                      bat 'docker build -t angular-ecr .'
                      bat 'docker tag angular-ecr:latest 891376912626.dkr.ecr.us-east-1.amazonaws.com/angular-ecr:latest'
                      bat 'docker push 891376912626.dkr.ecr.us-east-1.amazonaws.com/angular-ecr:latest'
                          }
                    }
              }
        }
    }
}

