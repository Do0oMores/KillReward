package top.mores.killReward.Utils;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

public class RewardUtil {

    /**
     * 实体是否为玩家类型判断
     * @param entity 实体
     * @return boolean
     */
    public boolean IsPlayer(Entity entity){
        return entity.getType().equals(EntityType.PLAYER);
    }
}
