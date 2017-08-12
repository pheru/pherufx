package de.pheru.fx.mvp;

import de.pheru.fx.mvp.annotations.AdditionalStylesheets;
import de.pheru.fx.mvp.exceptions.ApplicationInitializationException;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

final class CssUtil {

    private CssUtil() {
        // Utility-Klasse
    }

    public static List<String> loadStylesheets(final Class<?> clazz, final String name) {
        final List<String> stylesheets = new ArrayList<>();

        final String stylesheet = CssUtil.getStylesheet(clazz, name);
        if (stylesheet != null) {
            stylesheets.add(stylesheet);
        }

        final String[] additionalStylesheetsByAnnotation = CssUtil.getAdditionalStylesheetsByAnnotation(clazz);
        for (final String additionalStylesheetByAnnotation : additionalStylesheetsByAnnotation) {
            final String additionalStylesheet = CssUtil.getStylesheet(clazz, additionalStylesheetByAnnotation);
            if (additionalStylesheet == null) {
                throw new ApplicationInitializationException("No stylesheet found for \"" + additionalStylesheetByAnnotation + "\"!");
            }
            stylesheets.add(additionalStylesheet);
        }
        return stylesheets;
    }

    private static String getStylesheet(final Class<?> clazz, final String name) {
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

    private static String[] getAdditionalStylesheetsByAnnotation(final Class<?> clazz) {
        final AdditionalStylesheets annotation = clazz.getAnnotation(AdditionalStylesheets.class);
        if (annotation != null) {
            return annotation.value();
        }
        return new String[0];
    }
}
