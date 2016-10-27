import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by elohmar on 10/26/2016.
 */
public class FolderEntry extends JPanel implements ActionListener {

    JLabel partNoLabel, targetLabel, destLabel;
    JTextField partNoText, targetText, destText;
    JButton targetButton, destButton;
    JFileChooser fc;

    public FolderEntry() {

        fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        //Part Number Text Field
        partNoLabel = new JLabel("Part Number");
        partNoText = new JTextField(10);

        //Target Folder Text Field with a Browse Button
        targetLabel = new JLabel("Target Directory");
        targetText = new JTextField(25);
        targetButton = new JButton("Browse");
        targetButton.addActionListener(this);

        //Destination Folder Text Field and Browse Button
        destLabel = new JLabel("Destination Directory");
        destText = new JTextField(25);
        destButton = new JButton("Browse");
        destButton.addActionListener(this);


        setLayout(new GridBagLayout());

        GridBagConstraints gcon = new GridBagConstraints();

        ////// First Column //////

        gcon.anchor = GridBagConstraints.LINE_START;
        gcon.gridx = 0;
        gcon.gridy = 0;
        add(partNoLabel, gcon);

        gcon.gridx = 0;
        gcon.gridy = 1;
        add(partNoText, gcon);

        gcon.insets = new Insets(15, 0, 0, 0);
        gcon.gridx = 0;
        gcon.gridy = 3;
        add(targetLabel, gcon);

        gcon.insets = new Insets(0, 0, 0, 0);
        gcon.gridx = 0;
        gcon.gridy = 4;
        add(targetText, gcon);

        ////// Second Column //////

        gcon.gridx = 1;
        gcon.gridy = 4;
        add(targetButton, gcon);

        ////// Third Column //////

        gcon.insets = new Insets(15, 30, 0, 0);
        gcon.gridx = 2;
        gcon.gridy = 3;
        add(destLabel, gcon);


        gcon.insets = new Insets(0, 30, 0, 0);
        gcon.gridx = 2;
        gcon.gridy = 4;
        add(destText, gcon);

        ////// Fourth Column //////

        gcon.insets = new Insets(0, 0, 0, 0);
        gcon.gridx = 3;
        gcon.gridy = 4;
        add(destButton, gcon);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        int returnVal = fc.showOpenDialog(FolderEntry.this);
        if (returnVal == JFileChooser.APPROVE_OPTION){
            File file = fc.getSelectedFile();
            if (e.getSource() == targetButton) {
                targetText.setText(file.toString());
            } else if (e.getSource() == destButton) {
                destText.setText(file.toString());
            }
        }
    }
}
