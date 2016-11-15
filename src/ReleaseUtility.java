import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
class ReleaseUtility {

    private static final Path saveFile =
            FileSystemView.getFileSystemView().getHomeDirectory()
                    .toPath().resolve("..\\Documents\\Release Utility Directories.txt").normalize();
    private static final JFileChooser fc = new JFileChooser();
    private static final String releasedString = "released: ", archiveString = "archive: ", cncString = "cnc: ";
    private static Path released, archive, cnc, source;

    private static String pn;

    static String getPn() {
        return pn;
    }

    static void setPn(String pn) {
        ReleaseUtility.pn = pn;
    }

    static String getReleasedString() {
        return releasedString;
    }

    static String getArchiveString() {
        return archiveString;
    }

    static String getCncString() {
        return cncString;
    }

    static Path getSaveFile() {
        return saveFile;
    }

    static Path getReleased() {
        return released;
    }

    static void setReleased(Path released) {
        ReleaseUtility.released = released;
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

    static Path getSource() {
        return source;
    }

    static void setSource(Path source) {
        ReleaseUtility.source = source;
    }

    static JFileChooser getFc() {
        return fc;
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Release Assistant");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Dimension minSize = new Dimension(600, 220);
        frame.setMinimumSize(minSize);

        frame.add(new MainScreen());

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {

        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        if (Files.exists(ReleaseUtility.saveFile)) {
            Pattern releasedPattern = Pattern.compile(releasedString + ".*");
            Pattern archivePattern = Pattern.compile(archiveString + ".*");
            Pattern cncPattern = Pattern.compile(cncString + ".*");

            try (BufferedReader reader = newBufferedReader(ReleaseUtility.saveFile)) {
                String l;
                while ((l = reader.readLine()) != null) {
                    Matcher releasedMatcher = releasedPattern.matcher(l);
                    Matcher archiveMatcher = archivePattern.matcher(l);
                    Matcher cncMatcher = cncPattern.matcher(l);

                    if (releasedMatcher.matches()) {
                        setReleased(Paths.get(l.substring(releasedString.length())));
                    } else if (archiveMatcher.matches()) {
                        setArchive(Paths.get(l.substring(archiveString.length())));
                    } else if (cncMatcher.matches()) {
                        setCnc(Paths.get(l.substring(cncString.length())));
                    }
                }
            } catch (FileNotFoundException f) {
                System.out.println("File Not Found");
                f.printStackTrace();
            } catch (IOException e) {
                System.out.println("IO Exception");
                e.printStackTrace();
            }
        } else {
            try (BufferedWriter writer = Files.newBufferedWriter(ReleaseUtility.getSaveFile())) {
                writer.write(releasedString + "\\\\brain\\data\\Drawings Released");
                ReleaseUtility.setReleased(Paths.get("\\\\brain\\data\\Drawings Released"));
                writer.newLine();

                writer.write(archiveString + "\\\\brain\\data\\Drawings Archive");
                ReleaseUtility.setArchive(Paths.get("\\\\brain\\data\\Drawings Archive"));
                writer.newLine();

                writer.write(cncString + "\\\\brain\\data\\Velocity Metal Works\\CNC");
                ReleaseUtility.setCnc(Paths.get("\\\\brain\\data\\Velocity Metal Works\\CNC"));
                writer.newLine();

            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        SwingUtilities.invokeLater(ReleaseUtility::createAndShowGUI);

    }

}