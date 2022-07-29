package me.night0721.network.commands;

import me.night0721.network.Network;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.scheduler.ScheduledTask;

import java.util.concurrent.TimeUnit;

public class SkywarsCommand extends Command {
    private Network main;
    private int i = 3;
    private ScheduledTask task;
    public SkywarsCommand(Network main) {
        super("skywars");
        this.main = main;
    }
    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) sender;
            if(player.getServer().getInfo().getName().equalsIgnoreCase("Skywars")) {
                player.sendMessage(ChatColor.RED + "You are already in Skywars server!");
            } else {
                ServerInfo targetServer = ProxyServer.getInstance().getServerInfo("Skywars");
                task = ProxyServer.getInstance().getScheduler().schedule(main, () -> {
                    player.sendMessage(ChatColor.GREEN + "Wait for " + i + " seconds");
                    i--;
                    if(i == 0) {
                        task.cancel();
                        player.connect(targetServer);
                    }
                }, 3, 1, TimeUnit.SECONDS);
            }
        }
    }
}
