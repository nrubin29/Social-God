package me.nrubin29.socialgod.util;

import java.awt.*;

public class FontUtil {

    private Font font;

    public Font getFont() {
        if (font != null) return font;

        try {
            font = Font.createFont(Font.PLAIN, UtilityProvider.getResourceUtil().getResourceAsStream("font.ttf"));
            font = font.deriveFont(Font.PLAIN, 7);
            return font;
        } catch (Exception e) {
            return null;
        }
    }

    public Font getFont(int size) {
        return getFont().deriveFont(Font.PLAIN, size);
    }
}