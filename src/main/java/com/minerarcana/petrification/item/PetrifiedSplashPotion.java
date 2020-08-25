package com.minerarcana.petrification.item;

import com.google.common.collect.Lists;
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
import net.minecraft.potion.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.*;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class PetrifiedSplashPotion extends PotionItem {

    private final Supplier<Potion> potion;

    public PetrifiedSplashPotion(Supplier<Potion> potion) {
        super(new Item.Properties().maxStackSize(1).group(ItemGroup.BREWING));
        this.potion = potion;
    }

    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand handIn) {
        ItemStack itemstack = player.getHeldItem(handIn);
        if (!world.isRemote) {
            PotionEntity potionentity = getPotionEntity(world, player);
            potionentity.setItem(itemstack);
            potionentity.func_234612_a_(player, player.rotationPitch, player.rotationYaw, -20.0F, 0.5F, 1.0F);
            world.addEntity(potionentity);
        }

        if (!player.abilities.isCreativeMode) {
            itemstack.shrink(1);
        }

        return ActionResult.func_233538_a_(itemstack, world.isRemote());
    }

    public PotionEntity getPotionEntity(World world, PlayerEntity player){
        return new PetrifiedPotionEntity(world, player);
    }

    public Potion getPotion() {
        return potion.get();
    }

    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
        if (this.isInGroup(group)) {
            items.add(PotionUtils.addPotionToItemStack(new ItemStack(this), getPotion()));
        }
    }

    @Override
    public ItemStack getDefaultInstance() {
        return PotionUtils.addPotionToItemStack(super.getDefaultInstance(), getPotion());
    }

    public String getTranslationKey(ItemStack stack) {
        return this.getTranslationKey();
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        List<EffectInstance> list = getPotion().getEffects();
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

    public int getColour(int index){
        if(index == 0) {
            return getPotion().getEffects().get(0).getPotion().getLiquidColor();
        }
        return 16777215;
    }

}
