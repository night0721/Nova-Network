package me.night0721.network;

import me.night0721.network.commands.MessageCommand;
import me.night0721.network.commands.SkywarsCommand;
import me.night0721.network.util.Utils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.Favicon;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public final class Network extends Plugin implements Listener {
    private Favicon f;
    @Override
    public void onEnable() {
        System.out.println("[NETWORK] 123");
        getProxy().getPluginManager().registerListener(this, this);
        getProxy().getPluginManager().registerCommand(this, new SkywarsCommand(this));
        getProxy().getPluginManager().registerCommand(this, new MessageCommand());
        try {
            f = Favicon.create(ImageIO.read(new File("icon.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @EventHandler
    public void onPing(ProxyPingEvent e) {
        ServerPing p = e.getResponse();
        String s = Utils.centerText("Matrix", 45);
        String s2 = Utils.centerText("Support 1.18 & 1.8.9", 45);
        p.setDescription(ChatColor.of("#00a4ff").toString() + ChatColor.BOLD + s + "\n" + ChatColor.GOLD + ChatColor.BOLD + s2);
        p.setPlayers(new ServerPing.Players(8964, 721, p.getPlayers().getSample()));
        p.setVersion(new ServerPing.Protocol("Connect with 1.18.1", 757));
        p.setFavicon(f);
        e.setResponse(p);
    }
}
