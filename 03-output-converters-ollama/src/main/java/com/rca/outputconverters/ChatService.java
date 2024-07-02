package com.rca.outputconverters;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.converter.ListOutputConverter;
import org.springframework.ai.converter.MapOutputConverter;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ChatService {
    private final ChatClient chatClient;

    ChatService(ChatClient.Builder chatClientBuilder){
        this.chatClient = chatClientBuilder.build();
    }


    //Returning clearly an artistInfo as the output.
    ArtistInfo chatWithBeanOutput(MusicQuestion question){
        var userPromptTemplate = """
                Tell me the name of the band of one musician famous for playing in a {genre} band.
                Consider only the musicians that play the {instrument} in that band.
                """;
        return chatClient
                .prompt()
                .user(userSpec ->
                        userSpec
                                .text(userPromptTemplate)
                                .param("instrument", question.instrument())
                                .param("genre", question.genre()))
                .call()
                .entity(ArtistInfo.class);
    }

    //Returning the map output from the model.
    Map<String, Object> chatWithMapOutput(MusicQuestion question){
        var outputConverter  = new MapOutputConverter();

        var userPromptTemplate = """
                Tell me the names of three musicians famous for playing in a {genre} band.
                Consider only the musicians that play the {instrument} in that band.
                {format}
                """;

        var result =  chatClient
                .prompt()
                .user(userSpec ->
                        userSpec
                                .text(userPromptTemplate)
                                .param("instrument", question.instrument())
                                .param("genre", question.genre())
                                .param("format", outputConverter.getFormat()))
                .call()
                .content();
        return outputConverter.convert(result);
    }

    //Returning the list output from the model.
    List<String> chatWithListOutput(MusicQuestion question){
        var outputConverter = new ListOutputConverter(new DefaultConversionService());
        var userPromptTemplate = """
                Tell me the names of three musicians famous for playing in a {genre} band.
                Consider only the musicians that play the {instrument} in that band.
                {format}
                """;
        var result = chatClient.prompt()
                .user(userSpec -> userSpec
                        .text(userPromptTemplate)
                        .param("genre", question.genre())
                        .param("instrument", question.instrument())
                        .param("format", outputConverter.getFormat()))
                .call()
                .content();
        return  outputConverter.convert(result);
    }

}
