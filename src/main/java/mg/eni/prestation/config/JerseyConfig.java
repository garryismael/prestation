package mg.eni.prestation.config;

import mg.eni.prestation.controllers.MedecinController;
import mg.eni.prestation.controllers.PatientController;
import mg.eni.prestation.controllers.TraitementController;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig(){
        register(PatientController.class);
        register(MedecinController.class);
        register(TraitementController.class);
    }
}
