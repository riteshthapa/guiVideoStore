import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class Listener implements ActionListener  {
	
	MyGui gui;
    Broker broker;
	
	@Override
	public void actionPerformed(ActionEvent event){
		
		JButton clicked = (JButton) event.getSource();
		String text = clicked.getText();
		System.out.println("\""+text+"\" button is clicked.");
		
		if (text.equals("Stocks")) {
			String output = "";
			Stock[] stocks = broker.getStocks();
			for(Stock stock : stocks) {
				output += stock.toString()+"\n";
			}
			gui.getOutputArea().setText(output);
		}
		if (text.equals("Mutual Funds")) {
			
			String output = "";
			
			MutualFund[] funds = broker.getMutualFunds();
			for(MutualFund fund : funds) {
				output += fund.toString()+"\n";
			}
			gui.getOutputArea().setText(output);
		}
		if (text.equals("Bonds")) {
			
			String output = "";
			Bond[] bonds = broker.getBonds();
			for(Bond bond : bonds) {
				output += bond.toString()+"\n";
			}
			gui.getOutputArea().setText(output);
		}
		if (text.equals("Customer Info")) {
			
			String output = "";
			CustomerAccount[] accounts = broker.getCustomers();
			for(CustomerAccount account : accounts) {
				output += account.toString()+"\n";
			}
			gui.getOutputArea().setText(output);
		}
	}
	
	
	public void setBroker(Broker broker){
		this.broker = broker;
	}

	//setGui method 
	public void setGui(MyGui gui) {
		this.gui = gui;
	}
}
