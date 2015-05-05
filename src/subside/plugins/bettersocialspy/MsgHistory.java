package subside.plugins.bettersocialspy;

import java.util.HashMap;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;

public class MsgHistory {
	private HashMap<String, String> history = new HashMap<String, String>();
	private static MsgHistory instance = new MsgHistory();
	
	public static MsgHistory get(){
		return instance;
	}
	
	public String getTo(Player player){
		if(history.containsKey(player.getName())){
			return history.get(player.getName());
		}
		return null;
	}
	
	public void updateHistory(Player from, Player to){
		history.put(from.getName(), to.getName());
		history.put(to.getName(), from.getName());
	}
	
	
	public boolean hasSocialSpyEnabled(Player player){
		if(player.hasMetadata("BSS_enabled")){
			List<MetadataValue> meta = player.getMetadata("BSS_enabled");
			if(meta.size() > 0){
				if(meta.get(0).asBoolean()){
					return true;
				}
			}
		}
		return false;
	}
	
	public void setSocialSpy(Player player, boolean value){
		player.setMetadata("BSS_enabled", new FixedMetadataValue(BetterSocialSpy.getInstance(), value));
	}
}
