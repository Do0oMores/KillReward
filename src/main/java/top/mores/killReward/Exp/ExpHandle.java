package top.mores.killReward.Exp;

import org.bukkit.entity.Player;

import java.util.Random;

public class ExpHandle {
    ExpReward expReward = new ExpReward();
    private final Random random = new Random();

    public void addPlayerExp(Player player) {
        int PlayerLevel = player.getLevel();
        if (PlayerLevel < expReward.getXP_LEVEL()) {
            int xpToAdd = RandomValue();
            player.giveExp(xpToAdd);
        }
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
}
