#!/bin/bash

# launch an EC2 instance

aws cloudformation create-stack --stack-name "$1" --template-body file://create-ec2-stack.yml --parameters file://ec2-parameters.json
