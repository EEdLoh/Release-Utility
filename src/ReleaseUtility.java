import java.io.IOException;
import java.nio.file.*;
import java.util.EnumSet;
import java.util.HashSet;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 * Created by elohmar on 8/23/2016.
 */
public class ReleaseUtility {
    public static void main(String[] args) throws IOException {

        Iterable<Path> roots = FileSystems.getDefault().getRootDirectories();
        NCFilesFinder ncFilesFinder = new NCFilesFinder();
        EnumSet<FileVisitOption> opts = EnumSet.of(FileVisitOption.FOLLOW_LINKS);

        for (Path name: roots){
            System.out.println(roots);
            Files.walkFileTree(name, opts, 1, ncFilesFinder);
        }

        Path target = ncFilesFinder.result;

        if(target != null) {
            Path source = Paths.get("\\\\brain\\data\\Velocity Metal Works\\CNC\\Turret Programs");
            HashSet<Path> ncHashSet = new HashSet<>();
            PathMatcher ncMatcher = FileSystems.getDefault().getPathMatcher("glob:**.nc");

            try (DirectoryStream<Path> ncStream = Files.newDirectoryStream(target)) {
                for (Path file : ncStream) {
                    Files.delete(file);
                }
            }

            try (DirectoryStream<Path> stream = Files.newDirectoryStream(source)) {
                for (Path file : stream) {
                    if (ncMatcher.matches(file)) {
                        ncHashSet.add(file);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            for (Path file : ncHashSet) {
                String filename = file.getFileName().toString();
                Path destination = Paths.get(target.toString() + "\\" + filename);

                try {
                    Files.copy(file, destination, REPLACE_EXISTING);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}