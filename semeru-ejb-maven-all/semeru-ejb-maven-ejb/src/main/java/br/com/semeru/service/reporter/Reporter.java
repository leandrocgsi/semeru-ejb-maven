package br.com.semeru.service.reporter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import br.com.semeru.fakedata.DataBean;
import br.com.semeru.fakedata.DataBeanMaker;

@RequestScoped
public class Reporter {
	
    @Inject private Logger log;
    
//	private String currentDirectory = System.getProperty("user.dir");
//	private String complementarDirectory = "//src//main//java//br//com//semeru//service//reporter//template//";
//	private String jrxmlDirectory = System.getProperty("user.dir") + "//src//main//java//br//com//semeru//service//reporter//template//" + "test_jasper.jrxml";
    private String jrxmlDirectory = "C:/Users/LEANDRO/Desktop/" + "test_jasper.jrxml";
	
    @Produces
    @Named
	public File makeReport() throws Exception {
		
		long start = System.currentTimeMillis();
		log.info("Init the export process");
		
		InputStream inputStream = new FileInputStream(jrxmlDirectory);

		DataBeanMaker dataBeanMaker = new DataBeanMaker();
		ArrayList<DataBean> dataBeanList = dataBeanMaker.getDataBeanList();
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataBeanList);
		@SuppressWarnings("rawtypes")
		Map parameters = new HashMap();
		JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
		JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters, beanColDataSource);
		File pdf = File.createTempFile("output.", ".pdf");
		JasperExportManager.exportReportToPdfStream(jasperPrint, new FileOutputStream(pdf));// exportReportToPdfFile(jasperPrint,"C:/Users/LEANDRO/Desktop/test_jasper.pdf");
		log.info("Export process finished with " + calculateTime(start));
		return pdf;
	}

	private long calculateTime(Long start) {
		return ((System.currentTimeMillis() - start) / 1000);
	}
}
