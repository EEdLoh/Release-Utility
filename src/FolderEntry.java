import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * FolderEntry is the GUI for a page that includes a set of text boxes that will be used to store Paths to the source
 * and destination directories for the movement of files.
 * <p>
 * Created by elohmar on 10/26/2016.
 */
class FolderEntry extends JPanel implements ActionListener {

    private JLabel releasedLabel, archiveLabel, cncLabel;
    private JTextField releasedText, archiveText, cncText;
    private JButton releasedButton, archiveButton, cncButton, saveButton;
    private JFileChooser fc;

    FolderEntry() {

        fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        //Drawings Released Folder Text Field and Browse Button
        releasedLabel = new JLabel("Drawings Released Directory");
        releasedText = new JTextField();
        releasedButton = new JButton("Browse");
        releasedButton.addActionListener(this);

        //Drawings Archive Folder Text Field and Browse Button
        archiveLabel = new JLabel("Drawings Archive Directory");
        archiveText = new JTextField();
        archiveButton = new JButton("Browse");
        archiveButton.addActionListener(this);

        //CNC Folder Text Field and Browse Button
        cncLabel = new JLabel("CNC Directory");
        cncText = new JTextField();
        cncButton = new JButton("Browse");
        cncButton.addActionListener(this);

        saveButton = new JButton("Save");
        saveButton.addActionListener(this);

        setLayout(new GridBagLayout());

        GridBagConstraints gcon = new GridBagConstraints();

        ////// First Column //////
        gcon.anchor = GridBagConstraints.LAST_LINE_START;
        gcon.weightx = 1;
        gcon.insets = new Insets(15, 15, 0, 0);
        gcon.gridx = 0;
        gcon.gridy = 0;
        add(releasedLabel, gcon);

        gcon.fill = GridBagConstraints.HORIZONTAL;
        gcon.insets.set(0, 15, 0, 0);
        gcon.gridx = 0;
        gcon.gridy = 1;
        add(releasedText, gcon);

        gcon.fill = GridBagConstraints.NONE;
        gcon.insets.set(15, 15, 0, 0);
        gcon.weightx = 1;
        gcon.gridx = 0;
        gcon.gridy = 2;
        add(archiveLabel, gcon);

        gcon.fill = GridBagConstraints.HORIZONTAL;
        gcon.insets.set(0, 15, 0, 0);
        gcon.gridx = 0;
        gcon.gridy = 3;
        add(archiveText, gcon);

        ////// Second Column //////
        gcon.fill = GridBagConstraints.NONE;
        gcon.insets.set(0, 0, 0, 0);
        gcon.weightx = .25;
        gcon.gridx = 1;
        gcon.gridy = 1;
        add(releasedButton, gcon);

        gcon.gridx = 1;
        gcon.gridy = 3;
        add(archiveButton, gcon);

        ////// Third Column //////
        gcon.anchor = GridBagConstraints.LAST_LINE_START;
        gcon.insets.set(15, 15, 0, 0);
        gcon.weightx = 1;
        gcon.gridx = 2;
        gcon.gridy = 0;
        add(cncLabel, gcon);

        gcon.fill = GridBagConstraints.HORIZONTAL;
        gcon.insets.set(0, 15, 0, 0);
        gcon.gridx = 2;
        gcon.gridy = 1;
        add(cncText, gcon);

        ////// Fourth Column //////
        gcon.fill = GridBagConstraints.NONE;
        gcon.insets.set(0, 0, 0, 0);
        gcon.weightx = .25;
        gcon.gridx = 3;
        gcon.gridy = 1;
        add(cncButton, gcon);

        ////// Fifth Column //////
        gcon.fill = GridBagConstraints.NONE;
        gcon.gridx = 3;
        gcon.gridy = 3;
        add(saveButton, gcon);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {

        } else {
            int returnVal = fc.showOpenDialog(FolderEntry.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                if (e.getSource() == releasedButton) {
                    releasedText.setText(file.toString());
                } else if (e.getSource() == archiveButton) {
                    archiveText.setText(file.toString());
                } else if (e.getSource() == cncButton) {
                    cncText.setText(file.toString());
                }
            }
        }
    }
}


