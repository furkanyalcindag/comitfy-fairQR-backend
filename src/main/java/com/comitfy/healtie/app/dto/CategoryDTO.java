package com.comitfy.healtie.app.dto;

import com.comitfy.healtie.app.model.enums.LanguageEnum;
import com.comitfy.healtie.util.common.BaseDTO;
import lombok.Data;


import java.util.Set;

@Data
public class CategoryDTO extends BaseDTO {

   private String name;

   private LanguageEnum languageEnum;

   private Set<CategoryDTO> parentList;

}