gcloud compute instance-templates create nginx-template \
--metadata-from-file startup-script=startup.sh

gcloud compute target-pools create nginx-pool

gcloud compute instance-groups managed create nginx-group \
         --base-instance-name nginx \
         --size 2 \
         --template nginx-template \
         --target-pool nginx-pool

gcloud compute instances list

gcloud compute firewall-rules create www-firewall --allow tcp:80
