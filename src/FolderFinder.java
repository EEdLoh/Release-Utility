import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Created by elohmar on 8/26/2016.
 */
public class FolderFinder extends SimpleFileVisitor<Path> {

    PathMatcher folderMatcher = FileSystems.getDefault().getPathMatcher("glob:**ncfiles");
    Path result;

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (folderMatcher.matches(file)){
            result = file;
            return FileVisitResult.TERMINATE;
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file,
                                           IOException exc) {
        System.err.println(exc);
        return FileVisitResult.CONTINUE;
    }
}
