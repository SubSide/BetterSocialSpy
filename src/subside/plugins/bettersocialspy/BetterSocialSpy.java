package subside.plugins.bettersocialspy;

import org.bukkit.plugin.java.JavaPlugin;

public class BetterSocialSpy extends JavaPlugin {
	private static BetterSocialSpy instance;
	
	@Override
	public void onEnable() {
		instance = this;
		saveDefaultConfig();
		Utils.readConfig(getConfig());
		
		getServer().getPluginManager().registerEvents(new EventListener(), this);
		getCommand("socialspy").setExecutor(new CommandHandler());
	}

	@Override
	public void onDisable() {

	}
	
	public static BetterSocialSpy getInstance(){
		return instance;
	}
		
}
