package it.cascino.managmentbean;

import java.io.Serializable;
import java.util.List;
import it.cascino.model.AsAnmag0f;
import it.cascino.dao.AsAnmag0fDao;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.apache.log4j.Logger;

public class AsAnmag0fDaoMng implements AsAnmag0fDao, Serializable{
	private static final long serialVersionUID = 1L;
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("DB2AS400");
	private EntityManager em = emf.createEntityManager();
	private EntityTransaction utx = em.getTransaction();
	
	Logger log = Logger.getLogger(AsAnmag0fDaoMng.class);
	
	@SuppressWarnings("unchecked")
	public List<AsAnmag0f> getAll(){
		List<AsAnmag0f> cod = null;
		try{
			try{
				utx.begin();
				Query query = em.createNamedQuery("AsAnmag0f.findAll");
				cod = (List<AsAnmag0f>)query.getResultList();
			}catch(NoResultException e){
				cod = null;
			}
			utx.commit();
		}catch(Exception e){
			log.fatal(e.toString());
		}
		return cod;
	}
	
//	public void salva(AsAnmag0f a){
//		try{
//			try{
//				utx.begin();
//				// precodice.setId(null);
//				log.info("salva: " + a.toString());
//				em.persist(a);
//			}finally{
//				utx.commit();
//			}
//		}catch(Exception e){
//			log.fatal(e.toString());
//		}
//	}
//	
//	public void aggiorna(AsAnmag0f a){
//		try{
//			try{
//				utx.begin();
//				log.info("aggiorna: " + a.toString());
//				em.merge(a);
//			}finally{
//				utx.commit();
//			}
//		}catch(Exception e){
//			log.fatal(e.toString());
//		}
//	}
//	
//	public void elimina(AsAnmag0f aElimina){
//		// log.info("tmpDEBUGtmp: " + "> " + "elimina(" + produttoreElimina + ")");
//		try{
//			try{
//				utx.begin();
//				AsAnmag0f a = em.find(AsAnmag0f.class, aElimina.getMcoda());
//				log.info("elimina: " + a.toString());
//				em.remove(a);
//			}finally{
//				utx.commit();
//			}
//		}catch(Exception e){
//			log.fatal(e.toString());
//		}
//	}
	
	@SuppressWarnings("unchecked")
	public List<AsAnmag0f> getArticoliIngrosso(){
		List<AsAnmag0f> cod = null;
		try{
			try{
				utx.begin();
				Query query = em.createNamedQuery("AsAnmag0f.findAllIngrosso");
				cod = (List<AsAnmag0f>)query.getResultList();
			}catch(NoResultException e){
				cod = null;
			}
			utx.commit();
		}catch(Exception e){
			log.fatal(e.toString());
		}
		return cod;
	}
	
	public void close(){
		em.close();
		emf.close();
		log.info("chiuso");
	}
}
