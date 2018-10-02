package com.maruf.i18n.localization.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LocalizationDto {

    private Long id;

    private String langKey;

    private String value;

    private Long languageId;

    private Long projectId;
}