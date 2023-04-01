import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class StickerGenerator {

    public void criarImagem(InputStream inputStream, String nomeArquivo, String texto,InputStream inputImagemSobreposicao) throws Exception {
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

        BufferedImage imagemSobreposicao = ImageIO.read(inputImagemSobreposicao);
        int posicaoImgSobreposicaoY = novaAltura - imagemSobreposicao.getHeight()+20;
        graphics2d.drawImage(imagemSobreposicao, 20, posicaoImgSobreposicaoY,null);

        //configurar a fonte 
        var  fonte = new Font("Impact",Font.BOLD, 100); 
        graphics2d.setFont(fonte);
        graphics2d.setColor(Color.ORANGE);


        //Escrever uma frase na imagem.
        //String texto = "Bom dia! ESSE é TOP";
        FontMetrics fontMetrics = graphics2d.getFontMetrics();
        fontMetrics.getStringBounds(texto, graphics2d);
        Rectangle2D retuRectangle2d = fontMetrics.getStringBounds(texto, graphics2d);
        int textWidth = (int) retuRectangle2d.getWidth();
        int eixoX = (largura - textWidth)/2;
        int eixoY = novaAltura-80;
        graphics2d.drawString(texto,  eixoX,eixoY );

        //contorno no texto 
        FontRenderContext fontRenderContext = graphics2d.getFontRenderContext();
        var TextLayout  = new TextLayout(texto, fonte,fontRenderContext);
        Shape shape =  TextLayout.getOutline(null);
        AffineTransform transform = graphics2d.getTransform();

        transform.translate( eixoX,eixoY);
        graphics2d.setTransform(transform);
        var outlineStroke =  new BasicStroke(largura * 0.003f);
        graphics2d.setStroke(outlineStroke);
        graphics2d.setColor(Color.BLACK);
        graphics2d.draw(shape);
        graphics2d.setClip(shape);;





        //Escrever a nova imagem em um arquivo 
        ImageIO.write(novaImagem, "png", new File(nomeArquivo));
    }

}
