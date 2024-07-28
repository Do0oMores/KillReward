package top.mores.killReward.Utils;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import top.mores.killReward.KillReward;

import java.util.List;

public class RewardUtil {

    public boolean isWORLD_TP_ENABLED() {
        return KillReward.getInstance().getConfig().getBoolean("世界.启用");
    }

    public List<String> getExcludeWorlds(){
        return KillReward.getInstance().getConfig().getStringList("世界.排除的世界");
    }

    //实体是否为玩家类型
    public boolean IsPlayer(Entity entity) {
        return entity.getType().equals(EntityType.PLAYER);
    }

    //世界名是否在排除的世界列表内
    public boolean isInExcludedWorlds(String worldName) {
        return getExcludeWorlds().contains(worldName);
    }
}
