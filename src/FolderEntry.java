import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 * FolderEntry is the GUI for a page that includes a set of text boxes that will be used to store Paths to the source and destination directories for the movement of files.
 * Created by elohmar on 10/26/2016.
 */
public class FolderEntry extends JPanel implements ActionListener {

    JLabel partNoLabel, sourceLabel, destLabel;
    JTextField partNoText, sourceText, destText;
    JButton sourceButton, destButton, moveButton;
    JFileChooser fc;
    FileFinder fileFinder;

    public FolderEntry() {

        fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        //Part Number Text Field
        partNoLabel = new JLabel("Part Number");
        partNoText = new JTextField();

        //Source Folder Text Field with a Browse Button
        sourceLabel = new JLabel("Source Directory");
        sourceText = new JTextField();
        sourceButton = new JButton("Browse");
        sourceButton.addActionListener(this);

        //Destination Folder Text Field and Browse Button
        destLabel = new JLabel("Destination Directory");
        destText = new JTextField();
        destButton = new JButton("Browse");
        destButton.addActionListener(this);

        moveButton = new JButton("Move");
        moveButton.addActionListener(this);

        setLayout(new GridBagLayout());

        GridBagConstraints gcon = new GridBagConstraints();

        ////// First Column //////
        gcon.anchor = GridBagConstraints.LAST_LINE_START;
        gcon.weightx = .5;
        gcon.weighty = 1;
        gcon.gridx = 0;
        gcon.gridy = 0;
        add(partNoLabel, gcon);

        gcon.fill = GridBagConstraints.HORIZONTAL;
        gcon.gridx = 0;
        gcon.gridy = 1;
        add(partNoText, gcon);

        gcon.fill = GridBagConstraints.NONE;
        gcon.insets = new Insets(15, 0, 0, 0);
        gcon.gridx = 0;
        gcon.gridy = 3;
        add(sourceLabel, gcon);

        gcon.fill = GridBagConstraints.HORIZONTAL;
        gcon.insets = new Insets(0, 0, 0, 0);
        gcon.gridwidth = 2;
        gcon.weightx = 1;
        gcon.gridx = 0;
        gcon.gridy = 4;
        add(sourceText, gcon);

        gcon.fill = GridBagConstraints.NONE;
        gcon.gridwidth = 1;
        gcon.gridx = 0;
        gcon.gridy = 5;
        add(moveButton, gcon);

        ////// Second Column //////
        gcon.fill = GridBagConstraints.NONE;
        gcon.gridwidth = 1;
        gcon.weightx = .5;
        gcon.gridx = 2;
        gcon.gridy = 4;
        add(sourceButton, gcon);

        ////// Third Column //////
        gcon.insets = new Insets(0, 30, 0, 0);
        gcon.gridx = 3;
        gcon.gridy = 3;
        add(destLabel, gcon);

        gcon.fill = GridBagConstraints.HORIZONTAL;
        gcon.gridwidth = 2;
        gcon.weightx = 1;
        gcon.gridx = 3;
        gcon.gridy = 4;
        add(destText, gcon);

        ////// Fourth Column //////
        gcon.fill = GridBagConstraints.NONE;
        gcon.insets = new Insets(0, 0, 0, 0);
        gcon.gridwidth = 1;
        gcon.weightx = .5;
        gcon.gridx = 5;
        gcon.gridy = 4;
        add(destButton, gcon);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        int returnVal = fc.showOpenDialog(FolderEntry.this);
        if (returnVal == JFileChooser.APPROVE_OPTION){
            File file = fc.getSelectedFile();
            if (e.getSource() == sourceButton) {
                sourceText.setText(file.toString());
            } else if (e.getSource() == destButton) {
                destText.setText(file.toString());
            } else if (e.getSource() == moveButton) {
                if (!sourceText.getText().equals("") && !destText.getText().equals("")) {
                    Path source = Paths.get(sourceText.getText());
                    Path dest = Paths.get(destText.getText());

                    System.out.println("Source:" + source.toString());
                    System.out.println("P/N: " + partNoText.getText());

                    String matchString = "glob:**" + partNoText.getText() + "[, ]*";

                    System.out.println("Matching: " + matchString);

                    fileFinder = new FileFinder(matchString);

                    try {
                        Files.walkFileTree(source, fileFinder);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    for (Path path : fileFinder.getResult()) {
                        System.out.println("Moving: " + path.getFileName());
                        Path target = Paths.get(dest.toString() + "\\" + path.getFileName());

                        try {
                            Files.move(path, target, REPLACE_EXISTING);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}


