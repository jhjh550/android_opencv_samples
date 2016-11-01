package world.hello.kr.co.testopencv;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import org.opencv.core.Mat;

import static android.view.View.GONE;

public class BaseActivity extends AppCompatActivity {
    static {
        System.loadLibrary("opencv_java3");
    }
    protected SeekBar seekbar;
    protected ImageView iv1, iv2;
    protected TextView tv1, tv2;
    protected Mat src, dst1, dst2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        src = new Mat();
        dst1 = new Mat();
        dst2 = new Mat();

        seekbar = (SeekBar) findViewById(R.id.seekbar);
        seekbar.setMax(255);

        iv1 = (ImageView) findViewById(R.id.imageView1);
        iv2 = (ImageView) findViewById(R.id.imageView2);

        tv1 = (TextView) findViewById(R.id.textView1);
        tv2 = (TextView) findViewById(R.id.textView2);

        Intent intent = getIntent();
        String title = intent.getStringExtra("name");
        setTitle(title);

        seekbar.setVisibility(GONE);
        iv1.setVisibility(GONE);
        iv2.setVisibility(GONE);
        tv1.setVisibility(GONE);
        tv2.setVisibility(GONE);

    }
}
