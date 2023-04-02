public record Content(String title,String urlImage,double classification) {
    
    public Content(String title, String urlImage) {
        this(title, urlImage, 0.0);
    }
}
