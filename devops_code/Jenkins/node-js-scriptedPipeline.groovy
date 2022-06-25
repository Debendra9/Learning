node{
    stage('Chechoutcode'){
        git credentialsId: 'github', 
        url: 'https://github.com/Debendra9/nodejs-app-mss.git'
    }

    stage('Built'){
        nodejs(nodeJSInstallationName:"NodeJS16.15.1"){
            sh 'npm install'
        }
    }

    // stage('ExecuteSonarqubeReport'){
    //     nodejs(nodeJSInstallationName:"NodeJS16.15.1"){
    //         sh 'npm run sonar'
    //     }
    // }

    // stage('UploadArtifactsToArtifactory'){
    //     nodejs(nodeJSInstallationName:"NodeJS16.15.1"){
    //         sh 'npm publish'
    //     }
    // }

    //if nodejs is installed directly on the server not througt jenkins.

    stage('RunNodeJsApp'){
        // nodejs(nodeJSInstallationName:"NodeJS16.15.1"){
        sh 'npm run start &'
        // sh 'node app.js &'
        // }
    }
}