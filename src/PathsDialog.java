import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by elohmar on 1/13/2017.
 */
public class PathsDialog extends JDialog {

    JTextField textField;

    PathsDialog(String title, Dialog.ModalityType modalityType, ArrayList<String> textList) {
        super.setTitle(title);
        super.setModalityType(modalityType);

        this.setMinimumSize(new Dimension(500, 200));

        JLabel label = new JLabel("Released Drawing Paths:");
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));

        this.add(label);
        for (String string : textList) {
            textField = new JTextField(string);
            textField.setEditable(false);
            this.add(textField);
        }

    }
}
