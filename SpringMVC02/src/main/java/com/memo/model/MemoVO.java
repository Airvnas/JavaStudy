package com.memo.model;
import java.util.Date;

import lombok.Data;

//@Setter
//@Getter
//@ToString(includeFieldNames = true)
@Data
public class MemoVO {
	
	private int idx;
	private String name;
	private String msg;
	private Date wdate;
	
}
