package com.ssafy.happyhouse.model.dao;

//파라미터로 전달될 때 스트링으로 전달하는게 아니라 데이터를전달하는 객체인 DTO
//멤버에 대한 정보를 dto하나에 통으로 담아서 주기위해 
//이런것을 javaBeans라고 부르고 DTO(data transfer object)로 사용되는것이다. 
// JavaBeans : private member 변수, setter/getter, default constructor	포함
public class Member {
	private String memName;
	private String memEmail;
	private String memPwd;

	public Member(String memName, String memEmail, String memPwd) {
		this.memName = memName;
		this.memEmail = memEmail;
		this.memPwd = memPwd;
	}

	@Override
	public String toString() {
		return "Member [memName=" + memName + ", memEmail=" + memEmail + ", memPwd=" + memPwd + "]";
	}

	public Member() {

	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public String getMemEmail() {
		return memEmail;
	}

	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}

	public String getMemPwd() {
		return memPwd;
	}

	public void setMemPwd(String memPwd) {
		this.memPwd = memPwd;
	}

}