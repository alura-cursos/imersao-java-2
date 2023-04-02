import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ContentExtractorNASA implements ContentExtractor{
    public List<Content> getContent(String json){
               
        // extrair dados : parsear os dados  (titulo , poster, classificação),exibir e manipular o dados
        
        var jsonParser = new JsonParser();
        List<Map<String, String>> attributeList = jsonParser.parse(json);

        List<Content> contentList = new ArrayList<>();

        //popular a lista de conteudos 
        for (Map<String, String> attributes : attributeList) {
            String title = attributes.get("title");
            String urlImage = attributes.get("url");
            var content = new Content(title, urlImage);

            contentList.add(content);
        }
        
        return contentList;
    }
}
