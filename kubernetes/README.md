# Kubernetes Lab

## Requirements
Installed minikube
Installed docker
Installed kubectl

## Deploy and configure

```
minikube start --driver=docker
kubectl create ns cats-dogs
minikube addons enable ingress
kubectl apply -f cats-dogs-stateful.yaml -n cats-dogs
kubectl apply -f cats-dogs-deployment.yaml -n cats-dogs
kubectl apply -f cats-dogs-cronjob.yaml --namespace cats-dogs
kubectl get po -n cats-dogs
```

After that you can see Created and Running pods.

You need to put files with some data to /cats/cat.txt and dogs/dog.txt for example.

```
kubectl exec --stdin --tty pod/cats-dogs-webdav-0 -n cats-dogs -- /bin/bash
curl -X PUT -T cat.txt localhost/cats/cats.txt
```

After all steps you can view ingress address and try to get info about cats and dogs.

```
mdu@mdu-ubuntu-test:~$ curl 192.168.49.2/cats/cats.txt
CATS
mdu@mdu-ubuntu-test:~$ curl 192.168.49.2/dogs/dogs.txt
SNOOP DOGS
mdu@mdu-ubuntu-test:~$ curl 192.168.49.2/
Cats and Dogs Review App
```
