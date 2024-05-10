package by.dragonsurvivalteam.dragonsurvival.common.dragon_types.bodies;

import by.dragonsurvivalteam.dragonsurvival.common.dragon_types.AbstractDragonBody;
import by.dragonsurvivalteam.dragonsurvival.config.obj.ConfigOption;
import by.dragonsurvivalteam.dragonsurvival.config.obj.ConfigRange;
import by.dragonsurvivalteam.dragonsurvival.config.obj.ConfigSide;
import net.minecraft.nbt.CompoundTag;

public class EastBodyType extends AbstractDragonBody {

	@ConfigRange(min = -10.0, max = 100)
	@ConfigOption(side = ConfigSide.SERVER, category = {"bonuses", "body", "east"}, key = "eastJumpBonus", comment = "The jump bonus given to East-type dragons")
	public static Double eastJumpBonus = 0.0;

	@ConfigRange(min = -10.0, max = 100)
	@ConfigOption(side = ConfigSide.SERVER, category = {"bonuses", "body", "east"}, key = "eastRunBonus", comment = "The run bonus given to East-type dragons")
	public static Double eastRunBonus = 0.0;

	@ConfigRange(min = -10.0, max = 100)
	@ConfigOption(side = ConfigSide.SERVER, category = {"bonuses", "body", "east"}, key = "eastStepBonus", comment = "The step bonus given to East-type dragons")
	public static Double eastStepBonus = 0.0;

	@ConfigRange(min = -10.0, max = 100)
	@ConfigOption(side = ConfigSide.SERVER, category = {"bonuses", "body", "east"}, key = "eastDamageBonus", comment = "The damage bonus given to East-type dragons")
	public static Double eastDamageBonus = 0.0;

	@ConfigRange(min = -10.0, max = 100)
	@ConfigOption(side = ConfigSide.SERVER, category = {"bonuses", "body", "east"}, key = "eastArmorBonus", comment = "The armor bonus given to East-type dragons")
	public static Double eastArmorBonus = 0.0;

	@ConfigRange(min = -10.0, max = 100)
	@ConfigOption(side = ConfigSide.SERVER, category = {"bonuses", "body", "east"}, key = "eastManaBonus", comment = "The mana bonus given to East-type dragons")
	public static Double eastManaBonus = 0.0;

	@ConfigRange(min = -10.0, max = 100)
	@ConfigOption(side = ConfigSide.SERVER, category = {"bonuses", "body", "east"}, key = "eastSwimSpeedBonus", comment = "The swimSpeed bonus given to East-type dragons")
	public static Double eastSwimSpeedBonus = 0.0;

	@ConfigRange(min = -10.0, max = 100)
	@ConfigOption(side = ConfigSide.SERVER, category = {"bonuses", "body", "east"}, key = "eastHealthBonus", comment = "The health bonus given to East-type dragons")
	public static Double eastHealthBonus = 0.0;

	@ConfigRange(min = -10.0, max = 100)
	@ConfigOption(side = ConfigSide.SERVER, category = {"bonuses", "body", "east"}, key = "eastKnockbackBonus", comment = "The knockback bonus given to East-type dragons")
	public static Double eastKnockbackBonus = 0.0;

	@ConfigRange(min = 0.0, max = 100)
	@ConfigOption(side = ConfigSide.SERVER, category = {"bonuses", "body", "east"}, key = "eastDamageMult", comment = "The damage multiplier given to East-type dragons")
	public static Double eastDamageMult = 1.0;

	@ConfigRange(min = 0.0, max = 100)
	@ConfigOption(side = ConfigSide.SERVER, category = {"bonuses", "body", "east"}, key = "eastExpMult", comment = "The exp multiplier given to East-type dragons")
	public static Double eastExpMult = 1.0;

	@ConfigRange(min = 0.0, max = 10)
	@ConfigOption(side = ConfigSide.SERVER, category = {"bonuses", "body", "east"}, key = "eastFlightMult", comment = "The flight multiplier given to East-type dragons")
	public static Double eastFlightMult = 1.0;

	@ConfigRange(min = 0.0, max = 100)
	@ConfigOption(side = ConfigSide.SERVER, category = {"bonuses", "body", "east"}, key = "eastFlightStaminaMult", comment = "The flightStamina multiplier given to East-type dragons")
	public static Double eastFlightStaminaMult = 1.0;

	@ConfigRange(min = 0.0, max = 100)
	@ConfigOption(side = ConfigSide.SERVER, category = {"bonuses", "body", "east"}, key = "eastHealthMult", comment = "The health multiplier given to East-type dragons")
	public static Double eastHealthMult = 1.0;

	@ConfigRange(min = 0.0, max = 100)
	@ConfigOption(side = ConfigSide.SERVER, category = {"bonuses", "body", "east"}, key = "eastGravityMult", comment = "The gravity multiplier given to East-type dragons")
	public static Double eastGravityMult = 1.0;

	@Override
	public CompoundTag writeNBT() {
		CompoundTag tag = new CompoundTag();
		return tag;
	}

	@Override
	public void readNBT(CompoundTag base) {}

	@Override
	public String getBodyName() {
		return "east";
	}

	@Override
	public void onPlayerUpdate() {}

	@Override
	public void onPlayerDeath() {}

	public Double getJumpBonus() {
		return eastJumpBonus;
	}
	public Double getRunBonus() {
		return eastRunBonus;
	}
	public Double getFlightMult() {
		return eastFlightMult;
	}
	public Double getStepBonus() {
		return eastStepBonus;
	}
	public Double getDamageBonus() {
		return eastDamageBonus;
	}
	public Double getArmorBonus() {
		return eastArmorBonus;
	}
	public Double getManaBonus() {
		return eastManaBonus;
	}
	public Double getSwimSpeedBonus() {
		return eastSwimSpeedBonus;
	}
	public Double getHealthBonus() {
		return eastHealthBonus;
	}
	public Double getKnockbackBonus() {
		return eastKnockbackBonus;
	}
	public Double getDamageMult() {
		return eastDamageMult;
	}
	public Double getExpMult() {
		return eastExpMult;
	}
	public Double getFlightStaminaMult() {
		return eastFlightStaminaMult;
	}
	public Double getHealthMult() {
		return eastHealthMult;
	}
	public Double getGravityMult() {
		return eastGravityMult;
	}
}
