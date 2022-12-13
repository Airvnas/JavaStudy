package ex03;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor//기본 생성자
@AllArgsConstructor//매개변수를 받는 생성자
public class BoardVO {
	private int num;
	private String title;
	private String name;
}
