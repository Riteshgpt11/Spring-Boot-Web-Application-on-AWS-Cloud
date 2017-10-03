#!/bin/bash

# fetch instance id
instanceid=`aws ec2 describe-instances --query "Reservations[*].Instances[*].InstanceId" --filters "Name=tag-key,Values=aws:cloudformation:stack-name" "Name=tag-value,Values=$1" --output text`
echo "$instanceid"
## Disbale instance terminatio protection
aws ec2 modify-instance-attribute --instance-id "$instanceid" --no-disable-api-termination

## delete stack
aws cloudformation delete-stack --stack-name $1
