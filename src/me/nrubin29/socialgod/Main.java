package me.nrubin29.socialgod;

import me.nrubin29.socialgod.gui.CoreFrame;
import me.nrubin29.socialgod.gui.ErrorPopup;
import me.nrubin29.socialgod.quest.CreateAccounts;
import me.nrubin29.socialgod.tile.TilesheetManager;
import me.nrubin29.socialgod.util.UtilityProvider;

import java.awt.*;

class Main {

    private Main() {
        Thread.setDefaultUncaughtExceptionHandler((th, e) -> {
            e.printStackTrace();

            System.gc();
            for (Window window : Window.getWindows()) {
                window.dispose();
            }

            new ErrorPopup(e);
        });

        UtilityProvider.setup(getClass());
        TilesheetManager.getInstance().setup();

        CoreFrame.getInstance();

        new CreateAccounts().start();
    }

    public static void main(String[] args) {
        new Main();
    }
}