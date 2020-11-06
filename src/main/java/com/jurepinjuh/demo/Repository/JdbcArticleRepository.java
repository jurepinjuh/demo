package com.jurepinjuh.demo.Repository;

import com.jurepinjuh.demo.Mappers.ArticleMapper;
import com.jurepinjuh.demo.Models.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcArticleRepository implements IArticleRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Article> getAllArticles() {
        return jdbcTemplate.query("select * from ARTICLE",new ArticleMapper());
    }

    @Override
    public Article getArticleByID(int id) {
        Article article=null;
        try {
            article=jdbcTemplate.queryForObject("select * from ARTICLE where IDARTICLE=?",new Object[]{id},new ArticleMapper());
        }
        catch(Exception exception){

        }
        return article;
    }

    @Override
    public Article addArticle(Article article) {
        GeneratedKeyHolder holder=new GeneratedKeyHolder();
        jdbcTemplate.update("insert into ARTICLE(NAME,DESCRIPTION,CATEGORYID,GENDERCATEGORY,BRANDID,IMAGEPATH,PRICE) VALUES(?,?,?,?,?,?,?)",
                new Object[]{article.getName(),article.getDescription(),article.getCategoryId(),article.getGenderId(),
                        article.getBrandId(),article.getImagePath(),article.getPrice()},holder);
        return this.getArticleByID(holder.getKey().intValue());
    }

    @Override
    public boolean deleteArticle(int id) {
        return jdbcTemplate.update("delete from ARTICE where id=?",new Object[]{id})>0;
    }
}
