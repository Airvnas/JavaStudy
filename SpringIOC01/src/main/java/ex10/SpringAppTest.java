package ex10;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringAppTest {

	public static void main(String[] args) {
		//ApplicationContext ctx=new GenericXmlApplicationContext("classpath:ex10/application.xml");
		ApplicationContext ctx=new ClassPathXmlApplicationContext("ex10/application.xml");
		MessageBean mb = ctx.getBean("mb",MessageBean.class);
		mb.sayHello();
		mb.sayHi("¿µÈñ","¼øÈñ","Ã¶ÀÌ");
	}

}
