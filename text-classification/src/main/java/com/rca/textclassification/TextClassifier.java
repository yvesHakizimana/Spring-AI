package com.rca.textclassification;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.ChatOptionsBuilder;
import org.springframework.stereotype.Service;

@Service
public class TextClassifier {
    private final ChatClient chatClient;

    TextClassifier(ChatClient.Builder chatClientBuilder){
        this.chatClient = chatClientBuilder
                .defaultOptions(
                        ChatOptionsBuilder.builder()
                                .withTemperature(0.0f)
                                .build()
                )
                .build();

    }

    //Using the basic classification algorithms.
    String classifyClassNames(String text){
        return chatClient
                .prompt()
                .system(
                        """
                               Classify the provided text into one of these classes:
                               BUSINESS, SPORT, TECHNOLOGY, OTHER.
                               """
                )
                .user(text)
                .call()
                .content();

    }

    //Classifying the class descriptions but widened the view of the context.
    String classifyClassDescriptions(String text){
        return chatClient
                .prompt()
                .system(
                        """
                                Classify the provided text into one of these classes
                                
                                BUSINESS: Commerce, finance ,markets , entrepreneurship, corporate developments.
                                SPORT: Athletic events, tournament outcomes, performances of athletes and teams.
                                TECHNOLOGY: innovations and trends in software, artificial intelligence, cybersecurity.
                                OTHER: Anything that doesn't fit into other categories.
                                """
                )
                .user(text)
                .call()
                .content();
    }

    //Giving some few grounding information.
    String classifyFewShotPrompts(String text){
        return chatClient
                .prompt()
                .system("""
                        Classify the provided text into one of these classes.
                        
                        BUSINESS: Commerce, finance, markets, entrepreneurship, corporate developments.
                        SPORT: Athletic events, tournament outcomes, performance of athletes and teams.
                        TECHNOLOGY: innovations and trends in software, artificial intelligence, cybersecurity.
                        OTHER: Anything that doesn't fit into the other categories.
                        
                        Text: Clean Energy Startups Make Waves in 2024, Fueling a Sustainable Future.
                        Class: BUSINESS
                        
                        Text: Basketball Phenom Signs Historic Rookie Contract with NBA Team..
                        Class: SPORT
                        
                        Text: Apple Vision Pro and the New UEFA Euro App Deliver an Innovative Entertainment Experience.
                        Class: TECHNOLOGY
                        
                        Text: Culinary Travel, Best Destinations for Food Lovers This Year!
                        Class: OTHER
                        """)
                .user(text)
                .call()
                .content();
    }





}
