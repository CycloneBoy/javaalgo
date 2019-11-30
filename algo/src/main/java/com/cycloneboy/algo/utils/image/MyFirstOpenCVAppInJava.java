package com.cycloneboy.springcloud.kafkahello.util.image;

import static org.bytedeco.opencv.global.opencv_core.BORDER_DEFAULT;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imread;
import static org.bytedeco.opencv.global.opencv_imgproc.Laplacian;

import javax.swing.WindowConstants;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.opencv.opencv_core.Mat;

/**
 * Create by  sl on 2019-08-28 00:02
 */
public class MyFirstOpenCVAppInJava {

  public static void main(String[] args) {

    // Read an image.
    final Mat src = imread("/home/sl/workspace/image/boldt.jpg");
    display(src, "Input");

    // Apply Laplacian filter
    final Mat dest = new Mat();
    Laplacian(src, dest, src.depth(), 1, 3, 0, BORDER_DEFAULT);
    display(dest, "Laplacian");
  }

  //---------------------------------------------------------------------------

  static void display(Mat image, String caption) {
    // Create image window named "My Image".
    final CanvasFrame canvas = new CanvasFrame(caption, 1.0);

    // Request closing of the application when the image window is closed.
    canvas.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    // Convert from OpenCV Mat to Java Buffered image for display
    final OpenCVFrameConverter converter = new OpenCVFrameConverter.ToMat();
    // Show image on window.
    canvas.showImage(converter.convert(image));
  }
}
