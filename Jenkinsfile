node('maven') {
  stage('Greeting') {
    echo 'hello from pipeline'
    sh 'ls -la'
  }
  stage('Checkout and Unit test') {
    git branch: 'jenkinsfile2', url: 'https://github.com/sadhal/gradle-spingboot-seed.git'
    echo 'starting ./gradlew test'
    sh './gradlew test'
  }
  stage('Build image') { 
    sh 'ls -la'
    openshiftBuild(buildConfig: 'gradle-spingboot-seed', showBuildLogs: 'true', namespace: 'contacts-dev')
  }
  stage('deploy') {
    openshiftDeploy(deploymentConfig: 'gradle-spingboot-seed', namespace: 'contacts-dev')
  }
}
