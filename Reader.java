package assignment2;

import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JTextArea;


/*
 * Klassen Reader som läser en char i taget från en buffer och skriver till UI
 * @version 1.0
 * @author Ekaterina Korotetskaya
 */

public class Reader extends Thread{
	private String text;
	private Activity thread;
	private JGUIAssignment2 ui;
	private JTextArea textArea;
	private JLabel lblRec;
	private CharacterBuffer buffer;
	private boolean asyncIsSelected;
	private volatile boolean running = true;
	private int counter=0;
	private int antalChar;
	private String readFromBuffer;
	private char readFromBufferAsync;
	private char toPrint;
	
	/**
	 *Konstruktor för klassen Reader
	 * @param int antalChar
	 * @param CharacterBuffer buffer  
	 * @param boolean asyncIsSelected 
	 */
	
	public Reader (int antalChar, CharacterBuffer buffer, boolean asyncIsSelected ) {
		this.antalChar = antalChar;
		this.buffer=buffer;
		this.asyncIsSelected=asyncIsSelected;;
		
	}
	public void start() {
		if( thread == null ) {
			thread = new Activity();
			thread.start();
		}
	}
	
	/**
	* Inre klassen Activity som ärver Thread
	* @version 1.0
	* @author Ekaterina Korotetskaya
	*/
	private class Activity extends Thread {
		
		
		public void run() {	
			int counter = 0;
			while (running && counter < antalChar) {		
				
				//Asynchron.
					if (asyncIsSelected == true) {
						readFromBufferAsync = buffer.get();
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						textArea.append(readFromBufferAsync + "\n");
						lblRec.setText(textArea.getText());
				
						counter ++;
					
	
				
					
				//Synchron.
					} else {
						while (true) {
							try {
								toPrint = buffer.getSync();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							textArea.append(toPrint + "\n");
							lblRec.setText(textArea.getText());
						}					
								
					}					
				}
			}		
		}
	
	/**
	* En metod shutDown() stoppar tråden
	* @version 1.0
	* @author Ekaterina Korotetskaya
	*/
	
	public void shutdown() {
		running = false;
		textArea.setText(null);
		lblRec.setText(null);
	}
	
	public void setDisplayRec(JTextArea textArea) {
		this.textArea=textArea;
	}
	
	public void setDisplay(JLabel lblRec) {
		this.lblRec=lblRec;
	}
}


