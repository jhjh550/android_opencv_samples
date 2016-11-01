package world.hello.kr.co.testopencv;

import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class Activity_10Gabor extends BaseActivity
    implements SeekBar.OnSeekBarChangeListener{

    SeekBar seekBar2, seekBar3;
    int kSize = 101;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        seekBar2 = (SeekBar) findViewById(R.id.seekbar2);
        seekBar3 = (SeekBar) findViewById(R.id.seekbar3);

        seekbar.setVisibility(View.VISIBLE);
        seekBar2.setVisibility(View.VISIBLE);
        seekBar3.setVisibility(View.VISIBLE);

        seekbar.setMax(kSize/2);
        seekBar2.setMax(180);
        seekbar.setMax(100);

        seekbar.setOnSeekBarChangeListener(this);
        seekBar2.setOnSeekBarChangeListener(this);
        seekBar3.setOnSeekBarChangeListener(this);

        //
        tv1.setText("Error : (kernel+1.0) * 128.f");

    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        if(b){
            double sigma = (double)seekbar.getProgress();
            double theta = (double)(seekBar2.getProgress()*Math.PI/180);
            double lambd = (double)seekBar3.getProgress();
            double gamma = 1.f;

            Mat kernel = Imgproc.getGaborKernel(new Size(kSize, kSize), sigma, theta, lambd, gamma);
            Mat kernel_norm = new Mat();
            // ? //kernel_norm = (kernel+1.0) * 128.f;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
