import boto3
from sys import argv


def putParameter(name,value):
    # Create the SSM Client
    ssm = boto3.client('ssm',
        region_name='ap-southeast-1'
    )

    # Get the requested parameter
    response = ssm.put_parameter(
        Name=name,
        Value=value,
        Type='String'
    )
    return "successfully put "+value

name= argv[1]
value= argv[2]

print putParameter(name,value)
