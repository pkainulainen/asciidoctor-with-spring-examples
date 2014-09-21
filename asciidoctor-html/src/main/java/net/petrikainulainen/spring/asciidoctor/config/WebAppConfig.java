package net.petrikainulainen.spring.asciidoctor.config;

import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.util.EnumSet;

/**
 * This configuration class configures the example application.
 * @author Petri Kainulainen
 */
public class WebAppConfig implements WebApplicationInitializer {

    private static final String DISPATCHER_SERVLET_NAME = "dispatcher";

    private static final String SITEMESH3_FILTER_NAME = "sitemesh";
    private static final String[] SITEMESH3_FILTER_URL_PATTERNS = {"*.jsp", "/asciidoctor/*"};

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(WebAppContext.class);

        configureDispatcherServlet(servletContext, rootContext);
        configureSitemesh3Filter(servletContext);

        servletContext.addListener(new ContextLoaderListener(rootContext));
    }

    private void configureDispatcherServlet(ServletContext servletContext, WebApplicationContext rootContext) {
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet(
                DISPATCHER_SERVLET_NAME,
                new DispatcherServlet(rootContext)
        );
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }

    private void configureSitemesh3Filter(ServletContext servletContext) {
        FilterRegistration.Dynamic sitemesh = servletContext.addFilter(SITEMESH3_FILTER_NAME, new ConfigurableSiteMeshFilter());
        EnumSet<DispatcherType> dispatcherTypes = EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD);
        sitemesh.addMappingForUrlPatterns(dispatcherTypes, true, SITEMESH3_FILTER_URL_PATTERNS);
    }
}
