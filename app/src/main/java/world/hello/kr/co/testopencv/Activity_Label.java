package world.hello.kr.co.testopencv;

import android.os.Bundle;
import android.util.Log;
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

public class Activity_Label extends BaseActivity implements SeekBar.OnSeekBarChangeListener{

    int threshold = 128;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        src = new Mat();
//        dst1 = new Mat();
//        dst2 = new Mat();
//        MyUtils.ResourceToMat(this, R.drawable.polygon, src);
//        Imgproc.cvtColor(src, src, Imgproc.COLOR_BGR2GRAY);
//
//        seekbar.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int value, boolean b) {
        if(b){
            Imgproc.threshold(src, dst1, value, 255, Imgproc.THRESH_BINARY_INV);
            MyUtils.MatToImageView(this, "aaa", tv1, dst1, R.id.imageView1);


            Imgproc.cvtColor(src, dst2, Imgproc.COLOR_GRAY2BGR);
            Mat labels = new Mat();
            Mat stats = new Mat();
            Mat centroids = new Mat();
            int num_labels = Imgproc.connectedComponentsWithStats(dst1, labels, stats, centroids);

            Log.d("Activity_label", "num lables : "+num_labels);

            for(int i=1; i<num_labels; i++){
                int x = (int)stats.get(i, CC_STAT_LEFT)[0];
                int y = (int)stats.get(i, CC_STAT_TOP)[0];
                int w = (int)stats.get(i, CC_STAT_WIDTH)[0];
                int h = (int)stats.get(i, CC_STAT_HEIGHT)[0];

                if(stats.get(i, CC_STAT_AREA)[0] < 100 ||
                        stats.get(i, CC_STAT_AREA)[0] > (200*200))
                    continue;

//                Rect rect = new Rect(x,y,w,h);
                Point pt1 = new Point(x,y);
                Point pt2 = new Point(x+w, y+h);
                Imgproc.rectangle(dst2, pt1, pt2, new Scalar(0,255,255),2);


                MyUtils.MatToImageView(this, "bbb", tv2, dst2, R.id.imageView2);
            }
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
