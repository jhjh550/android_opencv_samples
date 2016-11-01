package world.hello.kr.co.testopencv;

import android.os.Bundle;

import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import static org.opencv.core.Core.MinMaxLocResult;
import static org.opencv.core.Core.NORM_MINMAX;
import static org.opencv.core.Core.minMaxLoc;
import static org.opencv.core.Core.normalize;
import static org.opencv.core.CvType.CV_8U;
import static world.hello.kr.co.testopencv.R.drawable.templ;

public class Activity_TemplateMatching extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MyUtils.ResourceToMat(this, R.drawable.lenna, src);
        Imgproc.cvtColor(src, src, Imgproc.COLOR_RGB2GRAY);

        Mat tmpl = new Mat();
        MyUtils.ResourceToMat(this, templ, tmpl);
        Imgproc.cvtColor(tmpl, tmpl, Imgproc.COLOR_RGB2GRAY);
        MyUtils.MatToImageView(this, "template", tv1, tmpl, R.id.imageView1);

        Imgproc.cvtColor(src, dst1, Imgproc.COLOR_GRAY2RGB);

        Mat res = new Mat();
        Mat res_norm = new Mat();
        Imgproc.matchTemplate(src, tmpl, res, Imgproc.TM_CCOEFF);
        normalize(res, res_norm, 0, 255, NORM_MINMAX, CV_8U);

        //http://stackoverflow.com/questions/17001083/opencv-template-matching-example-in-android
        MinMaxLocResult mmr = minMaxLoc(res);

//        // / Show me what you got
//        Imgproc.rectangle(dst1, matchLoc, new Point(matchLoc.x + templ.cols(),
//                matchLoc.y + templ.rows()), new Scalar(0, 255, 0));



        Imgproc.rectangle(dst1, mmr.maxLoc, mmr.minLoc, new Scalar(255,0,0),3);
        MyUtils.MatToImageView(this, "template matching", tv2, dst1, R.id.imageView2);

    }
}
