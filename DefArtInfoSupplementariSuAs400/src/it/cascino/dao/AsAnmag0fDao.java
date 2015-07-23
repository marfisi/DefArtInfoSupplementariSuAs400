package it.cascino.dao;

import java.util.List;
import it.cascino.model.AsAnmag0f;

public interface AsAnmag0fDao{
	List<AsAnmag0f> getAll();
	
//	void salva(AsAnmag0f a);
//	
//	void aggiorna(AsAnmag0f a);
//	
//	void elimina(AsAnmag0f a);

	List<AsAnmag0f> getArticoliIngrosso();
	
	void close();
}
