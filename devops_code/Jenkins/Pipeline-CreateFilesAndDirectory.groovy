pipeline{
    agent any
    stages{
        stage('CreateFilesAndDirectory'){
            steps{
                sh "touch debendra.txt"
                //here guru.txt is a directory.
                dir('/tmp/guru.txt'){
                    sh 'touch devops.txt'
                    sh 'touch lucifer.txt'
                }
            }

        }
    }
}