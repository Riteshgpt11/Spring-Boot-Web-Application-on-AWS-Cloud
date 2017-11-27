package com.csye6225.demo.Service;
import java.util.List;
import java.util.ArrayList;


import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.Topic;
import com.amazonaws.services.sns.model.ListTopicsRequest;
import com.amazonaws.services.sns.model.ListTopicsResult;
import com.amazonaws.services.sns.model.PublishRequest;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.csye6225.demo.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CreateTopic {

    private static AmazonSNS snsService;
    static String topicName = "SNScsye6225";

    @Value("${aws.AWS_ACCESS_KEY_ID}")
    private String awsId;

    @Value("${aws.AWS_SERECT_ACCESS_KEY}")
    private String awsKey;

    @Value("${aws.AWS_REGION}")
    private String region;


    public void createSnsTopic(User user, String url) throws Exception {
        System.out.println("Inside send");


        BasicAWSCredentials awsCreds = new BasicAWSCredentials(awsId, awsKey);
        snsService = AmazonSNSClientBuilder.standard()
                .withRegion(Regions.fromName(region))
                .withCredentials( new AWSStaticCredentialsProvider(awsCreds))
                .build();

        List<Topic> topics = listTopics(snsService);
        for (Topic topic : topics) {
            if (topic.getTopicArn().endsWith(topicName)) {
                snsService.publish(new PublishRequest(topic.getTopicArn(), user.getEmailId(), url));
            }

        }
    }



    public static List<Topic> listTopics(AmazonSNS snsService) {
        List<Topic> topics = new ArrayList<Topic>();
        String nextToken = null;

        do {

            // create the request, with nextToken if not empty
            ListTopicsRequest request = new ListTopicsRequest();
            if (nextToken != null) request = request.withNextToken(nextToken);

            // call the web service
            ListTopicsResult result = snsService.listTopics(request);

            nextToken = result.getNextToken();

            // get that list of topics
            topics.addAll(result.getTopics());

            // go on if there are more elements
        } while (nextToken != null);

        System.out.println("Topics: " + topics);

        return topics;
    }
}
