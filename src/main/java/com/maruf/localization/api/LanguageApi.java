package com.maruf.localization.api;

import com.maruf.localization.entity.Language;
import com.maruf.localization.service.LanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/protected/languages")
public class LanguageApi {

    private final LanguageService languageService;

    @PostMapping
    public ResponseEntity<Language> create(@RequestBody Language language){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(languageService.create(language));
    }

    @PutMapping
    public ResponseEntity<Language> update(@RequestBody Language language){
        return ResponseEntity.ok()
                .body(languageService.update(language));
    }

    @GetMapping("/{languageId}")
    public ResponseEntity<Language> findById(@PathVariable String languageId){
        return ResponseEntity.ok()
                .body(languageService.findById(languageId));
    }

    @GetMapping("/projects/{projectId}")
    public ResponseEntity<List<Language>> findAll(@PathVariable String projectId){
        return ResponseEntity.ok()
                .body(languageService.findAll(projectId));
    }

    @DeleteMapping("/{languageId}")
    public void delete(@PathVariable String languageId){
        languageService.delete(languageId);
    }

}
