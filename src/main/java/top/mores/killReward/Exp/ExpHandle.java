package top.mores.killReward.Exp;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Random;

public class ExpHandle {
    ExpReward expReward = new ExpReward();
    private final Random random = new Random();

    //更新经验数据
    public void addPlayerExp(Player player) {
        int xpToAdd = RandomValue();
        expReward.initPlayerExpAmount(player, xpToAdd);
    }

    private int RandomValue() {
        int min, max;
        min = expReward.getXP_MIN();
        max = expReward.getXP_MAX();
        if (min > max) {
            throw new IllegalArgumentException("max必须大于或者等于min");
        }
        return random.nextInt((max - min) + 1) + min;
    }

    //同步玩家经验
    public void SyncPlayerExp(Player player) {
        String playerName = player.getName();
        int changeExp = expReward.getPlayerExpAmount(playerName);
        int playerLevel = player.getLevel();
        if (playerLevel < expReward.getXP_LEVEL()) {
            player.giveExp(changeExp);
        }
        if (changeExp != 0) {
            int playerKill = expReward.getPlayerKillAmount(playerName);
            int playerDeath = expReward.getPlayerDeathAmount(playerName);
            float kd = playerDeath == 0 ? playerKill : (float) playerKill / playerDeath;
            String playerKD = String.format("%.2f", kd);

            player.sendMessage(ChatColor.GREEN + "您在刚才的游戏中共获得: " + ChatColor.GOLD + changeExp + ChatColor.GREEN + " 点经验");
            player.sendMessage(ChatColor.AQUA + "赛后总结： " +
                    ChatColor.DARK_GREEN + "击杀  " +
                    ChatColor.GOLD + playerKill +"-"+
                    ChatColor.DARK_RED + "  死亡  " +
                    ChatColor.GOLD + playerDeath +"-"+
                    ChatColor.DARK_PURPLE + "  K/D  " +
                    ChatColor.GOLD + playerKD);
        }
        //重置经验值
        expReward.replaceAmount(playerName);
    }
}
