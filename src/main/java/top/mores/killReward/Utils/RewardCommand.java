package top.mores.killReward.Utils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import top.mores.killReward.KillReward;

public class RewardCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1 && args[0].equalsIgnoreCase("load")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (!player.hasPermission("killreward.reload")) {
                    player.sendMessage("你没有权限执行此命令！");
                    return true;
                }
            }
            KillReward.getInstance().reloadConfigFile();
            sender.sendMessage("配置文件已重新加载！");
            return true;
        }
        return false;
    }
}
