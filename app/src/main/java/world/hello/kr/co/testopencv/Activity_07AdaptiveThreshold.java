package world.hello.kr.co.testopencv;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.SeekBar;

import org.opencv.imgproc.Imgproc;

public class Activity_07AdaptiveThreshold extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        seekbar.setVisibility(View.VISIBLE);
        seekbar.setMax(255);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(b)
                    changeAdaptiveThreshold(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        src = MyUtils.ResourceToMat("sudoku");
        MyUtils.MatToImageView(this, "original", tv2, src, R.id.imageView2);
        changeAdaptiveThreshold(100);

    }
    Handler handler = new Handler(){
        public void handleMessage(Message msg){
            MyUtils.MatToImageView(Activity_07AdaptiveThreshold.this,
                    "adaptive threshold "+msg.arg1, tv1, dst1, R.id.imageView1);
        }
    };

    class MyThread extends Thread{
        int value;
        MyThread(int value){
            this.value = value;
        }
        public void run(){
            Imgproc.adaptiveThreshold(src, dst1, 255, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C,
                    Imgproc.THRESH_BINARY, value, 0);
            handler.obtainMessage(0,value,0).sendToTarget();
        }
    }
    private void changeAdaptiveThreshold(int value){
        if(value<3) value = 3;

        if ((value & 0x00000001) == 0)
            value--;

        new MyThread(value).start();
    }
}
