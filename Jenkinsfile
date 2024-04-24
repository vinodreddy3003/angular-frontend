// Jenkinsfile

pipeline {
    agent any

    stages {
        stage('Build and Push Angular Docker Image') {
            steps {
                buildAndPushAngularDockerImage(
                    IMAGE_TAG: '0.1',
                    ECR_REPO_URL: '891376912626.dkr.ecr.us-east-1.amazonaws.com/angular-ecr'
                )
            }
        }
    }
}
