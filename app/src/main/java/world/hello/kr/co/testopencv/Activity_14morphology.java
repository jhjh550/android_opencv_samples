package world.hello.kr.co.testopencv;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class Activity_14morphology extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        src = MyUtils.ResourceToMat(this, "milkdrop");
//        Imgproc.morphologyEx(src, dst1, Imgproc.MORPH_GRADIENT, new Mat());
        Imgproc.morphologyEx(src, dst2, Imgproc.MORPH_TOPHAT, new Mat());
//
//        MyUtils.MatToImageView(this, "MORPH_GRADIENT", tv1, dst1, R.id.imageView1);
        MyUtils.MatToImageView(this, "MORPH_TOPHAT", tv2, dst2, R.id.imageView2);
        Imgproc.morphologyEx(src, dst1, Imgproc.MORPH_BLACKHAT, new Mat());
//        Imgproc.morphologyEx(src, dst2, Imgproc.MORPH_HITMISS, new Mat()); // HITMISS ?

        MyUtils.MatToImageView(this, "MORPH_GRADIENT", tv1, dst1, R.id.imageView1);

    }
}
