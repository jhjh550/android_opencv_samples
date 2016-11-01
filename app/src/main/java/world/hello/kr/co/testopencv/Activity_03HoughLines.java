package world.hello.kr.co.testopencv;

import android.os.Bundle;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

public class Activity_03HoughLines extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Mat src1 = MyUtils.ResourceToMat("building");
        MyUtils.MatToImageView(this, "", tv1, src1, R.id.imageView1);

        Imgproc.Canny(src1, dst1, 50, 200, 3, false);
        Imgproc.cvtColor(dst1, dst2, Imgproc.COLOR_GRAY2RGB);

        Mat lines = new Mat();
        Imgproc.HoughLinesP(dst1, lines, 1, (Math.PI/180.d), 160, 50.d, 5.d);

        for (int x = 0; x < lines.cols(); x++) {
            double[] vec = lines.get(0, x);
            double x1 = vec[0],
                    y1 = vec[1],
                    x2 = vec[2],
                    y2 = vec[3];
            Point start = new Point(x1, y1);
            Point end = new Point(x2, y2);

            Imgproc.line(dst2, start, end, new Scalar(255, 0, 0), 3);

        }
        MyUtils.MatToImageView(this, "HoughLines", tv2, dst2, R.id.imageView2);
    }
}
