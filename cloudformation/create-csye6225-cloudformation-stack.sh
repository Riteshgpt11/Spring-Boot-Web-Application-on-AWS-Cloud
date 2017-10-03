#!/bin/bash

aws cloudformation create-stack --stack-name "$1" --template-body file://create-ec2-stack.yml --parameters file://ec2-parameters.json
