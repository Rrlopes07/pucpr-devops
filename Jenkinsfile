def remote = [:]
remote.name = env.DEPLOY_NAME
remote.host = env.DEPLOY_URL
remote.allowAnyHosts = true

pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                git branch: 'main', url: 'https://github.com/Rrlopes07/pucpr-devops.git'

                sh "sed -i 's/\${DB_USER}/${env.DB_USER}/g' docker-compose.yml"
                sh "sed -i 's/\${DB_PASSWORD}/${env.DB_PASSWORD}/g' docker-compose.yml"
                sh "sed -i 's/\${MAIL_USERNAME}/${env.MAIL_USERNAME}/g' docker-compose.yml"
                sh "sed -i 's/\${MAIL_PASSWORD}/${env.MAIL_PASSWORD}/g' docker-compose.yml"
                sh "sed -i 's/\${JWT_KEY}/${env.JWT_KEY}/g' docker-compose.yml"
                sh "sed -i 's/\${DOCKER_USER}/${env.DOCKER_USER}/g' docker-compose.yml"

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

        stage('Docker') {
            steps {
                // Build e push
                withCredentials([usernamePassword(credentialsId: 'dockerHub', passwordVariable: 'dockerHubPassword', usernameVariable: 'dockerHubUser')]) {
                    sh "docker build -t ${env.dockerHubUser}/forum:latest ."
                    sh "docker login -u ${env.dockerHubUser} -p ${env.dockerHubPassword}"
                    sh "docker push ${env.dockerHubUser}/forum:latest"
                }
            }
        }

        stage('Deploy AWS') {
            steps {
                withCredentials([sshUserPrivateKey(credentialsId: 'ssh-credentials', keyFileVariable: 'identity', passphraseVariable: '', usernameVariable: 'userName')]) {
                    script {
                        remote.user = userName
                        remote.identityFile = identity
                    }
                    
                    sshPut remote: remote, from: 'docker-compose.yml', into: '.'
                    sshCommand remote: remote, command: 'docker-compose up -d'
                    sshCommand remote: remote, command: 'rm docker-compose.yml'
                }
            }
        }
    }

    post {
        success {
            script {
                discordSend description: "API Atualizada", footer: "Nova versão da API em produção", link: env.JENKINS_URL, result: currentBuild.currentResult, title: "Pipeline bem-sucedida", webhookURL: env.DISCORD_WEBHOOK
            }
        }

        failure {
            script {
                discordSend description: "Pipeline mau-sucedida", footer: "Ocorreu um problema no pipeline, verifique", link: env.JENKINS_URL, result: currentBuild.currentResult, title: "URGENTE API FORUM PIPELINE", webhookURL: env.DISCORD_WEBHOOK
            }
        }
    }
}
