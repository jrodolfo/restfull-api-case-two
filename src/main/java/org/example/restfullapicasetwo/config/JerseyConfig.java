package org.example.restfullapicasetwo.config;

import jakarta.annotation.PostConstruct;
import jakarta.ws.rs.ApplicationPath;
import org.example.restfullapicasetwo.controller.CatalogClientController;
import org.example.restfullapicasetwo.controller.CatalogImageController;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;


@Component
@ApplicationPath("/imageclient")
public class JerseyConfig extends ResourceConfig {

    @PostConstruct
    public void init() {
        configEndPoints();
    }

    private void configEndPoints(){
        register(CatalogClientController.class);
        register(CatalogImageController.class);
    }
}
