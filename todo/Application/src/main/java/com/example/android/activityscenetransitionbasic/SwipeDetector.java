package com.example.android.activityscenetransitionbasic;
import android.view.MotionEvent;
import android.view.View;

public class SwipeDetector implements View.OnTouchListener {

    public static enum Action {
        LEFT_TO_RIGHT,
        RIGHT_TO_LEFT,
        UP_TO_DOWN,
        DOWN_TO_UP,
        None
    }

    private static final int HORIZONTAL_MIN_DISTANCE = 100; //Min horizontal dist to swipe
    private static final int VERTICAL_MIN_DISTANCE = 80; // Min vertical dist to swipe
    private float downX, downY, upX, upY; // coordinates
    private Action mSwipeDetected = Action.None; // Last action

    public boolean swipeDetected() {
        return mSwipeDetected != Action.None;
    }

    public Action getAction() {
        return mSwipeDetected;
    }

    /**
     * Swipe detect
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                downX = event.getX();
                downY = event.getY();
                mSwipeDetected = Action.None;
                return false;
            }
            case MotionEvent.ACTION_MOVE: {
                upX = event.getX();
                upY = event.getY();

                float deltaX = downX - upX;
                float deltaY = downY - upY;

                // Horizontal swipe
                if (Math.abs(deltaX) > HORIZONTAL_MIN_DISTANCE) {
                    // left to right
                    if (deltaX < 0) {
                        mSwipeDetected = Action.LEFT_TO_RIGHT;
                        return true;
                    }
                    // right to left
                    if (deltaX > 0) {
                        mSwipeDetected = Action.RIGHT_TO_LEFT;
                        return true;
                    }
                } else

                    // vertical swipe
                    if (Math.abs(deltaY) > VERTICAL_MIN_DISTANCE) {
                        // up to down
                        if (deltaY < 0) {
                            mSwipeDetected = Action.UP_TO_DOWN;
                            return false;
                        }
                        // down to up
                        if (deltaY > 0) {
                            mSwipeDetected = Action.DOWN_TO_UP;
                            return false;
                        }
                    }
                return true;
            }
        }
        return false;
    }
}