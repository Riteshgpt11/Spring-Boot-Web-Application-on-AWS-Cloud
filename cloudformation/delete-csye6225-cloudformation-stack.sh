#!/bin/bash

#Yogita Jain, jain.yo@husky.neu.edu, 001643815
#Rohan Magare, magare.r@husky.neu.edu, 001231457
#Pratiksha Shetty, shetty.pr@husky.neu.edu, 001643697
#Ritesh Gupta, gupta.rite@husky.neu.edu, 001280361

# fetch instance id
instanceid=`aws ec2 describe-instances --query "Reservations[*].Instances[*].InstanceId" --filters "Name=tag-key,Values=aws:cloudformation:stack-name" "Name=tag-value,Values=$1" --output text`
echo "$instanceid"
## Disbale instance terminatio protection
aws ec2 modify-instance-attribute --instance-id "$instanceid" --no-disable-api-termination

## delete stack
aws cloudformation delete-stack --stack-name $1
