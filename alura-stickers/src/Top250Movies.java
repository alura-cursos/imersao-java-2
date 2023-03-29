import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;
import java.net.URL;


public class Top250Movies {
    public static void main(String[] args) throws Exception {
    
        //fazer uma conexão http e buscar os top 250 filmes 

        String url = "https://imdb-api.com/en/API/Top250Movies/k_6pui1d1n";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient(); 
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        
        // extrair dados : parsear os dados  (titulo , poster, classificação),exibir e manipular o dados
        
        var jsonParser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = jsonParser.parse(body); 
        var gerarImagem = new GeradorDeFigurinhas();

        var diretorioSaida = new File("saida/");
        diretorioSaida.mkdir();
       
        for ( int i = 0 ; i <= 5; i++ ) {
            Map<String,String> filme = listaDeFilmes.get(i);
            String urlImagem = filme.get("image");
            String titulo = filme.get("title");

           

            InputStream inputStream = new URL(urlImagem).openStream();
            String nomeArquivo = "saida/" + titulo + ".png";

            gerarImagem.criarImagem(inputStream, nomeArquivo);

           // System.out.println(filme.get("title"));
           // System.out.println(filme.get("image"));
           // System.out.println(filme.get("imDbRating"));
            System.out.println(titulo);
            System.out.println("");
        }

        
    }
}
