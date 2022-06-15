package com.comitfy.healtie.app.controller;

import com.comitfy.healtie.app.dto.CategoryDTO;
import com.comitfy.healtie.app.dto.requestDTO.CategoryRequestDTO;
import com.comitfy.healtie.app.entity.Category;
import com.comitfy.healtie.app.mapper.CategoryMapper;
import com.comitfy.healtie.app.model.enums.LanguageEnum;
import com.comitfy.healtie.app.repository.CategoryRepository;
import com.comitfy.healtie.app.service.CategoryService;
import com.comitfy.healtie.app.specification.CategorySpecification;
import com.comitfy.healtie.util.PageDTO;
import com.comitfy.healtie.util.common.BaseWithMultiLanguageCrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("category")
public class CategoryController extends BaseWithMultiLanguageCrudController<CategoryDTO, CategoryRequestDTO, Category, CategoryRepository, CategoryMapper, CategorySpecification, CategoryService> {

    @Autowired
    CategoryService categoryService;

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    protected CategoryService getService() {
        return categoryService;
    }

    @Override
    protected CategoryMapper getMapper() {
        return categoryMapper;
    }

    @GetMapping("category/{categoryId}")
    public ResponseEntity<PageDTO<CategoryDTO>> getByCategoryId(@RequestHeader(value = "accept-language", required = true) String language,
                                                                @PathVariable UUID categoryId, @RequestParam int pageNumber, @RequestParam int pageSize) {
        Optional<Category> optional = categoryService.getRepository().findByUuid(categoryId);
        if (optional == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(categoryService.getCategoryById(categoryId, pageNumber, pageSize, LanguageEnum.valueOf(language)), HttpStatus.OK);
        }
    }


}
