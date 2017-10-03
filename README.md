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
  
### Instruction to create a stack
  * Navigate to cloudformation directory and run the below command
   
    `./create-csye6225-cloudformation-stack.sh my_stack`
   
    `my_stack` is the stack name that'll be created.
   
### Instruction to delete a stack
   * Navigate to cloudformation directory and run the below command
         
        `./delete-csye6225-cloudformation-stack.sh my_stack`
         
        `my_stack` is the stack name that'll be deleted.
        
        First it will `disable` termination protection on `EC2 instance` and then delete the stack along with the resources
