package com.cycloneboy.algo.cv;

import static org.bytedeco.opencv.global.opencv_imgcodecs.imread;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imwrite;
import static org.bytedeco.opencv.global.opencv_imgproc.GaussianBlur;

import lombok.extern.slf4j.Slf4j;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Size;

/** Create by sl on 2019-08-27 23:45 */
@Slf4j
public class Smoother {

  public static void smooth() {

    String filename = "";

    Mat image = imread(filename);
    if (image != null) {
      GaussianBlur(image, image, new Size(3, 3), 0);
      imwrite(filename, image);
    }
  }
}
