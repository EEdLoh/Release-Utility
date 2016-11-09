import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * This class is intended to be run in a separate thread in order to update the tables based on their respective
 * sources and the entered part number.
 * <p>
 * Created by elohmar on 11/9/2016.
 */
class TableUpdate implements Runnable {

    private JTable table;
    private Path path;

    TableUpdate(JTable table, Path path) {
        this.table = table;
        this.path = path;
    }

    @Override
    public void run() {
        String pnMatch = "glob:**" + ReleaseUtility.getPn() + "[, ]*";

        FileFinder fileFinder = new FileFinder(pnMatch);
        try {
            Files.walkFileTree(path, fileFinder);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        table.setModel(new FileListTableModel(fileFinder.getResult()));
        MainScreen.initColumnSizes(table);
    }
}
