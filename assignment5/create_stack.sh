#!/bin/bash
#Yogita Jain, jain.yo@husky.neu.edu, 001643815
#Rohan Magare, magare.r@husky.neu.edu, 001231457
#Pratiksha Shetty, shetty.pr@husky.neu.edu, 001643697
#Ritesh Gupta, gupta.rite@husky.neu.edu, 001280361

##Check if enough arguements are passed
if [ $# -lt 1 ]; then
  echo 1>&2 "$0: Stack name not provided"
  exit 2
elif [ $# -gt 1 ]; then
  echo 1>&2 "$0: Too many Arguments"
  exit 2
fi

#aws cloudformation create-stack --stack-name "$1" --template-body file://merged_stack.yml --parameters file://merged-ec2-parameters.json
#aws cloudformation create-stack --stack-name "$1" --template-body file://create_security_groups_1.yml --parameters file://ec2-parameters.json
aws cloudformation create-stack --stack-name "$1" --template-body file://create-RDS-instance.yml --parameters file://testSubnet.json


# Create a security group
#aws cloudformation create-stack --stack-name "$1" --template-body file://create_security_groups.yml --parameters file://ec2-parameters.json

# create a S3 bucket
#aws cloudformation create-stack --stack-name "$1" --template-body file://create-s3-bucket.yml

#create a Dynamodb
#aws cloudformation create-stack --stack-name "$1" --template-body file://create-dynamoDBTable.yml --parameters file://DynamoDB-parameters.json


# create a RDS
#aws cloudformation create-stack --stack-name "$1" --template-body file://create-RDS-instance.yml --parameters file://RDS-parameters.json
