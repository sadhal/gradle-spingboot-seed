node('java') {
  stage('greeting') {
    echo 'hello from pipeline'
  }
  stage('build') {
    openshiftBuild(buildConfig: 'helloworldapp', showBuildLogs: 'true')
  }
  stage('test') {
    echo 'starting ./gradlew test'
    sh './gradlew test'
  }
  stage('deploy') {
    openshiftDeploy(deploymentConfig: 'helloworldapp')
  }
}
