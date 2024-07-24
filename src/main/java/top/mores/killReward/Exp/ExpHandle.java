package top.mores.killReward.Exp;

import org.bukkit.entity.Player;

public class ExpHandle {
    ExpReward expReward=new ExpReward();

    public void addPlayerExp(Player player) {
        int PlayerLevel = player.getLevel();
        if (PlayerLevel < expReward.getXP_LEVEL()) {
            int xpToAdd = expReward.getXP();
            player.giveExp(xpToAdd);
        }
    }
}
