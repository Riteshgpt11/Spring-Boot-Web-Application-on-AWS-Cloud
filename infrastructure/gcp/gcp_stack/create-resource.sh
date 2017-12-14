
#!/bin/bash

gcloud deployment-manager deployments create $1 --config gcp_resources.yaml
