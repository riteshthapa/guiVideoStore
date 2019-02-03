import java.io.FileNotFoundException;
/*
 * Ritesh Thapa
 * CS 250
 * Assignment 2C
 * Driver class that connects the gui and listener.
 * 
 */
public class Driver {
	public static void main(String[] args) {
		
		try {
			Broker broker = new Broker();
			MyGui myGui = new MyGui();
			Listener listener = new ExtendedListener2();
		
			myGui.setListener(listener); // gui needs to know about listener 
			listener.setBroker(broker);
			listener.setGui(myGui); // listener needs to know about gui
			
			myGui.display();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}

