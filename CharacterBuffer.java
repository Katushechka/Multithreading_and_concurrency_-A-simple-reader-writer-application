package assignment2;

/*
 * Klassen CharacterBuffer en buffer som innehåller en char
 * @version 1.0
 * @author Ekaterina Korotetskaya
 */

public class CharacterBuffer {
	private boolean hasCharacter;
	private char charToBuffer;
	private int buffer; 
	private boolean empty;
	
	public CharacterBuffer() {
		this.empty = true;
	}
	
	
	/**
	* En metod put - som lägger en char i bufferten (ej synkroniserad)
	* @version 1.0
	* @author Ekaterina Korotetskaya
	*/
	public void put(char character) {		
			charToBuffer=character;		
	}
	
	/**
	* En metod get- hämtar char som ligger i bufferten (ej synkroniserad)
	* @version 1.0
	* @author Ekaterina Korotetskaya
	*/
	public char get() {
		return charToBuffer;		
	}
	
	
	
	
	public void putSync (char character ) {
	
     		synchronized (this) {
     			while(!this.empty) {
     				try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
     			}	
     				charToBuffer=character;
     				this.empty = false;
     				this.notify();		
     		}
	}
	
	
	
	
	public char getSync() throws InterruptedException { 
		
			synchronized (this) {
				while(this.empty) {
					try { 
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				this.empty = true;
				this.notify();				
				return charToBuffer;			
			}
	}
}

