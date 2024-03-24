node {
    stage('SCM') {
        git branch: 'main', credentialsId: '20225586', url: 'https://github.com/Son0ngu/Project1'
    }
    stage('SonarQube Analysis') {
        scannerHome = tool 'SonarQube Scanner'
        withSonarQubeEnv('SonarQube Server') {
            sh "${scannerHome}/bin/sonar-scanner -Dsonar.java.binaries=. -Dsonar.projectKey=project1 -Dsonar.login=sqa_22e4bd1f7c2d01a0c12eb8d0b9958f8efa5e2b21"
        }
    }
}
