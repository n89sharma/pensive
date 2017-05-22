package Pensive.definitions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/definition")
public class DefinitionController {

    private DefinitionService definitionService;

    @Autowired
    public DefinitionController(DefinitionService definitionService) {
        this.definitionService = definitionService;
    }

    @RequestMapping(value = "/{keyword}", method = RequestMethod.GET)
    public DefinitionBundle getDefinition(@PathVariable String keyword) {
        return definitionService.getDefinition();
    }
}
