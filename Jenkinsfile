node {

    stage ('build') {
        sh 'java -version'
    }

    stage ('test') {
        git branch: 'main', url: 'https://github.com/Rrlopes07/pucpr-devops.git'
        sh 'javac ./src/main/kotlin/br/com/forum/hello.java && java ./src/main/kotlin/br/com/forum/hello.class'
    }

    stage ('deploy') {
        echo 'finalizado :)'
    }

}