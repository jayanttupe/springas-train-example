package com.skynet.spms.web.control.contractManagement;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import com.skynet.spms.manager.contractManagement.template.xml.XmlProvisionManager;

public class XmlDS extends HttpServlet {

	private static final long serialVersionUID = 1L;

	XmlProvisionManager mannager = new XmlProvisionManager();

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String fileName = req.getParameter("fn");
		if (fileName != null && !"".equals(fileName)) {
			String realPath = req.getSession().getServletContext()
					.getRealPath("ds/templates/" + fileName + ".xml");
			File xml = new File(realPath);
			if (!xml.exists()) {
				try {
					xml.createNewFile();
				} catch (RuntimeException e) {
					e.printStackTrace();
				}
				mannager.createDocument(null, realPath);
			}

			SAXReader saxReader = new SAXReader();
			Document document;
			try {
				document = saxReader.read(xml);
				resp.setContentType("text/xml;charset=utf-8");
				resp.getWriter().print(document.asXML());
			} catch (DocumentException e) {
				e.printStackTrace();
				resp.getWriter().print("error");
			}
		}

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
