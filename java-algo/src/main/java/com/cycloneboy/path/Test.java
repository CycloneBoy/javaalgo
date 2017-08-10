/**
 * 文件名    :Test.java
 * 项目名称:GraphAglo
 * 描述信息:
 * 版本信息: V1.0
 * 创建日期:2017年4月15日
 * 作者        :CycloneBoy
 */
package com.cycloneboy.path;

import java.util.Random;

/**
 * @author CycloneBoy
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final int max = 100; // 用来产生在[0, max)之间的数
        int prev = 0; // 保持前一个生成的随机数，利用它来让产生的随机数不与前一个数重复
        int up=0;
        
        Random rand = new Random(System.currentTimeMillis());
        for (int i = 0; i < 100; ++i) {
            int  r = (rand.nextInt(max - 1) + 1 +prev) % max;
            prev = r;
 
            if (r < 0.5 ) {
				up++;
			}
            
            System.out.printf("%f%s", r, (i + 1) % 10 == 0 ? "\n" : " ");
        }
        System.out.println(" r < max/2 的概率:"+ up*1.0/max);
	}

}
