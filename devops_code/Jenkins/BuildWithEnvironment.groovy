pipeline {
    agent any
    
    parameters {
        choice choices: ['Dev', 'QA', 'Prod'], 
        description: 'Select Environment Name', 
        name: 'EnvironmentName'
    }

    environment {
        DEPLOY_ENV = "${params.EnvironmentName}"
    }

    stages {
        stage('CheckoutCode') {
            steps {
                script{
                    if ("${DEPLOY_ENV}" == "Dev"){
                        def BranchName = "development"
                        git branch: "${BranchName}", 
                        credentialsId: 'github', 
                        url: 'https://github.com/Debendra9/maven-web-application.git'
                    }
                    else if ("${DEPLOY_ENV}" == "QA"){
                        def BranchName = "test" //"stage"
                        git branch: "${BranchName}", 
                        credentialsId: 'github', 
                        url: 'https://github.com/Debendra9/maven-web-application.git'
                    }
                    else{
                        def BranchName = "master"
                        git branch: "${BranchName}", 
                        credentialsId: 'github', 
                        url: 'https://github.com/Debendra9/maven-web-application.git'
                    }
                }
            }
        }
    }
}