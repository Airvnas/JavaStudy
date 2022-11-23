package ex08;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
//Config Ŭ������ ȯ�漳������ ����Ѵٴ� �ǹ��� ������̼�
@Configuration
public class Config {
	//�������� �⺻������ ���� singleton��ü(���ϰ�ü)�� �����Ѵ�.
	//<bean id="e1" class="ex07.Emp"/>
	@Bean(name="e1")
	@Scope(value="prototype")
	//@Scope(value="singleton")==>����Ʈ
	public Emp empInfo() {
		return new Emp("King","Research",5000);
	}
	//Bean�� name�Ӽ��� ���������� �̸��� �޼��� �̸��� Bean name�� �ȴ�.
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
	public ServiceImpl serice() {
		ServiceImpl s=new ServiceImpl();
		s.setEmp(this.empInfo3());
		return s; 
	}
	
}
