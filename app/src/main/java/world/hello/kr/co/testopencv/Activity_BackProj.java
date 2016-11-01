package world.hello.kr.co.testopencv;

import android.os.Bundle;

import org.opencv.core.Mat;

public class Activity_BackProj extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        src = new Mat();
        MyUtils.ResourceToMat(this, R.drawable.lenna, src);
        MyUtils.MatToImageView(this, "BackProj", tv1, src, R.id.imageView1);
    }
}
