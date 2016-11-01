package world.hello.kr.co.testopencv;

import android.os.Bundle;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import static org.opencv.core.CvType.CV_32FC1;

public class Activity_04HarrisCorner extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Mat src1 = MyUtils.ResourceToMat("building");
        MyUtils.MatToImageView(this, "", tv1, src1, R.id.imageView1);

        Mat harris = new Mat(src1.rows(), src1.cols(), CV_32FC1);
        Imgproc.cornerHarris(src1, harris, 2, 3, 0.04);

        Mat harris_norm = new Mat();
        Core.normalize(harris, harris_norm, 0, 255, Core.NORM_MINMAX, CV_32FC1, new Mat());

        dst1 = src1.clone();
        Imgproc.cvtColor(dst1, dst1, Imgproc.COLOR_GRAY2RGB);
        for(int i=0; i<harris.rows(); i++){
            for(int k=0; k<harris.cols(); k++){
                if(harris_norm.get(i,k)[0]>180.f){
                    Imgproc.circle(dst1, new Point(k,i), 5, new Scalar(255,0,0), 2);
                }
            }
        }

        MyUtils.MatToImageView(this, "", tv2, dst1, R.id.imageView2);
    }
}
