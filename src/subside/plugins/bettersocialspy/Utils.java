package subside.plugins.bettersocialspy;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Utils {

	private static String format;
	private static String prefix;

	
	public static void readConfig(FileConfiguration cfg){
		format = cfg.getString("format");
		prefix = cfg.getString("prefix");
	}

	private static String getFormat(){
		return format;
	}
	
	private static String getPrefix(){
		return prefix;
	}	
	
	public static String getFromArray(String[] args, int from) {
		String build = "";
		for (int x = from; x < args.length; x++) {
			build += args[x]+" ";
		}
		return build.trim();
	}
	

	
	public static void sendSSMessage(final String from, final String to, final String msg){
		Bukkit.getScheduler().runTaskAsynchronously(BetterSocialSpy.getInstance(), new Runnable(){
			@SuppressWarnings("deprecation")
			public void run(){
				for(Player player : Bukkit.getOnlinePlayers()){
					try {
						if(MsgHistory.get().hasSocialSpyEnabled(player)){
							player.sendMessage(ChatColor.translateAlternateColorCodes('&', getFormat().replaceAll("%from%", from).replaceAll("%to%", to)+msg));
						}
					} catch(Exception e){}
				}
			}
		});
	}
	
	public static void sendMsg(CommandSender player, String msg){
		player.sendMessage(ChatColor.translateAlternateColorCodes('&', getPrefix()+msg));
	}
	
	public enum Perm {
		socialspy("bss.socialspy"), other("bss.other");
		
		String permission;
		Perm(String permission){
			this.permission = permission;
		}
		
		public boolean has(Player player){
			return player.hasPermission(permission);
		}
		
	}
}
