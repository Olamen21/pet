package model;

public class Customer {
	private int CusId;
	private String CustName;
	private String CustAdd;
	private int CustPhone;
	
	public Customer() {
		
	}

	public int getCusId() {
		return CusId;
	}

	public void setCusId(int cusId) {
		CusId = cusId;
	}

	public String getCustName() {
		return CustName;
	}

	public void setCustName(String custName) {
		CustName = custName;
	}

	public String getCustAdd() {
		return CustAdd;
	}

	public void setCustAdd(String custAdd) {
		CustAdd = custAdd;
	}

	public int getCustPhone() {
		return CustPhone;
	}

	public void setCustPhone(int custPhone) {
		CustPhone = custPhone;
	}
	
}
