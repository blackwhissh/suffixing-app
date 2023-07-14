package copymove;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;

public class MoveFile extends FileRenamer{

    protected MoveFile(String suffix) {
        super(suffix);
    }

    @Override
    protected void processFile(Path file, String name) throws IOException {
        String pathSlash = file.toString().replace('\\','/');

        Path moveTo = file.resolveSibling(name);
        Files.move(file, moveTo);

        String moveToSlash = moveTo.toString().replace('\\','/');
        logger.log(Level.INFO, pathSlash + " => " + moveToSlash);
    }
}
