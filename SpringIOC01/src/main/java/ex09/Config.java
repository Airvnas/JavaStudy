package ex09;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Scope;
/*xml에 설정된 빈을 Java Config로 가져오기
 * ==>@ImportResource어노테이션을 붙여준다	
 * */
//Config 클래스를 환경설정으로 사용한다는 의미의 어노테이션
@Configuration
@ImportResource("classpath:ex09/applicationContext.xml")
public class Config {
	//스프링은 기본적으로 빈을 singleton객체(단일객체)로 생성한다.
	//<bean id="e1" class="ex07.Emp"/>
	@Bean(name="e1")
	@Scope(value="prototype")
	//@Scope(value="singleton")==>디폴트
	public Emp empInfo() {
		return new Emp("King","Research",5000);
	}
	//Bean에 name속성을 주지않으면 이름은 메서드 이름이 Bean name이 된다.
	@Bean
	public Emp empInfo2() {
		Emp e=this.empInfo();
		e.setName("Miller");
		e.setDept("Operation");
		e.setSalary(4000);
		return e;
	}
	
	@Bean
	public Emp empInfo3() {
		return new Emp("Scott","sales",3000);
	}
	
	@Bean
	public ServiceImpl service() {
		ServiceImpl s=new ServiceImpl();
		s.setEmp(this.empInfo3());
		return s; 
	}
	
}
