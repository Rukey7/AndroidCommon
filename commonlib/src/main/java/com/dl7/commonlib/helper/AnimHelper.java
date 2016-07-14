package com.dl7.commonlib.helper;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by long on 2016/6/6.
 * 动画帮助类
 */
public final class AnimHelper {

    private AnimHelper() {
        throw new RuntimeException("AnimHelper cannot be initialized!");
    }


    public static void bounceInUp(View view, int distance, int duration) {
        ObjectAnimator transYAnim = ObjectAnimator.ofFloat(view, "translationY", distance, -10, 10, 0);
        ObjectAnimator alphaAnim = ObjectAnimator.ofFloat(view, "alpha", 0, 1, 1, 1);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(duration);
        set.playTogether(transYAnim, alphaAnim);
        set.start();
    }


    public static void bounceOutDown(View view, int distance, int duration) {
        ObjectAnimator transYAnim = ObjectAnimator.ofFloat(view, "translationY", 0, -10, 0, distance);
        ObjectAnimator alphaAnim = ObjectAnimator.ofFloat(view, "alpha", 1, 1, 1, 0);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(duration);
        set.playTogether(transYAnim, alphaAnim);
        set.start();
    }


    public static void rotateOut(View view, int circleNum, int duration, Animator.AnimatorListener listener) {
        ObjectAnimator rotate = ObjectAnimator.ofFloat(view, "rotation", 0, 360 * circleNum);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(view, "alpha", 1, 0);
        ObjectAnimator scale = ObjectAnimator.ofFloat(view, "scale", 1, 0.3f);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(duration);
        set.setInterpolator(new AccelerateInterpolator());
        set.playTogether(rotate, alpha, scale);
        set.addListener(listener);
        set.start();
    }

    public static void rotateIn(View view, int circleNum, int duration) {
        ObjectAnimator rotate = ObjectAnimator.ofFloat(view, "rotation", 360 * circleNum, 0);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(view, "alpha", 0, 1);
        ObjectAnimator scale = ObjectAnimator.ofFloat(view, "scale", 0.3f, 1f);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(duration);
        set.setInterpolator(new DecelerateInterpolator());
        set.playTogether(rotate, alpha, scale);
        set.start();
    }

    public static void scaleOut(View view, int duration, Animator.AnimatorListener listener) {
        ObjectAnimator alpha = ObjectAnimator.ofFloat(view, "alpha", 1, 0);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 1f, 1.5f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 1f, 1.5f);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(duration);
        set.setInterpolator(new DecelerateInterpolator());
        set.playTogether(alpha, scaleX, scaleY);
        set.addListener(listener);
        set.start();
    }

    public static void scaleIn(View view, int duration) {
        ObjectAnimator alpha = ObjectAnimator.ofFloat(view, "alpha", 0, 1, 1);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 1.5f, 0.7f, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 1.5f, 0.7f, 1f);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(duration);
        set.setInterpolator(new AccelerateInterpolator());
        set.playTogether(alpha, scaleX, scaleY);
        set.start();
    }

    public static void zoomOut(View view, int duration) {
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 0f, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 0f, 1f);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(duration);
        set.setInterpolator(new BounceInterpolator());
        set.playTogether(scaleX, scaleY);
        set.start();
    }
}
