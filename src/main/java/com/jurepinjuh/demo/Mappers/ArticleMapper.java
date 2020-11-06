package com.jurepinjuh.demo.Mappers;

import com.jurepinjuh.demo.Models.Article;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class ArticleMapper implements RowMapper<Article> {
    @Override
    public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
        Article article=new Article();
        article.setId(rs.getInt("IDARTICLE"));
        article.setName(rs.getString("NAME"));
        article.setDescription(rs.getString("DESCRIPTION"));
        article.setCategoryId(rs.getInt("CATEGORYID"));
        article.setGenderId(rs.getInt("GENDERCATEGORY"));
        article.setBrandId(rs.getInt("BRANDID"));
        article.setImagePath(rs.getString("IMAGEPATH"));
        article.setPrice(rs.getDouble("PRICE"));
        return article;
    }
}
