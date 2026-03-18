package utils;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import net.thucydides.model.util.EnvironmentVariables;

public class ConfigReader {
    private static EnvironmentVariables environmentVariables =
            Serenity.environmentVariables();

    public static String getProperty(String key) {
        return EnvironmentSpecificConfiguration
                .from(environmentVariables)
                .getProperty(key);
    }
    public static int getIntProperty(String key) {
        return Integer.parseInt(getProperty(key));
    }

}
