package music;

import java.util.Iterator;
import java.util.List;

public class Song {
	
	private String title;
    private List<String> lyrics;
    
    public Song() {
    }

    public Song(String title, List<String> lyrics) {
        super();
        this.title = title;
        this.lyrics = lyrics;
    }
    
    public String pickLine(Voice voice, int linePointer) {
        if (voice == Voice.ALL) {
            return allLyrics();
        }
        if ((voice == Voice.LEAD) || (voice == Voice.BACKING)) {
            return lyrics.get(linePointer);
        }
        return null;
    }
    
    public String allLyrics() {
        StringBuffer lyrics = new StringBuffer();
        for (Iterator iterator = this.lyrics.iterator(); iterator.hasNext();) {
//            String string = (String) iterator.next();
            lyrics = lyrics.append((String) iterator.next() + '\n');
        }
        return lyrics.toString();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getLyrics() {
        return lyrics;
    }

    public void setLyrics(List<String> lyrics) {
        this.lyrics = lyrics;
    }

}
