package com.jurepinjuh.demo.jobs;

import com.jurepinjuh.demo.Models.Article;
import com.jurepinjuh.demo.Models.ERole;
import com.jurepinjuh.demo.Models.User;
import com.jurepinjuh.demo.Repository.JpaArticleRepository;
import com.jurepinjuh.demo.Repository.JpaUserRepository;
import com.jurepinjuh.demo.Services.EmailService;
import org.hibernate.mapping.Set;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class SendArticlesEmailJob extends QuartzJobBean {

    private final String SUBJECT = "BEST OFFERS TODAY-KICKZSHOP";

    @Autowired
    EmailService emailService;

    @Autowired
    JpaUserRepository userRepository;

    @Autowired
    JpaArticleRepository articleRepository;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Iterable<Article> articles = articleRepository.findAll();
        Iterable<User> users = userRepository.findAll();
        StringBuilder builder=new StringBuilder();
        for (Article article :
                articles) {
            builder.append(article.getName()+" Price:"+article.getPrice()+"$");
            builder.append("\n");
        }
        for (User user :
                users) {
            if (user.getRole().getId() == ERole.USER.value) {
            emailService.sendSimpleMessage(user.getEmail(),SUBJECT,builder.toString());
            }
        }
    }
}
