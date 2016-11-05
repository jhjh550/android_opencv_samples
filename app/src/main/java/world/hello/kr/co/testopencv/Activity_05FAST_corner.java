package world.hello.kr.co.testopencv;

import android.os.Bundle;

import org.opencv.core.KeyPoint;
import org.opencv.core.Mat;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.features2d.FeatureDetector;
import org.opencv.imgproc.Imgproc;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class Activity_05FAST_corner extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Mat src1 = MyUtils.ResourceToMat(this, "building");
        FeatureDetector fast = FeatureDetector.create(FeatureDetector.FAST);
        MatOfKeyPoint points = new MatOfKeyPoint();
        fast.detect(src1, points); // Threshold ???


        dst1 = src1.clone();
        Imgproc.cvtColor(dst1, dst1, Imgproc.COLOR_GRAY2RGB);

        List<KeyPoint> list = points.toList();
        for(KeyPoint kp : list){
            Imgproc.circle(dst1, new Point(kp.pt.x, kp.pt.y), 5, new Scalar(255,0,0), 2);
        }

        MyUtils.MatToImageView(this, "", tv2, dst1, R.id.imageView2);
    }
}
