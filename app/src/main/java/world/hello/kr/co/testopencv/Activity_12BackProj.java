package world.hello.kr.co.testopencv;

import android.os.Bundle;

import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

public class Activity_12BackProj extends BaseActivity {

    // http://www.java2s.com/Open-Source/Android_Free_Code/Development/opencv/org_opencv_tutorials_imgprocBackProjectionActivity_java.htm
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        src = MyUtils.ResourceToMat(this, "sistar1", Imgcodecs.CV_LOAD_IMAGE_COLOR);
        Imgproc.cvtColor(src, src, Imgproc.COLOR_BGR2RGB);

        Mat src_mask = MyUtils.ResourceToMat(this, "sistar1_mask");
        Mat src_ycrcb = new Mat();
        Imgproc.cvtColor(src, src_ycrcb, Imgproc.COLOR_RGB2YCrCb);

        List<Mat> listYcrcb = new ArrayList<>();
        listYcrcb.add(src_ycrcb);

        int cr_bins = 128;
        int cb_bins = 128;
        MatOfInt histSize = new MatOfInt(cr_bins, cb_bins);

        MatOfFloat ranges = new MatOfFloat(0, 256, 0, 256);
        Mat hist = new Mat();
        MatOfInt channels = new MatOfInt(0,1);
        Imgproc.calcHist(listYcrcb, channels, src_mask, hist, histSize, ranges);
        Core.normalize(hist, hist, 0, 255, Core.NORM_MINMAX, -1, new Mat());

        Mat backproj = new Mat();
        Imgproc.calcBackProject(listYcrcb, channels, hist, backproj, ranges, 1);

        Mat dst = new Mat(src.rows(), src.cols(), CvType.CV_8UC3);
        src.copyTo(dst, backproj);

        MyUtils.MatToImageView(this, "sistar", tv1, backproj, R.id.imageView1);
        MyUtils.MatToImageView(this, "mask", tv2, dst, R.id.imageView2);


    }
}
