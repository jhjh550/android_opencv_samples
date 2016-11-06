package world.hello.kr.co.testopencv;

import android.os.Bundle;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.Random;

import static org.opencv.core.CvType.CV_8UC3;
import static org.opencv.imgproc.Imgproc.LINE_8;

public class Activity_16contour extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        src = MyUtils.ResourceToMat(this, "contours");
        Mat src_copy = src.clone();

        Mat hierarchy = new Mat();
        ArrayList<MatOfPoint> contours = new ArrayList<>();
        Imgproc.findContours(src_copy, contours, hierarchy, Imgproc.RETR_CCOMP, Imgproc.CHAIN_APPROX_SIMPLE);

        Mat dst = new Mat(src.rows(), src.cols(), CV_8UC3);
        Random random = new Random();
        for(int i=0; i<=0; i=(int)hierarchy.get(i,0)[0]){
            Scalar color = new Scalar(random.nextInt(255), random.nextInt(255), random.nextInt(255));
            Imgproc.drawContours(dst, contours, i, color, -1, LINE_8, hierarchy, 8, new Point());
        }

        MyUtils.MatToImageView(this, "src", tv1, src, R.id.imageView1);
        MyUtils.MatToImageView(this, "dst", tv2, dst, R.id.imageView2);

    }
}
