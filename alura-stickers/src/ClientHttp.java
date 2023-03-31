import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class ClientHttp {

    public String getlistDados(){
    
        //fazer uma conexão http 
        
      try {
        Properties properties = new Properties();
        InputStream  inputStreamConfig = new FileInputStream("config.properties");
        properties.load(inputStreamConfig);

        //filmes
        //String apiKeyImdb = properties.getProperty("IMDB_API_KEY");
        //String url = "https://imdb-api.com/en/API/MostPopularMovies/" + apiKeyImdb;

        //nasa
        //String apiKeyNasa = properties.getProperty("NASA_API_KEY");
        //String url = "https://api.nasa.gov/planetary/apod?api_key=" + apiKeyNasa;

        String test = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/NASA-APOD.json";
        URI address = URI.create(test);
        var client = HttpClient.newHttpClient(); 
        var request = HttpRequest.newBuilder(address).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        return  body;
        

      } catch (IOException | InterruptedException ex) {  
        // TODO: handle exception
        throw new RuntimeException(ex);
      }

    }
    
}
