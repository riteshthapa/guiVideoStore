import java.awt.event.ActionEvent;
import javax.swing.AbstractButton;
import javax.swing.JOptionPane;
/*
 * Ritesh Thapa 
 * Cs 250
 * 
 * This is a extended listener class that inherits listener.
 * 
 */
public class ExtendedListener extends Listener{
	
	private MyGui gui;
	private Broker broker;
	protected String actionToBeTaken;

	@Override
	public void actionPerformed(ActionEvent event){
		AbstractButton clicked = (AbstractButton) event.getSource();
		String text = clicked.getText();
		gui.setTitle("Assignment2C : "+text);

		switch(text) {
			case "Pay Tax":
				System.out.println("\""+text+"\" button has been clicked!");
				actionToBeTaken = text;
				gui.displayPayTaxForm();
				break;
			case "Sell Stock":
				System.out.println("\""+text+"\" button has been clicked!");
				actionToBeTaken = text;
				gui.displaySellStockForm();
				break;
			case "Sell Mutual Fund":
				System.out.println("\""+text+"\" button has been clicked!");
				actionToBeTaken = text;
				gui.displaySellFundForm();
				break;
			case "Sell Bond":
				System.out.println("\""+text+"\" button has been clicked!");
				actionToBeTaken = text;
				gui.displaySellBondForm();
				break;
			case "continue":
				System.out.println("\""+text+"\" button has been clicked!");
				Integer custIndex = null;
				try {
					custIndex = broker.searchCustomerById(gui.getCustomerId());
				}catch (InvalidCustomerIDException e) {
					JOptionPane.showMessageDialog(gui, e.toString());
				}
				Integer numToSell = null;
				if(!actionToBeTaken.equals("Pay Tax")) {	// because number to sell is not required to pay tax
					try {
						numToSell = Integer.parseInt(gui.getNumberToOperate());
					}catch(NumberFormatException e) {
						JOptionPane.showMessageDialog(gui, "Invalid number to sell!");
					}
				}
				if(custIndex != null) {
					try {
						switch(actionToBeTaken) {
							case "Pay Tax":
								boolean success = broker.payTaxOfCustomer(custIndex);	
								if(success) {
									broker.updateFile(); // updates data in file
									JOptionPane.showMessageDialog(gui, "Tax successfully Paid!");
									gui.clearForm();
								} else {
									JOptionPane.showMessageDialog(gui, "Failed to pay tax!\nInsufficient cash in hand!");
								}
								break;
							case "Sell Stock":
								if(broker.getStocks().length == 0) {	 // if no stocks available
									JOptionPane.showMessageDialog(gui, "No Stocks Available!");
								} else {
									int stockIndex = broker.searchStockByName(gui.getAssetName());
									broker.sellStock(custIndex, stockIndex, numToSell);	
									broker.updateFile();	// updates data in file
									JOptionPane.showMessageDialog(gui, "Stock sold successfully!");
									gui.clearForm();
								}
								break;
							case "Sell Mutual Fund":
								if(broker.getMutualFunds().length == 0) {	 // if no mutual fund available
									JOptionPane.showMessageDialog(gui, "No Mutual Funds Available!");
								} else {
									int fundIndex = broker.searchMutualFundByName(gui.getAssetName());
									broker.sellMutualFund(custIndex, fundIndex, numToSell);	
									broker.updateFile();	// updates data in file
									JOptionPane.showMessageDialog(gui, "Mutual Fund sold successfully!");
									gui.clearForm();
								}
								break;
							case "Sell Bond":
								if(broker.getBonds().length == 0) {	 // if no mutual fund available
									JOptionPane.showMessageDialog(gui, "No Bonds Available!");
								} else {
									int fundIndex = broker.searchBondByName(gui.getAssetName());
									broker.sellBond(custIndex, fundIndex, numToSell);	
									broker.updateFile();	// updates data in file
									JOptionPane.showMessageDialog(gui, "Bond sold successfully!");
									gui.clearForm();
								}
								break;
						}
					}catch(InvalidAssetNameException | NotEnoughAssetToSellException e) {
						JOptionPane.showMessageDialog(gui, e.toString());
					}
				}
				break;
			default:
				gui.displayOutputArea();
				super.actionPerformed(event);
		}
	}
	
	@Override
	public void setBroker(Broker broker) {
		this.broker = broker;
		super.setBroker(broker);
	}

	@Override
	public void setGui(MyGui gui) {
		this.gui = gui;
		super.setGui(gui);
	}
}