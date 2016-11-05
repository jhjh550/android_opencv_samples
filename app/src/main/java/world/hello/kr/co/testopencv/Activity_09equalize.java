package world.hello.kr.co.testopencv;

import android.os.Bundle;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;

public class Activity_09equalize extends BaseActivity {

    ArrayList<Mat> planes = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        src = MyUtils.ResourceToMat(this, "pepper", Imgcodecs.CV_LOAD_IMAGE_COLOR);
        dst1 = src.clone();
        Imgproc.cvtColor(dst1, dst1, Imgproc.COLOR_BGR2RGB);
        MyUtils.MatToImageView(this, "original", tv1, dst1, R.id.imageView1);

        Imgproc.cvtColor(src, src, Imgproc.COLOR_BGR2YCrCb);
        Core.split(src, planes);

        Imgproc.equalizeHist(planes.get(0), planes.get(0));
        Imgproc.equalizeHist(planes.get(1), planes.get(1));
        Imgproc.equalizeHist(planes.get(2), planes.get(2));

        Core.merge(planes, dst2);
        Imgproc.cvtColor(dst2, dst2, Imgproc.COLOR_YCrCb2RGB);
        MyUtils.MatToImageView(this, "equalizeHist", tv2, dst2, R.id.imageView2);
    }
}
