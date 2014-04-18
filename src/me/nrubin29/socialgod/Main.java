package me.nrubin29.socialgod;

import me.nrubin29.socialgod.gui.ErrorPopup;
import me.nrubin29.socialgod.gui.Frame;
import me.nrubin29.socialgod.quest.TestQuest;
import me.nrubin29.socialgod.tile.TilesheetManager;
import me.nrubin29.socialgod.util.UtilityProvider;

class Main {

    private Main() {
        Thread.setDefaultUncaughtExceptionHandler((th, e) -> {
            e.printStackTrace();

            Frame.getInstance().dispose();

            new ErrorPopup(e);
        });

        UtilityProvider.setup(getClass());
        TilesheetManager.getInstance().setup();

        Frame.getInstance();

        new TestQuest().start();
    }

    public static void main(String[] args) {
        new Main();
    }
}