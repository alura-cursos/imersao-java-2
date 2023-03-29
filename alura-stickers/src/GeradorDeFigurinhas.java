import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GeradorDeFigurinhas {

    public void criarImagem(InputStream inputStream, String nomeArquivo) throws Exception {
        // leitura da imagem 
        //InputStream inputStream = new FileInputStream(new File("alura-stickers/entrada/filme-maior.jpg"));
        //InputStream inputStream = new URL ("https://m.media-amazon.com/images/M/MV5BNDE3ODcxYzMtY2YzZC00NmNlLWJiNDMtZDViZWM2MzIxZDYwXkEyXkFqcGdeQXVyNjAwNDUxODI@.jpg").openStream();
        
        
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        // Criar nova imagem em memoria com transparência e com tamanho novo 
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura,BufferedImage.TRANSLUCENT);

        //Copiar a imagem pra uma nova imagem (em memória)
        Graphics2D graphics2d = (Graphics2D) novaImagem.getGraphics();
        graphics2d.drawImage(imagemOriginal, 0, 0,null);

        //configurar a font
        var  fonte = new Font(Font.SANS_SERIF,Font.BOLD, 70); 
        graphics2d.setFont(fonte);
        graphics2d.setColor(Color.ORANGE);

        //Escrever uma frase comica na imagem.
        graphics2d.drawString("Bom dia! ESSE é TOP", 0 , novaAltura-100);

        //Escrever a nova imagem em um arquivo 
        ImageIO.write(novaImagem, "png", new File(nomeArquivo));
    }

}
