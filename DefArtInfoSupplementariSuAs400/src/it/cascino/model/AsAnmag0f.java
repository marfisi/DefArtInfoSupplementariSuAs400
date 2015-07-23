package it.cascino.model;

import java.io.Serializable;
import javax.persistence.*;

/**
* The persistent class for the cas_dat/anmag0f database table.
* 
*/
@Entity(name="Anmag0f")
@NamedQueries({
	@NamedQuery(name = "AsAnmag0f.findAll", query = "SELECT a FROM Anmag0f a WHERE a.atama != 'A'"),
	@NamedQuery(name = "AsAnmag0f.findById", query = "SELECT a FROM Anmag0f a WHERE a.atama != 'A' and a.mcoda = :mcoda "),
	@NamedQuery(name = "AsAnmag0f.findAllIngrosso", query = "SELECT a FROM Anmag0f a WHERE a.atama != 'A' and ((a.mdepi = 1) or (a.mdepi = 3))")
})
public class AsAnmag0f implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Logger
	 */
//	@Inject
//	private Logger log;
	
	private String atama;
	private String mcoda;
	private String mdesc;
	private String mesau;
	private String mdaum;
	private String mdepi;
	
	public AsAnmag0f(){
	}
	
	public AsAnmag0f(String atama, String mcoda, String mdesc, String mesau, String mdaum, String mdepi){
		super();
		this.atama = atama;
		this.mcoda = mcoda;
		this.mdesc = mdesc;
		this.mesau = mesau;
		this.mdaum = mdaum;
		this.mdepi = mdepi;
	}

	public String getAtama(){
		return atama;
	}

	public void setAtama(String atama){
		this.atama = atama;
	}

	@Id
	public String getMcoda(){
		return mcoda;
	}

	public void setMcoda(String mcoda){
		this.mcoda = mcoda;
	}
	
	public String getMdesc(){
		return mdesc;
	}

	public void setMdesc(String mdesc){
		this.mdesc = mdesc;
	}

	public String getMesau(){
		return mesau;
	}

	public void setMesau(String mesau){
		this.mesau = mesau;
	}

	public String getMdaum(){
		return mdaum;
	}

	public void setMdaum(String mdaum){
		this.mdaum = mdaum;
	}

	public String getMdepi(){
		return mdepi;
	}

	public void setMdepi(String mdepi){
		this.mdepi = mdepi;
	}
	
	@Override
	public String toString(){
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(this.getClass().getName().substring(this.getClass().getName().lastIndexOf(".") + 1));
		stringBuilder.append("[");
		stringBuilder.append("mcoda=" + mcoda).append(", ");
		stringBuilder.append("atama=" + atama).append(", ");
		stringBuilder.append("mcoda=" + mcoda).append(", ");
		stringBuilder.append("mdaum=" + mdaum).append(", ");
		stringBuilder.append("mdepi=" + mdepi).append(", ");
		stringBuilder.append("mdesc=" + mdesc).append(", ");
		stringBuilder.append("mesau=" + mesau);
		stringBuilder.append("]");
		return stringBuilder.toString();
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj instanceof AsAnmag0f){
			if(this.mcoda == ((AsAnmag0f)obj).mcoda){
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
		result = prime * result + ((atama == null) ? 0 : atama.hashCode());
		result = prime * result + ((mcoda == null) ? 0 : mcoda.hashCode());
		result = prime * result + ((mdaum == null) ? 0 : mdaum.hashCode());
		result = prime * result + ((mdepi == null) ? 0 : mdepi.hashCode());
		result = prime * result + ((mdesc == null) ? 0 : mdesc.hashCode());
		result = prime * result + ((mesau == null) ? 0 : mesau.hashCode());
		return result;
	}
}