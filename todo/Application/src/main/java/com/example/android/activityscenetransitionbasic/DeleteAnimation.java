package com.example.android.activityscenetransitionbasic;

import android.view.animation.Animation;

public class DeleteAnimation implements Animation.AnimationListener{
    private int position;
    public DeleteAnimation(int position)
    {
        this.position = position;
    }
    @Override
    public void onAnimationEnd(Animation arg0) {
       // removeItem(position);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {


    }

    @Override
    public void onAnimationStart(Animation animation) {

    }
}
