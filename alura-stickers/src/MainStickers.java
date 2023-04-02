import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.time.LocalTime;
import java.util.List;
import java.net.URL;


public class MainStickers {
    public static void main(String[] args) throws Exception {
    
        ClientHttp clientHttp = new ClientHttp();
        String json = clientHttp.getlistDados();
        
        //manipula os dados 
        ContentExtractorNASA extractorNASA = new ContentExtractorNASA();
        List<Content> contents = extractorNASA.getContent(json);
        //ContentExtractorIMDB extractorIMDB = new ContentExtractorIMDB();
        //List<Content> contents = extractorIMDB .getContent(json);

        var generatImage = new StickerGenerator();
        
        //criação do diretorio
        var diretorioSaida = new File("stickers/");
        diretorioSaida.mkdir();
       
        for ( int i = 0 ; i < 7; i++ ) {
            Content content = contents.get(i);

            InputStream inputStream = new URL(content.urlImage()).openStream();
            String nomeArquivo = "stickers/" + content.title() + ".png";

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
            if(content.classification() >= 8.0){
                textoFigurinha = textoSaudacao + " HYPOU";
                myImage = new FileInputStream(new File("img-sobreposicao/gatinho-muito-feliz.png"));
            }else if(content.classification() <= 6.0){
                textoFigurinha = textoSaudacao + " DE BOA";
                myImage = new FileInputStream(new File("img-sobreposicao/gatinho-feliz.png"));
            }else{
                textoFigurinha = textoSaudacao + " FLOPOU";
                myImage = new FileInputStream( new File("img-sobreposicao/gatinho-marrento.png"));
            }
            generatImage.criarImagem(inputStream, nomeArquivo,textoFigurinha,myImage);

        
            System.out.println("");
        }

        
    }
}
