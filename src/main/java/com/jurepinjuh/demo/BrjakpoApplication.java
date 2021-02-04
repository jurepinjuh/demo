package com.jurepinjuh.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import javax.sql.DataSource;
import java.util.Locale;
import java.util.Properties;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@PropertySource("classpath:database.properties")
@EntityScan
public class BrjakpoApplication {

    @Autowired
    Environment environment;
    private final String URL="url";
    private final String USER="dbuser";
    private final String DRIVER="driver";
    private final String PASSWORD="dbpassword";


    @Bean
    public LocaleResolver localeResolver(){
        AcceptHeaderLocaleResolver localeResolver=new AcceptHeaderLocaleResolver();
        localeResolver.setDefaultLocale(new Locale("en"));
        return localeResolver;
    }

    @Bean
    public MessageSource bundleMessageSource(){
        ReloadableResourceBundleMessageSource messageSource=new ReloadableResourceBundleMessageSource();
        return messageSource;
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(bundleMessageSource());
        return bean;
    }

    @Bean
    DataSource dataSource(){
        DriverManagerDataSource driverManagerDataSource=new DriverManagerDataSource();
        driverManagerDataSource.setUrl(environment.getProperty(URL));
        driverManagerDataSource.setUsername(environment.getProperty(USER));
        driverManagerDataSource.setDriverClassName(environment.getProperty(DRIVER));
        driverManagerDataSource.setPassword(environment.getProperty(PASSWORD));
        return driverManagerDataSource;
    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("kickzshop123@gmail.com");
        mailSender.setPassword("jurepinjuh121..");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }
    @Bean
    JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(dataSource());
    }

    public static void main(String[] args) {
        SpringApplication.run(BrjakpoApplication.class, args);
    }


}
