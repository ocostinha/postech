# Seguros - PosTech - FIAP

Esse projeto tem como finalidade a implementação das técnicas e teorias aprendidas no curso de MBA PosTech da FIAP. Nesse projeto, implementaremos a cotação, contratação e registro de sinistros de um seguro de veículo para pessoa física.


## Premissas

Todos os veículos terão seu preço de seguro baseados na tabela FIPE e a idade do contratante.

- Para condutores entre 18 e 25 anos - 20% da tabela FIPE do veículo
- Para condutores entre 26 e 35 anos - 15% da tabela FIPE do veículo
- Para condutores entre 36 e 55 anos - 10% da tabela FIPE do veículo
- Para condutores acima de 55 anos - 13% da tabela FIPE do veículo

## Documentação
//TODO Colocar os HLD / SeqDiagram

## Ambiente Local
Antes de iniciar a aplicação, é necessário que as filas SQS estejam criadas, para isso é necessário subir o LocalStack no Docker seguindo os passos abaixo:
1. Rodar o arquivo de compose do Docker está no diretório /Docker/docker-compose.yml
2. Iniciar as filas com o arquivo sqs.sh que está no diretório /docker/scripts/sqs.sh (Caso não tenha o AWSCli na sua máquina, rode por dentro do terminal do docker que iniciou o LocalStack)

Depois do ambiente preparado, podemos dar inicio a aplicação Spring Boot utilizando o profile DEV
```java
-Dspring-boot.run.profiles=dev
```
