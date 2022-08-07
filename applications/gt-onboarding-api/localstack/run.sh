echo "Starting to create DynamoDB table for customer"
awslocal dynamodb create-table \
    --table-name GTCustomers \
    --attribute-definitions AttributeName=CustomerId,AttributeType=S \
    --key-schema AttributeName=CustomerId,KeyType=HASH \
    --provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5

echo "Done to create GTCustomers successfully!"