package com.microtripit.mandrillapp.lutung;

import com.microtripit.mandrillapp.lutung.controller.MandrillUsersApi;
import com.microtripit.mandrillapp.lutung.model.MandrillApiError;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by yotam on 12/26/15.
 */
public class Main {
    public static void main(String[] args) throws IOException, MandrillApiError {
        final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext-LutungMandrill-all.xml");
        final PropertyPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertyPlaceholderConfigurer();
        final Properties properties = new Properties();

        properties.put("mandrill.root.url", "https://mandrillapp.com/api/1.0/");
        properties.put("mandrill.api.key", "put_your_api_key_here");
        propertyPlaceholderConfigurer.setProperties(properties);
        context.addBeanFactoryPostProcessor(propertyPlaceholderConfigurer);
        context.refresh();

        final MandrillUsersApi mandrillUsersApi = context.getBean("mandrillUsersApi", MandrillUsersApi.class);

        mandrillUsersApi.info();
    }
}
