node {
    stage('SCM') {
        git branch: 'main', credentialsId: '20225586', url: 'https://github.com/Son0ngu/Project1.git'
    }
    stage('SonarQube Analysis') {
        def scannerHome = tool 'SonarQube Scanner'
        withSonarQubeEnv() {
            sh "${scannerHome}/bin/sonar-scanner -Dsonar.java.binaries=. -Dsonar.projectKey=Project1 -Dsonar.login=sqa_0fabeb4de4d6f88265c9ad293eff938c0b3374b2"
        }
    }
}
