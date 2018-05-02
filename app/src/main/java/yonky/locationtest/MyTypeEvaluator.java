package yonky.locationtest;

import android.animation.PointFEvaluator;
import android.animation.TypeEvaluator;
import android.graphics.PointF;

/**
 * Created by Administrator on 2018/5/2.
 */

public class MyTypeEvaluator implements TypeEvaluator<PointF> {
    //二次贝塞尔曲线
    PointF control;
    public MyTypeEvaluator(PointF control) {
        this.control = control;
    }

    @Override
    public PointF evaluate(float fraction, PointF start, PointF end) {
        return beisaier(start,control,end,fraction);
    }
    public PointF beisaier(PointF start , PointF control ,PointF end ,float t){
        PointF pointF = new PointF();
        pointF.x=(1-t)*(1-t)*start.x +2*t*(1-t)*control.x +t*t*end.x;
        pointF.y=(1-t)*(1-t)*start.y +2*t*(1-t)*control.y +t*t*end.y;

        return pointF;
    }
}
