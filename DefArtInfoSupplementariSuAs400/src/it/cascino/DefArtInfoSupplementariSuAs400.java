package it.cascino;

import it.cascino.dao.AsAnmag0fDao;
import it.cascino.dao.PgArticoliDao;
import it.cascino.dao.AsAnmas0fDao;
import it.cascino.managmentbean.AsAnmag0fDaoMng;
import it.cascino.managmentbean.PgArticoliDaoMng;
import it.cascino.managmentbean.AsAnmas0fDaoMng;
import it.cascino.model.AsAnmag0f;
import it.cascino.model.PgArticoli;
import it.cascino.model.AsAnmas0f;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class DefArtInfoSupplementariSuAs400{
	
	private static PgArticoliDao pGarticoliDao = new PgArticoliDaoMng();
	private static List<PgArticoli> pGarticoliLs;
	
	// articoli con specifiche supplementari (anmaS)
	private static AsAnmas0fDao aSanmas0fDao = new AsAnmas0fDaoMng();
//	private static List<AsAnmas0f> aSanmas0fLs;
	
	// anagrafica articoli (anmaG) (non annullati logicamente)
	private static AsAnmag0fDao aSanmag0fDao = new AsAnmag0fDaoMng();
	private static List<AsAnmag0f> aSanmag0fLs;
	
	private static List<PgArticoli> artInPgNonAsLs = new ArrayList<PgArticoli>();
	
	private static List<AsAnmas0f> artConInfoSupplementariLs = new ArrayList<AsAnmas0f>();
	
	static Logger log;
	
	public static void main(String args[]){
		PropertyConfigurator.configure("logdir/logDefArtInfoSupplementariSuAs400.properties");
		log = Logger.getLogger(DefArtInfoSupplementariSuAs400.class);
		
		// log.debug("Test Livello DEBUG");
		// log.info("Test Livello INFO");
		// log.warn("Test Livello WARNING");
		// log.error("Test Livello ERROR");
		// log.fatal("Test Livello FATAL");
		
		pGarticoliLs = pGarticoliDao.getAll();
//		aSanmas0fLs = aSanmas0fDao.getAll();
		aSanmag0fLs = aSanmag0fDao.getAll();
		
		Iterator<PgArticoli> iter_pGarticoli = pGarticoliLs.iterator();
		// Iterator<AsAnmas0f> iter_aSanmas0f = aSanmas0fLs.iterator();
		Iterator<AsAnmag0f> iter_aSanmag0f = aSanmag0fLs.iterator();
		while(iter_pGarticoli.hasNext()){
			PgArticoli pgArt = iter_pGarticoli.next();
			boolean artInAsTrovato = false;
			iter_aSanmag0f = aSanmag0fLs.iterator();
			while(iter_aSanmag0f.hasNext()){
				AsAnmag0f asArt = iter_aSanmag0f.next();
				// String a = StringUtils.upperCase(pgArt.getCodice());
				// String b = StringUtils.stripEnd(asArt.getMcoda(), " ");
				// log.info(a + "-" + b);
				// if(StringUtils.equals(a, b)){
				if(StringUtils.equals(StringUtils.upperCase(pgArt.getCodice()), StringUtils.stripEnd(asArt.getMcoda(), " "))){
					artInAsTrovato = true;
					articoloConInfoSupplementari(asArt.getMcoda());
					iter_aSanmag0f.remove();
					log.info("Rimossa: " + asArt.getMcoda());
					break;
				}
			}
			if(artInAsTrovato == false){
				artInPgNonAsLs.add(pgArt);
			}
		}
		
		gestisci_aSanmas0f();
		
		reportArtInPgNonAs();
		
		pGarticoliDao.close();
		aSanmas0fDao.close();
		aSanmag0fDao.close();
	}
	
	static void articoloConInfoSupplementari(String codArt){
		log.info("start: " + "articoloConInfoSupplementari");
		// se l'articolo ha disponibile almeno uno tra
		// foto
		// allegati
		// caratteristiche
		// allora viene aggiunto in lista
		
		boolean daAggiungere = false;
		
		codArt = StringUtils.trim(codArt);
		
		Integer numeroFoto = pGarticoliDao.getNumeroFotoArticoloDaCodice(codArt);
		// se ha foto disponibile la segnalo, altrimenti analizzo le altre caratteristiche
		if(numeroFoto != 0){
			daAggiungere = true;
		}else{
			Integer numeroAllegati = pGarticoliDao.getNumeroAllegatiArticoloDaCodice(codArt);
			if(numeroAllegati != 0){
				daAggiungere = true;
			}else{
				Integer numeroCaratteristiche = pGarticoliDao.getNumeroCaratteristicheArticoloDaCodice(codArt);
				if(numeroCaratteristiche != 0){
					daAggiungere = true;
				}else{
					daAggiungere = false;
				}
			}
		}
		
		if(daAggiungere){
			artConInfoSupplementariLs.add(new AsAnmas0f(codArt));
		}
		log.info("stop: " + "articoloConInfoSupplementari");
	}
	
	static void gestisci_aSanmas0f(){
		log.info("start: " + "gestisci_aSanmas0f");
		aSanmas0fDao.svuotaTabella();
		Iterator<AsAnmas0f> iter_artConInfoSupplementari = artConInfoSupplementariLs.iterator();
		while(iter_artConInfoSupplementari.hasNext()){
			AsAnmas0f art = iter_artConInfoSupplementari.next();
//			AsAnmas0f artInDb = aSanmas0fDao.getArticoloDaCodice(art.getMcoda());
//			if(artInDb == null){
//				log.info("aggiungo: " + art.getMcoda());
				aSanmas0fDao.salva(art);
//			}else{
//				// se lo trova lascialo com'e' e non fare niente
//			}
		}
		log.info("stop: " + "gestisci_aSanmas0f");
	}
	
	static void reportArtInPgNonAs(){
		log.info("start: " + "reportArtInPgNonAs");
		log.info("articoli in Postgres e non in AS400");
		Iterator<PgArticoli> iter_artInPgNonAs = artInPgNonAsLs.iterator();
		while(iter_artInPgNonAs.hasNext()){
			PgArticoli art = iter_artInPgNonAs.next();
			log.info(art.getCodice());
		}
		log.info("stop: " + "reportArtInPgNonAs");
	}
}
