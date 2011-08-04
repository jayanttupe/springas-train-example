package com.skynet.spms.manager.contractManagement.template.xml;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.skynet.spms.persistence.dto.ProvisionDTO;

/**
 * 合同条款模板操作
 * 
 * @author tqc
 * 
 */
public class XmlProvisionManager {

	private static Logger log = LoggerFactory
			.getLogger(XmlProvisionManager.class);

	private OutputFormat outFormat = new OutputFormat();

	Tools tool = new Tools();

	private List<Element> result = new ArrayList<Element>();;

	/**
	 * create doc for provision
	 * 
	 * @param o
	 *            if the parameter is null,the document will be created just
	 *            with a root element
	 * @throws Exception
	 */
	public void createDocument(ProvisionDTO o, String filePath) {
		// create a doc
		Document doc = DocumentHelper.createDocument();
		// the root element
		Element root = doc.addElement("provisions");
		if (o != null) {
			// the pk used uuid
			o.setId(tool.uuid());
			log.debug("Provision's id is:" + o.getId());
			tool.convertT(o, root);
		}
		log.debug("Provision writing into file");
		tool.writeDocument(doc, filePath, outFormat);

	}

	/**
	 * insert an element in a existing XML Tree
	 * 
	 * @param o
	 *            the new Provision
	 * @filePath filePath the existing file
	 * 
	 * @throws IOException
	 */
	public synchronized void insertElement(ProvisionDTO o, String filePath) {
		Document doc = tool.readDocument(filePath);
		Element root = doc.getRootElement();
		o.setId(tool.uuid());
		tool.convertT(o, root);
		log.debug("insert one element to root");
		tool.writeDocument(doc, filePath, outFormat);
	}

	/**
	 * remove an element from root by id
	 * 
	 * @param ProvisionId
	 */
	@SuppressWarnings("unchecked")
	public synchronized void removeElement(String provisionId, String filePath) {
		Document doc = tool.readDocument(filePath);
		Element root = doc.getRootElement();
		List<Node> list = root.content();
		Element e = getElementById(provisionId, doc);
		if (e != null) {
			log.debug("prepare to remove element ,id is:" + e.getText().trim());
			list.remove(e);
			// recursion cascade remove childs
			List<Element> childsElements = getElementByParentId(provisionId,
					doc);
			for (Element c : childsElements) {
				list.remove(c);
				log.debug("remove element succeed,id is:" + c.getText().trim());
			}
			tool.writeDocument(doc, filePath, outFormat);
			log.debug("remove element succeed,id is:" + e.getText().trim());
		}

	}

	@SuppressWarnings("unchecked")
	public synchronized void updateElement(ProvisionDTO o, String filePath) {
		// first,find the element remove it ,and add a new element
		Document doc = tool.readDocument(filePath);
		List<Node> list = doc.getRootElement().content();
		Element e = getElementById(o.getId(), doc);
		if (e != null) {
			list.remove(e);
			tool.convertT(o, doc.getRootElement());
			log.debug("update one element to root");
			tool.writeDocument(doc, filePath, outFormat);
		}
	}

	/**
	 * find element by id
	 * 
	 * @param id
	 * @param doc
	 * @return
	 */
	private Element getElementById(String id, Document doc) {
		List<?> ids = doc.selectNodes("/provisions/provision/id");
		Iterator<?> it = ids.iterator();
		Element o = null;
		while (it.hasNext()) {
			Element e = (Element) it.next();
			if (id.equals(e.getText().trim())) {
				o = e.getParent();
				break;
			}
		}
		return o;
	}

	/**
	 * recursion find element by parentId
	 * 
	 * @param parentId
	 * @param doc
	 * @return
	 */
	private List<Element> getElementByParentId(String parentId, Document doc) {
		List<?> ids = doc.selectNodes("/provisions/provision/parentId");
		Iterator<?> it = ids.iterator();
		while (it.hasNext()) {
			Element e = (Element) it.next();
			if (parentId.equals(e.getText().trim())) {
				result.add(e);
				String tar = e.getParent().asXML();
				String tempId = tar.substring(tar.indexOf("<id>") + 4,
						tar.lastIndexOf("</id>"));
				Element ee = getElementById(tempId, doc);
				result.add(ee);
				getElementByParentId(tempId, doc);
			}
		}
		return result;
	}

}
