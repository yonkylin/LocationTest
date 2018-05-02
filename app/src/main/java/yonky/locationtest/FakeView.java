package yonky.locationtest;

import android.content.Context;
import android.graphics.PointF;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;


/**
 * Created by Administrator on 2018/5/2.
 */

public class FakeView extends AppCompatImageView {
    public FakeView(Context context) {
        super(context);
    }

    public FakeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FakeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    PointF pointF;

    public PointF getPointF() {
        return pointF;
    }

    public void setPointF(PointF pointF) {
        this.pointF = pointF;
        setX(pointF.x);
        setY(pointF.y);
        invalidate();
    }
}
