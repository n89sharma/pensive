package Pensive.wikipedia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/wikipedia")
public class WikipediaController {

    private WikipediaService wikipediaService;

    @Autowired
    public WikipediaController(WikipediaService wikipediaService) {
        this.wikipediaService = wikipediaService;
    }

    @RequestMapping(value = "/{keyword}", method = RequestMethod.GET)
    public Article getArticle(@PathVariable String keyword) throws URISyntaxException, IOException {
        return wikipediaService.getArticle(keyword);
    }
}
