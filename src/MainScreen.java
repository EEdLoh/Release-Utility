import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static java.nio.file.StandardCopyOption.ATOMIC_MOVE;

/**
 * MainScreen sets the GUI for the main screen of this application.
 * <p>
 * It has the P/N field, settings button, source path field, file list boxes, and the go button.
 * <p>
 * Created by elohmar on 11/1/2016.
 */
class MainScreen extends JPanel implements ActionListener {

    private JCheckBox releaseDrawingBox, releaseCNCBox;
    private JTextField partNoText, sourceText;
    private JButton sourceButton, settingsButton, findButton, goButton;
    private JTable pdfFromSourceTable, releasedTable, dxfFromSourceTable, cncTable;

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
        releaseCNCBox = new JCheckBox("Release DXF Files", true);

        //Test Button
        goButton = new JButton("Go");
        goButton.addActionListener(this);

        //File List Boxes and Container
        JPanel tableContainer = new JPanel();

        JLabel sourceTableLabel = new JLabel("Drawings to Release");
        pdfFromSourceTable = new JTable(new FileListTableModel(new ArrayList<>()));
        pdfFromSourceTable.setFillsViewportHeight(true);
        JScrollPane sourceScrollPane = new JScrollPane(pdfFromSourceTable);
        initColumnSizes(pdfFromSourceTable);
        pdfFromSourceTable.setAutoCreateRowSorter(true);

        JLabel releasedTableLabel = new JLabel("Drawings to Archive");
        releasedTable = new JTable(new FileListTableModel(new ArrayList<>()));
        releasedTable.setFillsViewportHeight(true);
        JScrollPane releasedScrollPane = new JScrollPane(releasedTable);
        initColumnSizes(releasedTable);
        releasedTable.setAutoCreateRowSorter(true);

        JLabel archiveTableLabel = new JLabel("CNC Files to Release");
        dxfFromSourceTable = new JTable(new FileListTableModel(new ArrayList<>()));
        dxfFromSourceTable.setFillsViewportHeight(true);
        JScrollPane archiveScrollPane = new JScrollPane(dxfFromSourceTable);
        initColumnSizes(dxfFromSourceTable);
        dxfFromSourceTable.setAutoCreateRowSorter(true);

        JLabel cncTableLabel = new JLabel("CNC Files to Delete");
        cncTable = new JTable(new FileListTableModel(new ArrayList<>()));
        cncTable.setFillsViewportHeight(true);
        JScrollPane cncScrollPane = new JScrollPane(cncTable);
        initColumnSizes(cncTable);
        cncTable.setAutoCreateRowSorter(true);

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
        add(releaseCNCBox, gc);

        ////// Column 2 //////
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
        gc.gridy = 8;
        add(tableContainer, gc);

        gc.insets.set(0, 15, 15, 0);
        gc.fill = GridBagConstraints.NONE;
        gc.gridwidth = 1;
        gc.gridy = 9;
        add(goButton, gc);
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

            (new Thread(new TableUpdate(pdfFromSourceTable, ReleaseUtility.getSource(),
                    "glob:**" + ReleaseUtility.getPn() + "[, ]*.pdf"))).start();

            (new Thread(new TableUpdate(dxfFromSourceTable, ReleaseUtility.getSource(),
                    "glob:**" + ReleaseUtility.getPn() + "[, ]*.dxf"))).start();

            (new Thread(new TableUpdate(releasedTable, ReleaseUtility.getReleased(),
                    "glob:**" + ReleaseUtility.getPn() + "[, ]*"))).start();

            (new Thread(new TableUpdate(cncTable, ReleaseUtility.getCnc(),
                    "glob:**" + ReleaseUtility.getPn() + "{[, ],[a-z,A-Z]}*"))).start();

        } else if (e.getSource() == sourceButton) {
            int returnVal = ReleaseUtility.getFc().showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                Path path = ReleaseUtility.getFc().getSelectedFile().toPath();
                ReleaseUtility.setSource(path);
                sourceText.setText(path.toString());
            }
        } else if (e.getSource() == goButton) {
            if (releaseDrawingBox.isSelected()) {

                Object[] options = {"Yes", "No"};
                int i = JOptionPane.showOptionDialog(this, "Any checked Files in the \"Drawings to Archive\" list" +
                                " will be moved from Drawings Released to Drawings Archive. \nIs this OK?",
                        "Archiving Drawings",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE,
                        null,
                        options,
                        options[0]);

                if (i == JOptionPane.OK_OPTION) {
                    new Thread(() -> {

                        FileListTableModel toArchiveModel = (FileListTableModel) releasedTable.getModel();

                        if (toArchiveModel.getData().size() > 0) {
                            toArchiveModel.getData().stream().filter(DrawingFile::isActionable).forEach(file -> {
                                try {
                                    Files.move(file.getFilePath(),
                                            (ReleaseUtility.getArchive().resolve(file.getFilePath().getFileName())),
                                            ATOMIC_MOVE);

                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                }
                            });
                        }

                        FileListTableModel toReleasedModel = (FileListTableModel) pdfFromSourceTable.getModel();

                        if (toReleasedModel.getData().size() > 0) {
                            toReleasedModel.getData().stream().filter(DrawingFile::isActionable).forEach(file -> {
                                try {
                                    Files.move(file.getFilePath(),
                                            (ReleaseUtility.getReleased().resolve(file.getFilePath().getFileName())),
                                            ATOMIC_MOVE);

                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                }
                            });
                        }

                        (new Thread(new TableUpdate(releasedTable, ReleaseUtility.getReleased(),
                                "glob:**" + ReleaseUtility.getPn() + "[, ]*"))).start();

                        (new Thread(new TableUpdate(pdfFromSourceTable, ReleaseUtility.getSource(),
                                "glob:**" + ReleaseUtility.getPn() + "[, ]*.pdf"))).start();
                    }).start();
                }
            }

            if (releaseCNCBox.isSelected()) {

                Object[] options = {"Yes", "No"};
                int i = JOptionPane.showOptionDialog(this, "Any checked Files in the \"CNC Files to Delete\" will be " +
                                "deleted. \nIs this OK?",
                        "Archiving Drawings",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE,
                        null,
                        options,
                        options[0]);

                if (i == JOptionPane.OK_OPTION) {
                    new Thread(() -> {

                        FileListTableModel cncModel = (FileListTableModel) cncTable.getModel();

                        if (cncModel.getData().size() > 0) {
                            cncModel.getData().stream().filter(DrawingFile::isActionable).forEach(file -> {
                                try {
                                    Files.delete(file.getFilePath());

                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                }
                            });
                        }


                        FileListTableModel toCNCModel = (FileListTableModel) dxfFromSourceTable.getModel();

                        if (toCNCModel.getData().size() > 0) {
                            toCNCModel.getData().stream().filter(DrawingFile::isActionable).forEach(file -> {
                                Path dest = Paths.get(ReleaseUtility.getCnc() + "\\DXFs\\" + file.getFilePath().getFileName());
                                try {
                                    Files.move(file.getFilePath(), dest, ATOMIC_MOVE);

                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                }
                            });
                        }

                        (new Thread(new TableUpdate(cncTable, ReleaseUtility.getCnc(),
                                "glob:**" + ReleaseUtility.getPn() + "{[, ],[a-z,A-Z]}*"))).start();

                        (new Thread(new TableUpdate(dxfFromSourceTable, ReleaseUtility.getSource(),
                                "glob:**" + ReleaseUtility.getPn() + "[, ]*.dxf"))).start();
                    }).start();
                }
            }
        }
    }
}
