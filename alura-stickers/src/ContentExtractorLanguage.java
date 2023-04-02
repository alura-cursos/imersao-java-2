import java.util.List;
import java.util.Map;

public class ContentExtractorLanguage implements ContentExtractor{
    public List<Content> getContent(String json){
               
        // extrair dados : parsear os dados  (titulo , poster, classificação),exibir e manipular o dados
        
        var jsonParser = new JsonParser();
        List<Map<String, String>> attributeList = jsonParser.parse(json);


        return attributeList.stream()
        .map(attributes -> {
            String rankingStr = attributes.get("ranking");
            double ranking = 0.0;
            if (rankingStr != null && !rankingStr.isEmpty()) {
                try {
                    ranking = Double.parseDouble(rankingStr);
                } catch (NumberFormatException e) {
                    // Tratar exceção, se necessário
                }
            }
            return new Content(
                attributes.get("title"), 
                attributes.get("image"),
                ranking
            );
        })
        .toList();

    }
}
