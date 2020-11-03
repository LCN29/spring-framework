package com.can.spring.core;

import org.springframework.beans.factory.xml.DefaultDocumentLoader;
import org.springframework.beans.factory.xml.DocumentLoader;
import org.springframework.beans.factory.xml.ResourceEntityResolver;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * <pre>
 *
 * </pre>
 *
 * @author canxin.li
 * @date 2020-11-03 16:10
 */
public class XmlReader {

	public static void main(String[] args) throws Exception {

		// 设置资源
		EncodedResource encodedResource = new EncodedResource(new ClassPathResource("spring-bean.xml"));
		// 加载解析
		InputSource inputSource = new InputSource(encodedResource.getResource().getInputStream());

		DocumentLoader documentLoader = new DefaultDocumentLoader();
		// DefaultHandler 回调函数, ResourceEntityResolver 解析
		Document doc = documentLoader.loadDocument(inputSource, new ResourceEntityResolver(new PathMatchingResourcePatternResolver()), new DefaultHandler(), 3, false);

		// 输出结果
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
			//System.out.println("测试结果 beanName:" + id + " beanClass："+ clazz +" scope：" + scope);
		}

		defaultFn();
	}


	public static void defaultFn() throws Exception {
		SAXParserFactory sf = SAXParserFactory.newInstance();
		SAXParser sp = sf.newSAXParser();

		sp.parse(new InputSource("C:\\Users\\Administrator\\Desktop\\spring-bean.xml"), new MyHandler());
	}

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
