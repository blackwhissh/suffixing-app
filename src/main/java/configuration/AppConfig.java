package configuration;

import java.nio.file.Path;
import java.util.Set;

public interface AppConfig {
    Mode getMode();
    String getSuffix();
    Set<Path> getFiles();
}
