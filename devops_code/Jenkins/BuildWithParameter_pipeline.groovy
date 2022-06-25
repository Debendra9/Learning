pipeline {
    agent any
    
    parameters {
        choice choices: ['master', 'development', 'test'], 
        description: 'Select Branch Name', 
        name: 'BranchName'
    }

    stages {
        stage('CheckoutCode') {
            steps {
                git branch: "${params.BranchName}", 
                credentialsId: 'github', 
                url: 'https://github.com/Debendra9/maven-web-application.git'
            }
        }
    }
}
