package com.can.spring.core;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * <pre>
 * SAX 读取xml
 * </pre>
 *
 * @author canxin.li
 * @date 2020-11-03 16:10
 */
public class SaxXmlReader {

	private final static String XML_FILE_PATH = "C:\\Users\\Administrator\\Desktop\\spring-bean.xml";

	public static void main(String[] args) throws Exception {
		saxReadXml(XML_FILE_PATH);
	}


	public static void saxReadXml(String xmlFilePaht) throws Exception {
		// 获取解析工厂
		SAXParserFactory sf = SAXParserFactory.newInstance();
		// 通过工厂获取对应的解析实现类
		SAXParser sp = sf.newSAXParser();
		sp.parse(new InputSource(xmlFilePaht), new MyHandler());
	}

	/**
	 * 自定义事件实现类
	 */
	public static class MyHandler extends DefaultHandler {

		public void characters(char ch[], int start, int length) throws SAXException {
			String s = new String(ch, start, length);
			System.out.println(s);
		}

		public void startElement(String uri, String localName, String qName, Attributes attrs) {
			System.out.println(localName + "///" + qName + "///" + uri + "////" + attrs.getValue("id"));
		}

	}

}
