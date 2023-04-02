import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ContentExtractorIMDB {
    

    public List<Content> getContent(String json){
               
        // extrair dados : parsear os dados  (titulo , poster, classificação),exibir e manipular o dados
        
        var jsonParser = new JsonParser();
        List<Map<String, String>> attributeList = jsonParser.parse(json);

        List<Content> contentList = new ArrayList<>();

        //popular a lista de conteudos 
        for (Map<String, String> attributes : attributeList) {
            String title = attributes.get("title");
            String urlImage = attributes.get("image")
                    .replaceAll("(@+)(.*).jpg$","$1.jpg");
            String imDbRatingStr = attributes.get("imDbRating");
            double imDbRating = imDbRatingStr.isEmpty() ? 0.0 : Double.parseDouble(imDbRatingStr);
            //double imDbRating = Double.parseDouble(attributes.get("imDbRating"));
                
            //urlImage.replaceAll("(_V\\d+_UX\\d+)_CR\\d+,\\d+,\\d+,\\d+_AL_.", "");
           
            var content = new Content(title, urlImage,imDbRating);
        
            contentList.add(content);
        }
        
        return contentList;
    }
}
