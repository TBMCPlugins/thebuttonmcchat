package buttondevteam.thebuttonmcchat.commands.ucmds;

import buttondevteam.thebuttonmcchat.commands.TBMCCommandBase;

public abstract class UCommandBase extends TBMCCommandBase {

	public abstract String[] GetHelpText(String alias);

	@Override
	public String GetCommandPath() {
		return "u/" + GetUCommandPath();
	}

	public abstract String GetUCommandPath();

	@Override
	public boolean GetPlayerOnly() {
		return true;
	}

	@Override
	public boolean GetModOnly() {
		return false;
	}
}
