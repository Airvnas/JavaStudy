package ex01;
import java.io.IOException;
import java.nio.charset.Charset;
//Files, Paths
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
public class TextFileRead {

	public static void main(String[] args) {
		String file="src/main/java/ex01/readme.txt";
		//try(){}catch(예외 e){} java1.7이상, 리소스 자원을 자동으로 반납해줌
		try(Stream<String> stream=Files.lines(Paths.get(file),Charset.forName("euc-kr"))){
			//stream.forEach((str)->System.out.println(str));
			//stream.forEach(System.out::println);//method reference-메모리 참조
			//$.each(arr,function(i,item){})
			stream.forEach(new Demo()::foo);
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		
		List<String> list=Arrays.asList("Apple","Banana","Cherry");
		list.forEach(System.out::println);
		System.out.println("-----------------------------------");
		list.forEach(new Demo()::foo);
	}

}//////////////////////////////////////////////////////////
class Demo{
	public void foo(String str) {
		System.out.println(str);
	}
	
	
	
}