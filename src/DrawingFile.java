import java.nio.file.Path;

/**
 * This class is used to store information about files in order to create the tables.
 * <p>
 * Created by elohmar on 11/8/2016.
 */
class DrawingFile {

    private static String fileName, fileType;
    private static boolean actionable;
    private static Path filePath;

    public DrawingFile(Path path) {
        setFilePath(path);
        setFileName(stripExtension(path));
        setFileType(getExtension(path));
        setActionable(true);
    }

    static String getFileName() {
        return fileName;
    }

    static void setFileName(String fileName) {
        DrawingFile.fileName = fileName;
    }

    static String getFileType() {
        return fileType;
    }

    private static void setFileType(String fileType) {
        DrawingFile.fileType = fileType;
    }

    static boolean isActionable() {
        return actionable;
    }

    static void setActionable(boolean actionable) {
        DrawingFile.actionable = actionable;
    }

    static Path getFilePath() {
        return filePath;
    }

    static void setFilePath(Path filePath) {
        DrawingFile.filePath = filePath;
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
