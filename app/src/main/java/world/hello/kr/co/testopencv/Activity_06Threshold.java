package world.hello.kr.co.testopencv;

import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

import org.opencv.imgproc.Imgproc;

import static android.R.attr.value;

public class Activity_06Threshold extends BaseActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        seekbar.setVisibility(View.VISIBLE);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(b){
                    changeThresholdImage(i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        src = MyUtils.ResourceToMat("lenna");
        changeThresholdImage(128);

        Imgproc.threshold(src, dst2, value, 255, Imgproc.THRESH_OTSU);
        MyUtils.MatToImageView(this, "otsu", tv2, dst2, R.id.imageView2);


    }

    private void changeThresholdImage(int value){
        Imgproc.threshold(src, dst1, value, 255, Imgproc.THRESH_BINARY);
        MyUtils.MatToImageView(this, "threshold "+value, tv1, dst1, R.id.imageView1);
    }
}
