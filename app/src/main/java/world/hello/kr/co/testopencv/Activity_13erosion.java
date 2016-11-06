package world.hello.kr.co.testopencv;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class Activity_13erosion extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        src = MyUtils.ResourceToMat(this, "milkdrop");
        MyUtils.MatToImageView(this, "milkdrop", tv1, src, R.id.imageView1);

        Mat src_th = new Mat();
        double otsu_th = Imgproc.threshold(src, src_th, 0, 255, Imgproc.THRESH_BINARY | Imgproc.THRESH_OTSU);

        Mat dst_erode = new Mat();
        Mat dst_dilate = new Mat();

        Imgproc.erode(src_th, dst_erode, new Mat());
        Imgproc.dilate(src_th, dst_dilate, new Mat());

        MyUtils.MatToImageView(this, "binarization - ostu threshold :  "+otsu_th, tv1, src_th, R.id.imageView1);
//        MyUtils.MatToImageView(this, "erosion", tv2, dst_erode, R.id.imageView2);
        MyUtils.MatToImageView(this, "dilation", tv2, dst_dilate, R.id.imageView2);

    }
}
