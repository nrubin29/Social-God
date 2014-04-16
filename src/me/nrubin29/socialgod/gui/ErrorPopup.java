package me.nrubin29.socialgod.gui;

import me.nrubin29.socialgod.misc.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;

public class ErrorPopup extends JFrame {

    public ErrorPopup(final Throwable e) {
        super("Error");

        try {
//			JLabel player = new JLabel(UtilityProvider.getResourceUtil().resizeImage(Session.getInstance().getPlayer().getImage(Direction.DOWN, false), 25, 25));
//			JPanel playerPanel = new JPanel();
//			playerPanel.add(player);
//			add(playerPanel);
        } catch (Exception ignored) {
        }

        JTextArea notice = new JTextArea(
                "Congratulations, beta tester, you have discovered a bug! Please follow the steps below to correctly submit a bug report:\n\n" +
                        "1. Click Submit Bug Report. This opens a new email, so if it opens in an email client you don't use, compose a new email in your favorite client.\n" +
                        "2. Click Copy Information to copy the bug information to your clipboard. Paste the info into the email.\n" +
                        "3. In the report, explain exactly what you did to produce the problem.\n" +
                        "4. Hit Submit. That's it! Thanks!"
        );
        notice.setEditable(false);
        notice.setFocusable(false);
        notice.setWrapStyleWord(true);
        notice.setLineWrap(true);
        notice.setBorder(null);
        notice.setBackground(getBackground());

        JLabel newReport = new JLabel("Submit Bug Report");
        newReport.setCursor(new Cursor(Cursor.HAND_CURSOR));
        newReport.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                try {
                    Desktop.getDesktop().mail(new URI("mailto:nrubin29@gmail.com"));
                } catch (Exception ignored) {
                }
            }
        });

        JLabel copyInfo = new JLabel("Copy Information");
        copyInfo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        copyInfo.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent ev) {
                StringBuilder infoBuffer = new StringBuilder();

                infoBuffer.append("OS: ").append(System.getProperty("os.name")).append("\n");
                infoBuffer.append("Java Version: ").append(System.getProperty("java.version")).append("\n\n");

                infoBuffer.append("Error Type: ").append(e.getClass().getSimpleName()).append("\n");
                infoBuffer.append("Error Details: ").append(e.getMessage()).append("\n\n");
                for (StackTraceElement ste : e.getStackTrace()) infoBuffer.append(ste).append("\n");

                infoBuffer.append("\nSteps I took to produce this problem:\n");

                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(infoBuffer.toString()), null);
            }
        });

        JLabel click = new JLabel("Click to Exit.");
        click.setCursor(new Cursor(Cursor.HAND_CURSOR));
        click.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent ev) {
                System.exit(0);
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(notice);
        panel.add(newReport);
        panel.add(copyInfo);
        panel.add(click);
        add(panel);

        setSize(Constants.ERROR_DIMENSION);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setVisible(true);
    }
}