package top.mores.killReward;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import top.mores.killReward.Exp.ExpHandle;
import top.mores.killReward.Exp.ExpReward;
import top.mores.killReward.Utils.RewardUtil;

public class PlayerListener implements Listener {
    private final ExpReward expReward;
    RewardUtil rewardUtil;
    ExpHandle expHandle;

    public PlayerListener(ExpReward expReward, RewardUtil rewardUtil, ExpHandle expHandle) {
        this.expReward = expReward;
        this.expHandle = expHandle;
        this.rewardUtil = rewardUtil;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        if (player.getKiller() != null) {
            Entity killer = player.getKiller();
            if (expReward.isENABLED()) {
                if (rewardUtil.IsPlayer(killer)) {
                    expHandle.addPlayerExp((Player) killer);
                }
            }
        }
    }

    @EventHandler
    public void onPlayerGetExp(PlayerExpChangeEvent event) {
        if (!expReward.isEXP_FROM_ORB()) {
            event.setAmount(0);
        }
    }
}
