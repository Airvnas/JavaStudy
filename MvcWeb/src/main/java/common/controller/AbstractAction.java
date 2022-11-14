package common.controller;

public abstract class AbstractAction implements Action{
	//execute() 추상메서드 가지고있음
	private String viewPage;//보여줄 뷰페이지 이름
	private boolean isRediret;
	//rediret방식의 이동이면 true,forward방식이면 false값을 가짐
	
	//setter,getter 구성
	public String getViewPage() {
		return viewPage;
	}
	public void setViewPage(String viewPage) {
		this.viewPage = viewPage;
	}
	public boolean isRediret() {
		return isRediret;
	}
	public void setRediret(boolean isRediret) {
		this.isRediret = isRediret;
	}
	
}////////////////////////////////////////////////////
