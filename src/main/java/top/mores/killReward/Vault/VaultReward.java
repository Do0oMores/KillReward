package top.mores.killReward.Vault;

import top.mores.killReward.KillReward;

public class VaultReward {

    public boolean isENABLED() {
        return KillReward.getInstance().getConfig().getBoolean("击杀给予经济.启用");
    }

    public double getVAULT_MIN() {
        return KillReward.getInstance().getConfig().getDouble("击杀给予经济.经济.min");
    }

    public double getVAULT_MAX() {
        return KillReward.getInstance().getConfig().getDouble("击杀给予经济.经济.max");
    }
}
