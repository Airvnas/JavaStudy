package ex01;
//Target
public class ServiceImpl implements Service {

	@Override
	public void sayHello(String... names) {
		if(names!=null) {
			for(String name:names) {
				System.out.println("Hello! "+name);
			}
		}
	}

}
