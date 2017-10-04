# csye6225-fall2017

* Yogita Jain jain.yo@husky.neu.edu 001643815
* Rohan Magare magare.r@husky.neu.edu 001231457
* Pratiksha Shetty shetty.pr@husky.neu.edu 001643697
* Ritesh Gupta gupta.rite@husky.neu.edu 001280361

## Instructions to create an EC2 instance using cloud formation stack

### Parameters
  * The parameters are added in the `ec2-parameters.json`
  * The `create-ec2-stack.yml` contains the cloud formation stack to create an EC2 instance
  * The `create-csye6225-cloudformation-stack.sh` contains aws command to create the EC2 instance.
  * The `delete-csye6225-cloudformation-stack.sh` contains aws command to delete the stack and associated resources with it.
  
 Change ParameterValue for the following:
  * `KeyName`: put for EC2Keypair
  * `SubnetId`: your subnet id
  * `VpcId`: your VpcId
  * `HostedZoneId`: hosted zone id created for the domain
  * `DomainName`: domain name created in route 53
  
### Instruction to create a stack
  * Navigate to cloudformation directory and run the below command
   
    `./create-csye6225-cloudformation-stack.sh my_stack`
   
    `my_stack` is the stack name that'll be created.
   
### Instruction to delete a stack
   * Navigate to cloudformation directory and run the below command
         
        `./delete-csye6225-cloudformation-stack.sh my_stack`
         
        `my_stack` is the stack name that'll be deleted.
        
        First it will `disable` termination protection on `EC2 instance` and then delete the stack along with the resources


## Instructions to create an EC2 instance   
   * Navigate to script directory and run the script launch-ec2-instance.sh. The script uses the record.json file for DNS record.

     `./launch-ec2-instance.sh`

### Instruction to delete an EC2 instance
   * Navigate to script directory and run the below command

        `./terminate-ec2-instance.sh <Instance ID>`

        First it will `disable` termination protection on `EC2 instance` and then delete the instance along with the resources

