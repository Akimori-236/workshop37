package nus.iss.tfip.workshop37.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class DigitalOceanConfig {

    @Value("${do.storage.access.key}")
    private String accessKey;

    @Value("${do.storage.secret.key}")
    private String secretKey;

    @Value("${do.storage.endpoint}")
    private String endpoint;

    @Value("${do.storage.endpoint.region}")
    private String endpointRegion;

    @Bean
    public AmazonS3 createS3Client(){
        System.out.println(" " + accessKey);
        System.out.println(" " + secretKey);
        System.out.println(" " + endpoint);
        System.out.println(" " + endpointRegion);

        BasicAWSCredentials cred= new BasicAWSCredentials(accessKey, secretKey);
        EndpointConfiguration ep = new EndpointConfiguration(endpoint, endpointRegion);
        System.out.println(" " + ep.getServiceEndpoint());
        System.out.println(" " + ep.getSigningRegion());

        return AmazonS3ClientBuilder.standard()
            .withEndpointConfiguration(ep)
            .withCredentials(new AWSStaticCredentialsProvider(cred))
            .build();
    }
}
