package world.hello.kr.co.testopencv;

import android.os.Bundle;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;

import static org.opencv.imgproc.Imgproc.COLOR_GRAY2RGB;
import static org.opencv.imgproc.Imgproc.THRESH_BINARY_INV;
import static org.opencv.imgproc.Imgproc.THRESH_OTSU;
import static org.opencv.imgproc.Imgproc.arcLength;
import static org.opencv.imgproc.Imgproc.dilate;
import static org.opencv.imgproc.Imgproc.erode;
import static org.opencv.imgproc.Imgproc.threshold;
//http://stackoverflow.com/questions/11273588/how-to-convert-matofpoint-to-matofpoint2f-in-opencv-java-api
public class Activity_18ShapeDetect extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        src = MyUtils.ResourceToMat(this, "polygon");
        Mat src_bin = new Mat();
        threshold(src, src_bin, 0, 255, THRESH_BINARY_INV | THRESH_OTSU);
        erode(src_bin, src_bin, new Mat());
        dilate(src_bin, src_bin, new Mat());

        MyUtils.MatToImageView(this, "polygon", tv1, src_bin, R.id.imageView1);

        Mat hierarchy = new Mat();
        ArrayList<MatOfPoint> contours = new ArrayList<>();
        Imgproc.findContours(src_bin, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_NONE);

        Mat dst = new Mat();
        Imgproc.cvtColor(src_bin, dst, COLOR_GRAY2RGB);

        for(int i=0; i<contours.size(); i++){
            double value = Imgproc.arcLength(new MatOfPoint2f(contours.get(i).toArray()), true)*0.02;
            MatOfPoint2f approx = new MatOfPoint2f();
            Imgproc.approxPolyDP(new MatOfPoint2f(contours.get(i).toArray()), approx, value, true);

            MatOfPoint approxf1 = new MatOfPoint();
            approx.convertTo(approxf1, CvType.CV_32S);
            float areaContourArea = (float) Imgproc.contourArea(approx);
            if(Math.abs(areaContourArea)<100 || !Imgproc.isContourConvex(approxf1))
                continue;

            int vtc = approx.toList().size();
            switch (vtc){
                case 3:
                    setLabel(dst, "TRI", contours.get(i));
                    break;
                case 4:
                    setLabel(dst, "RECT", contours.get(i));
                    break;
                case 5:
                    setLabel(dst, "PENTA", contours.get(i));
                    break;
                case 6:
                    setLabel(dst, "HEXA", contours.get(i));
                    break;
                default:
                    double len = arcLength(approx, true);
                    if ((4. * Math.PI * areaContourArea / (len * len)) > 0.9) {
                        setLabel(dst, "CIR", contours.get(i));
                    }
                    break;
            }

        }
        MyUtils.MatToImageView(this, "dst", tv2, dst, R.id.imageView2);


    }

    private void setLabel(Mat img, String label, MatOfPoint contour){
        ArrayList<MatOfPoint> list = new ArrayList<MatOfPoint>();
        list.add(contour);
        Imgproc.drawContours(img, list, 0, new Scalar(255, 255, 255), 1);

        Rect rect = Imgproc.boundingRect(contour);
        Point pt = new Point(rect.x, rect.y);
        Imgproc.putText(img, label, pt, Core.FONT_HERSHEY_SIMPLEX, 0.4, new Scalar(255, 255, 0));
    }
}








