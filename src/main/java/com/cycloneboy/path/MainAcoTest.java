/**
 * 文件名    :MainAcoTest.java
 * 项目名称:GraphAglo
 * 描述信息:
 * 版本信息: V1.0
 * 创建日期:2017年4月13日
 * 作者        :CycloneBoy
 *//*

package com.cycloneboy.path;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
*/
/**
 * 主程序 调用ACO求解问题
 * @author CycloneBoy
 *
 *//*

public class MainAcoTest {

    */
/**
     * @param args the command line arguments
     *//*

    public static void main(String[] args) {
        // TODO code application logic here
        acoTest aco;
        aco=new acoTest();
        try {
            aco.init("src//com//algo//path//att48.txt", 100);
            aco.run(1000);
            aco.ReportResult();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainAcoTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainAcoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
*/
