node {
    stage('SCM') {
        git branch: 'main', credentialsId: '20225586', url: 'https://github.com/Son0ngu/Project1.git'
    }
    stage('SonarQube Analysis') {
        def scannerHome = tool 'SonarQube Scanner'
        withSonarQubeEnv() {
            sh "${scannerHome}/bin/sonar-scanner -Dsonar.java.binaries=. -Dsonar.projectKey=Project1 -Dsonar.login=sqa_cafa9019dbdaec8879c1d6d50f120fbb9898b708"
        }
    }
}
