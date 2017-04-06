package com.cjt2325.cameralibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;

/**
 * 445263848@qq.com.
 */
public class JCameraView extends JCameraViewBase {


    public JCameraView(Context context) {
        this(context, null);
    }

    public JCameraView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public JCameraView(final Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        findAvailableCameras();
        SELECTED_CAMERA = CAMERA_POST_POSITION;
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.JCameraView, defStyleAttr, 0);

        iconWidth = a.getDimensionPixelSize(R.styleable.JCameraView_iconWidth, (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, 35, getResources().getDisplayMetrics()));
        iconMargin = a.getDimensionPixelSize(R.styleable.JCameraView_iconMargin, (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, 15, getResources().getDisplayMetrics()));
        iconSrc = a.getResourceId(R.styleable.JCameraView_iconSrc, R.drawable.ic_repeat_black_24dp);

        initView();
        addHolderCallback();
        setAutoFocusCallback();
        setCaptureListener();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        float widthSize = MeasureSpec.getSize(widthMeasureSpec);
        float heightSize = MeasureSpec.getSize(heightMeasureSpec);
        screenProp = heightSize / widthSize;
        Log.i(TAG, "ScreenProp = " + screenProp + " " + widthSize + " " + heightSize);
    }

    public void onResume() {
        mCamera = getCamera(SELECTED_CAMERA);
        if (mCamera != null) {
//            setStartPreview(mCamera, mHolder);
        } else {
            Log.i(TAG, "Camera is null!");
        }
        wakeLock.acquire();
    }

    public void onPause() {
        releaseCamera();
        wakeLock.release();
    }

    @Override
    public boolean onTouchEvent(final MotionEvent event) {
        if (!autoFoucs && event.getAction() == MotionEvent.ACTION_DOWN && SELECTED_CAMERA == CAMERA_POST_POSITION && !isPlay) {
            mCameraFocusListener.onFocusBegin(event.getX(), event.getY());
        }
        return super.onTouchEvent(event);
    }

}
