package com.maruf.i18n.language.service;

import com.maruf.i18n.language.entity.Language;

import java.util.List;

public interface LanguageService {

    List<Language> findAll(Long projectId);

    Language findById(Long languageId);

    Language create(Language language);

    Language update(Language language);

    void delete(Long languageId);

    List<Language> findLanguageByProject(String projectName);
}