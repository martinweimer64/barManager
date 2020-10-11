package com.weimer.barManager.config;

import com.weimer.barManager.jaxrs.BarResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.wadl.internal.WadlResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JaxRsConfig extends ResourceConfig {

    private String PACKAGE = "com.weimer.barManager.jaxrs";

    @Autowired
    public JaxRsConfig() {
        register(BarResource.class);
        register(WadlResource.class);
    }

}