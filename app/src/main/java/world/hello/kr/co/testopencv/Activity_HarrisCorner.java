package world.hello.kr.co.testopencv;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import java.io.IOException;

import static org.opencv.core.CvType.CV_32FC1;


public class Activity_HarrisCorner extends AppCompatActivity {

    static {
        System.loadLibrary("opencv_java3");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_harris_corner);

        Mat drawImage;
        Bitmap drawBitmap = null;


//
//        try {
//            drawImage = Utils.loadResource(this, R.drawable.lenna);
//            Imgproc.cvtColor(drawImage, drawImage, Imgproc.COLOR_BGR2GRAY);//.COLOR_BGR2RGB);
//
//            Mat harris  = new Mat(drawImage.rows(), drawImage.cols(), CV_32FC1);
//            Imgproc.cornerHarris(drawImage, harris, 2,3,0.04);
//
//            Mat harris_norm = new Mat();
//            Core.normalize(harris, harris_norm, 0, 255, Core.NORM_MINMAX, CV_32FC1, new Mat());
//
//            Mat dst = drawImage.clone();
//            Imgproc.cvtColor(dst, dst, Imgproc.COLOR_GRAY2BGR);
//
//            for(int i=0; i<harris.rows(); i++){
//                for(int k=0; k<harris.cols(); k++){
//                    if(harris_norm.get(i,k)[0]>180.f){
//                        Imgproc.circle(dst, new Point(i,k), 5, new Scalar(255,0,0));
//                    }
//                }
//            }
//
//
//
//            drawBitmap = Bitmap.createBitmap(drawImage.cols(), drawImage.rows(),
//                    Bitmap.Config.ARGB_8888);
//            Utils.matToBitmap(dst, drawBitmap);
//            ImageView iv = (ImageView) findViewById(R.id.imageView);
//            iv.setImageBitmap(drawBitmap);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
