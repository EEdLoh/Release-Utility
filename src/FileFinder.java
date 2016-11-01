import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;

/**
 * FileFinder is used to walk a file tree, match file paths to a string, and add them to a HashSet that can be used
 * later.
 * <p>
 * Created by elohmar on 8/26/2016.
 */
class FileFinder extends SimpleFileVisitor<Path> {

    private PathMatcher fileMatcher;
    private HashSet<Path> result = new HashSet<>();

    FileFinder(String string) {
        this.setFileMatcher(string);
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        System.out.println(file.toString());
        System.out.println(fileMatcher.matches(file));
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

    HashSet<Path> getResult() {
        return result;
    }

    private void setFileMatcher(String string) {
        this.fileMatcher = FileSystems.getDefault().getPathMatcher(string);
    }
}
