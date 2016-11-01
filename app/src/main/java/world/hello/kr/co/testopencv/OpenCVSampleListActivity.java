package world.hello.kr.co.testopencv;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class OpenCVSampleListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    ListView listView;
    String[] nameList = {"Test", "Sift Detect",
            "Marker Detect", "Template Matching", "VideoCapture", "Harris",
            "BackProj", "Labeling", "hand", "01_SobelEdge", "02_Canny",
            "03_HoughLines", "04_HarrisCorner", "05_FAST corner detect", "06_Threshold",
            "07_Adaptive Threshold", "08_Split Color",
            "09_EqualizeHist", "10_GaborKernel (Operator Error)", "11_InRange"
    };

    Class<?>[] classList = {Activity_Test.class, Activity_siftDetect.class,
            Activity_markerDetect.class, Activity_TemplateMatching.class,
            Activity_VideoCapture.class, Activity_HarrisCorner.class,

            Activity_BackProj.class,  Activity_Label.class,
            Activity_hand.class, Activity_01EdgeSobel.class, Activity_02Canny.class,
            Activity_03HoughLines.class, Activity_04HarrisCorner.class,
            Activity_05FAST_corner.class, Activity_06Threshold.class,
            Activity_07AdaptiveThreshold.class, Activity_08SplitColor.class,
            Activity_09equalize.class, Activity_10Gabor.class,
            Activity_11InRange.class

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_list);

        listView = (ListView) findViewById(R.id.listview);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, nameList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
        Intent intent= new Intent();
        intent.setClass(this, classList[pos]);
        intent.putExtra("name", nameList[pos]);
        startActivity(intent);
    }
}
