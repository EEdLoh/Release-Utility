import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;

/**
 * MainScreen sets the GUI for the main screen of this application.
 * <p>
 * It has the P/N field, settings button, source path field, file list boxes, and the go button.
 * <p>
 * Created by elohmar on 11/1/2016.
 */
class MainScreen extends JPanel implements ActionListener {

    private JTextField partNoText, sourceText;
    private JButton sourceButton, settingsButton;

    MainScreen() {

        //Part Number Text Field
        JLabel partNoLabel = new JLabel("Part Number");
        partNoText = new JTextField();

        //Source Folder Text Field with a Browse Button
        JLabel sourceLabel = new JLabel("Source Directory");
        sourceText = new JTextField();
        sourceButton = new JButton("Browse");
        sourceButton.addActionListener(this);

        //Settings Button Setup
        settingsButton = new JButton("Options");
        settingsButton.addActionListener(this);

        ////// Layout //////

        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        ////// Column 1 //////
        gc.anchor = GridBagConstraints.LAST_LINE_START;
        gc.insets.set(15, 15, 0, 0);
        gc.weightx = .5;
        gc.weighty = .1;
        gc.gridx = 0;
        gc.gridy = 0;
        add(partNoLabel, gc);

        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets.set(0, 15, 0, 0);
        gc.gridx = 0;
        gc.gridy = 1;
        gc.fill = GridBagConstraints.HORIZONTAL;
        add(partNoText, gc);

        gc.anchor = GridBagConstraints.LAST_LINE_START;
        gc.insets.set(15, 15, 0, 0);
        gc.gridx = 0;
        gc.gridy = 2;
        gc.fill = GridBagConstraints.NONE;
        add(sourceLabel, gc);

        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets.set(0, 15, 0, 0);
        gc.gridwidth = 2;
        gc.gridx = 0;
        gc.gridy = 3;
        gc.fill = GridBagConstraints.HORIZONTAL;
        add(sourceText, gc);

        ////// Column 2 //////
        gc.anchor = GridBagConstraints.CENTER;
        gc.weightx = 1;
        gc.insets.set(0, 0, 0, 0);
        gc.gridwidth = 1;
        gc.gridx = 1;
        gc.gridy = 1;
        gc.fill = GridBagConstraints.NONE;
        add(new JPanel(), gc);

        ////// Column 3 //////
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.weightx = .1;
        gc.gridx = 2;
        gc.gridy = 3;
        add(sourceButton, gc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == settingsButton) {
            FolderEntry folderEntry = new FolderEntry("Settings", Dialog.ModalityType.APPLICATION_MODAL);
            folderEntry.setVisible(true);
        } else if (e.getSource() == sourceButton) {
            int returnVal = ReleaseUtility.getFc().showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                Path path = ReleaseUtility.getFc().getSelectedFile().toPath();
                ReleaseUtility.setSource(path);
                sourceText.setText(path.toString());
            }
        }
    }
}
