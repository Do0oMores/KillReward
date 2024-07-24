package top.mores.killReward;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import top.mores.killReward.Exp.ExpHandle;
import top.mores.killReward.Exp.ExpReward;
import top.mores.killReward.Utils.RewardUtil;

import java.io.File;

public final class KillReward extends JavaPlugin {
    public static FileConfiguration config;
    KillReward instance;

    @Override
    public void onEnable() {
        File configFile = new File(getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            boolean isCreateDir = configFile.getParentFile().mkdirs();
            if (!isCreateDir) {
                getLogger().warning("Failed to create config.yml!");
                return;
            }
            saveResource("config.yml", false);
        }
        config = getConfig();
        ExpReward expReward = new ExpReward();
        ExpHandle expHandle=new ExpHandle();
        RewardUtil rewardUtil=new RewardUtil();
        getServer().getPluginManager().registerEvents(new PlayerListener(expReward,rewardUtil,expHandle), this);
        getLogger().info("Enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Disabled!");
    }

    //获取插件实例
    public KillReward getInstance() {
        return instance;
    }
}
