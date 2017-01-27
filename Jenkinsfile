node('maven') {
  stage('greeting') {
    echo 'hello from pipeline'
    sh 'ls -la'
  }
  //stage('build') { openshiftBuild(buildConfig: 'gradle-spingboot-seed', showBuildLogs: 'true') }
  //stage('Checkout') {
  //  git branch: 'jenkinsfile', url: 'https://github.com/sadhal/gradle-spingboot-seed.git'
  //}
  stage('Build') {
    sh 'ls -la'
    sh "./gradlew clean build -x test"
  }
  stage('test') {
    echo 'starting ./gradlew test'
    sh './gradlew test'
  }
  stage('Dockerize') {
    echo "docker dry run"
  }
  stage('deploy') {
    //openshiftDeploy(deploymentConfig: 'gradle-spingboot-seed')
    echo "openshiftDeploy(deploymentConfig: 'gradle-spingboot-seed')"
  }
}
