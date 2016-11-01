import javax.swing.*;
import java.awt.*;

/**
 * ReleaseUtility is the class that creates the main page for the Release Utility Application. The application is to be used by the Engineers at Linkup International LLC in order to simplify the proper movement and deletion of controlled documents in the computer system. It does this by finding PDF and DXF files in a number source folders and moves the files to the proper destinations or deletes them when appropriate.
 * Created by elohmar on 8/23/2016.
 */
public class ReleaseUtility {

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Release Assistant");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension minSize = new Dimension(775, 150);
        frame.setMinimumSize(minSize);

        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        frame.add(new FolderEntry());

        frame.pack();
        frame.setVisible(true);
    }
    public static void main(String[] args) { SwingUtilities.invokeLater(() -> createAndShowGUI());


    }
}