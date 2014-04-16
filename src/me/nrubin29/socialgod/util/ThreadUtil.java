package me.nrubin29.socialgod.util;

import javax.swing.*;

public class ThreadUtil {

    public void runTimer(int duration, final Runnable run) {
        Timer t = new Timer(duration, e -> run.run());
        t.setRepeats(false);
        t.start();
    }

    public void animate(Runnable run) {
        runTimer(75, run);
    }

    public void runThreadInBackground(final Runnable run, final boolean loop) {
        Thread th = new Thread(new Runnable() {
            public void run() {
                run.run();
                if (loop) run();
            }
        });

        th.start();
    }

//	public void runGUITaskInBackground(final Runnable run) {
//		SwingWorker<Void, Void> sw = new SwingWorker<Void, Void>() {
//			@Override
//			protected Void doInBackground() {
//				run.run();
//				return null;
//			}
//		};
//		
//		sw.execute();
//	}
}