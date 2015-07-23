package it.cascino.model;

import java.io.Serializable;
import javax.persistence.*;

/**
* The persistent class for the cas_dat/anmas0f database table.
* 
*/
@Entity(name="Anmas0f")
@NamedQueries({
		@NamedQuery(name = "AsAnmas0f.findAll", query = "SELECT a FROM Anmas0f a"),
		@NamedQuery(name = "AsAnmas0f.findByCodice", query = "SELECT a FROM Anmas0f a WHERE a.mcoda = :mcoda"),
		@NamedQuery(name = "AsAnmas0f.updByCodice", query = "UPDATE Anmas0f a set a.mcoda = :mcoda where a.mcoda = :mcoda"),
		@NamedQuery(name = "AsAnmas0f.svuota", query = "DELETE FROM Anmas0f a WHERE a.mcoda != '1'")
})
public class AsAnmas0f implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Logger
	 */
//	@Inject
//	private Logger log;
	
	private String mcoda;
	
	public AsAnmas0f(){
	}
	
	public AsAnmas0f(String mcoda){
		super();
		this.mcoda = mcoda;
	}

	@Id
	public String getMcoda(){
		return mcoda;
	}

	public void setMcoda(String mcoda){
		this.mcoda = mcoda;
	}
	
	@Override
	public String toString(){
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(this.getClass().getName().substring(this.getClass().getName().lastIndexOf(".") + 1));
		stringBuilder.append("[");
		stringBuilder.append("mcoda=" + mcoda);
		stringBuilder.append("]");
		return stringBuilder.toString();
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj instanceof AsAnmas0f){
			if(this.mcoda == ((AsAnmas0f)obj).mcoda){
				return true;
			}else{
				return false;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mcoda == null) ? 0 : mcoda.hashCode());
		return result;
	}
}