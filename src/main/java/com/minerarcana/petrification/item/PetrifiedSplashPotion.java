package com.minerarcana.petrification.item;

import com.google.common.collect.Lists;
import com.minerarcana.petrification.content.PetrificationPotions;
import com.minerarcana.petrification.entities.PetrifiedPotionEntity;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PotionEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PotionItem;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectUtils;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.*;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static com.minerarcana.petrification.content.PetrificationEffects.PETRIFICATION;

public class PetrifiedSplashPotion extends PotionItem {

    private final Supplier<EffectInstance> potionEffect;

    public PetrifiedSplashPotion(int amplifier) {
        super(new Item.Properties().maxStackSize(1).group(ItemGroup.BREWING));
        this.potionEffect = () -> new EffectInstance(PETRIFICATION.get(), 600, amplifier);
    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        if (!worldIn.isRemote) {
            PotionEntity potionentity = new PetrifiedPotionEntity(worldIn, playerIn);
            potionentity.setItem(itemstack);
            potionentity.func_234612_a_(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, -20.0F, 0.5F, 1.0F);
            worldIn.addEntity(potionentity);
        }

        if (!playerIn.abilities.isCreativeMode) {
            itemstack.shrink(1);
        }

        return ActionResult.func_233538_a_(itemstack, worldIn.isRemote());
    }

    public Supplier<EffectInstance> getPotionEffect() {
        return potionEffect;
    }

    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
        if (this.isInGroup(group)) {
            items.add(PotionUtils.addPotionToItemStack(new ItemStack(this), PetrificationPotions.PETRIFICATION_POTION.get()));
        }
    }

    @Override
    public ItemStack getDefaultInstance() {
        return PotionUtils.addPotionToItemStack(super.getDefaultInstance(), PetrificationPotions.PETRIFICATION_POTION.get());
    }

    public String getTranslationKey(ItemStack stack) {
        return this.getTranslationKey();
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        List<EffectInstance> list = new ArrayList<>();
        list.add(getPotionEffect().get());
        List<Pair<Attribute, AttributeModifier>> list1 = Lists.newArrayList();

        for (EffectInstance effectinstance : list) {
            IFormattableTextComponent iformattabletextcomponent = new TranslationTextComponent(effectinstance.getEffectName());
            Effect effect = effectinstance.getPotion();
            Map<Attribute, AttributeModifier> map = effect.getAttributeModifierMap();
            if (!map.isEmpty()) {
                for (Map.Entry<Attribute, AttributeModifier> entry : map.entrySet()) {
                    AttributeModifier attributemodifier = entry.getValue();
                    AttributeModifier attributemodifier1 = new AttributeModifier(attributemodifier.getName(), effect.getAttributeModifierAmount(effectinstance.getAmplifier(), attributemodifier), attributemodifier.getOperation());
                    list1.add(new Pair<>(entry.getKey(), attributemodifier1));
                }
            }

            if (effectinstance.getAmplifier() > 0) {
                iformattabletextcomponent = new TranslationTextComponent("potion.withAmplifier", iformattabletextcomponent, new TranslationTextComponent("potion.potency." + effectinstance.getAmplifier()));
            }

            if (effectinstance.getDuration() > 20) {
                iformattabletextcomponent = new TranslationTextComponent("potion.withDuration", iformattabletextcomponent, EffectUtils.getPotionDurationString(effectinstance, 1.0F));
            }

            tooltip.add(iformattabletextcomponent.mergeStyle(effect.getEffectType().getColor()));
        }


        if (!list1.isEmpty()) {
            tooltip.add(StringTextComponent.EMPTY);
            tooltip.add((new TranslationTextComponent("potion.whenDrank")).mergeStyle(TextFormatting.DARK_PURPLE));

            for (Pair<Attribute, AttributeModifier> pair : list1) {
                AttributeModifier attributemodifier2 = pair.getSecond();
                double d0 = attributemodifier2.getAmount();
                double d1;
                if (attributemodifier2.getOperation() != AttributeModifier.Operation.MULTIPLY_BASE && attributemodifier2.getOperation() != AttributeModifier.Operation.MULTIPLY_TOTAL) {
                    d1 = attributemodifier2.getAmount();
                } else {
                    d1 = attributemodifier2.getAmount() * 100.0D;
                }

                if (d0 > 0.0D) {
                    tooltip.add((new TranslationTextComponent("attribute.modifier.plus." + attributemodifier2.getOperation().getId(), ItemStack.DECIMALFORMAT.format(d1), new TranslationTextComponent(pair.getFirst().func_233754_c_()))).mergeStyle(TextFormatting.BLUE));
                } else if (d0 < 0.0D) {
                    d1 = d1 * -1.0D;
                    tooltip.add((new TranslationTextComponent("attribute.modifier.take." + attributemodifier2.getOperation().getId(), ItemStack.DECIMALFORMAT.format(d1), new TranslationTextComponent(pair.getFirst().func_233754_c_()))).mergeStyle(TextFormatting.RED));
                }
            }
        }
    }


}
