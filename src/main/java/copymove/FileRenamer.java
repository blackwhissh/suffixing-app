package copymove;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class FileRenamer {
    protected final Logger logger;
    protected final String suffix;

    protected FileRenamer(String suffix){
        this.suffix = suffix;
        logger = Logger.getLogger(getClass().getName());
    }

    public final void rename(Path file) throws IOException {
        if (Files.exists(file, LinkOption.NOFOLLOW_LINKS)){
            processFile(file, getFileName(file.getFileName().toString(), suffix));
        }else{
            String pathSlash = file.toString().replace('\\', '/');
            logger.log(Level.SEVERE,"No such file: {0}", pathSlash);
        }
    }

    protected String getFileName(String name, String suffix){
        int lastDotIndex = name.lastIndexOf('.');
        return name.substring(0,lastDotIndex) + suffix + name.substring(lastDotIndex);
    }

    protected abstract void processFile(Path file, String name) throws IOException;
}
