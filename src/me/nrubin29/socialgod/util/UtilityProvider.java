package me.nrubin29.socialgod.util;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.plaf.FontUIResource;
import java.awt.*;

public class UtilityProvider {

    private static FileUtil fileUtil;
    private static FontUtil fontUtil;
    private static ResourceUtil resourceUtil;
    private static ThreadUtil threadUtil;

    public static void setup(Class<?> mainClass) {
        fileUtil = new FileUtil();
        resourceUtil = new ResourceUtil(mainClass);
        fontUtil = new FontUtil();
        threadUtil = new ThreadUtil();

        UIManager.put("nimbusBase", Color.DARK_GRAY);
        UIManager.put("nimbusBlueGrey", Color.GRAY);
        UIManager.put("control", Color.GRAY);
        UIManager.put("text", Color.DARK_GRAY);

        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    UIManager.getLookAndFeelDefaults().put("defaultFont", new FontUIResource(fontUtil.getFont()));
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("No nimbus");
        }
    }

    public static FileUtil getFileUtil() {
        return fileUtil;
    }

    public static FontUtil getFontUtil() {
        return fontUtil;
    }

    public static ResourceUtil getResourceUtil() {
        return resourceUtil;
    }

    public static ThreadUtil getThreadUtil() {
        return threadUtil;
    }
}