import java.util.List;
import java.util.Map;


public class ContentExtractorIMDB implements ContentExtractor{
    

    public List<Content> getContent(String json){
               
        // extrair dados : parsear os dados  (titulo , poster, classificação),exibir e manipular o dados
        
        var jsonParser = new JsonParser();
        List<Map<String, String>> attributeList = jsonParser.parse(json);


        return attributeList.stream()
        .map(attributes -> {
            String imDbRatingStr = attributes.get("imDbRating");
            double imDbRating = 0.0;
            if (imDbRatingStr != null && !imDbRatingStr.isEmpty()) {
                try {
                    imDbRating = Double.parseDouble(imDbRatingStr);
                } catch (NumberFormatException e) {
                    // Tratar exceção, se necessário
                }
            }
            return new Content(
                attributes.get("title"), 
                attributes.get("image").replaceAll("(@+)(.*).jpg$","$1.jpg"),
                imDbRating
            );
        })
        .toList();

    }
}
