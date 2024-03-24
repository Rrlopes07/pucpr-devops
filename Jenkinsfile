node {

    stage ('build') {
        sh 'java -version'
    }

    stage ('test') {
        git branch: 'main', url: 'https://github.com/Rrlopes07/pucpr-devops.git'
        sh 'cd ./src/main/kotlin/br/com/forum && javac hello.java && java hello'
    }

    stage ('deploy') {
        echo 'finalizado :)'
    }

}