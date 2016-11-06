package world.hello.kr.co.testopencv;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import static org.opencv.imgproc.Imgproc.CC_STAT_AREA;
import static org.opencv.imgproc.Imgproc.CC_STAT_HEIGHT;
import static org.opencv.imgproc.Imgproc.CC_STAT_LEFT;
import static org.opencv.imgproc.Imgproc.CC_STAT_TOP;
import static org.opencv.imgproc.Imgproc.CC_STAT_WIDTH;
import static org.opencv.imgproc.Imgproc.COLOR_GRAY2RGB;
import static org.opencv.imgproc.Imgproc.THRESH_BINARY_INV;
import static org.opencv.imgproc.Imgproc.connectedComponents;
import static org.opencv.imgproc.Imgproc.connectedComponentsWithStats;
import static org.opencv.imgproc.Imgproc.cvtColor;
import static org.opencv.imgproc.Imgproc.dilate;
import static org.opencv.imgproc.Imgproc.erode;

public class Activity_15labeling extends BaseActivity implements SeekBar.OnSeekBarChangeListener{

    Mat src_th, dst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        src_th = new Mat();
        dst = new Mat();
        src = MyUtils.ResourceToMat(this, "polygon");
//        MyUtils.MatToImageView(this, "polygon", tv2, src, R.id.imageView2);

        seekbar.setVisibility(View.VISIBLE);
        seekbar.setOnSeekBarChangeListener(this);
        labeling(100);
        seekbar.setProgress(100);

    }

    private void labeling(int value){
        Imgproc.threshold(src, src_th, value, 255, THRESH_BINARY_INV);
        erode(src_th, src_th, new Mat());
        dilate(src_th, src_th, new Mat());
        cvtColor(src, dst, COLOR_GRAY2RGB);

        MyUtils.MatToImageView(this, "threashold", tv2, src_th, R.id.imageView2);

        Mat labels = new Mat();
        Mat stats = new Mat();
        Mat centroids = new Mat();
        int num_labels = connectedComponentsWithStats(src_th, labels, stats, centroids);

        for(int i=0; i<num_labels; i++){
            int x = (int)stats.get(i, CC_STAT_LEFT)[0];
            int y = (int)stats.get(i, CC_STAT_TOP)[0];
            int w = (int)stats.get(i, CC_STAT_WIDTH)[0];
            int h = (int)stats.get(i, CC_STAT_HEIGHT)[0];

            if(stats.get(i, CC_STAT_AREA)[0]<100 ||
                    stats.get(i, CC_STAT_AREA)[0]>(200*200)){
                continue;
            }

            Point pt1 = new Point(x,y);
            Point pt2 = new Point(x+w, y+h);
            Imgproc.rectangle(dst, pt1, pt2, new Scalar(255, 0, 0), 2);

            MyUtils.MatToImageView(this, "labeling : "+value, tv1, dst, R.id.imageView1);
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
        if(b) {
            labeling(progress);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
