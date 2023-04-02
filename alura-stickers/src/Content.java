public class Content {

    private final String title;
    private final String urlImage;
    private  double classification;


    public Content(String title, String urlImage) {
        this.title = title;
        this.urlImage = urlImage;
    }
    

    public Content(String title, String urlImage, double classification) {
        this.title = title;
        this.urlImage = urlImage;
        this.classification = classification;
    }


    public String getTitle() {
        return title;
    }
    public String getUrlImage() {
        return urlImage;
    }
    public double getClassification() {
        return classification;
    }
    
}
