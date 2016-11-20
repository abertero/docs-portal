package com.docs.web.config;

import com.docs.managers.ProductManager;
import com.docs.managers.PropertiesManager;
import com.docs.utils.SheetsUtils;
import com.google.api.services.sheets.v4.Sheets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.annotation.Resource;
import java.io.IOException;

@Configuration
@ComponentScan(basePackages = {"com.docs.web"})
@EnableWebMvc
@ImportResource("classpath:config/applicationContext.xml")
@PropertySource("classpath:config/application.properties")
public class ApplicationContext {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationContext.class);

    private static final String PROPERTY_NAME_MESSAGESOURCE_BASENAME = "message.source.basename";
    private static final String PROPERTY_NAME_MESSAGESOURCE_USE_CODE_AS_DEFAULT_MESSAGE = "message.source.use.code.as.default.message";

    private static final String VIEW_RESOLVER_PREFIX = "/WEB-INF/views/";
    private static final String VIEW_RESOLVER_SUFFIX = ".jsp";

    @Resource
    private Environment environment;

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();

        messageSource.setBasename(environment.getRequiredProperty(PROPERTY_NAME_MESSAGESOURCE_BASENAME));
        messageSource.setUseCodeAsDefaultMessage(Boolean.parseBoolean(environment.getRequiredProperty(PROPERTY_NAME_MESSAGESOURCE_USE_CODE_AS_DEFAULT_MESSAGE)));

        LOGGER.info("Init messageSource");
        return messageSource;
    }

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix(VIEW_RESOLVER_PREFIX);
        viewResolver.setSuffix(VIEW_RESOLVER_SUFFIX);

        LOGGER.info(String.format("Init viewResolver %s", viewResolver));
        return viewResolver;
    }

    @Bean
    public Sheets sheets() throws IOException {
        Sheets sheets = SheetsUtils.getSheetsService();
        LOGGER.info(String.format("Init sheets %s", sheets));
        return sheets;
    }

    @Bean
    public PropertiesManager propertiesManager() {
        PropertiesManager propertiesManager = new PropertiesManager();
        LOGGER.info(String.format("Init propertiesManager %s", propertiesManager));
        return propertiesManager;
    }

    @Bean
    public ProductManager productManager(Sheets sheets, PropertiesManager propertiesManager) {
        ProductManager productManager = new ProductManager(sheets, propertiesManager);
        LOGGER.info(String.format("Init productManager %s", productManager));
        return productManager;
    }
}
