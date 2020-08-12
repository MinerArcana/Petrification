package com.minerarcana.petrification.content;

import software.bernie.geckolib.animation.builder.AnimationBuilder;

public class PetrificationAnimations {

    public static final AnimationBuilder IDLE1 = new AnimationBuilder().addAnimation("idle1",true);
    public static final AnimationBuilder IDLE2 = new AnimationBuilder().addAnimation("idle2",false).addAnimation("idle1",true);
    public static final AnimationBuilder WALK = new AnimationBuilder().addAnimation("walk",true);
    public static final AnimationBuilder RUN = new AnimationBuilder().addAnimation("run",true);
    public static final AnimationBuilder ATTACK = new AnimationBuilder().addAnimation("attack");
    public static final AnimationBuilder MOVING_ATTACK = new AnimationBuilder().addAnimation("walk",true).addAnimation("attack");
    public static final AnimationBuilder PETRIFY = new AnimationBuilder().addAnimation("petrify").addAnimation("idle1",true);
    public static final AnimationBuilder LAYEGG = new AnimationBuilder().addAnimation("layegg").addAnimation("idle1",true);

}
