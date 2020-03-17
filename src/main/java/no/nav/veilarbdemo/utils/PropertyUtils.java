package no.nav.veilarbdemo.utils;

import java.util.List;
import java.util.Properties;

import static no.nav.sbl.util.EnvironmentUtils.getRequiredProperty;

public class PropertyUtils {

    public static final String DEFAULT_APP_ENV_PROPERTY_PREFIX = "app.env";

    public static final String DEFAULT_APP_SECRET_PROPERTY_PREFIX = "app.secret";

    public static void addPropertiesFromEnv(List<String> envVariableNames, Properties properties) {
        addPropertiesFromEnv(envVariableNames, properties, DEFAULT_APP_ENV_PROPERTY_PREFIX);
    }

    public static void addPropertiesFromEnv(List<String> envVariableNames, Properties properties, String propertyPrefix) {
        String normalizedPrefix = normalizePropertyPrefix(propertyPrefix);
        envVariableNames.forEach((name) -> {
            properties.setProperty(String.join(".", normalizedPrefix, name), getRequiredProperty(name));
        });
    }

    public static void addPropertiesFromFile(String filePath, Properties properties) {
        // TODO: Use NaisUtils or something
    }

    public static String normalizePropertyPrefix(String propertyPrefix) {
        if (propertyPrefix.endsWith(".")) {
            return propertyPrefix.substring(0, propertyPrefix.length() - 1);
        }

        return propertyPrefix;
    }

}
