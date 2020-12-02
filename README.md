# NedTwitter

![alt text](https://github.com/pleaner/nedTwitter/blob/main/NedSundhine.png)
nedvana-tweet-backend 
MONGODB_URI
nedvana-tweet-frontend
BACKEND_URI

## 1: Set up AKS

[AKS Workshop](https://docs.microsoft.com/en-us/learn/modules/aks-workshop/02-deploy-aks "Azure Official Workshop")

https://shell.azure.com/

### Set Variables

REGION_NAME=eastus
RESOURCE_GROUP=aksworkshop
SUBNET_NAME=aks-subnet
VNET_NAME=aks-vnet

az group create \
    --name $RESOURCE_GROUP \
    --location $REGION_NAME

az network vnet create \
    --resource-group $RESOURCE_GROUP \
    --location $REGION_NAME \
    --name $VNET_NAME \
    --address-prefixes 10.0.0.0/8 \
    --subnet-name $SUBNET_NAME \
    --subnet-prefixes 10.240.0.0/16

SUBNET_ID=$(az network vnet subnet show \
    --resource-group $RESOURCE_GROUP \
    --vnet-name $VNET_NAME \
    --name $SUBNET_NAME \
    --query id -o tsv)

### Create the AKKS Instance

VERSION=$(az aks get-versions \
    --location $REGION_NAME \
    --query 'orchestrators[?!isPreview] | [-1].orchestratorVersion' \
    --output tsv)

AKS_CLUSTER_NAME=aksworkshop-$RANDOM
echo $AKS_CLUSTER_NAME

az aks create \
--resource-group $RESOURCE_GROUP \
--name $AKS_CLUSTER_NAME \
--vm-set-type VirtualMachineScaleSets \
--node-count 2 \
--load-balancer-sku standard \
--location $REGION_NAME \
--kubernetes-version $VERSION \
--network-plugin azure \
--vnet-subnet-id $SUBNET_ID \
--service-cidr 10.2.0.0/24 \
--dns-service-ip 10.2.0.10 \
--docker-bridge-address 172.17.0.1/16 \
--generate-ssh-keys

### Test cluster connectivity by using kubectl

az aks get-credentials \
    --resource-group $RESOURCE_GROUP \
    --name $AKS_CLUSTER_NAME

kubectl get nodes

### Create a Kubernetes namespace for the application

kubectl get namespace
kubectl create namespace nedtwitter

## 2: Create a container registry

ACR_NAME=acr$RANDOM

az acr create \
    --resource-group $RESOURCE_GROUP \
    --location $REGION_NAME \
    --name $ACR_NAME \
    --sku Standard

## 3: Build the container images by using Azure Container Registry Tasks

 git clone https://github.com/pleaner/nedTwitter.git

### Build Frontend

 cd ~/nedTwitter/nedtweet_frontend

az acr build \
    --resource-group $RESOURCE_GROUP \
    --registry $ACR_NAME \
    --image nedvana-tweet-frontend:v1 .

### Build Backend

 cd ~/nedTwitter/nedvana-tweet-backend

 az acr build \
    --resource-group $RESOURCE_GROUP \
    --registry $ACR_NAME \
    --image nedvana-tweet-backend:v1 .

### Verify Images

az acr repository list \
    --name $ACR_NAME \
    --output table

az aks update \
    --name $AKS_CLUSTER_NAME \
    --resource-group $RESOURCE_GROUP \
    --attach-acr $ACR_NAME

### Configure the AKS cluster to authenticate to the container registry
az aks update \
    --name $AKS_CLUSTER_NAME \
    --resource-group $RESOURCE_GROUP \
    --attach-acr $ACR_NAME

## Deploy MongoDB

### using helm...

helm repo add bitnami https://charts.bitnami.com/bitnami

helm search repo bitnami1

helm install tweets bitnami/mongodb \
    --namespace nedtwitter \
    --set auth.username=mongouser,auth.password=P1sD0ntH4xMi,auth.database=nedtweetdb

### K8s sercret for connection string...

kubectl create secret generic mongosecret \
    --namespace nedtwitter \
    --from-literal=MONGOCONNECTION="mongodb://mongouser:P1sD0ntH4xMi@tweets-mongodb.nedtwitter:27017/nedtweetdb"

kubectl describe secret mongosecret --namespace nedtwitter

## Deploy the backend

code nedvana-tweet-backend.yml

paste in content of this repo's /deployment/nedvana-tweet-backend.yml
ctrl-s to save ctrl-q to exit.

kubectl apply \
    --namespace nedtwitter \
    -f nedvana-tweet-backend.yml

kubectl get pods \
    --namespace nedtwitter \
    -l app=nedvana-tweet-backend -w

### Create the backend K8s Service (loadbalancer)

code nedvana-tweet-backend-service.yaml

paste in content of this repo's /deployment/nedvana-tweet-backend-service.yml
ctrl-s to save ctrl-q to exit.

kubectl apply \
    --namespace nedtwitter \
    -f nedvana-tweet-backend-service.yaml

kubectl get service ratings-api --namespace ratingsappls

## Deploy the Frontend
