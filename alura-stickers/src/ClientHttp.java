import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class ClientHttp {

    public String getlistDados(String url){
    
        //fazer uma conexão http 
        
      try {
      
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
