package copymove;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;

public class CopyFile extends FileRenamer{
    protected CopyFile(String suffix) {
        super(suffix);
    }

    @Override
    protected void processFile(Path file, String name) throws IOException {
        String pathSlash = file.toString().replace('\\','/');


        Path copyTo = file.resolveSibling(name);
        Files.copy(file, copyTo);

        String copyToSlash = copyTo.toString().replace('\\','/');
        logger.log(Level.INFO, pathSlash + " -> " + copyToSlash);
    }
}
