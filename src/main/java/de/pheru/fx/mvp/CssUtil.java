package de.pheru.fx.mvp;

import de.pheru.fx.mvp.annotations.AdditionalStylesheets;

import java.net.URL;

final class CssUtil {

    private CssUtil() {
        // Utility-Klasse
    }

    public static String getStylesheet(final Class<?> clazz, final String name){
        final URL uri;
        if (name.endsWith(".css")) {
            uri = clazz.getResource(name);
        } else {
            uri = clazz.getResource(name + ".css");
        }
        if (uri == null) {
            return null;
        }
        return uri.toExternalForm();
    }

    public static String[] getAdditionalStylesheetsByAnnotation(final Class<?> clazz){
        final AdditionalStylesheets annotation = clazz.getAnnotation(AdditionalStylesheets.class);
        if (annotation != null) {
            return annotation.value();
        }else{
            return new String[0];
        }
    }
}
