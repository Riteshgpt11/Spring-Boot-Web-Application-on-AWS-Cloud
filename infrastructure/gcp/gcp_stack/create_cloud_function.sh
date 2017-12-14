
#!/bin/bash

gcloud deployment-manager deployments create $1 --config cloud_function.yaml
