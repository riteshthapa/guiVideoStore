import java.awt.event.ActionEvent;
import javax.swing.AbstractButton;
import javax.swing.JOptionPane;

/*
 * Ritesh Thapa 
 * Cs 250
 * 
 * This is a extended listener2 class that inherits extendedlistener.
 * 
 */

public class ExtendedListener2 extends ExtendedListener{
	
	private MyGui gui;
	private Broker broker;

	@Override
	public void actionPerformed(ActionEvent event){
		
		AbstractButton clicked = (AbstractButton) event.getSource();
		String text = clicked.getText();
		gui.setTitle("Assignment2C : "+text);
		
		switch(text) {
			case "Buy Stock":
				System.out.println("\""+text+"\" button has been clicked!");
				actionToBeTaken = text;
				gui.displayBuyStockForm();
				break;
			case "Buy Mutual Fund":
				System.out.println("\""+text+"\" button has been clicked!");
				actionToBeTaken = text;
				gui.displayBuyFundForm();
				break;
			case "Buy Bond":
				System.out.println("\""+text+"\" button has been clicked!");
				actionToBeTaken = text;
				gui.displayBuyBondForm();
				break;
			case "continue":
				System.out.println("\""+text+"\" button has been clicked!");
				Integer custIndex = null;
				try {
					custIndex = broker.searchCustomerById(gui.getCustomerId());
				}catch (InvalidCustomerIDException e) {
					JOptionPane.showMessageDialog(gui, e.toString());
				}
				Integer numToBuy = null;
				try {
					numToBuy = Integer.parseInt(gui.getNumberToOperate());
				}catch(NumberFormatException e) {
					JOptionPane.showMessageDialog(gui, "Invalid number to buy!");
				}
				if(custIndex != null) {
					try {
						switch(actionToBeTaken) {
							case "Buy Stock":
								if(broker.getStocks().length == 0) {	 // if no stocks available
									JOptionPane.showMessageDialog(gui, "No Stocks Available!");
								} else {
									int stockIndex = broker.searchStockByName(gui.getAssetName());
									broker.buyStock(custIndex, stockIndex, numToBuy);	
									broker.updateFile();	// updates data in file
									JOptionPane.showMessageDialog(gui, "Stock Purchased successfully!");
									gui.clearForm();
								}
								break;
							case "Buy Mutual Fund":
								if(broker.getMutualFunds().length == 0) {	 // if no mutual fund available
									JOptionPane.showMessageDialog(gui, "No Mutual Funds Available!");
								} else {
									int fundIndex = broker.searchMutualFundByName(gui.getAssetName());
									broker.buyMutualFund(custIndex, fundIndex, numToBuy);	
									broker.updateFile();	// updates data in file
									JOptionPane.showMessageDialog(gui, "Mutual Fund Purchased successfully!");
									gui.clearForm();
								}
								break;
							case "Buy Bond":
								if(broker.getBonds().length == 0) {	 // if no mutual fund available
									JOptionPane.showMessageDialog(gui, "No Bonds Available!");
								} else {
									int bondIndex = broker.searchBondByName(gui.getAssetName());
									broker.buyBond(custIndex, bondIndex, numToBuy);	
									broker.updateFile();	// updates data in file
									JOptionPane.showMessageDialog(gui, "Bond Purchased successfully!");
									gui.clearForm();
								}
								break;
							default :
								super.actionPerformed(event);
						}
					}catch(InvalidAssetNameException | NotEnoughAssetToBuyException | NotEnoughCashInHandToBuyException e) {
						JOptionPane.showMessageDialog(gui, e.toString());
					}
				}
				break;
			default:
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