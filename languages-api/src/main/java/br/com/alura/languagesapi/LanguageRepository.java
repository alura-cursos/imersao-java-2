package br.com.alura.languagesapi;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends MongoRepository<Language,String> {
    List<Language>findByOrderByRanking();
}
