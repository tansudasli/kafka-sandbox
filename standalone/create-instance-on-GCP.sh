# create an instance on GCP w/ additional data disk

PROJECT_ID=sandbox-236618
INSTANCE_NAME=kafka-instance-2
ZONE_NAME=europe-west3-c

# deletes data disk! keep in mind 
gcloud beta compute --project=$PROJECT_ID instances create $INSTANCE_NAME \
    --zone=$ZONE_NAME \
    --machine-type=n1-standard-1 \
    --subnet=default \
    --network-tier=PREMIUM \
    --maintenance-policy=MIGRATE \
    --service-account=923205623626-compute@developer.gserviceaccount.com \
    --scopes=https://www.googleapis.com/auth/devstorage.read_only,https://www.googleapis.com/auth/logging.write,https://www.googleapis.com/auth/monitoring.write,https://www.googleapis.com/auth/servicecontrol,https://www.googleapis.com/auth/service.management.readonly,https://www.googleapis.com/auth/trace.append \
    --image=ubuntu-1904-disco-v20190605 \
    --image-project=ubuntu-os-cloud \
    --boot-disk-size=20GB \
    --boot-disk-type=pd-standard \
    --boot-disk-device-name=$INSTANCE_NAME \
    --create-disk=mode=rw,size=200,type=projects/sandbox-236618/zones/europe-west3-c/diskTypes/pd-ssd,name=kafka-datadisk-1,device-name=kafka-datadisk-1

# then connect w/ ssh
gcloud compute --project $PROJECT_ID ssh --zone $ZONE_NAME $INSTANCE_NAME