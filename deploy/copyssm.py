import boto3
from sys import argv



def getParameter(param_name):
    """
    This function reads a secure parameter from AWS' SSM service.
    The request must be passed a valid parameter name, as well as
    temporary credentials which can be used to access the parameter.
    The parameter's value is returned.
    """
    # Create the SSM Client
    ssm = boto3.client('ssm',
        region_name='ap-southeast-1'
    )

    # Get the requested parameter
    response = ssm.get_parameters(
        Names=[
            param_name,
        ],
        WithDecryption=True
    )

    # Store the credentials in a variable
    credentials = response['Parameters'][0]['Value']

    return credentials

def putParameter(old_value,new_value):
    # Create the SSM Client
    ssm = boto3.client('ssm',
        region_name='ap-southeast-1'
    )

    # Get the requested parameter
    response = ssm.put_parameter(
        Name=new_value,
        Value=getParameter(old_value),
        Type='String'
    )
    return "successfully put "+new_value


filepath = 'values.txt'
with open(filepath) as fp:
   line = fp.readline()
   cnt = 1
   while line:
       old_value=str(line.strip())
       new_value=old_value.replace("Dev","Staging")
       #new_value=old_value.replace("Staging","Prod")
       print "creating ssm value for "+new_value
       putParameter(old_value,new_value)
       line = fp.readline()
