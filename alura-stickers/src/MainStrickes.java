import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.net.URL;


public class MainStrickes {
    public static void main(String[] args) throws Exception {
    
        //fazer uma conexão http e buscar os top 250 filmes 
        //String url = "https://imdb-api.com/en/API/Top250Movies/k_6pui1d1n";
        //String url = "https://imdb-api.com/en/API/MostPopularMovies/k_6pui1d1n";
        Properties properties = new Properties();
        InputStream  inputStreamConfig = new FileInputStream("config.properties");
        properties.load(inputStreamConfig);
        String apiKey = properties.getProperty("IMDB_API_KEY");
        String url = "https://imdb-api.com/en/API/MostPopularMovies/" + apiKey;
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient(); 
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        
        // extrair dados : parsear os dados  (titulo , poster, classificação),exibir e manipular o dados
        
        var jsonParser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = jsonParser.parse(body); 
        var gerarImagem = new GeradorDeFigurinhas();

        var diretorioSaida = new File("stickers/");
        diretorioSaida.mkdir();
       
        for ( int i = 8 ; i <= 12; i++ ) {
            Map<String,String> filme = listaDeFilmes.get(i);
            String urlImagem = filme.get("image");
            String titulo = filme.get("title");
            double classificacao = Double.parseDouble(filme.get("imDbRating"));

            InputStream inputStream = new URL(urlImagem).openStream();
            String nomeArquivo = "stickers/" + titulo + ".png";

            LocalTime horaAtual = LocalTime.now();
            String textoSaudacao;
            
            if(horaAtual.isBefore(LocalTime.of(12, 0))){
                textoSaudacao = "Bom dia!";
            }else if(horaAtual.isBefore(LocalTime.of(18,0))){
                textoSaudacao = "Boa tarde!";
            }else{
                textoSaudacao = "Boa Noite!";
            }
            
            String textoFigurinha;
            InputStream myImage;
            if(classificacao >= 8){
                textoFigurinha = textoSaudacao + " ESSE HYPE";
                myImage = new FileInputStream(new File("img-sobreposicao/gatinho-muito-feliz.png"));
            }else if(classificacao <= 6){
                textoFigurinha = textoSaudacao + " ESSE CHUCHU";
                myImage = new FileInputStream(new File("img-sobreposicao/gatinho-feliz.png"));
            }else{
                textoFigurinha = textoSaudacao + " ESSE FLOPOU";
                myImage = new FileInputStream( new File("img-sobreposicao/gatinho-marrento.png"));
            }
            gerarImagem.criarImagem(inputStream, nomeArquivo,textoFigurinha,myImage);

           // System.out.println(filme.get("title"));
            System.out.println(filme.get("image"));
            System.out.println("\u001b[38;2;255;255;255m\u001b[48;2;42;100;228m -> Título:\u001b[m" + titulo);
            System.out.println(" -> Clasificação: "+filme.get("imDbRating"));
            int estrelas = (int) classificacao;
            
            for (int j = 1; j <= estrelas; j++) {
                
                System.out.print(" ⭐ ");
               
            }
            System.out.println("\n");
            System.out.println("");
        }

        
    }
}
