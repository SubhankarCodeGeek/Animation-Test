package com.example.subhankar.animationtest;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.Guideline;
import androidx.appcompat.app.AppCompatActivity;

import android.transition.TransitionManager;
import android.view.View;
import android.view.animation.BounceInterpolator;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout rootLayout;
    private ConstraintSet constraintOldSet = new ConstraintSet();
    private ConstraintSet constraintNewSet = new ConstraintSet();
    private boolean altLayout = false;

    private Guideline guideline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        guideline = findViewById(R.id.guideline);
//        bounceingAnimation();

        rootLayout = findViewById(R.id.parent_layout);

        constraintOldSet.clone(rootLayout);
        constraintNewSet.clone(this, R.layout.final_xml);
    }

    private void bounceingAnimation() {
        int end = ((ConstraintLayout.LayoutParams) guideline.getLayoutParams()).guideEnd;

        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(guideline, "GuidelineEnd", 0, end);
        objectAnimator.setDuration(2000);
        objectAnimator.setInterpolator(new BounceInterpolator());

        objectAnimator.start();
    }

    public void swapView(View view) {
        TransitionManager.beginDelayedTransition(rootLayout);

        if (!altLayout) {
            constraintNewSet.applyTo(rootLayout);
            altLayout = true;
        } else {
            constraintOldSet.applyTo(rootLayout);
            altLayout = false;
        }
    }
}
