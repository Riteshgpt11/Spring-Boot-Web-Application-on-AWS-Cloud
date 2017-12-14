gcloud compute http-health-checks create http-basic-check

gcloud compute instance-groups managed \
set-named-ports nginx-group \
--named-ports http:80

gcloud compute backend-services create nginx-backend \
      --protocol HTTP --http-health-checks http-basic-check --global

gcloud compute backend-services add-backend nginx-backend \
        --instance-group nginx-group --global --instance-group-zone=us-east4-a

gcloud compute url-maps create web-map \
       --default-service nginx-backend

gcloud compute target-http-proxies create http-lb-proxy \
    --url-map web-map

gcloud compute forwarding-rules create http-content-rule \
      --global \
      --target-http-proxy http-lb-proxy \
      --port-range 80
