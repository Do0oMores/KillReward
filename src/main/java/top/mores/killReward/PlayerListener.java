package top.mores.killReward;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import top.mores.killReward.Exp.ExpHandle;
import top.mores.killReward.Exp.ExpReward;
import top.mores.killReward.Utils.RewardUtil;
import top.mores.killReward.Vault.VaultHandle;
import top.mores.killReward.Vault.VaultReward;

public class PlayerListener implements Listener {
    private final ExpReward expReward;
    VaultReward vaultReward=new VaultReward();
    VaultHandle vaultHandle=new VaultHandle();
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
            if (vaultReward.isENABLED()){
                if (rewardUtil.IsPlayer(killer)){
                    vaultHandle.addPlayerVault((Player) killer);
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

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        expReward.initPlayerData(player);
    }

    @EventHandler
    public void onPlayerChangeWorld(PlayerChangedWorldEvent event) {
        String MainWorldName = expReward.getMAIN_WORLD();
        String ChangeWorldName = event.getFrom().getName();
        Player player = event.getPlayer();
        if (!ChangeWorldName.equals(MainWorldName)) {
            expHandle.SyncPlayerExp(player);
        }
    }
}
