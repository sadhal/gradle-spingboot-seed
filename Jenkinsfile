node('master') {
  stage('greeting') {
    echo 'hello from pipeline'
  }
  stage('build') {
    openshiftBuild(buildConfig: 'gradle-spingboot-seed', showBuildLogs: 'true')
  }
  stage('test') {
    echo 'starting ./gradlew test'
    sh './gradlew test'
  }
  stage('deploy') {
    openshiftDeploy(deploymentConfig: 'gradle-spingboot-seed')
  }
}
