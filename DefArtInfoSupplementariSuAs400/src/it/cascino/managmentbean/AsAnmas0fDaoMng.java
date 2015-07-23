package it.cascino.managmentbean;

import java.io.Serializable;
import java.util.List;
import it.cascino.model.AsAnmas0f;
import java.util.Iterator;
import it.cascino.dao.AsAnmas0fDao;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

public class AsAnmas0fDaoMng implements AsAnmas0fDao, Serializable{
	private static final long serialVersionUID = 1L;
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("DB2AS400");
	private EntityManager em = emf.createEntityManager();
	private EntityTransaction utx = em.getTransaction();
	
	Logger log = Logger.getLogger(AsAnmas0fDaoMng.class);
	
	@SuppressWarnings("unchecked")
	public List<AsAnmas0f> getAll(){
		List<AsAnmas0f> cod = null;
		try{
			try{
				utx.begin();
				Query query = em.createNamedQuery("AsAnmas0f.findAll");
				cod = (List<AsAnmas0f>)query.getResultList();
			}catch(NoResultException e){
				cod = null;
			}
			utx.commit();
		}catch(Exception e){
			log.fatal(e.toString());
		}
		return cod;
	}
	
	public void salva(AsAnmas0f a){
		try{
			try{
				utx.begin();
				// precodice.setId(null);
				log.info("salva: " + a.toString());
				em.persist(a);
			}finally{
				utx.commit();
			}
		}catch(Exception e){
			log.fatal(e.toString());
		}
	}
	
	public void aggiorna(AsAnmas0f a){
		try{
			try{
				utx.begin();
				log.info("aggiorna: " + a.toString());
				em.merge(a);
			}finally{
				utx.commit();
			}
		}catch(Exception e){
			log.fatal(e.toString());
		}
	}
	
	public void elimina(AsAnmas0f aElimina){
		// log.info("tmpDEBUGtmp: " + "> " + "elimina(" + produttoreElimina + ")");
		try{
			try{
				utx.begin();
				AsAnmas0f a = em.find(AsAnmas0f.class, aElimina.getMcoda());
				log.info("elimina: " + a.toString());
				em.remove(a);
			}finally{
				utx.commit();
			}
		}catch(Exception e){
			log.fatal(e.toString());
		}
	}
	
	public AsAnmas0f getArticoloDaCodice(String codiceArticolo){
		AsAnmas0f a = null;
		try{
			try{
				utx.begin();
				Query query = em.createNamedQuery("AsAnmas0f.findByCodice");
				query.setParameter("mcoda", codiceArticolo);
				a = (AsAnmas0f)query.getSingleResult();
			}catch(NoResultException e){
				a = null;
			}
			utx.commit();
		}catch(Exception e){
			log.fatal(e.toString());
			utx.commit();
		}
		return a;
	}
	
	public void svuotaTabella(){
		try{
			try{
				utx.begin();
				Query query = em.createNamedQuery("AsAnmas0f.svuota");
				query.executeUpdate();
			}finally{
				utx.commit();
			}
		}catch(Exception e){
			log.fatal(e.toString());
		}
	}
	
	public void close(){
		em.close();
		emf.close();
		log.info("chiuso");
	}
}
