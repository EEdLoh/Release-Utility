import java.nio.file.Path;

/**
 * This class is used to store information about files in order to create the tables.
 * <p>
 * Created by elohmar on 11/8/2016.
 */
class DrawingFile {

    private String fileName, fileType;
    private boolean actionable;
    private Path filePath;

    DrawingFile(Path path) {
        setFilePath(path);
        setFileName(stripExtension(path));
        setFileType(getExtension(path));
        setActionable(true);
    }

    String getFileName() {
        return fileName;
    }

    private void setFileName(String fileName) {
        this.fileName = fileName;
    }

    String getFileType() {
        return fileType;
    }

    private void setFileType(String fileType) {
        this.fileType = fileType;
    }

    boolean isActionable() {
        return actionable;
    }

    void setActionable(boolean actionable) {
        this.actionable = actionable;
    }

    Path getFilePath() {
        return filePath;
    }

    private void setFilePath(Path filePath) {
        this.filePath = filePath;
    }

    private String stripExtension(Path path) {
        if (path == null) return null;

        String name = path.getFileName().toString();
        int pos = name.lastIndexOf(".");

        if (pos == -1) return name;

        return name.substring(0, pos);
    }

    private String getExtension(Path path) {
        if (path == null) return null;

        String name = path.getFileName().toString();
        int pos = name.lastIndexOf(".");

        if (pos == -1) return "";

        return name.substring(pos);
    }

    public String toString() {
        return "File: " + filePath.getFileName() + " Bool: " + actionable;
    }
}
