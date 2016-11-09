import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 * FileFinder is used to walk a file tree, match file paths to a string, and add them to a HashSet that can be used
 * later.
 * <p>
 * Created by elohmar on 8/26/2016.
 */
class FileFinder extends SimpleFileVisitor<Path> {

    private PathMatcher fileMatcher;
    private List<DrawingFile> result = new ArrayList<>();

    FileFinder(String string) {
        this.setFileMatcher(string);
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (fileMatcher.matches(file)) {
            result.add(new DrawingFile(file));
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

    List<DrawingFile> getResult() {
        return result;
    }

    private void setFileMatcher(String string) {
        this.fileMatcher = FileSystems.getDefault().getPathMatcher(string);
    }
}
