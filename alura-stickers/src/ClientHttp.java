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

        //buscar a url da API
        API api = API.NASA_APOD;


        
        //filmes
        //String apiKeyImdb = properties.getProperty("IMDB_API_KEY");
        //String url = api.getUrl() + apiKeyImdb;
        //String url = api.getUrl() + apiKeyImdb;

        //nasa
        String apiKeyNasa = properties.getProperty("NASA_API_KEY");
        String dateNasa = properties.getProperty("DATA_NASA");
        String url = api.getUrl() + apiKeyNasa + dateNasa;

        //String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/NASA-APOD.json";
        URI address = URI.create(url);
        var client = HttpClient.newHttpClient(); 
        var request = HttpRequest.newBuilder(address).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        return  body;
        

      } catch (IOException | InterruptedException ex) {  
        // TODO: handle exception
        throw new ClientHttpException("Erro ao burcar URL");
      }

    }
    
}
