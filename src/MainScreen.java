import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * MainScreen sets the GUI for the main screen of this application.
 * <p>
 * It has the P/N field, settings button, source path field, file list boxes, and the go button.
 * <p>
 * Created by elohmar on 11/1/2016.
 */
class MainScreen extends JPanel implements ActionListener {

    private JLabel partNoLabel, sourceLabel;
    private JTextField partNoText, sourceText;
    private JButton sourceButton, settingsButton;
    private FolderEntry folderEntry;

    MainScreen() {

        //Part Number Text Field
        partNoLabel = new JLabel("Part Number");
        partNoText = new JTextField();

        //Source Folder Text Field with a Browse Button
        sourceLabel = new JLabel("Source Directory");
        sourceText = new JTextField();
        sourceButton = new JButton("Browse");
        sourceButton.addActionListener(this);

        //Settings Button Setup
        settingsButton = new JButton("Options");
        settingsButton.addActionListener(this);

        ////// Layout //////

        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        add(settingsButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == settingsButton) {
            folderEntry = new FolderEntry("Settings", Dialog.ModalityType.APPLICATION_MODAL);
            folderEntry.setVisible(true);
        }
    }
}
