pipeline{
    agent any
    // agent {
    //     label 'node1'
    // }
    tools{
        maven 'maven3.8.5'
    }

    triggers {
        pollSCM '* * * * *'
    }

    options {
        buildDiscarder logRotator(
            artifactDaysToKeepStr: '', 
            artifactNumToKeepStr: '5', 
            daysToKeepStr: '', 
            numToKeepStr: '5'
        )
    }

    stages{
        stage('CheckoutCode'){
            steps{
                git branch: 'development',
                credentialsId: 'github',
                url: 'https://github.com/Debendra9/maven-web-application.git'
            }
        }

        stage('Built'){
            steps{
                 sh "mvn clean package"
            }
        }

        //  stage('SonarQubeReport'){
        //     steps{
        //          sh "mvn sonar:sonar"
        //     }
        // }

        // stage('UploadArtifactsIntoNexus'){
        //     steps{
        //         sh "mvn deploy"
        //     }
        // }

        stage('DeployToTomcat'){
            steps{
                sshagent(['tomcat-credential']) {
                    // some block
                    sh "scp -o StrictHostKeyChecking=no target/maven-web-application.war ec2-user@15.206.147.157:/opt/apache-tomcat-9.0.63/webapps/"
                }
            }
        }
    }
}