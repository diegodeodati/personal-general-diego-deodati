package it.bplus.util;


import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 * classe che pubblica un metodo per la generazione randomica e univoca di un id identificativo dell'eccezione
 * @author lucat
 *
 */
public class UniqueIDGenerator{

	private static long counter = 0;



	/**
	 * dato l'ip dell'utente e l'ora attuale in millisecondi viene costruito un ID a cui viene aggiunto
	 * anche un contatore intero che rende ancora più univoco il suo valore.
	 * @return un identificativo unico del messaggio di log
	 */
	public static String getUniqueID()  {

		String exceptionID = null;
		try{
			exceptionID = InetAddress.getLocalHost().getHostName();
			exceptionID = exceptionID + System.currentTimeMillis() + counter++;
		}
		catch(UnknownHostException es){
			//exceptionID = exceptionID + System.currentTimeMillis() + counter++;
		}

		return exceptionID;
	}

	/**
	 *
	 * @return
	 */
	public static String getShortUniqueID()  {

		String exceptionID = null;
		try{
			//exceptionID = InetAddress.getLocalHost().getHostName();
			exceptionID = /*exceptionID + */ ""+System.currentTimeMillis() + counter++;
		}
		catch(/*UnknownHost*/Exception es){
			//exceptionID = exceptionID + System.currentTimeMillis() + counter++;
			es.printStackTrace();
		}

		return exceptionID;
	}

}

