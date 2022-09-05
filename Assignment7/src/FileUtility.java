import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

public class FileUtility {

    private static final int PATH_LENGTH = 250;

    private FileUtility(){}

    public static List<File> searchByName(File directory, final String name){
        return search(directory,name,FileUtility::searchByNamePredicate);

    }

    public static List<File> searchByContent(File directory, final String content){
        return search(directory, content,FileUtility::searchByContentPredicate);
    }

    private static List<File> search(File directory, String searchParameter, BiPredicate<Path,String> predicate){
        if(!directory.isDirectory() || !directory.exists() || !directory.canRead()){
            return null;
        }
        try{
            Path path = directory.toPath();
            List<File> files = Files
                    .find(path,PATH_LENGTH,(p,attributes) -> true)
                    .parallel()
                    .filter(p -> predicate.test(p, searchParameter))
                    .map(Path::toFile)
                    .filter(File::isFile).collect(Collectors.toList());
            return files;
        }catch (Exception e){
            System.err.println("Oooops...");
        }
        return null;
    }

    private static boolean searchByNamePredicate(Path path, String name){
        File file = path.toFile();
        if(file.isDirectory()){
            return true;
        }
        String filename = file.getName();
        return filename.contains(name);
    }

    private static boolean searchByContentPredicate(Path path, String content){
        try{
            File file = path.toFile();
            if(file.isDirectory()){
                return true;
            }
            InputStream input = new FileInputStream(path.toFile());
            return FileContentUtility.contains(input,content,(int)file.length());
        }catch (Exception e){
            System.err.println("Oooops...");
        }
        return false;
    }
}