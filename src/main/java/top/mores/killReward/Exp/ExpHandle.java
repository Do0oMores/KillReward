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
        player.giveExp(changeExp);
        player.sendMessage(ChatColor.GREEN + "您在刚才的游戏中共获得: " + ChatColor.GOLD + changeExp + ChatColor.GREEN + " 点经验");
        //重置经验值
        expReward.replaceExpAmount(playerName);
    }
}
