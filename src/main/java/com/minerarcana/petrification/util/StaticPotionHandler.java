package com.minerarcana.petrification.util;

import com.google.common.collect.ImmutableList;
import net.minecraft.potion.EffectInstance;

import java.util.List;

import static com.minerarcana.petrification.content.PetrificationEffects.PETRIFICATION;

public class StaticPotionHandler {

    public static final List<EffectInstance> STRONG_PETRIFICATION_INSTANCE = ImmutableList.of(new EffectInstance(PETRIFICATION.get(), 3000, 9));
    public static final List<EffectInstance> LONG_PETRIFICATION_INSTANCE = ImmutableList.of(new EffectInstance(PETRIFICATION.get(), 3000, 2));

}
