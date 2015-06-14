package music;

public class Performance {
	 
	private Song song;
	    private int delay;
	    private int delayInst;
	    
	    public Performance() {
	    }

	    public Performance(Song song, int delay,int delayInst) {
	        super();
	        this.song = song;
	        this.delay = delay;
	        this.delayInst = delayInst;
	    }

	    public Song getSong() {
	        return song;
	    }

	    public void setSong(Song song) {
	        this.song = song;
	    }

	    public int getDelay() {
	        return delay;
	    }

	    public void setDelay(int delay) {
	        this.delay = delay;
	    }

		public int getDelayInst() {
			return delayInst;
		}

		public void setDelayInst(int delayInst) {
			this.delayInst = delayInst;
		}

}
