package com.maruf.i18n.localization.service;

import com.maruf.i18n.localization.dto.LocalizationDto;
import com.maruf.i18n.localization.entity.LocalizationKey;

import java.util.List;
import java.util.Map;

public interface LocalizationService {

    LocalizationDto create(LocalizationDto localizationDto);

    LocalizationDto update(LocalizationDto localizationDto);

    void importToLocalization(List<LocalizationDto> localizationDtoList);

    List<Map<String, Object>> findAll(String languageId);

    LocalizationDto findOne(String localization, String languageId);

    void delete(String localizationId);

    Map<String, Object> findLocalizationByProjectNameAndLanguageCode(String projectName, String languageCode);

    Map<String, Object> findLocalizationByProjectIdAndLanguageCode(String languageId);

    List<String> findLocalizationKeyByProject(String projectName);

    LocalizationKey findLocalizationValueByKey(String projectName, String key);
}