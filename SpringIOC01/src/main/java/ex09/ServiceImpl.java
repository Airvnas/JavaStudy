package ex09;

public class ServiceImpl implements Service {
	
	//property
	private Emp emp;
	
	public Emp getEmp() {
		return emp;
	}
	public void setEmp(Emp emp) {
		this.emp = emp;
	}

	@Override
	public void test1() {
		System.out.printf("Name:%s%nDept:%s%nSalary:%d%n",emp.getName(),emp.getDept(),emp.getSalary());
	}

}
