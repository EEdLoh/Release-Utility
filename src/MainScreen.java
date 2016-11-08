import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * MainScreen sets the GUI for the main screen of this application.
 * <p>
 * It has the P/N field, settings button, source path field, file list boxes, and the go button.
 * <p>
 * Created by elohmar on 11/1/2016.
 */
class MainScreen extends JPanel implements ActionListener {

    private static JCheckBox clearCNCBox, releaseDrawingBox, archiveDrawingBox;
    private JTextField partNoText, sourceText;
    private JButton sourceButton, settingsButton, findButton, testButton;
    private JTable sourceTable, releasedTable, archiveTable, cncTable;
    private FileListTableModel sourceModel, releasedModel, archiveModel, cncModel;

    MainScreen() {

        //Part Number Text Field
        JLabel partNoLabel = new JLabel("Part Number");
        partNoText = new JTextField();

        findButton = new JButton("Find");
        findButton.addActionListener(this);

        //Source Folder Text Field with a Browse Button
        JLabel sourceLabel = new JLabel("Source Directory");
        sourceText = new JTextField();
        sourceButton = new JButton("Browse");
        sourceButton.addActionListener(this);

        //Settings Button Setup
        settingsButton = new JButton("Options");
        settingsButton.addActionListener(this);

        //Check Box panel and Check Boxes Setup
        releaseDrawingBox = new JCheckBox("Release Drawings", true);
        archiveDrawingBox = new JCheckBox("Archive Drawings", true);
        clearCNCBox = new JCheckBox("Clear CNC Folder", true);

        //Test Button
        testButton = new JButton("Test");
        testButton.addActionListener(this);

        //File List Boxes and Container
        JPanel tableContainer = new JPanel();

        sourceModel = new FileListTableModel(new ArrayList<>());
        sourceTable = new JTable(sourceModel);
        sourceTable.setFillsViewportHeight(true);
        JScrollPane sourceScrollPane = new JScrollPane(sourceTable);

        releasedModel = new FileListTableModel(new ArrayList<>());
        releasedTable = new JTable(releasedModel);
        releasedTable.setFillsViewportHeight(true);
        JScrollPane releasedScrollPane = new JScrollPane(releasedTable);

        archiveModel = new FileListTableModel(new ArrayList<>());
        archiveTable = new JTable(archiveModel);
        archiveTable.setFillsViewportHeight(true);
        JScrollPane archiveScrollPane = new JScrollPane(archiveTable);

        cncModel = new FileListTableModel(new ArrayList<>());
        cncTable = new JTable(cncModel);
        cncTable.setFillsViewportHeight(true);
        JScrollPane cncScrollPane = new JScrollPane(cncTable);

        tableContainer.setLayout(new GridBagLayout());
        GridBagConstraints gcon1 = new GridBagConstraints();
        tableContainer.setPreferredSize(new Dimension(250, 220));

        gcon1.anchor = GridBagConstraints.LINE_START;
        gcon1.fill = GridBagConstraints.BOTH;
        gcon1.gridx = 0;
        gcon1.gridy = 0;
        gcon1.weightx = 1;
        gcon1.weighty = 1;
        tableContainer.add(sourceScrollPane, gcon1);

        gcon1.gridx = 0;
        gcon1.gridy = 1;
        tableContainer.add(releasedScrollPane, gcon1);

        gcon1.anchor = GridBagConstraints.LINE_END;
        gcon1.gridx = 1;
        gcon1.gridy = 0;
        tableContainer.add(cncScrollPane, gcon1);

        gcon1.gridx = 1;
        gcon1.gridy = 1;
        tableContainer.add(archiveScrollPane, gcon1);

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

        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridwidth = 1;
        gc.gridx = 0;
        gc.gridy = 4;
        gc.fill = GridBagConstraints.NONE;
        add(releaseDrawingBox, gc);

        gc.gridx = 0;
        gc.gridy = 5;
        add(archiveDrawingBox, gc);

        gc.gridx = 0;
        gc.gridy = 6;
        add(clearCNCBox, gc);

        ////// Column 2 //////
        // gc.anchor = GridBagConstraints.CENTER;
        gc.weightx = 1;
        gc.insets.set(0, 0, 0, 0);
        gc.gridwidth = 1;
        gc.gridx = 1;
        gc.gridy = 1;
        gc.fill = GridBagConstraints.NONE;
        add(findButton, gc);

        ////// Column 3 //////
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.weightx = .1;
        gc.gridx = 2;
        gc.gridy = 3;
        add(sourceButton, gc);

        gc.gridx = 2;
        gc.gridy = 5;
        add(settingsButton, gc);

        ////// File Boxes //////
        gc.fill = GridBagConstraints.BOTH;
        gc.gridwidth = GridBagConstraints.REMAINDER;
        gc.weighty = 1;
        gc.gridx = 0;
        gc.gridy = 7;
        add(tableContainer, gc);

        gc.fill = GridBagConstraints.NONE;
        gc.gridwidth = 1;
        gc.gridy = 8;
        add(testButton, gc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == settingsButton) {
            FolderEntry folderEntry = new FolderEntry("Settings", Dialog.ModalityType.APPLICATION_MODAL);
            folderEntry.setVisible(true);

        } else if (e.getSource() == findButton) {
            if (!partNoText.getText().equals("") && !ReleaseUtility.getSource().toString().equals("")) {
                System.out.println("Testing");
                String pnMatch = "glob:**" + partNoText.getText() + "[, ]*";
                System.out.println("Looking For: " + partNoText.getText());

                FileFinder fileFinder = new FileFinder(pnMatch);
                try {
                    Files.walkFileTree(ReleaseUtility.getSource(), fileFinder);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                for (DrawingFile file : fileFinder.getResult()) {
                    System.out.println(file.toString());
                }
                sourceModel.setData(fileFinder.getResult());
            }
        } else if (e.getSource() == sourceButton) {
            int returnVal = ReleaseUtility.getFc().showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                Path path = ReleaseUtility.getFc().getSelectedFile().toPath();
                ReleaseUtility.setSource(path);
                sourceText.setText(path.toString());
            }
        } else if (e.getSource() == testButton) {
            System.out.println(sourceTable.getModel());
        }
    }
}
