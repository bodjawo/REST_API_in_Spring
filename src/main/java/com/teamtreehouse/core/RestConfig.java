package com.teamtreehouse.core;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class RestConfig extends RepositoryRestConfigurerAdapter {
    @Autowired
    @Lazy
    private Validator validator;
    
    @Override
    public void configureValidatingRepositoryEventListener(
        ValidatingRepositoryEventListener validatingListener) {
        validatingListener.addValidator("beforeCreate",validator);
        validatingListener.addValidator("beforeSave",validator);
        
    }
    
    @Bean
    public javax.validation.Validator localValidatorFactoryBean() {
        return new LocalValidatorFactoryBean();
    }
}