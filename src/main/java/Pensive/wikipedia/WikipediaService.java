package Pensive.wikipedia;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WikipediaService {

    private RestTemplate restTemplate;
    private ObjectMapper objectMapper;
    private final static String WIKIPEDIA_API_URL = "https://en.wikipedia.org/w/api.php";
    private final static String ACTION_KEY = "action";
    private final static String QUERY_VALUE = "query";
    private final static String PROPS_PARAM = "prop";
    private final static String PROP_EXTRACT_VALUE = "extracts";
    private final static String EXINTRO_PARAM = "exintro";
    private final static String EXPLAIN_TEXT_PARAM = "explaintext";
    private final static String TITLES_PARAM = "titles";
    private final static String EXTRACT_KEY = "extract";
    private final static String FORMAT_KEY = "format";
    private final static String FORMAT_JSON = "json";
    private final static String PROP_IMAGE_INFO_VALUE = "imageinfo";
    private final static String IMAGE_INFO_KEY = "imageinfo";
    private final static String PROP_IMAGES = "images";
    private final static String IMAGES_KEY = "images";
    private final static String IIPROP_KEY = "iiprop";
    private final static String IIPROP_URL_VALUE = "url";
    private final static String TITLE_KEY = "title";
    private final static String URL_KEY = "url";
    private final static String IM_LIMIT_KEY = "imlimit";
    private final static String IMAGE_LIMIT = "1";
    private final static String PROP_INFO_VALUE = "info";
    private final static String IN_PROP_KEY = "inprop";
    private final static String IN_PROP_URL_VALUE = "url";
    private final static String FULL_URL_KEY = "fullurl";

    public WikipediaService() {
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    public Article getArticle(String keyword) throws URISyntaxException, IOException {
        Article article = new Article();
        article.setKeyword(keyword);
        article.setWikipediaUrl(getWikipediaUrl(keyword));
        article.setImageUrls(getImageUrls(keyword));
        article.setSummary(getSummary(keyword));
        article.setRelatedKeywords(getRelatedKeywords(keyword));
        return article;
    }

    private String getWikipediaUrl(String keyword) throws URISyntaxException, IOException {
        String wikipediaTopicUrl = null;
        URI wikipediaTopicUrlQuery = getWikipediaJsonQueryUri(keyword)
                .setParameter(PROPS_PARAM, PROP_INFO_VALUE)
                .setParameter(IN_PROP_KEY, IN_PROP_URL_VALUE)
                .build();
        ResponseEntity<String> response = restTemplate.getForEntity(wikipediaTopicUrlQuery, String.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            wikipediaTopicUrl = objectMapper.readTree(response.getBody()).findValuesAsText(FULL_URL_KEY).get(0);
        }
        return wikipediaTopicUrl;
    }

    private List<String> getImageUrls(String keyword) throws URISyntaxException, IOException {
        List<String> imageList = null;
        List<String> imageUrlList = null;

        URI wikipediaImageListQuery = getWikipediaJsonQueryUri(keyword)
                .setParameter(PROPS_PARAM, PROP_IMAGES)
                .setParameter(IM_LIMIT_KEY, IMAGE_LIMIT)
                .build();
        ResponseEntity<String> response = restTemplate.getForEntity(wikipediaImageListQuery, String.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            JsonNode imagesNode = objectMapper.readTree(response.getBody()).findValue(IMAGES_KEY);
            imageList = null != imagesNode ? imagesNode.findValuesAsText(TITLE_KEY) : new ArrayList<>();
        }
        if (null != imageList) {
            imageUrlList = imageList.stream()
                    .map(this::getImageUrl)
                    .collect(Collectors.toList());
        }
        return imageUrlList;
    }

    private String getImageUrl(String imageName) {
        String imageUrl = null;
        try {
            URI wikipediaImageUrlQuery = getWikipediaJsonQueryUri(imageName)
                    .setParameter(PROPS_PARAM, PROP_IMAGE_INFO_VALUE)
                    .setParameter(IIPROP_KEY, IIPROP_URL_VALUE)
                    .build();
            ResponseEntity<String> response = restTemplate.getForEntity(wikipediaImageUrlQuery, String.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                JsonNode imageInfoNode = objectMapper.readTree(response.getBody()).findValue(IMAGE_INFO_KEY);
                imageUrl = null != imageInfoNode ? imageInfoNode.findValuesAsText(URL_KEY).get(0) : null;
            }
        } catch (URISyntaxException e) {
            //
        } catch (JsonProcessingException e) {
        } catch (IOException e) {
        }
        return imageUrl;
    }

    private String getSummary(String keyword) throws URISyntaxException, IOException {
        String summary = null;
        URI wikipediaSummaryQuery = getWikipediaJsonQueryUri(keyword)
                .setParameter(PROPS_PARAM, PROP_EXTRACT_VALUE)
                .setParameter(EXINTRO_PARAM, "")
                .setParameter(EXPLAIN_TEXT_PARAM, "")
                .build();
        ResponseEntity<String> response = restTemplate.getForEntity(wikipediaSummaryQuery, String.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            summary = objectMapper.readTree(response.getBody()).findValuesAsText(EXTRACT_KEY).get(0);
        }
        return summary;
    }

    private List<String> getRelatedKeywords(String keyword) {
        return null;
    }

    private URIBuilder getWikipediaJsonQueryUri(String... titles) {
        return new URIBuilder().setPath(WIKIPEDIA_API_URL)
                .setParameter(FORMAT_KEY, FORMAT_JSON)
                .setParameter(ACTION_KEY, QUERY_VALUE)
                .setParameter(TITLES_PARAM, String.join("|", titles));
    }
}
