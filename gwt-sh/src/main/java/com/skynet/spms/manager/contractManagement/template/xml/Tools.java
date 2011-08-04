package com.skynet.spms.manager.contractManagement.template.xml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.skynet.spms.persistence.dto.ProvisionDTO;

public class Tools {

	private static Logger log = LoggerFactory.getLogger(Tools.class);

	public Tools() {
	}

	/**
	 * java bean to xml doc
	 * 
	 * @param o
	 * @param root
	 */
	public void convertT(ProvisionDTO o, Element root) {
		// create a provision element
		Element provision = root.addElement("provision");
		// create child element for provision
		Element idElement = provision.addElement("id");
		idElement.setText(o.getId());
		Element parentIdElement = provision.addElement("parentId");
		parentIdElement.setText(o.getParentId());
		Element createByElement = provision.addElement("createBy");
		createByElement.setText(o.getCreateBy());
		Element createDateElement = provision.addElement("createDate");
		createDateElement.setText(o.getCreateDate());
		Element keywordElement = provision.addElement("keyword");
		keywordElement.setText(o.getKeywordkey());
		Element versionElement = provision.addElement("version");
		versionElement.setText(o.getVersion());
		Element deletedElement = provision.addElement("deleted");
		deletedElement.setText(o.getDeleted());
		Element itemNumberElement = provision.addElement("itemNumber");
		itemNumberElement.setText(o.getItemNumber());
		Element titleEnElement = provision.addElement("title_en");
		titleEnElement.setText(o.getTitle_en());
		Element titleZhElement = provision.addElement("title_zh");
		titleZhElement.setText(o.getTitle_zh());
		Element contentEnElement = provision.addElement("content_en");
		contentEnElement.setText(o.getContent_en());
		Element contentZhElement = provision.addElement("content_zh");
		contentZhElement.setText(o.getContent_zh());
		log.info("the bean converted to doc");
	}

	/**
	 * make uuid
	 * 
	 * @return
	 */
	public synchronized String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * read xml return document
	 * 
	 * @param filePath
	 * @return
	 */
	public synchronized Document readDocument(String filePath) {
		SAXReader saxReader = new SAXReader();
		Document document = null;
		try {
			document = saxReader.read(new File(filePath));
		} catch (DocumentException e) {
			e.printStackTrace();
			log.error("the file was not found" + e.getMessage());
		}
		return document;
	}

	public synchronized void writeDocument(Document doc, String filePath,OutputFormat outFormat) {
		XMLWriter xmlWriter;
		try {
			xmlWriter = new XMLWriter(new FileWriter(filePath),
					outFormat);
			xmlWriter.write(doc);
			xmlWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
			log.error("the file write failed" + e.getMessage());
		}
		
	}
}
