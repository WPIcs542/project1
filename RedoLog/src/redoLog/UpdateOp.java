package redoLog;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;

public class UpdateOp {
	// Relation city = new Relation("city.db");
	// Relation country = new Relation("country.db");
	Relation r;
	int popindex;
	byte[] newenumofr = null;
	RedoLog log;

	/**
	 * constructor to open the log files
	 * @param popindex
	 */
	public UpdateOp(int popindex) {
		if (popindex == 4) {

			try {
				log = new RedoLog("city.log");
			} catch (IOException e) {
				e.printStackTrace();
			}
			this.popindex = popindex;

		} else {

			try {
				log = new RedoLog("country.log");
			} catch (IOException e) {
				e.printStackTrace();
			}
			this.popindex = popindex;
		}
	}

	public void open(Relation r) {
		this.r = r;
	}

	/**
	 * increase the population by 2% for all tuples 
	 */
	public void getNext() {
		log.writeInit();
		log.writeStart();

		String[] tupleofr = null;
		Enumeration<byte[]> enumofr = r.getValuesEnum();
		while (enumofr.hasMoreElements()) {
			byte[] rtupleofbyte = enumofr.nextElement();

			try {
				tupleofr = splitoftuple(rtupleofbyte);

			} catch (UnsupportedEncodingException exp) {
				exp.printStackTrace();
			}
			// Long tupleP=Long.parseLong(tupleofr[popindex]);
			Double tupleP = Double.parseDouble(tupleofr[popindex]);
			Long newtupleP = (long) (tupleP * 1.02);
			tupleofr[popindex] = newtupleP.toString();
			try {
				newenumofr = unsplitoftuple(tupleofr);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();

			}
			r.put(tupleofr[0].hashCode(), newenumofr);

			log.writeUpdate(tupleofr[0].hashCode(), tupleofr[1], tupleP, newtupleP);

		}
		r.saveContents();
		log.writeCommit();
		try {
			log.savelog();
		} catch (IOException e) {
			e.printStackTrace();
		}
		log.clearlog();

	}

	/**
	 * clean the log memory
	 */
	public void close() {
		// log.writeCommit();
		// try{log.savelog();}catch(IOException e){e.printStackTrace();}
		// log.clearlog();
		r.clear();

	}

	/**
	 * split tuple by ","
	 * @param tuple
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String[] splitoftuple(byte[] tuple) throws UnsupportedEncodingException {
		String str = new String(tuple, StandardCharsets.UTF_8);
		return str.split(","); // ignores commas inside quotation marks
	}

	protected static byte[] unsplitoftuple(String[] splitArray) throws UnsupportedEncodingException {
		String retString = null;
		for (String str : splitArray) {
			if (retString == null) {
				retString = str;
			} else {
				retString = retString + "," + str;
			}
		}
		return retString.getBytes("UTF-8");
	}
}