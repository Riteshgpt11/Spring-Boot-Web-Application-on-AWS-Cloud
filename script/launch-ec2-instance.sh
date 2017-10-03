#!/bin/bash

##Create a security group
group_id=`aws ec2 create-security-group --group-name csye6225-fall2017-webapp --description "Csye6225" --vpc-id vpc-9e9374e6`
echo $group_id


## Adding Rules to the Security group
`aws ec2 authorize-security-group-ingress --group-id "$group_id" --protocol tcp --port 22 --cidr 0.0.0.0/0`
`aws ec2 authorize-security-group-ingress --group-id "$group_id" --protocol tcp --port 80 --cidr 0.0.0.0/0`
`aws ec2 authorize-security-group-ingress --group-id "$group_id" --protocol tcp --port 443 --cidr 0.0.0.0/0`


##Fetch the subnet id
subnet_id=`aws ec2 describe-subnets --filters "Name=availabilityZone,Values=us-east-1b" | awk '{print$9}'`
echo $subnet_id

##Run an ec2 Instance and Fetch Instance Id
ec2_instance_id=`aws ec2 run-instances --image-id ami-cd0f5cb6 --count 1 --instance-type t2.micro --security-group-ids "$group_id" --subnet-id "$subnet_id" --disable-api-termination --block-device-mappings "[{\"DeviceName\":\"/dev/sdf\",\"Ebs\":{\"VolumeSize\":16,\"VolumeType\":\"gp2\",\"DeleteOnTermination\":true}}]" | awk 'NR==2{print $7}'`
echo $ec2_instance_id

##Wait for the instance to be up and running
`aws ec2 wait instance-running --instance-ids $ec2_instance_id`

##Fetch the public IP Address of the Instance
ip=`aws ec2 describe-instances --instance-ids "$ec2_instance_id" | grep ASSOCIATION | awk 'NR==1{print $4}'`
echo $ip

##Parse the input json file and send Public IP of the instance  to the file
sed -i '/Value/ s/^\(.*\)\("\)/\1'$ip'\2/' record.json

##Add/Update type A resource record set to the domain in the Route 53 zone with the IP of the newly launched EC2 instance
dns=`aws route53 change-resource-record-sets --hosted-zone-id ZFLUDICG22F7Z --change-batch file://record.json`
echo $dns

