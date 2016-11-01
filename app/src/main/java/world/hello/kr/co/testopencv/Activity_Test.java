package world.hello.kr.co.testopencv;

import android.os.Bundle;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;


public class Activity_Test extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Mat src1 = MyUtils.ResourceToMat("lenna");
        Imgproc.Canny(src1, dst1, 50, 150);
        MyUtils.MatToImageView(this, "test", tv1, dst1, R.id.imageView1);
    }
}
