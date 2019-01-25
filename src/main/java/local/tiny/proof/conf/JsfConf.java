/**
 *
 * Copyright 2019 paolo mococci
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package local.tiny.proof.conf;

import com.sun.faces.config.ConfigureListener;
import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@Configuration
public class JsfConf 
        extends SpringBootServletInitializer
        implements  ServletContextAware
{
    public void addResourceHandlers(ResourceHandlerRegistry resource) 
            throws ServletException {
        if (!resource.hasMappingForPattern("/webjars/**")) {
            resource.addResourceHandler("/webjars/**")
                    .addResourceLocations("classpath:/META-INF/resources/webjars/");
        }
    }
    
    @Bean
    public ServletRegistrationBean facesServlet() {
        FacesServlet servlet = new FacesServlet();
        ServletRegistrationBean registration = new ServletRegistrationBean(servlet, "*.xhtml");
        registration.setName("FacesServlet");
        registration.setLoadOnStartup(1);
        return registration;
    }
    
    @Bean
    public ServletListenerRegistrationBean<ConfigureListener> jsfConfigureListener() {
        return new ServletListenerRegistrationBean<>(new ConfigureListener());
    }

    @Override
    public void onStartup(ServletContext servletContext) 
            throws ServletException {
        super.onStartup(servletContext);
        servletContext.setInitParameter("primefaces.CLIENT_SIDE_VALIDATION", "true");
        servletContext.setInitParameter("javax.faces.PROJECT_STAGE", "Development");
    }
    
    @Override
    public void setServletContext(ServletContext servletContext) {
        servletContext
                .setInitParameter(
                        "com.sun.faces.forceLoadConfiguration", 
                        java.lang.Boolean.TRUE.toString());
    }
}
