package top.mores.killReward.Exp;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import top.mores.killReward.KillReward;

public class ExpReward {
    FileConfiguration ConfigFile = KillReward.config;
    private final boolean ENABLED = ConfigFile.getBoolean("击杀给予经验值.启用");
    private final int XP_MIN = ConfigFile.getInt("击杀给予经验值.经验值.min");
    private final int XP_MAX=ConfigFile.getInt("击杀给予经验值.经验.max");
    private final int XP_LEVEL = ConfigFile.getInt("击杀给予经验值.等级上限");
    private final boolean EXP_FROM_ORB = ConfigFile.getBoolean("击杀给予经验值.获取掉落经验");
    private final String MAIN_WORLD = ConfigFile.getString("击杀给予经验值.主世界");

    public boolean isEXP_FROM_ORB() {
        return EXP_FROM_ORB;
    }

    public boolean isENABLED() {
        return ENABLED;
    }

    public int getXP_MIN() {
        return XP_MIN;
    }

    public int getXP_MAX(){
        return XP_MAX;
    }

    public int getXP_LEVEL() {
        return XP_LEVEL;
    }

    public String getMAIN_WORLD() {
        return MAIN_WORLD;
    }

    //初始化玩家数据
    public void initPlayerData(Player player){
        String playerName = player.getName();
        if (!ConfigFile.contains("Player."+playerName)){
            ConfigFile.set("Player." + playerName + ".ChangeExp", false);
            ConfigFile.set("Player." + playerName + ".ExpAmount", 0);
            KillReward.getInstance().saveConfigFile();
        }
    }
}
