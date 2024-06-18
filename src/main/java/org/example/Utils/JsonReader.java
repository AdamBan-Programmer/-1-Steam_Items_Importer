package org.example.Utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;

public class JsonReader {

    public JsonReader()
    {
    }

    private String getJSON(String url)
    {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }

    public JsonNode getJsonNode(String url) throws JsonProcessingException
    {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readTree(getJSON(url));
    }
}
