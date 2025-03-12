package pcd.lab01.ex01;

import org.fusesource.jansi.Ansi;

import static pcd.lab01.ex01.AuxLib.*;

public class TestAuxLib {

	public static void main(String[] args) throws InterruptedException {
		
		var sentence = "This is a simple sentence with words ready to fall";

		Screen screen = Screen.getInstance();
		screen.clear();

		var wordList = getWordsPos(sentence);

		screen.writeStringAt(1, 0, Ansi.Color.CYAN, sentence);

		Thread.sleep(2000);

		screen.writeStringAt(1, 0, Ansi.Color.BLACK, sentence);

		for (var wp: wordList) {
			Thread w = new Word(wp.word(), wp.pos());
			w.start();
		}		
	}

}
