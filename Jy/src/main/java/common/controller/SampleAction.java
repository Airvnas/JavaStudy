package common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SampleAction extends AbstractAction{
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res)
	throws Exception{
		System.out.println("SampleAction의 execute()실행됨...");
		req.setAttribute("msg","From SampleAction:안녕 sample?");
		this.setViewPage("/template.jsp");
		this.setRediret(false);
	}
}
