#!/bin/bash

#Yogita Jain, jain.yo@husky.neu.edu, 001643815
#Rohan Magare, magare.r@husky.neu.edu, 001231457
#Pratiksha Shetty, shetty.pr@husky.neu.edu, 001643697
#Ritesh Gupta, gupta.rite@husky.neu.edu, 001280361

#Checking for  number of arguments
set -e
##Check if enough arguements are passed
if [ $# -lt 1 ]; then
  echo 1>&2 "$0: Stack name not provided"
  exit 2
elif [ $# -gt 1 ]; then
  echo 1>&2 "$0: Too many Arguments"
  exit 2
fi

#Deleting the stack
aws cloudformation delete-stack --stack-name $1

aws cloudformation wait stack-delete-complete --stack-name $1

echo "Stack $1 deleted!"
