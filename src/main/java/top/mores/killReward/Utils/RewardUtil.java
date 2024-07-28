package top.mores.killReward.Utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import top.mores.killReward.KillReward;

import java.util.List;

public class RewardUtil {

    FileConfiguration ConfigFile = KillReward.config;

    private final boolean WORLD_TP_ENABLED = ConfigFile.getBoolean("击杀给予经验值.世界.启用");
    private final List<String> EXCLUDE_WORLDS = ConfigFile.getStringList("击杀给予经验值.世界.排除的世界");

    public boolean isWORLD_TP_ENABLED() {
        return WORLD_TP_ENABLED;
    }

    /**
     * 实体是否为玩家类型判断
     *
     * @param entity 实体
     * @return boolean
     */
    public boolean IsPlayer(Entity entity) {
        return entity.getType().equals(EntityType.PLAYER);
    }

    public boolean isInExcludedWorlds(String worldName) {
        return EXCLUDE_WORLDS.contains(worldName);
    }
}
