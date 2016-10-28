import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;

/**
 * Created by elohmar on 8/26/2016.
 */
public class FileFinder extends SimpleFileVisitor<Path> {

    private PathMatcher fileMatcher;
    private HashSet<Path> result = new HashSet<>();

    public FileFinder(String string) {
        this.setFileMatcher(string);
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (fileMatcher.matches(file)) {
            result.add(file);
            return FileVisitResult.CONTINUE;
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file,
                                           IOException exc) {
        System.err.println(exc);
        return FileVisitResult.CONTINUE;
    }

    public HashSet<Path> getResult() {
        return result;
    }

    private void setFileMatcher(String string) {
        this.fileMatcher = FileSystems.getDefault().getPathMatcher(string);
    }
}
