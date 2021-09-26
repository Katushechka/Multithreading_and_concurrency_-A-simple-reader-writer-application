package assignment2;

import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JTextArea;


/*
 * Klassen Writer som läser från UI och skriver en char i taget till en buffer. Skriver också till UI
 * @version 1.0
 * @author Ekaterina Korotetskaya
 */

public class Writer {
	private String text;
	private Activity thread;
	private CharacterBuffer buffer;
	private JGUIAssignment2 ui;
	private JTextArea textArea;
	private boolean asyncIsSelected;
	private volatile boolean running = true;
	private JLabel lblTrans;
	private boolean tryAgain;
	
	
	/**
	 *Konstruktor för klassen Writer
	 * @param String text - text som användaren skriver i UI
	 * @param CharacterBuffer buffer  
	 * @param boolean asyncIsSelected 
	 */
	
	public Writer (String text, CharacterBuffer buffer, boolean asyncIsSelected) {
		this.text=text;
		this.buffer = buffer;
		this.asyncIsSelected = asyncIsSelected;
		
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
			int counter=0;
			while (running && counter<text.length()) {

				//Asynchronous
					if(asyncIsSelected == true) {					
						buffer.put(text.charAt(counter));
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						textArea.append(text.charAt(counter) + "\n");
						lblTrans.setText(textArea.getText());
						counter ++;
						
					//Synchronous
					} else {										
							try {
				                Thread.sleep(500);
				                buffer.putSync(text.charAt(counter));
				            }
				            catch(InterruptedException e) {
				                break;
				            }
									
						textArea.append(text.charAt(counter) + "\n");
						lblTrans.setText(textArea.getText());
						counter ++;
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
		lblTrans.setText(null);
	}	
		
	public void setDisplayTrans(JTextArea textArea) {
		this.textArea=textArea;		
	}
	
	public void setDisplay(JLabel lblTrans) {
		this.lblTrans=lblTrans;
		
	}	

}

