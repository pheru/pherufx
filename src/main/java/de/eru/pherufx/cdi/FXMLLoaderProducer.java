package de.eru.pherufx.cdi;

import javafx.fxml.FXMLLoader;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import org.jboss.weld.interceptor.util.proxy.TargetInstanceProxy;

/**
 *
 * @author Philipp Bruckner
 */
public class FXMLLoaderProducer {

    @Inject
    Instance<Object> instance;

    @Produces
    public FXMLLoader createLoader() {
        FXMLLoader loader = new FXMLLoader();
        loader.setControllerFactory((Class<?> param) -> {
            Object controller = instance.select(param).get();
            if (controller instanceof TargetInstanceProxy) {
                controller = ((TargetInstanceProxy<?>) controller).getTargetInstance();
            }
            return controller;
        });
        return loader;
    }
}
