package buttondevteam.chat.commands.ucmds;

import org.bukkit.command.CommandSender;

import buttondevteam.chat.commands.CommandCaller;
import buttondevteam.chat.commands.TBMCCommandBase;

public final class UCommand extends TBMCCommandBase {

	@Override
	public String[] GetHelpText(String alias) {
		return CommandCaller.GetSubCommands(this);
	}

	@Override
	public boolean OnCommand(CommandSender sender, String alias, String[] args) {
		return false;
	}

	@Override
	public String GetCommandPath() {
		return "u";
	}

	@Override
	public boolean GetPlayerOnly() {
		return false;
	}

	@Override
	public boolean GetModOnly() {
		return false;
	}
}