package world.hello.kr.co.testopencv;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;

public class Activity_08SplitColor extends BaseActivity {

    ArrayList<Mat> planes = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        src = MyUtils.ResourceToMat(this, "pepper", Imgcodecs.CV_LOAD_IMAGE_COLOR);
        Imgproc.cvtColor(src, src, Imgproc.COLOR_BGR2YCrCb); //.COLOR_BGR2HSV); //.COLOR_BGR2RGB);
        MyUtils.MatToImageView(this, "YCrCb", tv1, src, R.id.imageView1);

        Core.split(src, planes);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    handler.sendEmptyMessage(0);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    int index = 0;
    Handler handler = new Handler(){
        public void handleMessage(Message msg){
            MyUtils.MatToImageView(Activity_08SplitColor.this,
                    "split "+index, tv2, planes.get(index), R.id.imageView2);
            index++;
            if(index>=planes.size()) index = 0;
        }
    };
}
