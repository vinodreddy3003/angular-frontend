def call(String imageName, String awsAccountId, String awsRegion) {
    pipeline {
        agent any

        stages {
            stage('Build Docker Image') {
                steps {
                    script {
                        docker.build(angular-app)
                    }
                }
            }
            stage('Push Docker Image to AWS ECR') {
                steps {
                    script {
                        docker.withRegistry('https://' + 8913-7691-2626 + '.dkr.ecr.' + us-east-1 + '.amazonaws.com', 'ecr:us-east-1:aws-credentials') {
                            docker.image(angular-app).push('latest')
                        }
                    }
                }
            }
        }
    }
}
