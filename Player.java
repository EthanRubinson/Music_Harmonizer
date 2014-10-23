import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.StringTokenizer;


public class Player {
	
	private static boolean debugMode = false;
	
	HashMap<String,Integer> scaleToKey;
	HashMap<String,Triple<String,String,String>> noteToChord;
	Robot robot;
	Random rand;
	
	public Player(){
		scaleToKey = new HashMap<String,Integer>();
		scaleToKey.put("LC", KeyEvent.VK_Q);
		scaleToKey.put("LD", KeyEvent.VK_W);
		scaleToKey.put("LE", KeyEvent.VK_E);
		scaleToKey.put("LF", KeyEvent.VK_R);
		scaleToKey.put("LG", KeyEvent.VK_T);
		scaleToKey.put("LA", KeyEvent.VK_Y);
		scaleToKey.put("LB", KeyEvent.VK_U);
		scaleToKey.put("C", KeyEvent.VK_I);
		scaleToKey.put("D", KeyEvent.VK_O);
		scaleToKey.put("E", KeyEvent.VK_P);
		scaleToKey.put("F", KeyEvent.VK_Z);
		scaleToKey.put("G", KeyEvent.VK_X);
		scaleToKey.put("A", KeyEvent.VK_C);
		scaleToKey.put("B", KeyEvent.VK_V);
		scaleToKey.put("HC", KeyEvent.VK_B);
		scaleToKey.put("HD", KeyEvent.VK_N);
		scaleToKey.put("HE", KeyEvent.VK_M);
		scaleToKey.put("HF", KeyEvent.VK_COMMA);
		scaleToKey.put("HG", KeyEvent.VK_PERIOD);
		scaleToKey.put("HA", KeyEvent.VK_SLASH);
		
		noteToChord = new HashMap<String,Triple<String,String,String>>();
		noteToChord.put("LA", new Triple<String,String,String>("LD,LF,LA", "LF,LA,C", "LA,C,E"));
		noteToChord.put("LB", new Triple<String,String,String>("LE,LG,LB", "LG,LB,D", "LB,D,F"));
		noteToChord.put("C", new Triple<String,String,String>("LF,LA,C", "LA,C,E", "C,E,G"));
		noteToChord.put("D", new Triple<String,String,String>("LG,LB,D", "LB,D,F", "D,F,A"));
		noteToChord.put("E", new Triple<String,String,String>("LA,C,E",  "C,E,G",  "E,G,B"));
		noteToChord.put("F", new Triple<String,String,String>("LB,D,F",  "D,F,A",  "F,A,HC"));
		noteToChord.put("G", new Triple<String,String,String>("C,E,G",   "E,G,B",  "G,B,HD"));
		noteToChord.put("A", new Triple<String,String,String>("D,F,A",   "F,A,HC", "A,HC,HE"));
		noteToChord.put("B", new Triple<String,String,String>("E,G,B",   "G,B,HD", "B,HD,HF"));
		
		rand = new Random();
		
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// We're screwed....
			e.printStackTrace();
		}
	}
	
	
	public static void debugPrintln(String p){
		if (debugMode) System.out.println(p);
	}
	
	
	public void playChord(String chord, double time) throws AWTException{
		
		StringTokenizer chordNotes = new StringTokenizer(chord, ",");
		
		int note1 = -1;
		int note2 = -1;
		int note3 = -1;
		int note4 = -1;
		int note5 = -1;
		int note6 = -1;
		
		if(chordNotes.hasMoreTokens())
			note1 = scaleToKey.get(chordNotes.nextToken());
		
		if(chordNotes.hasMoreTokens())
			note2 = scaleToKey.get(chordNotes.nextToken());
		
		if(chordNotes.hasMoreTokens())
			note3 = scaleToKey.get(chordNotes.nextToken());
		
		if(chordNotes.hasMoreTokens())
			note4 = scaleToKey.get(chordNotes.nextToken());
		
		if(chordNotes.hasMoreTokens())
			note5 = scaleToKey.get(chordNotes.nextToken());
		
		if(chordNotes.hasMoreTokens())
			note6 = scaleToKey.get(chordNotes.nextToken());
		

		if(note1 != -1)
			robot.keyPress(note1);
		
		if(note2 != -1)
			robot.keyPress(note2);
		
		if(note3 != -1)
			robot.keyPress(note3);
		
		if(note4 != -1)
			robot.keyPress(note4);
		
		if(note5 != -1)
			robot.keyPress(note5);
		
		if(note6 != -1)
			robot.keyPress(note6);
		
		try {
			Thread.sleep((int)(time * 450));
		} catch (InterruptedException e) {
			// Whatever...
		}
		if(note1 != -1)
			robot.keyRelease(note1);
		
		if(note2 != -1)
			robot.keyRelease(note2);
		
		if(note3 != -1)
			robot.keyRelease(note3);
		
		if(note4 != -1)
			robot.keyRelease(note4);
		
		if(note5 != -1)
			robot.keyRelease(note5);
		
		if(note6 != -1)
			robot.keyRelease(note6);
		
		try {
			Thread.sleep((int)(time * 50));
		} catch (InterruptedException e) {
			// Whatever...
		}
	}
	
	
	public String tuneToHarmony(String tune){
		
		StringTokenizer tuneToken = new StringTokenizer(tune," ");
		
		String harmonizedTune = "";
		while(tuneToken.hasMoreTokens()){
			StringTokenizer tempToken = new StringTokenizer(tuneToken.nextToken(),"|");
			String aNote = tempToken.nextToken();
			String aLength = tempToken.nextToken();
			
			
			Triple<String, String, String> possibleNotes = noteToChord.get(aNote);
			
			//int randomNum = rand.nextInt(3);
			//randomNum = 1;
			
			/*if(randomNum == 0){
				harmonizedTune += possibleNotes.z;
			}
			else if(randomNum == 1){
				harmonizedTune += possibleNotes.y;
			}
			else {
				harmonizedTune += possibleNotes.z;
			}*/
			
			harmonizedTune += possibleNotes.z;
			harmonizedTune += "|" + aLength + " ";
		}
		
		return harmonizedTune;
	}
	
	public String tuneToChords(String tune){
		
		StringTokenizer tuneToken = new StringTokenizer(tune," ");
		
		String harmonizedTune = "";
		while(tuneToken.hasMoreTokens()){
			StringTokenizer tempToken = new StringTokenizer(tuneToken.nextToken(),"|");
			String aNote = tempToken.nextToken();
			String aLength = tempToken.nextToken();
			
			
			Triple<String, String, String> possibleNotes = noteToChord.get(aNote);
			
			int randomNum = 1;
			
			if(randomNum == 0){
				harmonizedTune += possibleNotes.x;
			}
			else if(randomNum == 1){
				harmonizedTune += possibleNotes.y;
			}
			else {
				harmonizedTune += possibleNotes.z;
			}
			
			harmonizedTune += "|" + aLength + " ";
		}
		
		return harmonizedTune;
	}
	
	public static void main (String [] args) throws AWTException{
		
		// Initialize
		Player player = new Player();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		String hot_cross_buns = "B|1.25 A|1.25 G|2.25 B|1.25 A|1.25 G|2.25 G|.75 G|.75 G|.75 G|.75 A|.75 A|.75 A|.75 A|.75 "
							  + "B|1.25 A|1.25 G|2.25";
		
		
		String old_farm = "G|1.25 G|1.25 G|1.25 D|1.25 E|1.25 E|1.25 D|2.25 B|1.25 B|1.25 A|1.25 A|1.25 G|3.25 "
						+ "G|1.25 G|1.25 G|1.25 D|1.25 E|1.25 E|1.25 D|2.25 B|1.25 B|1.25 A|1.25 A|1.25 G|3.25 "
						+ "D|.75 D|.75 G|1.25 G|1.25 G|1.25 D|.75 D|.75 G|1.25 G|1.25 G|2.25 "
						+ "G|.75 G|.75 G|1.25 G|.75 G|.75 G|1.25 G|.75 G|.75 G|.75 G|.75 G|1.25 G|1.25 "
						+ "G|1.25 G|1.25 G|1.25 D|1.25 E|1.25 E|1.25 D|2.25 B|1.25 B|1.25 A|1.25 A|1.25 G|3.25 ";
		
		String twinkle = "C|1 C|1 G|1 G|1 A|1 A|1 G|2 F|1 F|1 E|1 E|1 D|1 D|1 C|2 G|1 G|1 F|1 F|1 E|1 E|1 D|2 G|1 G|1 F|1 F|1 E|1 E|1 D|2 C|1 C|1 G|1 G|1 A|1 A|1 G|2 F|1 F|1 E|1 E|1 D|1 D|1 C|2";

		
		String songSelection = twinkle;
		
		
		
		
		String nonharmonizedTune = songSelection;
		
		// Parse chords and time from the tune
		ArrayList<Tuple<String,Double>> chords = new ArrayList<Tuple<String,Double>>();
		StringTokenizer tok3Chord = new StringTokenizer(nonharmonizedTune, " ");
		while(tok3Chord.hasMoreTokens()){
			String nextChord = tok3Chord.nextToken();
			StringTokenizer tokLength = new StringTokenizer(nextChord, "|");
			
			String notes = tokLength.nextToken();
			double length = Double.parseDouble(tokLength.nextToken());
			
			chords.add(new Tuple<String,Double>(notes,length));
		}
		
		debugPrintln(chords.toString());
		
		for (Tuple<String,Double> chord : chords){
			player.playChord(chord.x, chord.y);
		}
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		String harmonizedTune = player.tuneToHarmony(songSelection);
		// Parse chords and time from the tune
		chords = new ArrayList<Tuple<String,Double>>();
		tok3Chord = new StringTokenizer(harmonizedTune, " ");
		while(tok3Chord.hasMoreTokens()){
			String nextChord = tok3Chord.nextToken();
			StringTokenizer tokLength = new StringTokenizer(nextChord, "|");
			
			String notes = tokLength.nextToken();
			double length = Double.parseDouble(tokLength.nextToken());
			
			chords.add(new Tuple<String,Double>(notes,length));
		}
		
		debugPrintln(chords.toString());
		
		for (Tuple<String,Double> chord : chords){
			player.playChord(chord.x, chord.y);
		}
		
	}
}
