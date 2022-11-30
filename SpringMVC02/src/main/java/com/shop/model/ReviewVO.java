package com.shop.model;

import lombok.Data;

@Data
public class ReviewVO {
	private int num;
	private String name;
	private String content;
	private int score;
	private String filename;
	private java.util.Date wdate;
	private String pnum_fk;
}
