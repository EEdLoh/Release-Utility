import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * MainScreen sets the GUI for the main screen of this application.
 * <p>
 * It has the P/N field, settings button, source path field, file list boxes, and the go button.
 * <p>
 * Created by elohmar on 11/1/2016.
 */
class MainScreen extends JPanel implements ActionListener {

    private JCheckBox clearCNCBox, releaseDrawingBox, archiveDrawingBox, releaseCNCBox;
    private JTextField partNoText, sourceText;
    private JButton sourceButton, settingsButton, findButton, testButton;
    private JTable sourceTable, releasedTable, archiveTable, cncTable;

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
        releaseCNCBox = new JCheckBox("Release DXF Files", true);

        //Test Button
        testButton = new JButton("Test");
        testButton.addActionListener(this);

        //File List Boxes and Container
        JPanel tableContainer = new JPanel();

        JLabel sourceTableLabel = new JLabel("Source");
        sourceTable = new JTable(new FileListTableModel(new ArrayList<>()));
        sourceTable.setFillsViewportHeight(true);
        JScrollPane sourceScrollPane = new JScrollPane(sourceTable);
        initColumnSizes(sourceTable);

        JLabel releasedTableLabel = new JLabel("Drawings Released");
        releasedTable = new JTable(new FileListTableModel(new ArrayList<>()));
        releasedTable.setFillsViewportHeight(true);
        JScrollPane releasedScrollPane = new JScrollPane(releasedTable);
        initColumnSizes(releasedTable);

        JLabel archiveTableLabel = new JLabel("Drawings Archive");
        archiveTable = new JTable(new FileListTableModel(new ArrayList<>()));
        archiveTable.setFillsViewportHeight(true);
        JScrollPane archiveScrollPane = new JScrollPane(archiveTable);
        initColumnSizes(archiveTable);

        JLabel cncTableLabel = new JLabel("CNC");
        cncTable = new JTable(new FileListTableModel(new ArrayList<>()));
        cncTable.setFillsViewportHeight(true);
        JScrollPane cncScrollPane = new JScrollPane(cncTable);
        initColumnSizes(cncTable);

        tableContainer.setLayout(new GridBagLayout());
        GridBagConstraints gcon1 = new GridBagConstraints();
        tableContainer.setPreferredSize(new Dimension(250, 220));

        gcon1.anchor = GridBagConstraints.LINE_START;
        gcon1.fill = GridBagConstraints.BOTH;
        gcon1.gridx = 0;
        gcon1.gridy = 1;
        gcon1.weightx = 1;
        gcon1.weighty = 1;
        tableContainer.add(sourceScrollPane, gcon1);

        gcon1.gridx = 0;
        gcon1.gridy = 3;
        tableContainer.add(archiveScrollPane, gcon1);

        gcon1.anchor = GridBagConstraints.LINE_END;
        gcon1.gridx = 1;
        gcon1.gridy = 1;
        tableContainer.add(releasedScrollPane, gcon1);

        gcon1.gridx = 1;
        gcon1.gridy = 3;
        tableContainer.add(cncScrollPane, gcon1);

        gcon1.fill = GridBagConstraints.NONE;
        gcon1.anchor = GridBagConstraints.LAST_LINE_START;
        gcon1.gridx = 0;
        gcon1.gridy = 0;
        gcon1.weighty = .1;
        tableContainer.add(sourceTableLabel, gcon1);

        gcon1.insets.set(15, 0, 0, 0);
        gcon1.gridx = 0;
        gcon1.gridy = 2;
        tableContainer.add(archiveTableLabel, gcon1);

        gcon1.insets.set(0, 0, 0, 0);
        gcon1.gridx = 1;
        gcon1.gridy = 0;
        tableContainer.add(releasedTableLabel, gcon1);

        gcon1.insets.set(15, 0, 0, 0);
        gcon1.gridx = 1;
        gcon1.gridy = 2;
        tableContainer.add(cncTableLabel, gcon1);

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

