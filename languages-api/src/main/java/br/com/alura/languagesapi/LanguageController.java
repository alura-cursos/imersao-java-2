package br.com.alura.languagesapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LanguageController {
    
    @Autowired
    private LanguageRepository languageRepository;
       
    @GetMapping(value = "/language")
    public String getLanguage(){
        return "Olá Java";
    }

    @GetMapping(value = "/languages")
    public List<Language> getlanguageList(){
        List<Language> languages = languageRepository.findAll();
        return languages;
    }

    @PostMapping(value = "/languages")
    public Language addLanguage(@RequestBody  Language language){
        return languageRepository.save(language);
    }
}

