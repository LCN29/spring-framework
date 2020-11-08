package com.can.spring.core;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
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
		// 方式 1
		saxReadXml(XML_FILE_PATH);
		// 方式 2
		saxReadXml2(XML_FILE_PATH);
	}


	public static void saxReadXml(String xmlFilePath) throws Exception {
		// 获取解析工厂
		SAXParserFactory sf = SAXParserFactory.newInstance();
		// 通过工厂获取对应的解析实现类
		SAXParser sp = sf.newSAXParser();
		sp.parse(new InputSource(xmlFilePath), new MyHandler());
	}

	public static void saxReadXml2(String xmlFilePath) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		// 不使用命名空间
		factory.setNamespaceAware(false);
		// 不启用校验
		factory.setValidating(false);
		MyHandler handler = new MyHandler();
		DocumentBuilder docBuilder = factory.newDocumentBuilder();
		docBuilder.setEntityResolver(handler);
		docBuilder.setErrorHandler(handler);
		Document doc = docBuilder.parse(new InputSource(xmlFilePath));

		Element root = doc.getDocumentElement();
		NodeList nodeList = root.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if (!(node instanceof Element)) continue;
			Element ele = (Element) node;
			if (!"bean".equals(ele.getNodeName())) continue;
			String id = ele.getAttribute("id");
			String clazz = ele.getAttribute("class");
			String scope = ele.getAttribute("scope");
			System.out.println("Result: beanName: " + id + ", beanClass: "+ clazz +", scope: " + scope);
		}
	}

	/**
	 * 自定义事件实现类
	 */
	public static class MyHandler extends DefaultHandler {

		@Override
		public void characters(char ch[], int start, int length) throws SAXException {
			String s = new String(ch, start, length);
			System.out.println(s);
		}

		@Override
		public void startElement(String uri, String localName, String qName, Attributes attrs) {
			System.out.println(localName + "///" + qName + "///" + uri + "////" + attrs.getValue("id"));
		}
	}

}
