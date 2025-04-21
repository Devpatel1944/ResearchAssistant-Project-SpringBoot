package com.researchAssistant.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.researchAssistant.Entity.ResearchRequest;
import com.researchAssistant.helper.GeminiResponse;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class ResearchService {

    @Value("${gemini.api.url}")
    private String geminiapiurl;
    @Value("${gemini.api.key}")
    private String geminiapikey;


    private final WebClient webClient;
    private final ObjectMapper objectMapper;



    public ResearchService(WebClient.Builder webClientBuilder, ObjectMapper objectMapper) {
        this.webClient = webClientBuilder.build();
        this.objectMapper = objectMapper;
    }

    public String processContent(ResearchRequest researchRequest) {
        //Build a Prompt
        String prompt = buildPrompt(researchRequest);

        //Query the api  model
        Map<String , Object> requestBody = Map.of(
                "contents",new Object[]{
                        Map.of(
                                "parts" ,new Object[]{
                                        Map.of("text" ,prompt)
                                }
                        )
                }
        );

        String response = webClient.post()
                .uri(geminiapiurl+geminiapikey)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        //parse the response
        //return response
        return extractTextFromResponse(response);
    }



    private String buildPrompt(ResearchRequest request){
        StringBuilder prompt = new StringBuilder();

        switch (request.getOperation()){
            case "summarize":
                prompt.append("Provide a clear and concise summary of the following text in few sentences:\n\n");
                break;

            case "suggest":
                prompt.append("Based on the following content: suggest related topics and further reading. Format the response With clear heading and bullet points:\n\n");
                break;

            default:
                throw new IllegalArgumentException("Unknown Operation: "+request.getOperation());
        }
        prompt.append(request.getContent());
        return prompt.toString();
    }

    private String extractTextFromResponse(String response) {
     try{
         GeminiResponse geminiResponse1 = objectMapper.readValue(response, GeminiResponse.class);
         if(geminiResponse1.getCandidates() != null && !geminiResponse1.getCandidates().isEmpty()){
         GeminiResponse.Candidate firstcandidate = geminiResponse1.getCandidates().get(0);
         if(firstcandidate.getContent()  != null &&
                   firstcandidate.getContent().getParts() != null &&
                 !firstcandidate.getContent().getParts().isEmpty()){
                return firstcandidate.getContent().getParts().get(0).getText();
         }
         }
         return "Not content Found in response";
     } catch (Exception e) {
         return "Error Parsing "+e.getMessage();
     }


    }


}
