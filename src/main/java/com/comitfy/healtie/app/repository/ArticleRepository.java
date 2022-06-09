package com.comitfy.healtie.app.repository;

import com.comitfy.healtie.app.entity.Article;
import com.comitfy.healtie.app.entity.Category;
import com.comitfy.healtie.app.entity.Doctor;
import com.comitfy.healtie.app.model.enums.LanguageEnum;
import com.comitfy.healtie.userModule.entity.User;
import com.comitfy.healtie.util.common.BaseWithMultiLanguageRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface ArticleRepository extends BaseWithMultiLanguageRepository<Article> {

    Page<Article> findAllByDoctorAndLanguageEnum(Pageable pageable, Doctor doctor, LanguageEnum languageEnum);

    Page<Article> findAllByDoctor(Pageable pageable, Doctor doctor);

    Page<Article> findAllByCategoryListInAndLanguageEnum(Pageable pageable, Set<Category> category, LanguageEnum languageEnum);

    @Query("SELECT COUNT(likes) FROM Article article " +
            "inner join article.userLikes likes  WHERE article.uuid=?1")
    long getCountOfArticleLike(UUID articleUUID);

    @Query("SELECT COUNT(saves) FROM Article article " +
            "inner join article.userSaves saves  WHERE article.uuid=?1")
    long getCountOfArticleSave(UUID articleUUID);


    @Query("SELECT article from Article article" +
            " inner join article.userSaves saves WHERE saves.uuid=?1 ")
    Page<Article>  getSavedArticleOfUser(Pageable pageable,UUID uuid);

  //  Page<Article> findAllByUser(Pageable pageable,User user);


}
