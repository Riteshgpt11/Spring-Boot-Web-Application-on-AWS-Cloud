#!/bin/bash
#Updating the termination protection state from enabled to disabled 
aws cloudformation update-termination-protection --no-enable-termination-protection --stack-name "$1"

#Deleting the stack
aws cloudformation delete-stack  --stack-name "$1"


