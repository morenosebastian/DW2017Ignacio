package lista4;

import javax.faces.bean.ManagedBean;

@ManagedBean(name="atividade2Beann")
public class Atividade2Bean {
	
	public void teste(){
		ConnectionFactory conn = new ConnectionFactory();
		
		conn.obterConexao();
	}
}
