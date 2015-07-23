package it.cascino.dao;

import java.util.List;
import it.cascino.model.AsAnmas0f;

public interface AsAnmas0fDao{
	List<AsAnmas0f> getAll();
	
	void salva(AsAnmas0f a);
	
	void aggiorna(AsAnmas0f a);
	
	void elimina(AsAnmas0f a);
	
	AsAnmas0f getArticoloDaCodice(String codiceArticolo);

	void svuotaTabella();
	
	void close();
}
