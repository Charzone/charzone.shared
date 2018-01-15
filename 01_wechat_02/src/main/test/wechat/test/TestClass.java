package wechat.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import org.junit.Test;

import com.duapp.heart2heart.utils.EmailUtils;
import com.duapp.heart2heart.utils.QRcodeUtil;
import com.google.zxing.WriterException;

public class TestClass {
	
	@Test
	public void testPropertiesRead() throws FileNotFoundException, IOException{
		Properties properties = new Properties();
		properties.load(new FileInputStream("src/main/resources/mail.properties"));
		Enumeration<Object> elements = properties.elements();
		while(elements.hasMoreElements()){
			System.out.println(elements.nextElement());
		}
	}
	
	@Test
	public void testSendEmail() throws Exception {
		EmailUtils.sendEmail("269363194@qq.com", "look at me !");
	}
	
	@Test
	public void testGenQRcode() throws WriterException, IOException {
//		QRcodeUtil.encodeQrCode();
	}
	
	@Test
	public void testDecodeQr() {
		QRcodeUtil.decodeQrCode();
	}
	
}
