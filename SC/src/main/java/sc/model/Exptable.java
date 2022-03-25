package sc.model;

import java.util.Date;

public class Exptable {
	
	private int EXPIDX;
	private int MEMIDX;
	private int GETEXP;
	private int FINALEXP;
	private Date EXPDATE;
	
	
	public int getEXPIDX() {
		return EXPIDX;
	}
	public void setEXPIDX(int eXPIDX) {
		EXPIDX = eXPIDX;
	}
	public int getMEMIDX() {
		return MEMIDX;
	}
	public void setMEMIDX(int mEMIDX) {
		MEMIDX = mEMIDX;
	}
	public int getGETEXP() {
		return GETEXP;
	}
	public void setGETEXP(int gETEXP) {
		GETEXP = gETEXP;
	}
	public int getFINALEXP() {
		return FINALEXP;
	}
	public void setFINALEXP(int fINALEXP) {
		FINALEXP = fINALEXP;
	}
	public Date getEXPDATE() {
		return EXPDATE;
	}
	public void setEXPDATE(Date eXPDATE) {
		EXPDATE = eXPDATE;
	}

	
}
