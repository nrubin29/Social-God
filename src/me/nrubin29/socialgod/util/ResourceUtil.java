package me.nrubin29.socialgod.util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.net.URL;

public class ResourceUtil {

    private final Class<?> mainClass;

    public ResourceUtil(Class<?> mainClass) {
        this.mainClass = mainClass;
    }

    public URL getResource(String path) {
        return mainClass.getResource("/res/" + path);
    }

    public InputStream getResourceAsStream(String path) {
        return mainClass.getResourceAsStream("/res/" + path);
    }

    public BufferedImage getBufferedImage(String imagePath) {
        try {
            return ImageIO.read(getResource(imagePath + ".png"));
        } catch (Exception e) {
            return null;
        }
    }

    public ImageIcon getImage(String imagePath) {
        return new ImageIcon(getBufferedImage(imagePath));
    }

    public ImageIcon getImage(String imagePath, int width, int height) {
        return resizeImage(getImage(imagePath), width, height);
    }

    public ImageIcon getImageScaled(String imagePath, int widthX, int heightX) {
        ImageIcon icon = getImage(imagePath);
        return resizeImage(icon, icon.getIconWidth() / widthX, icon.getIconHeight() / heightX);
    }

    public ImageIcon resizeImage(ImageIcon image, int width, int height) {
        return new ImageIcon(image.getImage().getScaledInstance(width, height, 0));
    }

    public ImageIcon resizeImageScaled(ImageIcon image, int widthX, int heightX) {
        return resizeImage(image, image.getIconWidth() / widthX, image.getIconHeight() / heightX);
    }
}