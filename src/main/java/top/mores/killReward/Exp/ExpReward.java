package top.mores.killReward.Exp;

import top.mores.killReward.KillReward;

public class ExpReward {
    private final boolean ENABLED= KillReward.config.getBoolean("击杀给与经验值.启用");
    private final boolean ONLY_PLAYER=KillReward.config.getBoolean("击杀给予经验值.仅限玩家");
    private final int XP=KillReward.config.getInt("击杀给予经验值.经验值");
    private final int XP_LEVEL=KillReward.config.getInt("击杀给予经验值.等级上限");


}
