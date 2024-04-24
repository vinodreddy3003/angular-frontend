def call(String imageName, String awsAccountId, String awsRegion) {
    pipeline {
        agent any

        stages {
            stage('Build Docker Image') {
                steps {
                    script {
                        docker.build(imageName)
                    }
                }
            }
            stage('Push Docker Image to AWS ECR') {
                steps {
                    script {
                        docker.withRegistry('https://' + awsAccountId + '.dkr.ecr.' + awsRegion + '.amazonaws.com', 'ecr:us-west-2:aws-credentials') {
                            docker.image(imageName).push('latest')
                        }
                    }
                }
            }
        }
    }
}
