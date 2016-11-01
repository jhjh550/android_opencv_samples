package world.hello.kr.co.testopencv;

import android.os.Bundle;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

public class Activity_markerDetect extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MyUtils.ResourceToMat(this, R.drawable.capture01, src);
        Imgproc.cvtColor(src, src, Imgproc.COLOR_RGB2GRAY);

        Mat src_bin = new Mat();
        Imgproc.threshold(src, src_bin, 0, 255, Imgproc.THRESH_BINARY_INV | Imgproc.THRESH_OTSU);
        MyUtils.MatToImageView(this, "marker", tv1, src_bin, R.id.imageView1);

        Mat src_bin_copy = src_bin.clone();
        List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
        Mat hierachy = new Mat();
        Imgproc.findContours(src_bin_copy, contours, hierachy, Imgproc.RETR_CCOMP, Imgproc.CHAIN_APPROX_NONE);

        Imgproc.cvtColor(src, dst1, Imgproc.COLOR_GRAY2RGB);



        for(int i=0; i<contours.size(); i++){
            MatOfPoint wrapper = contours.get(i);
//        for(MatOfPoint wrapper : contours){
            double area = Imgproc.contourArea(wrapper);
//            if(area < 200 || area > 240000)
//                continue;

            Imgproc.drawContours(dst1, contours, i, new Scalar(255,0,0), 1);
        }

        MyUtils.MatToImageView(this, "contours", tv2, dst1, R.id.imageView2);




    }
}
