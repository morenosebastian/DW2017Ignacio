package lista3;

import javax.faces.bean.ManagedBean;

@ManagedBean(name="atividade1Bean")
public class Atividade1Bean {
	private String msg1;
	private String msg2;
	private String msg;
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getMsg1() {
		return msg1;
	}

	public void setMsg1(String msg1) {
		this.msg1 = msg1;
	}

	public String getMsg2() {
		return msg2;
	}

	public void setMsg2(String msg2) {
		this.msg2 = msg2;
	}

	public void mostrarMsg(){
		
		this.setMsg("Mensagem 1: "+msg1 +" /Mensagem 2: "+ msg2);
	}

}
