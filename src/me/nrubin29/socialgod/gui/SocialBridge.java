package me.nrubin29.socialgod.gui;

import me.nrubin29.socialgod.misc.Constants;
import me.nrubin29.socialgod.network.Network;
import me.nrubin29.socialgod.network.NetworkManager;
import me.nrubin29.socialgod.network.Post;
import me.nrubin29.socialgod.util.UtilityProvider;

import javax.swing.*;
import java.awt.*;

public class SocialBridge extends JPanel {

    private JPanel panel;

    public SocialBridge() {
        setPanel(new Menu(this));

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setSize(Constants.SOCIAL_DIMENSION);
        setLocation(UtilityProvider.getMiscUtil().center(Constants.SOCIAL_DIMENSION));
    }

    void setPanel(JPanel panel) {
        if (this.panel != null) remove(this.panel);
        add(this.panel = panel);
    }

    private class Menu extends JPanel {

        private final SocialBridge socialBridge;

        public Menu(SocialBridge socialBridge) {
            this.socialBridge = socialBridge;

            JLabel title = new JLabel("SocialBridge");
            title.setFont(UtilityProvider.getFontUtil().getFont(12));
            title.setAlignmentX(Component.CENTER_ALIGNMENT);
            add(title);

            JPanel buttonPanel = new JPanel(new GridLayout(2, 3));
            buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

            for (Network n : NetworkManager.getInstance().getNetworks()) {
                JButton button = new JButton(n.getName());
                button.setForeground(n.getColorScheme().getBackground());
                button.addActionListener(e -> socialBridge.setPanel(new NetworkPage(this.socialBridge, n)));
                buttonPanel.add(button);
            }

            add(buttonPanel);

            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        }
    }

    private class NetworkPage extends JPanel {

        private final SocialBridge socialBridge;
        private final Network network;

        public NetworkPage(SocialBridge socialBridge, Network network) {
            this.socialBridge = socialBridge;
            this.network = network;

            JLabel title = new JLabel(network.getName());
            title.setForeground(network.getColorScheme().getText());
            title.setFont(UtilityProvider.getFontUtil().getFont(12));
            title.setAlignmentX(Component.CENTER_ALIGNMENT);
            add(title);

            setBackground(network.getColorScheme().getBackground());
        }

        public SocialBridge getSocialBridge() {
            return socialBridge;
        }

        public Network getNetwork() {
            return network;
        }

        private class PostPage extends JPanel {

            private final NetworkPage networkPage;
            private final Post post;

            public PostPage(NetworkPage networkPage, Post post) {
                this.networkPage = networkPage;
                this.post = post;
            }
        }
    }
}