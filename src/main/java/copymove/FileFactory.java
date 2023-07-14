package copymove;

import configuration.Mode;

public class FileFactory {
    private FileFactory(){

    }

    public static FileRenamer createFileRenamer(Mode mode, String suffix){
        if(!suffix.isEmpty()){
            if(mode == Mode.COPY){
                return new CopyFile(suffix);
            }else if(mode == Mode.MOVE){
                return new MoveFile(suffix);
            }else{
                throw new IllegalArgumentException("Invalid mode: + " + mode);
            }

        }else {
            throw new IllegalArgumentException("Suffix is non-null value");
        }
    }
}
