#!/bin/bash

export AWS_REGION=us-east-1
#stack_name="$1"
aws --region $AWS_REGION cloudformation validate-template --template-body file://./ec2.yml
aws --region $AWS_REGION cloudformation create-stack --stack-name "$1" --template-body file://./ec2.yml
#--parameters file://./ec2-parameter.json --template-body file://./ec2.yml
