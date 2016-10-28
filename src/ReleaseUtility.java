import javax.swing.*;
import java.awt.*;

/**
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