        ////// Column 2 //////
        // gc.anchor = GridBagConstraints.CENTER;
        gc.weightx = 1;
        gc.insets.set(0, 0, 0, 0);
        gc.gridwidth = 1;
        gc.gridx = 1;
        gc.gridy = 1;
        gc.fill = GridBagConstraints.NONE;
        add(findButton, gc);

        gc.gridx = 1;
        gc.gridy = 4;
        add(releaseCNCBox, gc);

        gc.gridx = 1;
        gc.gridy = 5;
        add(clearCNCBox, gc);

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
        gc.gridy = 8;
        add(tableContainer, gc);

        gc.fill = GridBagConstraints.NONE;
        gc.gridwidth = 1;
        gc.gridy = 9;
        add(testButton, gc);
    }

    static void initColumnSizes(JTable table) {
        FileListTableModel model = (FileListTableModel) table.getModel();
        TableColumn column;
        Component comp;
        int headerWidth;
        int cellWidth;
        Object[] longValues = FileListTableModel.longValues;
        TableCellRenderer headerRenderer =
                table.getTableHeader().getDefaultRenderer();

        for (int i = 0; i < 3; i++) {
            column = table.getColumnModel().getColumn(i);

            comp = headerRenderer.getTableCellRendererComponent(
                    null, column.getHeaderValue(),
                    false, false, 0, 0);
            headerWidth = comp.getPreferredSize().width;

            comp = table.getDefaultRenderer(model.getColumnClass(i)).
                    getTableCellRendererComponent(
                            table, longValues[i],
                            false, false, 0, i);
            cellWidth = comp.getPreferredSize().width;

            if (i == 0 || i == 2) {
                column.setPreferredWidth(Math.max(headerWidth + 6, cellWidth + 6));
            } else column.setPreferredWidth(Math.max(headerWidth, cellWidth));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == settingsButton) {
            FolderEntry folderEntry = new FolderEntry("Settings", Dialog.ModalityType.APPLICATION_MODAL);
            folderEntry.setVisible(true);

        } else if (e.getSource() == findButton) {
            ReleaseUtility.setPn(partNoText.getText());
            ReleaseUtility.setSource(Paths.get(sourceText.getText()));

            (new Thread(new TableUpdate(sourceTable, ReleaseUtility.getSource()))).start();
            (new Thread(new TableUpdate(releasedTable, ReleaseUtility.getReleased()))).start();
            (new Thread(new TableUpdate(archiveTable, ReleaseUtility.getArchive()))).start();
            (new Thread(new TableUpdate(cncTable, ReleaseUtility.getCnc()))).start();
        } else if (e.getSource() == sourceButton) {
            int returnVal = ReleaseUtility.getFc().showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                Path path = ReleaseUtility.getFc().getSelectedFile().toPath();
                ReleaseUtility.setSource(path);
                sourceText.setText(path.toString());
            }
        } else if (e.getSource() == testButton) {
            if (releaseDrawingBox.isSelected()) {
                System.out.println("Drawings to Release");

                FileListTableModel releasedModel = (FileListTableModel) sourceTable.getModel();
                releasedModel.getData().stream().filter(DrawingFile::isActionable).forEach(System.out::println);

                System.out.println();
            }

            if (archiveDrawingBox.isSelected()) {
                System.out.println("Drawings to Archive");

                FileListTableModel archiveModel = (FileListTableModel) releasedTable.getModel();
                archiveModel.getData().stream().filter(DrawingFile::isActionable).forEach(System.out::println);

                System.out.println();
            }

            if (clearCNCBox.isSelected()) {
                System.out.println("Files in the CNC folder");

                FileListTableModel cncModel = (FileListTableModel) cncTable.getModel();
                cncModel.getData().stream().filter(DrawingFile::isActionable).forEach(System.out::println);

                System.out.println();
            }
        }
    }
}
