aws --endpoint-url=http://localhost:4566 --region=us-west-2 sqs create-queue --queue-name dados_pessoais_sqs
aws --endpoint-url=http://localhost:4566 --region=us-west-2 sqs create-queue --queue-name dados_veiculos_sqs
#aws sqs receive-message --queue-url http://sqs.us-west-2.localhost.localstack.cloud:4566/000000000000/dados_pessoais_sqs --attribute-names All --message-attribute-names All --max-number-of-messages 10
#aws sqs receive-message --queue-url http://sqs.us-west-2.localhost.localstack.cloud:4566/000000000000/dados_veiculos_sqs --attribute-names All --message-attribute-names All --max-number-of-messages 10