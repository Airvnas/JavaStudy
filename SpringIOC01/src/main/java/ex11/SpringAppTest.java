package ex11;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringAppTest {

	public static void main(String[] args) {
		ApplicationContext ctx=new ClassPathXmlApplicationContext("ex11/appContext.xml");
		AdminVO a1=ctx.getBean("admin1",AdminVO.class);
		a1.info();
		AdminVO a2=ctx.getBean("admin2",AdminVO.class);
		a2.info();
	}

}
