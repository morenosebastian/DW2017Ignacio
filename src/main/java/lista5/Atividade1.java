package lista5;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean(name="atividade1BeanL5")
public class Atividade1 {

	public String foward(){
		return "OutraPag.xhtml";
	}
	
	public String fowardMesmaPagina(){
		return null;
	}
	
	public String redirectMesmaPagina(){
		return "OutraPag.xhtml?faces-redirect=true";
	}
	
	public void redirect() throws IOException {

		ExternalContext contexto = FacesContext.getCurrentInstance().getExternalContext();

		contexto.redirect("http://www.sc.senac.br");
		//return "http://www.sc.senac.br";
		
	}
}
