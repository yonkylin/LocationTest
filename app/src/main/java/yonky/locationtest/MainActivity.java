package yonky.locationtest;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.PointF;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView recycler;
    ConstraintLayout parent;
    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("yonky","creat activity");
        ImageView shoot = findViewById(R.id.shoot);
        parent = findViewById(R.id.parent);
        recycler = findViewById(R.id.recycle);
        mContext = this;
        shoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final FakeView fakeView = new FakeView(mContext);
                parent.addView(fakeView);
                fakeView.setImageResource(R.drawable.circle);

                int[] start = new int[2];
                int[] parentPosition = new int[2];
                int[] end = new int[2];

                view.getLocationInWindow(start);
                recycler.getLocationInWindow(end);
                parent.getLocationInWindow(parentPosition);

                PointF controlPoint=new PointF(end[0],start[1]-parentPosition[1]);
                PointF startPoint = new PointF(start[0],start[1]-parentPosition[1]);
                PointF endPoint = new PointF(end[0],end[1]-parentPosition[1]);

                fakeView.setPointF(controlPoint);

                ObjectAnimator objectAnimator = ObjectAnimator.ofObject(fakeView,"pointF",new MyTypeEvaluator(controlPoint),startPoint,endPoint);
                objectAnimator.setDuration(500);
                objectAnimator.setInterpolator(new AccelerateInterpolator(1));
                objectAnimator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {
                        fakeView.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        fakeView.setVisibility(View.GONE);
                        parent.removeView(fakeView);
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });

                objectAnimator.start();

            }
        });
    }
}
