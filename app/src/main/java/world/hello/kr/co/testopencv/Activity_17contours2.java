package world.hello.kr.co.testopencv;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.Random;

import static org.opencv.core.CvType.CV_8UC3;

public class Activity_17contours2 extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        src = MyUtils.ResourceToMat(this, "milkdrop");
        Mat src_bin = new Mat();
        Imgproc.threshold(src, src_bin, 0, 255, Imgproc.THRESH_BINARY | Imgproc.THRESH_OTSU);

        Mat hierarchy = new Mat();
        ArrayList<MatOfPoint> contours = new ArrayList<>();
        Imgproc.findContours(src_bin, contours, hierarchy, Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_NONE);

        Random random = new Random();
        Mat dst = new Mat(src.rows(), src.cols(), CV_8UC3);
        for(int i=0; i<contours.size(); i++){
            Scalar color = new Scalar(random.nextInt(255), random.nextInt(255), random.nextInt(255));
            Imgproc.drawContours(dst, contours, i, color, Imgproc.LINE_8);
        }

        MyUtils.MatToImageView(this, "milkdrop", tv1, src, R.id.imageView1);
        MyUtils.MatToImageView(this, "contour", tv2, dst, R.id.imageView2);

    }
}
