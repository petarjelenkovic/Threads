package music;

import gui.ThreadsGUI;

public class Synchronizer {
	
private boolean leadLineFlag;
private boolean instrumentalFlag;
    
    public Synchronizer() {
    }

    public Synchronizer(boolean leadLineFlag, boolean instrumentalFlag) {
        super();
        this.leadLineFlag = leadLineFlag;
        this.instrumentalFlag = instrumentalFlag;
    }

    public synchronized void singLeadLine(String leadLine, long delay) {
        while (!leadLineFlag || instrumentalFlag) {
            try {
                wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        singOneLine(leadLine, delay);
    }

    public synchronized void singBackingLine(String backingLine, long delay) {
        while (leadLineFlag || instrumentalFlag) {
            try {
                wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
      singOneLine(backingLine, delay);
    }
    
    public synchronized void playInstrumental(String text, long delay) {
    	while(!instrumentalFlag) {
    		try {
                wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    	}
//    	System.out.println(text);
    	ThreadsGUI.getTA().setText(ThreadsGUI.getTA().getText()+'\n'+text);
    	try {
			wait(delay);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	endOfSinging();
    	
    }
    
    private void singOneLine(String line, long delay) {
//       System.out.println(line);
       ThreadsGUI.getTA().setText(ThreadsGUI.getTA().getText()+'\n'+line);
        try {
            wait(delay);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        leadLineFlag = !leadLineFlag;
        notifyAll();
        
    }
    public synchronized void endOfSinging(){
    	instrumentalFlag = !instrumentalFlag;
    	notifyAll();
    }


}
