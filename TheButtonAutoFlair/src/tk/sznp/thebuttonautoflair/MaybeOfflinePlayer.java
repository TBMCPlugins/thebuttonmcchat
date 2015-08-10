package tk.sznp.thebuttonautoflair;

import java.util.HashMap;

public class MaybeOfflinePlayer
{ //2015.08.08.
	public String PlayerName;
	public String UserName;
	public String Flair; //If the user comments their name, it gets set, it doesn't matter if they accepted it or not
	public boolean AcceptedFlair;
	public boolean IgnoredFlair;
	public boolean FlairDecided; //2015.08.09.
    public static HashMap<String, MaybeOfflinePlayer> AllPlayers=new HashMap<>(); //2015.08.08.
    public static MaybeOfflinePlayer AddPlayerIfNeeded(String playername)
    {
		if(!AllPlayers.containsKey(playername))
		{
	    	MaybeOfflinePlayer player=new MaybeOfflinePlayer();
	    	player.PlayerName=playername;
	    	AllPlayers.put(playername, player);
	    	return player;
		}
		return AllPlayers.get(playername);
    }
    /*public enum FlairClassType
    { //2015.08.09.
    	NonPresser,
    	CantPress,
    	Undecided
    }*/
}