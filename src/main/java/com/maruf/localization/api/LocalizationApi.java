package com.maruf.localization.api;

import com.maruf.localization.entity.Localization;
import com.maruf.localization.service.LocalizationService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/localizations")
public class LocalizationApi {

    private final LocalizationService localizationService;
    public LocalizationApi(LocalizationService localizationService) {
        this.localizationService = localizationService;
    }

    @GetMapping
    public ResponseEntity findAll(@RequestParam(required = false) String name, Pageable pageable){
        return null;
    }

    @GetMapping("/{localizationId}")
    public ResponseEntity findById(@PathVariable Long localizationId){
        return null;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Localization localization){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(localizationService.create(localization));
    }

    @PutMapping
    public ResponseEntity update(@RequestBody Localization localization){
        return null;
    }

    @DeleteMapping("/{localizationId}")
    public void delete(@RequestParam Long localizationId){

    }
}