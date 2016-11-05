package world.hello.kr.co.testopencv;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.os.Environment;
import android.renderscript.ScriptGroup;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by jhjh on 2016-10-22.
 */

public class MyUtils {
    public static void ResourceToMat(Context context, int resId, Mat mat){
//        Bitmap myBitmap = BitmapFactory.decodeResource(context.getResources(), resId);
//        Utils.bitmapToMat(myBitmap, mat);

        String path = Environment.getExternalStorageDirectory()+context.getString(R.string.app_name);
        File dir = new File(path);
        File[] files = dir.listFiles();
        for(File f : files){
            String name = f.getName();
            name = name.substring(0, name.lastIndexOf("."));

        }
    }

    public static Mat ResourceToMat(Context context, String resName, int flags){
        String dirPath = Environment.getExternalStorageDirectory()+"/" +
                context.getString(R.string.app_name);
        File dir = new File(dirPath);
        if(dir.exists() == false)
            dir.mkdirs();

        String outputPath="";
        // 외부 폴더에 복사하기
        try {
            AssetManager assetMgr = context.getAssets();
            String[] list = assetMgr.list("");
            for(String fileName : list){
                int lastIndex = fileName.lastIndexOf(".");
                if(lastIndex < 0) continue;
                String name = fileName.substring(0, lastIndex);
                if(name.equals(resName)){
                    InputStream in = assetMgr.open(fileName);
                    outputPath = dirPath+"/"+fileName;
                    File f = new File(outputPath);
                    FileOutputStream out = new FileOutputStream(f);
                    byte[] buffer = new byte[2048];
                    int read = 0;
                    while ( (read = in.read(buffer))>0){
                        out.write(buffer, 0, read);
                    }
                    in.close();
                    out.close();
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return Imgcodecs.imread(outputPath, flags);
    }
    public static Mat ResourceToMat(Context context, String resName){
        return ResourceToMat(context, resName, Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE);
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
