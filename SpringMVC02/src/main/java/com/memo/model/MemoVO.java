package com.memo.model;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Setter
//@Getter
//@ToString(includeFieldNames = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemoVO {
	
	private int idx;
	private String name;
	private String msg;
	private Date wdate;
	
}
