def call(String imageName, String awsAccountId, String awsRegion) {
    pipeline {
        agent any

        stages {
            stage('Authenticate with AWS ECR') {
                steps {
                    script {
                        withCredentials([[
                            $class: 'AmazonWebServicesCredentialsBinding',
                            accessKeyVariable: 'AWS_ACCESS_KEY_ID',
                            credentialsId: 'AWS-Credentials',
                            secretKeyVariable: 'AWS_SECRET_ACCESS_KEY'
                        ]]) {
                            sh "aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin 891376912626.dkr.ecr.us-east-1.amazonaws.com"
                        }
                    }
                }
            }
            stage('Build Docker Image') {
                steps {
                    script {
                        docker.build('angular-app')
                    }
                }
            }
            stage('Push Docker Image to AWS ECR') {
                steps {
                    script {
                        docker.withRegistry('https://891376912626.dkr.ecr.us-east-1.amazonaws.com/angular-app') {
                            docker.image('angular-app').push('latest')
                        }
                    }
                }
            }
        }
    }
}
