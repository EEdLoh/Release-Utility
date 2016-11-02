import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.Files.newBufferedReader;

/**
 * ReleaseUtility is the class that defines the JFrame for the app window and calls the appropriate displays. It also
 * holds any variables and methods that are used in multiple areas of the app.
 * <p>
 * The application is to be used by the Engineers at Linkup International LLC in order to simplify the proper movement
 * and deletion of controlled documents in the computer system.
 * <p>
 * It does this by finding PDF and DXF files in a number source folders and moves the files to the proper destinations
 * or deletes them when appropriate.
 * <p>
 * Created by elohmar on 8/23/2016.
 */
public class ReleaseUtility {

    private static final Path saveFile =
            FileSystemView.getFileSystemView().getHomeDirectory()
                    .toPath().resolve("..\\Documents\\Release Utility Directories.txt").normalize();

    private static Path released, archive, cnc;

    static Path getSaveFile() {
        return saveFile;
    }

    static Path getReleased() {
        return released;
    }

    static void setReleased(Path path) {
        released = path;
    }

    static Path getArchive() {
        return archive;
    }

    static void setArchive(Path archive) {
        ReleaseUtility.archive = archive;
    }

    static Path getCnc() {
        return cnc;
    }

    static void setCnc(Path cnc) {
        ReleaseUtility.cnc = cnc;
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Release Assistant");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Dimension minSize = new Dimension(775, 200);
        frame.setMinimumSize(minSize);

        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        frame.add(new FolderEntry());

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        if (Files.exists(ReleaseUtility.saveFile)) {
            try (BufferedReader reader = newBufferedReader(ReleaseUtility.saveFile)) {
                released = Paths.get(reader.readLine());
                archive = Paths.get(reader.readLine());
                cnc = Paths.get(reader.readLine());
            } catch (FileNotFoundException f) {
                System.out.println("File Not Found");
                f.printStackTrace();
            } catch (IOException e) {
                System.out.println("IO Exception");
                e.printStackTrace();
            }
        }

        SwingUtilities.invokeLater(ReleaseUtility::createAndShowGUI);

    }

}