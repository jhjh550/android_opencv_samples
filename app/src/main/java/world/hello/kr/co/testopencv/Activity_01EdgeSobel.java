package world.hello.kr.co.testopencv;

import android.os.Bundle;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class Activity_01EdgeSobel extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Mat src1 = MyUtils.ResourceToMat(this, "lenna");
        Imgproc.Sobel(src1, dst1, -1,1,0,3,1.0,128);
        MyUtils.MatToImageView(this, "Sobel", tv1, dst1, R.id.imageView1);

        Imgproc.Sobel(src1, dst2, -1,1,0,Imgproc.CV_SCHARR, 1.0, 128);
        MyUtils.MatToImageView(this, "Scharr", tv2, dst2, R.id.imageView2);
    }
}
