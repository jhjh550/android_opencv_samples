package world.hello.kr.co.testopencv;

import android.os.Bundle;
import android.widget.Toast;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfInt;
import org.opencv.core.MatOfInt4;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

public class Activity_19hand extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        src = MyUtils.ResourceToMat(this, "hand", Imgcodecs.CV_LOAD_IMAGE_COLOR);
        Imgproc.cvtColor(src, src, Imgproc.COLOR_BGR2RGB);
        MyUtils.MatToImageView(this, "hand", tv1, src, R.id.imageView1);

        Mat src_hsv = new Mat();
        Imgproc.cvtColor(src, src_hsv, Imgproc.COLOR_RGB2HSV);

        Mat skin = new Mat();
        Scalar lowerb = new Scalar(0,40,0);
        Scalar upperb = new Scalar(40, 200, 255);
        Core.inRange(src_hsv, lowerb, upperb, skin);
        Imgproc.erode(skin, skin, new Mat());
        Imgproc.dilate(skin, skin, new Mat());

//        MyUtils.MatToImageView(this, "hand", tv2, skin, R.id.imageView2);

        Mat hierarchy = new Mat();
        ArrayList<MatOfPoint> contours = new ArrayList<>();
        Imgproc.findContours(skin, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);

        if(contours.size()<1){
            Toast.makeText(this, "Hand not found", Toast.LENGTH_SHORT).show();
            return;
        }


        int maxIdx = 0;
        double maxArea = Imgproc.contourArea(contours.get(0));
        for(int i=0; i< contours.size(); i++){
            if(Imgproc.contourArea(contours.get(i))>maxArea){
                maxArea = Imgproc.contourArea(contours.get(i));
                maxIdx = i;
            }
        }




        Mat dst = src.clone();
        MatOfPoint hand_contour = contours.get(maxIdx);
        MatOfInt hull = new MatOfInt();
        MatOfInt4 defects = new MatOfInt4();

        Imgproc.convexHull(hand_contour, hull);
        Imgproc.convexityDefects(hand_contour, hull, defects);

        ArrayList<MatOfPoint> list = new ArrayList<MatOfPoint>();
        list.add(hand_contour);
        Imgproc.drawContours(dst, list, 0, new Scalar(255, 255, 0), 1);

        //http://stackoverflow.com/questions/18073239/opencv-java-convexity-defects-computer-vision
        List<Integer> defactsList =  defects.toList();
        List<Point> data = hand_contour.toList();
        for(int i=0; i<defactsList.size(); i+=4){
            Point ptEnd = data.get(defactsList.get(i));
            Point ptStart = data.get(defactsList.get(i+1));
            Point ptFar = data.get(defactsList.get(i+2));
            float depth = defactsList.get(i+3) / 256.f;

            if(depth>10){
                Imgproc.circle(dst, ptStart, 5, new Scalar(255,0,0), 2);
                Imgproc.circle(dst, ptEnd, 5, new Scalar(0,255,0), 2);
                Imgproc.circle(dst, ptFar, 5, new Scalar(0,0,255), 2);

                Imgproc.line(dst, ptStart, ptFar, new Scalar(255, 255, 255),2);
                Imgproc.line(dst, ptEnd, ptFar, new Scalar(0, 255, 255), 2);
            }
        }

        MyUtils.MatToImageView(this, "dst", tv2, dst, R.id.imageView2);

    }
}















