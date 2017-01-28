# gradle-spingboot-seed
This project is an application skeleton for a typical spring boot web app that is built with gradle. You can use itto quickly bootstrap your webapp projects and dev environment for these projects.
The seed contains a sample spring boot application exposing http resources on port 8080.

The seed app doesn't do much, by default it is listening on path ```/hello``` and returns a String value.

## Getting Started
To get you started you can simply clone repository and run ./gradlew bootRun to start server. Gradle and spring will start with downloading dependencies.

## Setting up openshift project
```
# Fire up cluster
oc cluster up

# Log in as developer in to the Web Console and CLI. Links and instructions are provided by oc cluster up command.
# Create projects for demo:
oc new-project jenkins
oc new-project contacts-dev
oc new-project contacts-test

# Enter the jenkins project from Web Console. Add to project - jenkins pipeline. Even ephemeral works for this demo. 
# Log in to jenkins with openshift credentials.

# Log in as system:admin in CLI in order to edit user policies for jenkins:
oc login -u system:admin
oc adm policy add-role-to-user edit system:serviceaccount:jenkins:jenkins -n contacts-dev
oc adm policy add-role-to-user edit system:serviceaccount:jenkins:jenkins -n contacts-test
oc login -u developer -p developer

# Let's create our demo app based on spring boot with builder-image. We will do it in both dev and test projects!
oc project contacts-dev
oc new-app jorgemoralespou/s2i-java~https://github.com/sadhal/gradle-spingboot-seed#jenkinsfile2
oc project contacts-test
oc new-app jorgemoralespou/s2i-java~https://github.com/sadhal/gradle-spingboot-seed#jenkinsfile2

# Now we will create pipeline for building and deploying application in dev env. Pipeline will exist in our jenkins project.
oc project jenkins
oc new-app https://github.com/sadhal/gradle-spingboot-seed#jenkinsfile2 --strategy=pipeline --context-dir='pipeline/dev' --name gradlespringboot-pipeline-dev
oc new-app https://github.com/sadhal/gradle-spingboot-seed#jenkinsfile2 --strategy=pipeline --context-dir='pipeline/test' --name gradlespringboot-pipeline-test
```

### Prerequisites
Latest openshift origin CLI client.
