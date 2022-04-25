package com.comitfy.healtie.app.mapper;

import ch.qos.logback.core.pattern.Converter;
import com.comitfy.healtie.app.dto.LanguageDTO;
import com.comitfy.healtie.app.entity.Language;
import com.comitfy.healtie.util.PageDTO;
import com.comitfy.healtie.util.common.BaseMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Component
public class LanguageMapper implements BaseMapper<LanguageDTO, Language> {
    @Override
    public LanguageDTO entityToDTO(Language entity) {
        LanguageDTO languageDTO = new LanguageDTO();
        languageDTO.setCode(entity.getCode());
        languageDTO.setName(entity.getName());
        languageDTO.setUuid(entity.getUuid());
        return languageDTO;
    }

    @Override
    public Language dtoToEntity(LanguageDTO dto) {
        Language language = new Language();
        language.setName(dto.getName());
        language.setCode(dto.getCode());
        return language;
    }

    @Override
    public List<Language> dtoListToEntityList(List<LanguageDTO> languageDTOS) {
        List<Language> languageList = new ArrayList<>();
        for (LanguageDTO languageDTO : languageDTOS) {
            Language language = dtoToEntity(languageDTO);
            languageList.add(language);
        }
        return languageList;
    }

    @Override
    public List<LanguageDTO> entityListToDTOList(List<Language> languages) {
        List<LanguageDTO> languageDTOList = new ArrayList<>();
        for (Language language : languages) {
            LanguageDTO languageDTO = entityToDTO(language);
            languageDTOList.add(languageDTO);
        }

        return languageDTOList;
    }

    @Override
    public PageDTO<LanguageDTO> pageEntityToPageDTO(Page<Language> pageEntity) {


        PageDTO<LanguageDTO> pageDTO = new PageDTO<LanguageDTO>();
        List<Language> entityList = pageEntity.toList();
        List<LanguageDTO> languageDTOList = entityListToDTOList(entityList);
        pageDTO.setStart(pageEntity,languageDTOList);

        return pageDTO;
    }
}
