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
import org.xml.sax.InputSource;
import org.xml.sax.helpers.DefaultHandler;

/**
 * <pre>
 * Spring 内部对 SAX 解析的
 * </pre>
 *
 * @author canxin.li
 * @date 2020-11-04 14:59
 */
public class SpringXmlReader {

	public static void main(String[] args) throws Exception {

		// 资源路径
		// 1. ClassPathResource 支持 classpath: 等配置
		// 2. EncodedResource 对我们的资源进行一层包装, 支持指定编码方式和字符集
		EncodedResource encodedResource = new EncodedResource(new ClassPathResource("classpath:spring-bean.xml"));

		// 获取需要解析的文件的 SAXParser 需要的 InputSource 对象
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
			System.out.println("测试结果 beanName:" + id + " beanClass："+ clazz +" scope：" + scope);
		}
	}
}
