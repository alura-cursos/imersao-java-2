import java.util.List;
import java.util.Map;


public class ContentExtractorIMDB implements ContentExtractor{
    

    public List<Content> getContent(String json){
               
        // extrair dados : parsear os dados  (titulo , poster, classificação),exibir e manipular o dados
        
        var jsonParser = new JsonParser();
        List<Map<String, String>> attributeList = jsonParser.parse(json);


        return  attributeList.stream()
            .map(attributes -> new Content(
                attributes.get("title"), 
                attributes.get("image").replaceAll("(@+)(.*).jpg$","$1.jpg"),
                Double.valueOf(attributes.getOrDefault("imDbRating","0.0"))
        ))
        .toList();

    }
}
