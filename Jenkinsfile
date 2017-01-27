node('maven') {
  stage('greeting') {
    echo 'hello from pipeline'
    sh 'ls -la'
  }
  stage('build') { 
    sh 'ls -la'
    openshiftBuild(buildConfig: 'gradle-spingboot-seed', showBuildLogs: 'true')
  }
  stage('Dockerize') {
    echo 'docker dry run'
  }
  stage('deploy') {
    openshiftDeploy(deploymentConfig: 'gradle-spingboot-seed')
  }
}
