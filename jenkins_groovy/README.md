# General
This pipelines install with Ansble all common services for LAMP. In this case MySQL, Apache2 and PHP. Also I realise pipeline for dump MySQL db and run simple sql_query from db.

# Pipelines
- deploy LAMP server
- Dump DB
- Run parametrised sql query

# Requirements:
- Jenkins Docker container
- Installed mysql-client in Jenkins docker
- Installed Ansible in Jenkins docker

# How to install

```
docker run   --name jenkins-blueocean   --restart=on-failure   --detach   --network host   --env DOCKER_HOST=tcp://docker:2376   --env DOCKER_CERT_PATH=/certs/client   --env DOCKER_TLS_VERIFY=1    --volume jenkins-data:/var/jenkins_home   --volume jenkins-docker-certs:/cer   jenkins/jenkins:2.387.3
```

Install recommended plugins.

Create pipelines with following code.

# Example
Examples files in output directory.
