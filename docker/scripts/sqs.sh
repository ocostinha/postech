aws --endpoint-url=http://localhost:4566 --region=us-east-1 sqs create-queue --queue-name dados_pessoais_sqs
aws --endpoint-url=http://localhost:4566 --region=us-east-1 sqs create-queue --queue-name dados_veiculos_sqs
aws --endpoint-url=http://localhost:4566 --region=us-east-1 s3api create-bucket --bucket documentos
aws --endpoint-url=http://localhost:4566 --region=us-east-1 s3api put-bucket-acl --bucket documentos --acl public-read
#aws sqs receive-message --queue-url http://sqs.us-west-2.localhost.localstack.cloud:4566/000000000000/dados_pessoais_sqs --attribute-names All --message-attribute-names All --max-number-of-messages 10
#aws sqs receive-message --queue-url http://sqs.us-west-2.localhost.localstack.cloud:4566/000000000000/dados_veiculos_sqs --attribute-names All --message-attribute-names All --max-number-of-messages 10