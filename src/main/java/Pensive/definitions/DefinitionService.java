package Pensive.definitions;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DefinitionService {

    private RestTemplate restTemplate;
    private ObjectMapper objectMapper;

    public DefinitionService() {
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    public DefinitionBundle getDefinition() {
        DefinitionBundle definitionBundle = new DefinitionBundle();
        return definitionBundle;
    }
}
