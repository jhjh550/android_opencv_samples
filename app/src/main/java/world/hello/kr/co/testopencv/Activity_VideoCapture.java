package world.hello.kr.co.testopencv;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import org.opencv.android.FpsMeter;
import org.opencv.videoio.VideoCapture;

import java.util.List;

import static org.opencv.videoio.Videoio.CV_CAP_ANDROID;
import static org.opencv.videoio.Videoio.CV_CAP_PROP_FRAME_HEIGHT;
import static org.opencv.videoio.Videoio.CV_CAP_PROP_FRAME_WIDTH;


public class Activity_VideoCapture extends AppCompatActivity {

    Camera mCamera;
    abstract class MyVideoView extends SurfaceView implements SurfaceHolder.Callback, Runnable{
        private static final String TAG = "MyVideoView";
        private SurfaceHolder holder;
        private VideoCapture videoCapture;
        private FpsMeter fps;

        public MyVideoView(Context context) {
            super(context);
            holder = getHolder();
            holder.addCallback(this);
            fps = new FpsMeter();
        }

        @Override
        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            videoCapture = new VideoCapture(CV_CAP_ANDROID);
            if(videoCapture.isOpened()){
                (new Thread(this)).start();
            }else{
                videoCapture.release();
                videoCapture = null;
            }
        }

        //http://chiwoos.tistory.com/189
        @Override
        public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int newWidth, int newHeight) {
            synchronized (this){
                if(videoCapture != null && videoCapture.isOpened()){
                    Log.i(TAG, "before mCamera.getSupportedPreviewSizes()");

                    List<Camera.Size> sizes = mCamera.getParameters().getSupportedVideoSizes();
                    int width = newWidth;
                    int height = newHeight;

                    // selecting optimal camera preview size
                    double minDiff = Double.MAX_VALUE;
                    for(Camera.Size size: sizes){
                        if(Math.abs(size.height - newHeight)<minDiff){
                            width = size.width;
                            height = size.height;
                            minDiff = Math.abs(size.height - newHeight);
                        }
                    }
                    videoCapture.set(CV_CAP_PROP_FRAME_WIDTH, width);
                    videoCapture.set(CV_CAP_PROP_FRAME_HEIGHT, height);

//
//                    Log.i(TAG, "after mCamera.getSupportedPreviewSizes()");
//
//                    int mFrameWidth = width;
//
//                    int mFrameHeight = height;
                }
            }
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            if(videoCapture !=  null){
                synchronized (this){
                    videoCapture.release();
                    videoCapture = null;
                }
            }
        }

        protected abstract Bitmap processFrame(VideoCapture capture);

        @Override
        public void run() {
            fps.init();
            while (true){
                Bitmap bmp = null;
                synchronized (this){
                    if(videoCapture == null)
                        break;

                    if(!videoCapture.grab()){
                        Log.e(TAG, "videocapture.grab() failed");
                        break;
                    }
                    bmp = processFrame(videoCapture);
                    fps.measure();
                }
                if(bmp != null){
                    Canvas canvas = holder.lockCanvas();
                    if(canvas != null){
                        canvas.drawBitmap(bmp, (canvas.getWidth()-bmp.getWidth())/2,
                                (canvas.getHeight()-bmp.getHeight())/2, null);
                        fps.draw(canvas, (canvas.getWidth()-bmp.getWidth())/2,0);
                        holder.unlockCanvasAndPost(canvas);
                    }
                    bmp.recycle();
                }
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCamera = Camera.open();
    }
}
