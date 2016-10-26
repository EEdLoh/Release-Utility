import javax.swing.*;

/**
 * Created by elohmar on 8/23/2016.
 */
public class ReleaseUtility {

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Release Assistant");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(new FilterEntry());

        frame.pack();
        frame.setSize(400, 200);
        frame.setVisible(true);
    }
    public static void main(String[] args) { SwingUtilities.invokeLater(() -> createAndShowGUI());


    }
}