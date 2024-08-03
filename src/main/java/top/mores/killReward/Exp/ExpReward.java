package top.mores.killReward.Exp;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import top.mores.killReward.KillReward;

public class ExpReward {
    FileConfiguration ConfigFile = KillReward.config;

    public boolean isEXP_FROM_ORB() {
        return KillReward.getInstance().getConfig().getBoolean("击杀给予经验值.获取掉落经验");
    }

    public boolean isENABLED() {
        return KillReward.getInstance().getConfig().getBoolean("击杀给予经验值.启用");
    }

    public int getXP_MIN() {
        return KillReward.getInstance().getConfig().getInt("击杀给予经验值.经验值.min");
    }

    public int getXP_MAX() {
        return KillReward.getInstance().getConfig().getInt("击杀给予经验值.经验值.max");
    }

    public int getXP_LEVEL() {
        return KillReward.getInstance().getConfig().getInt("击杀给予经验值.等级上限");
    }

    public String getMAIN_WORLD() {
        return KillReward.getInstance().getConfig().getString("世界.主世界名");
    }

    //初始化玩家数据
    public void initPlayerData(Player player) {
        String playerName = player.getName();
        if (!ConfigFile.contains("Player." + playerName)) {
            ConfigFile.set("Player." + playerName + ".ExpAmount", 0);
            ConfigFile.set("Player." + playerName + ".KillAmount", 0);
            ConfigFile.set("Player." + playerName + ".DeathAmount", 0);
            KillReward.getInstance().saveConfigFile();
        }
    }

    //更新玩家更改的经验数值
    public void initPlayerExpAmount(Player player, int ExpAmount) {
        String playerName = player.getName();
        int changeExpAmount = ConfigFile.getInt("Player." + playerName + ".ExpAmount") + ExpAmount;
        ConfigFile.set("Player." + playerName + ".ExpAmount", changeExpAmount);
        KillReward.getInstance().saveConfigFile();
    }

    //更新玩家更改的值
    public void addPlayerAmount(Player player, String charValue) {
        String playerName = player.getName();
        int changeAmount = ConfigFile.getInt("Player." + playerName + "." + charValue) + 1;
        ConfigFile.set("Player." + playerName + "." + charValue, changeAmount);
        KillReward.getInstance().saveConfigFile();
    }

    public int getPlayerExpAmount(String playerName) {
        return ConfigFile.getInt("Player." + playerName + ".ExpAmount");
    }

    public int getPlayerKillAmount(String playerName) {
        return ConfigFile.getInt("Player." + playerName + ".KillAmount");
    }

    public int getPlayerDeathAmount(String playerName) {
        return ConfigFile.getInt("Player." + playerName + ".DeathAmount");
    }

    public void replaceAmount(String playerName) {
        ConfigFile.set("Player." + playerName + ".ExpAmount", 0);
        ConfigFile.set("Player." + playerName + ".KillAmount", 0);
        ConfigFile.set("Player." + playerName + ".DeathAmount", 0);
        KillReward.getInstance().saveConfigFile();
    }
}
