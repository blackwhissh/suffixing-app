import configuration.AppConfig;
import configuration.AppConfigLoad;
import copymove.FileFactory;
import copymove.FileRenamer;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        LOGGER.log(Level.INFO,"Starting with argument: {0}", Arrays.toString(args));
        try{
            AppConfig config = AppConfigLoad.loadFromFile(Path.of(args[0]));
            FileRenamer renamer = FileFactory.createFileRenamer(config.getMode(), config.getSuffix());
            for(Path file : config.getFiles()){
                renameFile(renamer,file);
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to configure file");
        }

    }

    private static void renameFile(FileRenamer renamer, Path file) throws IOException {
        renamer.rename(file);
    }
}
