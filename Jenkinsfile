@Library('my_shared_library')_
node {
    
    stage('Checkout external proj') {
        git branch: 'master',
            credentialsId: 'github',
            url: 'https://github.com/soul-reaper48/jenkins-helm.git'
    }
    
    stage('create chart'){
         echo 'creating an image'
        helmcreate "webapp"
    }
    stage('install chart'){
        echo 'creating an instance'   
        terraformexec "webapp"
    }
}
