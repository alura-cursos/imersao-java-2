import java.util.List;

public interface ContentExtractor {

    List<Content> getContent(String json);
    
}
