package CarRental;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class XmlUtils {
	public static final String CarS_XML = "libs/Cars.xml";
	public static Document getDocument(File file){
		SAXReader sr = new SAXReader();
		try {
			return sr.read(file);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void save(Document doc,File file){
		XMLWriter x;
		try {
			x = new XMLWriter(new FileOutputStream(file));
			x.write(doc);
			x.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//TODO 改变车的状态
	public static void ChangeState(String id,String state,String calRent,String user_name){
		System.out.println("---------");
	Document doc = XmlUtils.getDocument(new File(CarS_XML));
	Element root = doc.getRootElement();
	List<Element> list=root.elements("car");
//	System.out.println(list.size());
	for(Element e:list){
//		System.out.println("id="+id);
//		System.out.println("e.attributeValue(\"id\").equals(id):"+e.attributeValue("id").equals(id));
		if(e.attributeValue("id").equals(id)){
//			System.out.println("e.getName()>>>"+e.getName());
			e.addAttribute("state", state);
			e.addAttribute("calRent",calRent);
			e.addAttribute("user",user_name);
			save(doc,new File(CarS_XML));
			break;
			}
		}
	}
	public static Element findLxElement(Document doc,String lxName){
		Element lx = null;
		String xpath = "/Cars/lx/brand/type";
		return null;
		
	}
}
