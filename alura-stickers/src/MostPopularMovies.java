import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;



public class MostPopularMovies {
    public static void main(String[] args) throws Exception {
        //Desafio dia 01 
        //fazer uma conexão http e buscar os filmes mais populares  

        String url = "https://imdb-api.com/en/API/MostPopularMovies/k_6pui1d1n";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient(); 
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        
        // extrair dados  (titulo , poster, classificação, ano) 
        var jsonParser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = jsonParser.parse(body); 
       
       
        for (Map<String,String> filme : listaDeFilmes) {
            System.out.println("\u001b[38;2;255;255;255m \u001b[48;2;42;122;228m Título: \u001b[m" + filme.get("title"));
            System.out.println("\u001b[38;2;255;255;255m \u001b[48;2;42;122;228m URL imagem: \u001b[m" + filme.get("image"));
            System.out.println("\u001b[38;2;255;255;255m \u001b[48;2;42;122;228m Classificação: \u001b[m" + filme.get("imDbRating"));
            System.out.println("\u001b[38;2;255;255;255m \u001b[48;2;42;122;228m Ano: \u001b[m"+ filme.get("year"));
            System.out.println("⭐⭐⭐⭐⭐");

           

        }

        
    }
}
