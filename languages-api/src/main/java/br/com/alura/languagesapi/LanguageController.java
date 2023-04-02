package br.com.alura.languagesapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/languages")
public class LanguageController {

    @Autowired
    private LanguageRepository languageRepository;


    @GetMapping
    public List<Language> getlanguageList(){
        List<Language> languages = languageRepository.findByOrderByRanking();
        return languages;
    }

    @GetMapping("/{idLanguage}")
    public Language getlanguageId(@PathVariable String idLanguage){
        
        return languageRepository.findById(idLanguage)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public Language addLanguage(@RequestBody  Language language){
        return languageRepository.save(language);
    }

    @PutMapping("/{idLanguage}")
    public Language updadeLanguage(@PathVariable String idLanguage, @RequestBody Language language){
        
        if(!languageRepository.existsById(idLanguage)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        language.setId(idLanguage);
        Language languageUpdate = languageRepository.save(language);
        return languageUpdate;
    }

    @DeleteMapping("/{idLanguage}")
    public void deleteLanguage(@PathVariable String idLanguage ){
        languageRepository.deleteById(idLanguage);
    }

}
