echo "Starting to create DynamoDB table for customer"
awslocal dynamodb create-table \
    --table-name GTCustomers \
    --attribute-definitions AttributeName=CustomerId,AttributeType=S \
    --key-schema AttributeName=CustomerId,KeyType=HASH \
    --provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5

awslocal dynamodb create-table \
    --table-name GTWallet \
    --attribute-definitions AttributeName=CustomerId,AttributeType=S \
     AttributeName=Operation,AttributeType=S \
    --key-schema AttributeName=CustomerId,KeyType=HASH AttributeName=Operation,KeyType=RANGE \
    --provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5

echo "Done to create GTCustomers successfully!"