import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;

/**
 * Created by elohmar on 10/26/2016.
 */
public class FilterEntry extends JPanel implements ActionListener {

    JLabel partNoLabel, targetLabel, destLabel;
    JTextField partNoText, targetText, destText;
    JButton targetButton, destButton;
    JFileChooser fc;

    public FilterEntry() {

        fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        //Part Number Text Field
        partNoLabel = new JLabel("Part Number");
        partNoText = new JTextField();

        //Target Folder Text Field with a Browse Button
        targetLabel = new JLabel("Target Directory");
        targetText = new JTextField();
        targetButton = new JButton("Browse");
        targetButton.addActionListener(this);

        //Destination Folder Text Field and Browse Button
        destLabel = new JLabel("Target Directory");
        destText = new JTextField();
        destButton = new JButton("Browse");
        destButton.addActionListener(this);


        setLayout(new GridBagLayout());

        GridBagConstraints gcon = new GridBagConstraints();

        gcon.gridx = 0;
        gcon.gridy = 0;
        add(targetButton, gcon);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        int returnVal = fc.showOpenDialog(FilterEntry.this);
        if (returnVal == JFileChooser.APPROVE_OPTION){
            File file = fc.getSelectedFile();
            System.out.println(file.getAbsoluteFile());
        }
    }
}
