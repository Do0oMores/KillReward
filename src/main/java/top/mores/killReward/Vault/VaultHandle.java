package top.mores.killReward.Vault;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.util.Random;

public class VaultHandle {

    VaultReward vaultReward = new VaultReward();
    private final Random random = new Random();
    private static Economy economy;

    // 初始化 economy 对象
    public static boolean setupEconomy() {
        if (Bukkit.getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        economy = rsp.getProvider();
        return true;
    }

    public void addPlayerVault(Player player) {
        if (economy == null) {
            System.out.println("Economy plugin not loaded!");
            return;
        }
        double vaultAmount = RandomValue();
        EconomyResponse response = economy.depositPlayer(player, vaultAmount);
        if (response.transactionSuccess()) {
            player.sendMessage(ChatColor.GREEN + "您因击杀获得 " + ChatColor.GOLD + vaultAmount + ChatColor.GREEN +" 金币");
        } else {
            System.out.println("添加金币失败");
        }
    }

    private double RandomValue() {
        double min = vaultReward.getVAULT_MIN();
        double max = vaultReward.getVAULT_MAX();
        if (min > max) {
            throw new IllegalArgumentException("max必须大于或者等于min");
        }
        double value = random.nextDouble() * (max - min) + min;
        return Math.round(value * 100.0) / 100.0;
    }
}
