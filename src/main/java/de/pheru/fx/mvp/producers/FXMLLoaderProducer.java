package de.pheru.fx.mvp.producers;

import de.pheru.fx.mvp.qualifiers.PheruFXMLLoader;
import javafx.fxml.FXMLLoader;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import org.jboss.weld.interceptor.util.proxy.TargetInstanceProxy;

public class FXMLLoaderProducer {

    @Inject
    private Instance<Object> instance;

    @Produces
    @PheruFXMLLoader
    public FXMLLoader createLoader() {
        final FXMLLoader loader = new FXMLLoader();
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
