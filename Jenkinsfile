pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                git branch: 'main', url: 'https://github.com/Rrlopes07/pucpr-devops.git'

                sh'mvn dependency:sources'

                sh 'mvn clean package -DskipTests=true'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn -B test'
            }
        }

        stage('SAST') {
            steps {
                snykSecurity(
                    snykInstallation: env.SNYK_INSTALLATION,
                    snykTokenId: env.SNYK_TOKEN,
                    severity: "Critical"
                )
            }
        }

        stage('Deploy') {
            steps {
                // Build e push
                withCredentials([usernamePassword(credentialsId: 'dockerHub', passwordVariable: 'dockerHubPassword', usernameVariable: 'dockerHubUser')]) {
                    sh "docker build -t ${env.dockerHubUser}/forum:latest ."
                    sh "docker login -u ${env.dockerHubUser} -p ${env.dockerHubPassword}"
                    sh "docker push ${env.dockerHubUser}/forum:latest"
                }

                // Deploy na aws
                sh """ssh -i ${env.SSH_KEY} ubuntu@${env.DEPLOY_URL} << EOF
                    if [ -f docker-compose.yml ]
                    then
                        sudo docker-compose down
                        rm docker-compose.yml
                    fi

                    wget https://raw.githubusercontent.com/Rrlopes07/pucpr-devops/main/docker-compose.yml
                    sudo docker-compose up -d
                EOF"""
            }
        }
    }

    post {
        success {
            script {
                discordSend description: "Pipeline bem-sucedida", footer: "Backend da API REST Forum atualizada", link: env.API_URL, result: currentBuild.currentResult, title: "API FORUM PIPELINE", webhookURL: env.DISCORD_WEBHOOK
            }
        }

        failure {
            script {
                discordSend description: "Pipeline mau-sucedida", footer: "Ocorreu um problema no pipeline, verifique", link: env.JENKINS_URL, result: currentBuild.currentResult, title: "URGENTE API FORUM PIPELINE", webhookURL: env.DISCORD_WEBHOOK
            }
        }
    }
}
