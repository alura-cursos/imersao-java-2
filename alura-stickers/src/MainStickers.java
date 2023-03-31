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
        //List<Content> content = 

        var generatImage = new StickerGenerator();
        
        //criação do diretorio
        var diretorioSaida = new File("stickers/");
        diretorioSaida.mkdir();
       
        for ( int i = 0 ; i < 3; i++ ) {
            Content content = contents.get(i);

            
            //double classificacao = Double.parseDouble(content.getClassificao());

            InputStream inputStream = new URL(content.getUrlImage()).openStream();
            String nomeArquivo = "stickers/" + content.getTitle() + ".png";

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
            if(content.getClassification() >= 8){
                textoFigurinha = textoSaudacao + " ESSE HYPOU";
                myImage = new FileInputStream(new File("img-sobreposicao/gatinho-muito-feliz.png"));
            }else if(content.getClassification() <= 6){
                textoFigurinha = textoSaudacao + " ESSE PASSA";
                myImage = new FileInputStream(new File("img-sobreposicao/gatinho-feliz.png"));
            }else{
                textoFigurinha = textoSaudacao + " ESSE FLOPOU";
                myImage = new FileInputStream( new File("img-sobreposicao/gatinho-marrento.png"));
            }
            generatImage.criarImagem(inputStream, nomeArquivo,textoFigurinha,myImage);

        
            System.out.println("");
        }

        
    }
}
