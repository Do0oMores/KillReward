package top.mores.killReward;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import top.mores.killReward.Exp.ExpHandle;
import top.mores.killReward.Exp.ExpReward;
import top.mores.killReward.Utils.RewardCommand;
import top.mores.killReward.Utils.RewardUtil;
import top.mores.killReward.Vault.VaultHandle;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public final class KillReward extends JavaPlugin {
    public static FileConfiguration config;
    private static KillReward instance;
    private File configFile;

    @Override
    public void onEnable() {
        instance = this;
        configFile = new File(getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            boolean isCreateDir = configFile.getParentFile().mkdirs();
            if (!isCreateDir) {
                getLogger().warning("Failed to create config.yml!");
                return;
            }
            saveResource("config.yml", false);
        }
        reloadConfigFile();
        ExpReward expReward = new ExpReward();
        RewardUtil rewardUtil = new RewardUtil();
        ExpHandle expHandle = new ExpHandle();
        getServer().getPluginManager().registerEvents(new PlayerListener(this, expReward, rewardUtil, expHandle), this);
        if (!VaultHandle.setupEconomy()) {
            getLogger().severe("Vault plugin not found! Disabling plugin.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        // 注册命令
        Objects.requireNonNull(getCommand("kr")).setExecutor(new RewardCommand());
        getLogger().info("Enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Disabled!");
    }

    //获取插件实例
    public static KillReward getInstance() {
        return instance;
    }

    //保存配置文件
    public void saveConfigFile() {
        try {
            config.save(configFile);
        } catch (IOException e) {
            System.out.println("保存配置文件出错！");
        }
    }

    // 重载配置文件
    public void reloadConfigFile() {
        config = YamlConfiguration.loadConfiguration(configFile);
    }

    // 获取配置文件
    @Override
    public FileConfiguration getConfig() {
        return config;
    }
}
