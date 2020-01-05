package com.cycloneboy.basic;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/** Create by sl on 2020-01-15 23:55 */
@Slf4j
public class ClassLoaderDemo {

  @Test
  public void testLoader() {
    System.out.println("ClassLodarDemo's ClassLoader is " + ClassLoaderDemo.class.getClassLoader());
    System.out.println(
        "The Parent of ClassLodarDemo's ClassLoader is "
            + ClassLoaderDemo.class.getClassLoader().getParent());
    System.out.println(
        "The GrandParent of ClassLodarDemo's ClassLoader is "
            + ClassLoaderDemo.class.getClassLoader().getParent().getParent());
  }
}
