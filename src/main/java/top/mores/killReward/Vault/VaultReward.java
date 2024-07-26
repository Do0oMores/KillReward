package top.mores.killReward.Vault;

import org.bukkit.configuration.file.FileConfiguration;
import top.mores.killReward.KillReward;

public class VaultReward {
    FileConfiguration ConfigFile = KillReward.config;
    private final boolean ENABLED=ConfigFile.getBoolean("击杀给予经济.启用");
    private final double VAULT_MIN=ConfigFile.getDouble("击杀给予经济.经济.min");
    private final double VAULT_MAX= ConfigFile.getDouble("击杀给予经济.经济.max");

    public boolean isENABLED() {
        return ENABLED;
    }

    public double getVAULT_MIN() {
        return VAULT_MIN;
    }

    public double getVAULT_MAX() {
        return VAULT_MAX;
    }
}
