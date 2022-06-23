node 
//node ('node1')
{    
    def mavenhome = tool name: "maven3.8.5"

    properties(
        [buildDiscarder(
            logRotator(artifactDaysToKeepStr: '', 
                       artifactNumToKeepStr: '5', 
                       daysToKeepStr: '', 
                       numToKeepStr: '5'
                       )
            ), 
        [$class: 'JobLocalConfiguration', changeReasonComment: ''], 
        pipelineTriggers([pollSCM('* * * * *')])
        ]
    )

    stage('checkoutCode'){
        git branch: 'development',
        credentialsId: 'github',
        url: 'https://github.com/Debendra9/maven-web-application.git'
    }

    stage('built'){
        sh "${mavenhome}/bin/mvn clean package"
    }

    // stage ('ExecuteSonarqubeReport'){
    //     sh "${mavenhome}/bin/mvn sonar:sonar"
    // }

    // stage ('UploadArtifactsIntoNexus'){
    //     sh "${mavenhome}/bin/mvn deploy"
    // }

    stage ('DeployToTomcat'){
        sshagent(['tomcat-credential']) {
            // some block
            sh "scp -o StrictHostKeyChecking=no target/maven-web-application.war ec2-user@43.204.212.74:/opt/apache-tomcat-9.0.63/webapps/"
        }
    }

    stage ('sendEmailNotification'){
        mail bcc: '',
        body: '''Built over 

        Regarts
        Debendra Kumar Guru
        9658028334''', 
        cc: '', 
        from: '', 
        replyTo: '', 
        subject: 'built over', 
        to: 'debendraguru@gmail.com'
    }
}