package tk.sznp.thebuttonautoflair;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {
    // This method is called, when somebody uses our command
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
	    if (sender instanceof Player) {
	        Player player = (Player) sender;
	        if(args.length<1)
	        	return false;
        	MaybeOfflinePlayer p=MaybeOfflinePlayer.AllPlayers.get(player.getName()); //2015.08.08.
	        //if(!PluginMain.PlayerFlairs.containsKey(player.getName()))
	        if(p.Flair==null)
	        {
	        	player.sendMessage("Error: You need to write your username to the reddit thread at /r/TheButtonMinecraft");
	        	return true;
	        }
	        switch(args[0])
	        {
	        case "accept":
	        {
	        	/*if(PluginMain.IgnoredPlayers.contains(player.getName()))
	        		PluginMain.IgnoredPlayers.remove(player.getName());*/
	        	if(p.IgnoredFlair)
	        		p.IgnoredFlair=false; //2015.08.08.
	        	//if(!PluginMain.AcceptedPlayers.contains(player.getName()))
	        	if(!p.AcceptedFlair)
	        	{
		        	//String flair=PluginMain.PlayerFlairs.get(player.getName());
	        		String flair=p.Flair; //2015.08.08.
		    		//player.setDisplayName(player.getDisplayName()+flair);
		        	PluginMain.AppendPlayerDisplayFlairFinal(player, flair); //2015.07.20.
	        		//PluginMain.AcceptedPlayers.add(player.getName());
		        	p.AcceptedFlair=true; //2015.08.08.
	        		player.sendMessage("�6Your flair has been set:�r "+flair);
	        	}
	        	else
	        		player.sendMessage("�cYou already have this user's flair.�r");
	        	break;
	        }
	        case "ignore":
	        {
	        	if(p.AcceptedFlair)
	        		p.AcceptedFlair=false; //2015.08.08.
        		if(!p.IgnoredFlair)
        		{
    	    		p.IgnoredFlair=true;
	        		//String dname=player.getDisplayName();
		        	String flair=p.Flair; //2015.08.08.
		    		//player.setDisplayName(dname.substring(0, dname.indexOf(flair)));
		        	PluginMain.RemovePlayerDisplayFlairFinal(player, flair); //2015.07.20.
		    		player.sendMessage("�6You have ignored this request. You can still use /u accept though.�r");
        		}
        		else
        			player.sendMessage("�cYou already ignored this request.�r");
	        	break;
	        }
	        case "reload": //2015.07.20.
	        	DoReload(player);
	        	break;
        	default:
        		return false;
	        }
	        return true;
		}
		
	    if(args[0]=="reload")
	    	DoReload(null); //2015.07.20.
	    return false;
	}
	private static void DoReload(Player player)
	{ //2015.07.20.
    	if(player==null || player.isOp())
    	{
    		try
    		{
        		File file=new File("autoflairconfig.txt");
        		if(file.exists())
        		{
        			PluginMain.TownColors.clear();
    				BufferedReader br=new BufferedReader(new FileReader(file));
    				String line;
    				while((line=br.readLine())!=null)
    				{
    					String[] s=line.split(" ");
    					PluginMain.TownColors.put(s[0], s[1]);
    				}
    				br.close();
    				//for(Player p : PluginMain.Players)
    				for(Player p : PluginMain.GetPlayers())
    				{
    					MaybeOfflinePlayer mp = MaybeOfflinePlayer.AllPlayers.get(p.getName());
    					if(mp.Flair!=null)
    					{
    						String flair=mp.Flair;
    						PluginMain.RemovePlayerDisplayFlairFinal(p, flair);
    						PluginMain.AppendPlayerDisplayFlairFinal(p, flair);
    					}	
    				}
    				String msg="�6Reloaded config file.�r";
    				if(player!=null)
    					player.sendMessage(msg);
    				else
    					System.out.println(msg);
        		}
    		}
    		catch(Exception e)
    		{
    			System.out.println("Error!\n"+e);
    			if(player!=null)
    				player.sendMessage("�cAn error occured. See console for details.�r");
    		}
    	}
    	else
			player.sendMessage("�cYou need to be OP to use this command.�r");
	}
}
