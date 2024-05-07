package com.fiap.pos.tech.challenge.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class SQSConfig {
//    @Value("${cloud.aws.region.static}")
//    private String region;
//
//    @Value("${cloud.aws.credentials.access-key}")
//    private String accessKeyId;
//
//    @Value("${cloud.aws.credentials.secret-key}")
//    private String secretAccessKey;
//
//    @Value("${cloud.aws.end-point.uri}")
//    private String sqsUrl;
//
//    @Bean
//    @Primary
//    public AmazonSQSAsync amazonSQSAsync() {
//        return AmazonSQSAsyncClientBuilder.standard()
//                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(sqsUrl, region))
//                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKeyId, secretAccessKey)))
//                .build();
//    }
//
//    @Bean
//    public QueueMessagingTemplate queueMessagingTemplate() {
//        return new QueueMessagingTemplate(amazonSQSAsync());
//    }
//
//    @Bean
//    protected MessageConverter messageConverter(ObjectMapper objectMapper) {
//
//        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
//        converter.setObjectMapper(objectMapper);
//        converter.setSerializedPayloadClass(String.class);
//        converter.setStrictContentTypeMatch(false);
//        return converter;
//    }
}
