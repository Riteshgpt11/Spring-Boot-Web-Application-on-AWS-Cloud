#!/bin/bash
#date2=`date`
#echo "Today's date $date2"

#echo "Enter image-id"
#read image
#echo $image
#########################################
##Create a security group
#group_id=`aws ec2 create-security-group --group-name my-security-group_a2 --description "Creating security group from aws cli" --vpc-id vpc-a9f6ffd0`
#echo $group_id

## Adding Rules to the Security group
#aws ec2 authorize-security-group-ingress --group-id "$group_id"  --protocol tcp --port 22 --cidr 0.0.0.0/0

#Fetch the subnet id
#subnet_id=`aws ec2 describe-subnets --filters "Name=availabilityZone,Values=us-east-1b" | awk '{print$8}'`

# Run an ec2 Instance
#ami-cd0f5cb6
#t2.micro
#sg-e4624697
#subnet-b833dcf3
#ec2_instance_id=`aws ec2 run-instances --image-id "$1"  --count 1 --instance-type "$2" --key-name EC2_keypair --security-group-ids "$group_id" --subnet-id "$subnet_id" --disable-api-termination

ec2_instance_id=`aws ec2 run-instances --image-id ami-cd0f5cb6 --count 1 --instance-type t2.micro --key-name EC2_keypair --security-group-ids sg-e4624697 --subnet-id subnet-b833dcf3 --disable-api-termination --block-device-mappings "[{\"DeviceName\":\"/dev/sdf\",\"Ebs\":{\"VolumeSize\":16,\"DeleteOnTermination\":true}}]"`

# --output text --query 'Instances[*].InstanceId'`
#echo $ec2_instance_id
#aws ec2 wait instance-running --instance-ids $ec2_instance_id


## Set ec2 instance volumn and volume size
