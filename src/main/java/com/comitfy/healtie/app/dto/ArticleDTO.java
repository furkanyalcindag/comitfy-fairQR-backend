package com.comitfy.healtie.app.dto;

import com.comitfy.healtie.app.entity.Category;
import com.comitfy.healtie.app.model.enums.LanguageEnum;
import com.comitfy.healtie.util.common.BaseDTO;
import lombok.Data;

import javax.persistence.ElementCollection;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
public class ArticleDTO extends BaseDTO {

    private String name;
    private String title;
    private String isLiked;
  //  private List<Category> categoryList;

    private List<ArticleDTO> likeList;

    @ElementCollection
    private List<String> tag;



}
