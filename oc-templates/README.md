
### Creating a Template from Existing Objects
Rather than writing an entire template from scratch, you can also export existing objects from your project in template form, and then modify the template from there by adding parameters and other customizations. To export objects in a project in template form, run:
```
oc export all --as-template=<template_name> > <template_filename>
```
or you can be more selective and skip some unwanted resources:
```
oc export svc,dc,bc --as-template=<template_name> > <template_filename>
```

You can also substitute a particular resource type or multiple resources instead of all. Run `oc export -h` for more examples.

The object types included in oc export all are:
* BuildConfig
* Build
* DeploymentConfig
* ImageStream
* Pod
* ReplicationController
* Route
* Service


### Generating a List of Objects
Using the CLI, you can process a file defining a template to return the list of objects to standard output:

```
oc process -f <filename>
```
Alternatively, if the template has already been uploaded to the current project:
```
oc process <template_name>
```
You can create objects from a template by processing the template and piping the output to oc create:
```
oc process -f <filename> | oc create -f -
```
