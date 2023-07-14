package configuration;

import exception.ConfigurationException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class AppConfigLoad {
    private static final String MODE = "mode";
    private static final String SUFFIX = "suffix";
    private static final String FILES = "files";
    private static final Logger LOGGER = Logger.getLogger(AppConfigLoad.class.getName());

    private AppConfigLoad(){

    }

    private static void validate(Properties properties) {

        boolean valid = false;
        if (Arrays.stream(Mode.values()).noneMatch(value ->
                value.toString().equalsIgnoreCase(properties.getProperty(MODE)))) {
            LOGGER.log(Level.SEVERE, "Mode is not recognized: {0}", properties.get(MODE));
        } else if (Objects.isNull(properties.getProperty(SUFFIX))) {
            LOGGER.log(Level.SEVERE, "No suffix is configured");
        } else if (Objects.isNull(properties.getProperty(FILES)) || properties.getProperty(FILES).isEmpty()) {
            LOGGER.log(Level.WARNING, "No files are configured to be copied/moved");
        } else {
            valid = true;
        }

        if (!valid) {
            throw new RuntimeException("Invalid configuration file");
        }
    }




    private static AppConfig createAppConfig(String mode, String suffix, String[] files){
        return new AppConfig() {
            @Override
            public Mode getMode() {
                return Mode.valueOf(mode.toUpperCase());
            }

            @Override
            public String getSuffix() {
                return suffix;
            }

            @Override
            public Set<Path> getFiles() {
                return Arrays.stream(files).map(Path::of).collect(Collectors.toSet());
            }
        };
    }

    public static AppConfig loadFromFile(Path file) throws IOException {
        Properties properties = loadPropertiesFromFile(file);
        validate(properties);
        return createAppConfig(properties.getProperty(MODE), properties.getProperty(SUFFIX),properties.getProperty(FILES).split(":"));
    }

    private static Properties loadPropertiesFromFile(Path file) throws IOException {
        Properties properties = new Properties();
        InputStream inputStream = new FileInputStream(file.toString());
        properties.load(inputStream);
        return properties;
    }

}
