import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * This class creates a table of DrawingFile objects in order to display them on the MainScreen.
 * <p>
 * Created by elohmar on 11/4/2016.
 */
class FileListTableModel extends AbstractTableModel {

    private static List<DrawingFile> data;
    private String[] columnNames = {"", "File Name", "ext"};

    public FileListTableModel(List<DrawingFile> fileList) {
        data = new ArrayList<>();
        data.clear();
        data.addAll(fileList);
        fireTableDataChanged();
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return data.get(rowIndex).isActionable();
        } else if (columnIndex == 1) {
            return data.get(rowIndex).getFileName();
        } else if (columnIndex == 2) {
            return data.get(rowIndex).getFileType();
        } else return null;
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return col == 0;
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        if (col == 0) {
            data.get(row).setActionable((Boolean) value);
            fireTableCellUpdated(row, col);
        }
    }

    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    public void setData(List<DrawingFile> list) {
        data = list;
        fireTableDataChanged();
    }

    public String toString() {
        for (DrawingFile file : data) {
            System.out.println(file.toString());
        }
        System.out.println();
        return "finished";
    }
}
