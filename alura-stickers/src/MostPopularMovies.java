import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;
import java.util.Properties;



public class MostPopularMovies {
    public static void main(String[] args) throws Exception {
        //Desafio dia 01 
        //fazer uma conexão http e buscar os filmes mais populares 
        //proteger as credenciais em um arquivo de configuração. 
        Properties properties = new Properties();
        InputStream  inputStream = new FileInputStream("config.properties");
        properties.load(inputStream);
        String apiKey = properties.getProperty("IMDB_API_KEY");
        String url = "https://imdb-api.com/en/API/MostPopularMovies/" + apiKey;
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient(); 
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        
        // extrair dados  (titulo , poster, classificação, ano) 
        var jsonParser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = jsonParser.parse(body); 
       
       
        for (int i = 0; i < 10;  i ++) {
            Map<String,String> filme = listaDeFilmes.get(i);
            System.out.println("\u001b[38;2;255;255;255m \u001b[48;2;42;100;228m -> Titulo: \u001b[m" + filme.get("title"));
            System.out.println("\u001b[38;2;255;255;255m \u001b[48;2;42;100;228m -> URL imagem: \u001b[m" + filme.get("image"));
            System.out.println("\u001b[38;2;255;255;255m \u001b[48;2;42;100;228m -> Ano: \u001b[m"+ filme.get("year"));
            System.out.println("\u001b[38;2;255;255;255m \u001b[48;2;42;100;228m -> Classificacao: \u001b[m"+ filme.get("imDbRating"));
            double classificacao  = Double.parseDouble(filme.get("imDbRating")); 
            int estrelas = (int) classificacao;
            
            for (int j = 1; j <= estrelas; j++) {
                
                System.out.print(" ⭐ ");
               
            }
            System.out.println("\n");

        }

        
    }
}
