package world.hello.kr.co.testopencv;

import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class Activity_11InRange extends BaseActivity
        implements SeekBar.OnSeekBarChangeListener{
    SeekBar seekBar2, seekBar3, seekBar4;
    Mat src_hsv = new Mat();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        seekBar2 = (SeekBar) findViewById(R.id.seekbar2);
        seekBar3 = (SeekBar) findViewById(R.id.seekbar3);
        seekBar4 = (SeekBar) findViewById(R.id.seekbar4);

        seekbar.setVisibility(View.VISIBLE);
        seekBar2.setVisibility(View.VISIBLE);
        seekBar3.setVisibility(View.VISIBLE);
        seekBar4.setVisibility(View.VISIBLE);

        seekbar.setMax(180);
        seekBar2.setMax(180);
        seekBar3.setMax(255);
        seekBar4.setMax(255);

        seekbar.setProgress(10);
        seekBar2.setProgress(30);
        seekBar3.setProgress(150);
        seekBar4.setProgress(255);

        seekbar.setOnSeekBarChangeListener(this);
        seekBar2.setOnSeekBarChangeListener(this);
        seekBar3.setOnSeekBarChangeListener(this);
        seekBar4.setOnSeekBarChangeListener(this);

        src = MyUtils.ResourceToMat("flower1", Imgcodecs.CV_LOAD_IMAGE_COLOR);
        Imgproc.cvtColor(src, src, Imgproc.COLOR_BGR2RGB);
        MyUtils.MatToImageView(this, "original", tv2, src, R.id.imageView2);

        Imgproc.cvtColor(src, src_hsv, Imgproc.COLOR_RGB2HSV);

        InRange();
    }

    private void InRange(){
        int lowerHue = seekbar.getProgress();
        int upperHue = seekBar2.getProgress();
        int lowerSat = seekBar3.getProgress();
        int upperSat = seekBar4.getProgress();

        String str = "InRange "+lowerHue+" "+upperHue+" "+lowerSat+" "+upperSat;

        Scalar lowerb = new Scalar(lowerHue, lowerSat, 0);
        Scalar upperb = new Scalar(upperHue, upperSat, 255);

        Mat dst_mask = new Mat();
        Core.inRange(src_hsv, lowerb, upperb,  dst_mask);

        Imgproc.cvtColor(src, dst1, Imgproc.COLOR_RGB2GRAY);
        Imgproc.cvtColor(dst1, dst1, Imgproc.COLOR_GRAY2RGB);
        src.copyTo(dst1, dst_mask);
        MyUtils.MatToImageView(this, str, tv1, dst1, R.id.imageView1);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        if(b){
            InRange();
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
