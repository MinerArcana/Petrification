package com.minerarcana.petrification.util;

import com.google.common.collect.ImmutableList;
import net.minecraft.potion.EffectInstance;

import java.util.List;

import static com.minerarcana.petrification.content.PetrificationEffects.PETRIFICATION;
import static com.minerarcana.petrification.content.PetrificationEffects.REVIVIFY;

public class StaticPotionHandler {

    public static final List<EffectInstance> STRONG_PETRIFICATION_INSTANCE = ImmutableList.of(new EffectInstance(PETRIFICATION.get(), 600, 9));
    public static final List<EffectInstance> LONG_PETRIFICATION_INSTANCE = ImmutableList.of(new EffectInstance(PETRIFICATION.get(), 600, 2));
    public static final List<EffectInstance> BASE_PETRIFICATION_INSTANCE = ImmutableList.of(new EffectInstance(PETRIFICATION.get(), 600, 0));

    public static final List<EffectInstance> BASE_REVIVIFY_INSTANCE = ImmutableList.of(new EffectInstance(REVIVIFY.get(), 600, 0));


}
