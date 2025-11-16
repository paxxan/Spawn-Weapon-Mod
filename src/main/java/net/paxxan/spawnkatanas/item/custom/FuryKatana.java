package net.paxxan.spawnkatanas.item.custom;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.paxxan.spawnkatanas.damage_type.ModDamageTypes;
import net.paxxan.spawnkatanas.component.ModComponents;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.Identifier;
import net.paxxan.spawnkatanas.SpawnKatanas;

import java.util.List;

public class FuryKatana extends SwordItem {

    public FuryKatana(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }



    public static AttributeModifiersComponent createAttributeModifiers() {
        return AttributeModifiersComponent.builder()
                .add(
                        EntityAttributes.ATTACK_DAMAGE,
                        new EntityAttributeModifier(BASE_ATTACK_DAMAGE_MODIFIER_ID, 6.0, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                )
                .add(
                        EntityAttributes.ATTACK_SPEED,
                        new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, 1F, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                )
                .add(
                        EntityAttributes.MOVEMENT_SPEED,
                        new EntityAttributeModifier(Identifier.of(SpawnKatanas.MOD_ID, "MOVEMENT_SPEED"), 3F, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                )
                .build();
    }

    public static ToolComponent createToolComponent() {
        return new ToolComponent(List.of(), 1.0F, 2);
    }

    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {

        int combo = stack.getOrDefault(ModComponents.FURY_COMBO,0);
        stack.set(ModComponents.FURY_COMBO, ++combo);
        if(target.getWorld() instanceof ServerWorld world) {
            if (stack.get(ModComponents.FURY_COMBO) == 3) {
                DamageSource damageSource = new DamageSource(
                        world.getRegistryManager()
                                .getOrThrow(RegistryKeys.DAMAGE_TYPE)
                                .getEntry(ModDamageTypes.COMBO_DAMAGE.getValue()).get()
                );
                stack.set(ModComponents.FURY_COMBO, 0);
                target.damage(world, damageSource, 6);
                attacker.velocityModified = true;
                attacker.addVelocity(attacker.getRotationVector());
                target.addVelocity(attacker.getRotationVector().multiply(-0.25));
                target.playSound(SoundEvents.ITEM_TRIDENT_RETURN, 2f, 0.7f);
                return super.postHit(stack, target, attacker);
            }
        }
        attacker.velocityModified = true;
        attacker.addVelocity(attacker.getRotationVector().multiply(0.5));
        target.addVelocity(attacker.getRotationVector().multiply(-0.125));
        return super.postHit(stack, target, attacker);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getMainHandStack();
        stack.damage(2, user);
        var instance = new StatusEffectInstance(StatusEffects.SPEED, 100, 4, false, false, false);
        user.addStatusEffect(instance);
        var instance2 = new StatusEffectInstance(StatusEffects.JUMP_BOOST, 100, 1, false, false, false);
        user.addStatusEffect(instance2);
        var instance3 = new StatusEffectInstance(StatusEffects.RESISTANCE, 20, 2, false, false, false);
        user.addStatusEffect(instance3);
        user.velocityModified = true;
        Vec3d direction = user.getRotationVector().multiply(0.25);
        user.addVelocity(direction);
        user.playSound(SoundEvents.ENTITY_EVOKER_CAST_SPELL, 2f, 0.7f);
        user.getItemCooldownManager().set(stack, 500);
        return super.use(world, user, hand);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (stack.contains(ModComponents.FURY_COMBO)) {
            int count = stack.getOrDefault(ModComponents.FURY_COMBO,0);
            tooltip.add(Text.translatable("item.spawn_katanas.fury_combo.info", count).formatted(Formatting.RED));
        }
        super.appendTooltip(stack, context, tooltip, type);
    }
}
