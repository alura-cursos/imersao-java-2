public record Content(String title,String urlImage,Double classification) {
    
    public Content(String title, String urlImage) {
        this(title, urlImage, 0.0);
    }
}
