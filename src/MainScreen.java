import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * MainScreen sets the GUI for the main screen of this application.
 * <p>
 * It has the P/N field, settings button, source path field, file list boxes, and the go button.
 * <p>
 * Created by elohmar on 11/1/2016.
 */
class MainScreen implements ActionListener {

    private JLabel partNoLabel, sourceLabel;
    private JTextField partNoText, sourceText;
    private JButton sourceButton;

    MainScreen() {

        //Part Number Text Field
        partNoLabel = new JLabel("Part Number");
        partNoText = new JTextField();

        //Source Folder Text Field with a Browse Button
        sourceLabel = new JLabel("Source Directory");
        sourceText = new JTextField();
        sourceButton = new JButton("Browse");
        sourceButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
