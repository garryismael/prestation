package mg.eni.prestation.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.stereotype.Component;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import mg.eni.prestation.config.mappers.IvalidDataMapper;
import mg.eni.prestation.config.mappers.NotFoundMapper;
import mg.eni.prestation.controllers.MedecinController;
import mg.eni.prestation.controllers.PatientController;
import mg.eni.prestation.controllers.TraitementController;

@Component
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(PatientController.class);
        register(MedecinController.class);
        register(TraitementController.class);
        register(IvalidDataMapper.class);
        register(NotFoundMapper.class);

        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        property(ServerProperties.BV_DISABLE_VALIDATE_ON_EXECUTABLE_OVERRIDE_CHECK, true);

        configureSwagger();
        property(ServletProperties.FILTER_FORWARD_ON_404, true);
    }

    public void configureSwagger() {
        this.register(ApiListingResource.class);
        this.register(SwaggerSerializers.class);
        final BeanConfig config = new BeanConfig();
        config.setConfigId("spring-jersey-swagger-example");
        config.setTitle("Prestation Swagger Docs");
        config.setVersion("2.0.0");
        config.setBasePath("/api");
        config.setResourcePackage("mg.eni.prestation");
        config.setScan(true);
    }
}
