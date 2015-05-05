package subside.plugins.bettersocialspy;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class EventListener implements Listener {
	private static ArrayList<String> CMDS;

	static {
		CMDS = new ArrayList<String>();
		CMDS.add("m");
		CMDS.add("msg");
		CMDS.add("t");
		CMDS.add("tell");
		CMDS.add("r");
		CMDS.add("reply");
		CMDS.add("whisper");
	}

	@EventHandler
	public void onCommandPreprocess(PlayerCommandPreprocessEvent e) {
		try {
			String msg = e.getMessage();
			if (msg.startsWith("/")) {
				msg = msg.substring(1);
			}
			msg = msg.replaceAll("([ ]+)", "$1");
			String[] args = msg.split(" ");

			if (CMDS.contains(args[0].toLowerCase())) {
				if (args[0].equalsIgnoreCase("r") || args[0].equalsIgnoreCase("reply")) {
					if (args.length > 1) {
						Player player = Bukkit.getPlayer(MsgHistory.get().getTo(e.getPlayer()));
						if (player != null) {
							MsgHistory.get().updateHistory(e.getPlayer(), player);
							Utils.sendSSMessage(e.getPlayer().getName(), player.getName(), Utils.getFromArray(args, 1).concat(" "));
						}
					}
				} else if (args.length > 2) {
					Player player = e.getPlayer().getServer().getPlayer(args[1]);
					if (player != null) {
						MsgHistory.get().updateHistory(e.getPlayer(), player);
						Utils.sendSSMessage(e.getPlayer().getName(), player.getName(), Utils.getFromArray(args, 2).concat(" "));
					}
				}
			}

		}
		catch (Exception f) {

		}
	}

}
