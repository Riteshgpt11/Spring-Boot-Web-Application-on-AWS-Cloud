#!/bin/bash

##Disabling instance termination protection
aws ec2 modify-instance-attribute --instance-id $1 --no-disable-api-termination

##Terminating the instance
aws ec2 terminate-instances --instance-ids $1

##Waiting for the instance to get terminated
aws ec2 wait instance-terminated --instance-ids $1

##Deleting the security group
aws ec2 delete-security-group --group-id $2


