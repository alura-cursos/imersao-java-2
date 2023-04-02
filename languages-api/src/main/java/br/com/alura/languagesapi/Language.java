package br.com.alura.languagesapi;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "languagesPrincipal")
public class Language {

    @Id
    private String id;
    private String title;
    private String image;
    private int ranking;

    public Language(){

    }

    public Language(String title, String image, int ranking) {
        this.title = title;
        this.image = image;
        this.ranking = ranking;
    }

    public String getId() {
        return id;
    }
    
    public String getTitle() {
        return title;
    }
    public String getImage() {
        return image;
    }
    public int getRanking() {
        return ranking;
    }

    public void setId(String id) {
        this.id = id;
    }

}