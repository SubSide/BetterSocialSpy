package subside.plugins.bettersocialspy;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHandler implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
		if(sender instanceof Player){
			Player player = (Player)sender;
			if(Utils.Perm.socialspy.has(player)){
				if(args.length > 0){
					if(Utils.Perm.other.has(player)){
						Player other = Bukkit.getPlayer(args[0]);
						if(other != null){
							MsgHistory.get().setSocialSpy(other, !MsgHistory.get().hasSocialSpyEnabled(other));
							Utils.sendMsg(player, "SocialSpy is now "+(MsgHistory.get().hasSocialSpyEnabled(other)?"enabled":"disabled")+" for "+other.getName()+".");
							Utils.sendMsg(other, "SocialSpy is now "+(MsgHistory.get().hasSocialSpyEnabled(player)?"enabled":"disabled")+".");
						} else {
							Utils.sendMsg(player, "This player is not online!");
						}
					} else {
						Utils.sendMsg(player, "You don't have permissions to do this!");
					}
				} else {
					MsgHistory.get().setSocialSpy(player, !MsgHistory.get().hasSocialSpyEnabled(player));
					Utils.sendMsg(player, "SocialSpy is now "+(MsgHistory.get().hasSocialSpyEnabled(player)?"enabled":"disabled")+".");
				}
			} else {
				Utils.sendMsg(player, "You don't have permissions to do this!");
			}
			
		} else {
			Utils.sendMsg(sender, "This can only be executed from ingame!");
		}
		return true;
	}

}
