package world.hello.kr.co.testopencv;

import android.os.Bundle;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;

public class Activity_hand extends BaseActivity {

    ArrayList<MatOfPoint> contours = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        src = new Mat();
        MyUtils.ResourceToMat(this, R.drawable.hand, src);

        Mat src_hsv = new Mat();
        Imgproc.cvtColor(src, src_hsv, Imgproc.COLOR_RGB2HSV);//.COLOR_BGR2HSV);

        Mat skin = new Mat();
        Scalar lowerb = new Scalar(0,40,0);
        Scalar upperb = new Scalar(20,180,255);

        Core.inRange(src_hsv, lowerb, upperb, skin);

        MyUtils.MatToImageView(this, "hand", tv1, skin, R.id.imageView1);


        Mat skin_copy = skin.clone();
        Imgproc.findContours(skin, contours, new Mat(), Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);


    }
}
