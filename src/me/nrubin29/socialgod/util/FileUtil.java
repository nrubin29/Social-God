package me.nrubin29.socialgod.util;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;

public class FileUtil {

    File getRootFolder() {
        String homedir = FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath();
        String osname = System.getProperty("os.name").toLowerCase();
        File rootFolder;

        String name = "Main";
        if (osname.startsWith("mac")) rootFolder = new File(homedir + "/Library/Application Support/" + name);
        else if (osname.startsWith("linux")) rootFolder = new File(homedir + "/." + name + "/");
        else if (osname.startsWith("win")) rootFolder = new File(System.getenv("APPDATA") + "\\." + name + "\\");
        else throw new RuntimeException("Unsupported OS: " + osname);

        if (!rootFolder.exists()) {
            try {
                rootFolder.mkdir();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Could not create data folder. This should never happen. Make sure to use the launcher.");
                System.exit(0);
            }
        }

        return rootFolder;
    }

    public File getFile(String folder, String name, boolean createIfNotExists) {
        File rootFolder = getRootFolder();

        if (!folder.equals("")) getFolder(folder, true);

        File f = new File((!folder.equals("") ? new File(rootFolder, folder) : rootFolder), name);

        if (!f.exists() && createIfNotExists) {
            try {
                f.createNewFile();
            } catch (Exception e) {
                System.out.println("Could not create file.");
            }
        }

        return f;
    }

    File getFolder(String name, boolean createIfNotExists) {
        File folder = new File(getRootFolder(), name);

        if (!folder.exists()) folder.mkdir();

        return folder;
    }
}