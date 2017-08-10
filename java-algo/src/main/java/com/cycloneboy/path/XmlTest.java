/**
 * 文件名    :XmlTest.java
 * 项目名称:GraphAglo
 * 描述信息:
 * 版本信息: V1.0
 * 创建日期:2017年4月13日
 * 作者        :CycloneBoy
 *//*

package com.cycloneboy.cycloneboy.path;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;

import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.dom4j.util.AttributeHelper;

import com.sun.beans.decoder.DocumentHandler;
import com.sun.corba.se.impl.orb.ParserTable.TestAcceptor1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;

*/
/**
 * @author CycloneBoy
 *
 *//*

public class XmlTest {

	*/
/**
	 * @param args
	 *//*

	public static void main(String[] args) {
		long lasting = System.currentTimeMillis();
		
		try {
			test();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	*/
/**
	 * test
	 *//*

	public static void test() throws Exception{
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File("src//com//algo//path//test.xml"));

		Element node = document.getRootElement();
		
		listNodes(node);
		
		Element element = node.element("红楼梦");
		Attribute attr = element.attribute("id");
		element.remove(attr);
		element.addAttribute("name", "作者");
		
		Element newElement = element.addElement("朝代");
		newElement.setText("清朝");
		
		Element author = element.element("作者");
		boolean flag = element.remove(author);
		System.out.println(flag);
		
		element.addCDATA("红楼梦，是一部爱情小说。");
		
		writer(document);
	}
	
	*/
/**
	 * 把document 对象写入新的文件
	 * 
	 * @author CycloneBoy
	 * 
	 *//*

	public static void writer(Document document) throws Exception{
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");
		XMLWriter writer = new XMLWriter(new OutputStreamWriter(
				new FileOutputStream(new File("src//com//algo//path//a.xml")),"UTF-8"),format);
		writer.write(document);
		writer.close();
		System.out.println("---------------------------");
		elementMethod(document.getRootElement());
	}
	
	*/
/**
	 * 遍历当前节点元素下面的所有(元素的)子节点
	 * @author CycloneBoy
	 *//*

	public static void listNodes(Element node){
		System.out.println("当前节点的名称::" + node.getName());;
		
		List<Attribute> list = node.attributes();
		for(Attribute attr : list) {
			System.out.println(attr.getText() + "----" + attr.getName()
					+"---" + attr.getValue());
		}
		
		if (!(node.getTextTrim().equals(""))) {
			System.out.println("文本内容::::" + node.getText());
		}
		
		Iterator<Element> it = node.elementIterator();
		
		while(it.hasNext()){
			Element element = it.next();
			listNodes(element);
		}
	}
	
	*/
/**
	 * 介绍Element 中element方法和elements方法的使用
	 * @author CycloneBoy
	 *//*

	 public static void elementMethod(Element node){
		 Element e = node.element("西游记");
		 Element author = e.element("作者");
		 System.out.println(e.getName()+"----"+author.getText());
		 
		 List<Element> authors = e.elements("作者");
		 for(Element aut : authors){
			 System.out.println(aut.getText());
		 }
		 
		 List<Element> elements = e.elements();
		 
		 for(Element el : elements){
			 System.out.println(el.getText());
		 }
	 }
}


















*/
