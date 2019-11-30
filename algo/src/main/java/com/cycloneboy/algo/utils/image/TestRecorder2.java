package com.cycloneboy.springcloud.kafkahello.util.image;


import static org.bytedeco.opencv.global.opencv_imgcodecs.imread;

import java.io.File;
import lombok.extern.slf4j.Slf4j;
import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.opencv.opencv_core.Mat;


/**
 * Create by  sl on 2019-08-28 00:09
 */
@Slf4j
public class TestRecorder2 {

  private static String mp3Path = "/home/sl/workspace/image/test_music/wKgEaVyQtyOAZKWwALJbPQ3L0cE890.mp3"; // 图片集合的目录

  public static void main(String[] args) throws Exception {
    System.out.println("start...");
    String saveMp4name = "/home/sl/workspace/image/f1.mp4"; //保存的视频名称
    // 目录中所有的图片，都是jpg的，以1.jpg,2.jpg的方式，方便操作
    String imagesPath = "/home/sl/workspace/image/test_thread2/"; // 图片集合的目录
    converToVideo(saveMp4name, imagesPath);
    System.out.println("end...");
  }

  public static void converToVideo(String saveMp4name, String imagesPath) throws Exception {
    FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(saveMp4name, 1080, 720);
//		recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264); // 28
    recorder.setVideoCodec(avcodec.AV_CODEC_ID_MPEG4); // 28
//		recorder.setVideoCodec(avcodec.AV_CODEC_ID_MPEG4); // 13
    recorder.setFormat("mp4");
    //	recorder.setFormat("mov,mp4,m4a,3gp,3g2,mj2,h264,ogg,MPEG4");
    recorder.setFrameRate(0.4);
    recorder.setVideoBitrate(8000000);
    recorder.setPixelFormat(0); // yuv420p
    recorder.setAudioQuality(0);
    recorder.setAudioBitrate(192000);     // 音频采样率 //
    recorder.setSampleRate(44100); // 双通道(立体声)
    recorder.setAudioChannels(2);
    recorder.start();
    //
    OpenCVFrameConverter.ToIplImage conveter = new OpenCVFrameConverter.ToIplImage();
    // 列出目录中所有的图片，都是jpg的，以1.jpg,2.jpg的方式，方便操作
    File file = new File(imagesPath);
    File[] flist = file.listFiles();

    FrameGrabber audioGrabber = new FFmpegFrameGrabber(mp3Path);
    audioGrabber.start();// 开始录制音频

    // 循环所有图片
    for (int i = 0; i < flist.length; i++) {
      log.info(flist[i].toString());
      Mat src = imread(flist[i].toString());
      recorder.record(conveter.convert(src));
      src.release();
    }

    Frame audioSamples = null;
    while ((audioSamples = audioGrabber.grabFrame()) != null) {
      recorder.setTimestamp(audioGrabber.getTimestamp());
//      recorder.record(audioSamples); //录入音频 }
    }

    //------------------->end 开始录制音频
    audioGrabber.stop();
    audioGrabber.release();

    recorder.stop();
    recorder.release();
  }


}
