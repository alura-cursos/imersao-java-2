import java.util.List;
import java.util.Map;

public class ContentExtractorNASA implements ContentExtractor{
    public List<Content> getContent(String json){
               
        // extrair dados : parsear os dados  (titulo , poster, classificação),exibir e manipular o dados
        
        var jsonParser = new JsonParser();
        List<Map<String, String>> attributeList = jsonParser.parse(json);

        //popular a lista de conteudos 
         return attributeList.stream()
            .map(attributes ->  new Content(attributes.get("title"),attributes.get("url")))
            .toList();   
    }
}
