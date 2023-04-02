package br.com.alura.languagesapi;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LanguageController {
    

    private List<Language> languages=
        List.of(
            new Language("Java", "https://raw.githubusercontent.com/abrahamcalf/programming-languages-logos/master/src/java/java_512x512.png", 1),
            new Language("JavaScript", "https://raw.githubusercontent.com/abrahamcalf/programming-languages-logos/master/src/javascript/javascript_512x512.png", 2)
        );
       
    @GetMapping(value = "/language")
    public String getLanguage(){
        return "Olá Java";
    }

    @GetMapping(value = "/languages")
    public List<Language> getlanguageList(){
        return languages;
    }
}

