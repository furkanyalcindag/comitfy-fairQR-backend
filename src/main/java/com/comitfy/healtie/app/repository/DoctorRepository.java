package com.comitfy.healtie.app.repository;

import com.comitfy.healtie.app.entity.Doctor;
import com.comitfy.healtie.userModule.entity.User;
import com.comitfy.healtie.util.common.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DoctorRepository extends BaseRepository<Doctor> {


    Optional<Doctor> findByUser(User user);


/*    @Query("SELECT COUNT(articles) FROM Doctor doctor" +
            " inner join doctor.articleList articles where doctor.uuid=?1")
    long getCountOfArticles(UUID doctorUUID);*/

  /*  select article.id,count(article.id) from article
inner join article_user_likes on article_user_likes.article_id = article.id group by article.id
having article.doctor_id=1

    @Query("SELECT COUNT(likes) FROM Article article " +
            "inner join article.userLikes likes  WHERE article.uuid=?1")
    long getCountOfArticleLike(UUID articleUUID);

   */


/*    @Query("SELECT COUNT(article.userLikes) from Article article" +
            " inner join article.userLikes on article.userLikes=article.id group by article.id having  article.user.id=?1")
    long getCountOfArticleLikes(UUID userUUID);*/

    @Query("SELECT COUNT(likes) from Article article" +
            " inner join article.userLikes likes  inner join article.user user where user.uuid=?1")
    long getCountOfArticleLikes(UUID userUUID);


}
