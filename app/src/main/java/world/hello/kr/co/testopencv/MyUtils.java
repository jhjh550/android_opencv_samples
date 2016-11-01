package world.hello.kr.co.testopencv;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import java.io.File;

/**
 * Created by jhjh on 2016-10-22.
 */

public class MyUtils {
    public static void ResourceToMat(Context context, int resId, Mat mat){
//        Bitmap myBitmap = BitmapFactory.decodeResource(context.getResources(), resId);
//        Utils.bitmapToMat(myBitmap, mat);

        String path = Environment.getExternalStorageDirectory()+"/0data";
        File dir = new File(path);
        File[] files = dir.listFiles();
        for(File f : files){
            String name = f.getName();
            name = name.substring(0, name.lastIndexOf("."));

        }
    }

    public static Mat ResourceToMat(String resName, int flags){
        String path = Environment.getExternalStorageDirectory()+"/0data";
        File dir = new File(path);
        File[] files = dir.listFiles();
        for(File f : files){
            String name = f.getName();
            name = name.substring(0, name.lastIndexOf("."));
            if(name.equals(resName)){
                return Imgcodecs.imread(f.getAbsolutePath(), flags);
            }
        }
        return null;
    }
    public static Mat ResourceToMat(String resName){
        return ResourceToMat(resName, Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE);
    }

    public static void MatToImageView(Activity activity, String text, TextView tv, Mat mat, int resId){
        Bitmap drawBitmap = Bitmap.createBitmap(mat.cols(), mat.rows(),
                Bitmap.Config.ARGB_8888);

        Utils.matToBitmap(mat, drawBitmap);
        ImageView iv = (ImageView)activity.findViewById(resId);
        iv.setImageBitmap(drawBitmap);
        iv.setVisibility(View.VISIBLE);

        tv.setVisibility(View.VISIBLE);
        tv.setText(text);
    }
}
