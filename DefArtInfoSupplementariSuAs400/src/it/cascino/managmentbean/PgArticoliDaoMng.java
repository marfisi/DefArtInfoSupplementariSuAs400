package it.cascino.managmentbean;

import java.io.Serializable;
import java.util.List;
import it.cascino.model.PgArticoli;
import it.cascino.dao.PgArticoliDao;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.apache.log4j.Logger;

public class PgArticoliDaoMng implements PgArticoliDao, Serializable{
	private static final long serialVersionUID = 1L;
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("Postgresql");
	private EntityManager em = emf.createEntityManager(); // Retrieve an application managed entity manager
	private EntityTransaction utx = em.getTransaction();
	
	Logger log = Logger.getLogger(PgArticoliDaoMng.class);
	
	@SuppressWarnings("unchecked")
	public List<PgArticoli> getAll(){
		List<PgArticoli> articoli = null;
		try{
			try{
				utx.begin();
				Query query = em.createNamedQuery("PgArticoli.findAll");
				articoli = (List<PgArticoli>)query.getResultList();
			}catch(NoResultException e){
				articoli = null;
			}
			utx.commit();
		}catch(Exception e){
			log.fatal(e.toString());
		}
		return articoli;
	}
	
	public PgArticoli getArticoloDaCodice(String codiceArticolo){
		PgArticoli articolo = null;
		try{
			try{
				utx.begin();
				Query query = em.createNamedQuery("PgArticoli.findByCodiceArticolo");
				query.setParameter("codiceArticolo", codiceArticolo);
				articolo = (PgArticoli)query.getSingleResult();
			}catch(NoResultException e){
				articolo = null;
			}
			utx.commit();
		}catch(Exception e){
			log.fatal(e.toString());
			utx.commit();
		}
		return articolo;
	}
	
	public Integer getNumeroFotoArticoloDaCodice(String codiceArticolo){
		Integer numero = null;
		try{
			try{
				utx.begin();
				String sql = "select count(*) " +
				"from articoli_foto af join articoli a on af.articolo = a.id  " +
				"where upper(codice) = :codice";
				Query query = em.createNativeQuery(sql);
				query.setParameter("codice", codiceArticolo);
				numero = ((Number)query.getSingleResult()).intValue();
			}catch(NoResultException e){
				numero = 0;
			}
			utx.commit();
		}catch(Exception e){
			log.fatal(e.toString());
			utx.commit();
		}
		return numero;
	}
	
	public Integer getNumeroAllegatiArticoloDaCodice(String codiceArticolo){
		Integer numero = null;
		try{
			try{
				utx.begin();
				String sql = "select count(*) " +
				"from articoli_allegati aa join articoli a on aa.articolo = a.id  " +
				"where upper(codice) = :codice";
				Query query = em.createNativeQuery(sql);
				query.setParameter("codice", codiceArticolo);
				numero = ((Number)query.getSingleResult()).intValue();
			}catch(NoResultException e){
				numero = 0;
			}
			utx.commit();
		}catch(Exception e){
			log.fatal(e.toString());
			utx.commit();
		}
		return numero;		
	}
	
	public Integer getNumeroCaratteristicheArticoloDaCodice(String codiceArticolo){
		Integer numero = null;
		try{
			try{
				utx.begin();
				String sql = "select count(*) " +
				"from caratteristiche c join articoli a on c.articolo = a.id  " +
				"where upper(codice) = :codice";
				Query query = em.createNativeQuery(sql);
				query.setParameter("codice", codiceArticolo);
				numero = ((Number)query.getSingleResult()).intValue();
			}catch(NoResultException e){
				numero = 0;
			}
			utx.commit();
		}catch(Exception e){
			log.fatal(e.toString());
			utx.commit();
		}
		return numero;		
	}

	public void close(){
		em.close();
		emf.close();
		log.info("chiuso");
	}
}
