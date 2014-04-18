package me.nrubin29.socialgod.network;

import me.nrubin29.socialgod.network.networks.Facebook;
import me.nrubin29.socialgod.network.networks.Instagram;
import me.nrubin29.socialgod.network.networks.Tumblr;
import me.nrubin29.socialgod.network.networks.Twitter;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class NetworkManager {

    private static final NetworkManager instance = new NetworkManager();
    private final ArrayList<Network> networks;

    private NetworkManager() {
        networks = new ArrayList<>();
        networks.add(new Facebook());
        networks.add(new Instagram());
        networks.add(new Tumblr());
        networks.add(new Twitter());
    }

    public static NetworkManager getInstance() {
        return instance;
    }

    public Network getNetwork(String name) {
        return networks.stream().filter(n -> n.getName().equals(name)).collect(Collectors.toList()).get(0);
    }

    public Network[] getNetworks() {
        return networks.toArray(new Network[networks.size()]);
    }
}