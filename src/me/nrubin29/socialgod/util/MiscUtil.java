package me.nrubin29.socialgod.util;

import me.nrubin29.socialgod.misc.Constants;

import java.awt.*;

public class MiscUtil {

    public Point center(Dimension object) {
        return new Point(
                (Constants.DIMENSION.width / 2) - (object.width / 2),
                (Constants.DIMENSION.height / 2) - (object.height / 2)
        );
    }
}