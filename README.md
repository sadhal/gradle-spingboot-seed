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
# Add to project - jenkins pipeline. Even ephemeral works for this demo.

# create the spring boot application with builder-image and correct branch
oc new-app jorgemoralespou/s2i-java~https://github.com/sadhal/gradle-spingboot-seed#jenkinsfile2

# now create the pipeline application without builder-image.
oc new-app https://github.com/sadhal/gradle-spingboot-seed#jenkinsfile2 --strategy=pipeline --name gradlespringboot-pipeline
```

### Prerequisites
Latest openshift origin CLI client.
