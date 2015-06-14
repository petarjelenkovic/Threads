package music;

public class Instrumental extends Thread {
	
	private String instr;
	private boolean stopIt;
	private Performance perf;
	private Synchronizer synch;

	public Instrumental(String instr,boolean stopIt,Performance perf,Synchronizer synch) {
		super();
		this.instr = instr;
		this.stopIt = stopIt;
		this.perf = perf;
		this.synch = synch;
	}
	@Override
	public void run() {
		play();
	}
	
	public  void play() {
		
		long delayIn = this.perf.getDelayInst();
		while(!stopIt){
			synch.playInstrumental(instr, delayIn);
		}
		
	}
	public boolean isStopIt() {
		return stopIt;
	}
	public void setStopIt(boolean stopIt) {
		this.stopIt = stopIt;
	}
	public String getInstr() {
		return instr;
	}
	public void setInstr(String instr) {
		this.instr = instr;
	}
	public Performance getPerf() {
		return perf;
	}
	public void setPerf(Performance perf) {
		this.perf = perf;
	}
	public Synchronizer getSynch() {
		return synch;
	}
	public void setSynch(Synchronizer synch) {
		this.synch = synch;
	}

}
