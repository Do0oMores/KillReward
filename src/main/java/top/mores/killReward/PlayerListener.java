package top.mores.killReward;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import top.mores.killReward.Exp.ExpHandle;
import top.mores.killReward.Exp.ExpReward;
import top.mores.killReward.Utils.RewardUtil;
import top.mores.killReward.Vault.VaultHandle;
import top.mores.killReward.Vault.VaultReward;

public class PlayerListener implements Listener {
    private final ExpReward expReward;
    VaultReward vaultReward = new VaultReward();
    VaultHandle vaultHandle = new VaultHandle();
    RewardUtil rewardUtil;
    ExpHandle expHandle;
    JavaPlugin plugin;

    public PlayerListener(JavaPlugin plugin, ExpReward expReward, RewardUtil rewardUtil, ExpHandle expHandle) {
        this.expReward = expReward;
        this.expHandle = expHandle;
        this.rewardUtil = rewardUtil;
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        Entity killer = player.getKiller();

        if (killer == null || !rewardUtil.IsPlayer(killer)) {
            return;
        }

        Player killerPlayer = (Player) killer;
        expReward.addPlayerAmount(player, "DeathAmount");
        expReward.addPlayerAmount(killerPlayer, "KillAmount");

        if (expReward.isENABLED()) {
            expHandle.addPlayerExp(killerPlayer);
        }
        if (vaultReward.isENABLED()) {
            vaultHandle.addPlayerVault(killerPlayer);
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

        if (!rewardUtil.isWORLD_TP_ENABLED() || rewardUtil.isInExcludedWorlds(player.getWorld().getName())) {
            return;
        }
        new BukkitRunnable() {
            @Override
            public void run() {
                teleportPlayerToMainWorld(player);
            }
        }.runTaskLater(plugin, 60L);
    }


    @EventHandler
    public void onPlayerChangeWorld(PlayerChangedWorldEvent event) {
        String mainWorldName = expReward.getMAIN_WORLD();
        String changeWorldName = event.getFrom().getName();

        if (changeWorldName.equals(mainWorldName)) {
            return;
        }

        Player player = event.getPlayer();
        new BukkitRunnable() {
            @Override
            public void run() {
                expHandle.SyncPlayerExp(player);
            }
        }.runTaskLater(plugin, 20L);
    }


    public void teleportPlayerToMainWorld(Player player) {
        String worldName = expReward.getMAIN_WORLD();
        if (!player.isOp()) {
            // 使用服务器控制台执行命令
            String command = String.format("mv tp %s %s", player.getName(), worldName);
            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), command);
        }
    }
}
