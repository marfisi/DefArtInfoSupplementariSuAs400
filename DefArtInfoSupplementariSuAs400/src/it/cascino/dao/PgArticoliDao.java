package it.cascino.dao;

import java.util.List;
import it.cascino.model.PgArticoli;

public interface PgArticoliDao{
	List<PgArticoli> getAll();
	
	PgArticoli getArticoloDaCodice(String codiceArticolo);
	
	Integer getNumeroFotoArticoloDaCodice(String codiceArticolo);
	
	Integer getNumeroAllegatiArticoloDaCodice(String codiceArticolo);
	
	Integer getNumeroCaratteristicheArticoloDaCodice(String codiceArticolo);
	
	void close();
}
