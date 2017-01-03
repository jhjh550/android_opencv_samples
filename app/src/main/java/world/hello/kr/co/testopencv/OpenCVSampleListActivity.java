package world.hello.kr.co.testopencv;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;



/**
 * http://webnautes.tistory.com/770
 */

// todo : 1. 파일 없으면 카피하는 식으로 수정 2. write/camera runtime permission
public class OpenCVSampleListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    ListView listView;
    String[] nameList = {"Test", "Sift Detect",
            "Marker Detect", "Template Matching", "VideoCapture", "Harris",
             "hand",
            "01_SobelEdge", "02_Canny", "03_HoughLines(...)", "04_HarrisCorner",
            "05_FAST corner detect", "06_Threshold",
            "07_Adaptive Threshold", "08_Split Color",
            "09_EqualizeHist", "10_GaborKernel (Operator Error)", "11_InRange",
            "12_BackProj", "13_Erosion", "14_Morphology", "15_Labeling",
            "16_Contour( drawcontours? )", "17_Contours2", "18_ShapeDetect"
    };

    Class<?>[] classList = {Activity_Test.class, Activity_siftDetect.class,
            Activity_markerDetect.class, Activity_TemplateMatching.class,
            Activity_VideoCapture.class, Activity_HarrisCorner.class,
            Activity_hand.class, Activity_01EdgeSobel.class, Activity_02Canny.class,
            Activity_03HoughLines.class, Activity_04HarrisCorner.class,
            Activity_05FAST_corner.class, Activity_06Threshold.class,
            Activity_07AdaptiveThreshold.class, Activity_08SplitColor.class,
            Activity_09equalize.class, Activity_10Gabor.class,
            Activity_11InRange.class, Activity_12BackProj.class,
            Activity_13erosion.class, Activity_14morphology.class,
            Activity_15labeling.class, Activity_16contour.class,
            Activity_17contours2.class, Activity_18ShapeDetect.class,


    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_list);

        initAsset();

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

    private void initAsset(){
        String dirPath = Environment.getExternalStorageDirectory()+"/TestOpenCV/";

        try {
            AssetManager assetMgr = getAssets();
            String[] list = assetMgr.list("/");
            byte[] buffer = new byte[2048];
            for(String name : list){
                InputStream in = assetMgr.open(name);
                String outPath = dirPath+name;
                FileOutputStream fout = new FileOutputStream(outPath);
                int count;

                while((count = in.read(buffer))>0) {
                    fout.write(buffer);
                }
                in.close();
                fout.close();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}





