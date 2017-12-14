#! /bin/bash
apt-get update
apt-get install -y nginx
service nginx start
sed -i -- 's/nginx/Google Cloud Platform - '"\$HOSTNAME"'/' /var/www/html/index.nginx-debian.html
# To install the Stackdriver monitoring agent:
curl -sSO https://repo.stackdriver.com/stack-install.sh
sudo bash stack-install.sh --write-gcm

# To install the Stackdriver logging agent:
curl -sSO https://dl.google.com/cloudagents/install-logging-agent.sh
sudo bash install-logging-agent.sh
EOF
