import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
/*
 * Ritesh Thapa    CS 250
 * 
 * Assignment 2C
 * 
 * This is a myGui class which sets the interface for the user to interact with it. This class helps to set the display sizes, adds different buttons
 * and displays output with the featured button.
 * 
 */
public class MyGui extends JFrame{
	
	// adding button
	private JButton stk = new JButton("Stocks");
	private JButton mf = new JButton("Mutual Funds");
	private JButton bond = new JButton("Bonds");
	private JButton cusName = new JButton("Customer Info");
	
	private JButton payTax = new JButton("Pay Tax");
	private JButton sellStock = new JButton("Sell Stock");
	private JButton sellMF = new JButton("Sell Mutual Fund");
	private JButton sellBond = new JButton("Sell Bond");
	
	private JButton buyStock = new JButton("Buy Stock");
	private JButton buyMF = new JButton("Buy Mutual Fund");
	private JButton buyBond = new JButton("Buy Bond");
	
	private JLabel cusId = new JLabel("Customer ID");
	private JLabel assetName = new JLabel();
	private JLabel numberToOperate = new JLabel();
	private JTextField cusIdField = new JTextField(20);
	private JTextField assetNameField = new JTextField(20);
	private JTextField numberToOperateField = new JTextField(20);
	private JButton continueBtn = new JButton("continue");
	
	// adding a output area to display the output
	private JTextArea outputArea = new JTextArea();
	private JPanel panel = new JPanel(); 	// for holding buttons
	JPanel panel2 = new JPanel(new GridLayout(4,2,5,5));
	
	public MyGui()
	{

		Container canvas = this.getContentPane(); // setting up the canvas
		
		panel.setLayout(new FlowLayout()); // adding a flowLayout
		outputArea.setLineWrap(true);
		outputArea.setText("This is the output area");
		outputArea.setPreferredSize(new Dimension(1000,900));
		
		panel.add(stk); // this how we add a button
		panel.add(mf);
		panel.add(bond);
		panel.add(cusName);
		panel.add(payTax);
		panel.add(sellStock);
		panel.add(sellMF);
		panel.add(sellBond);
		
		panel.add(buyStock);
		panel.add(buyMF);
		panel.add(buyBond);
		
		panel.add(outputArea);
		
		panel2.add(cusId);
		panel2.add(cusIdField);
		panel2.add(assetName);
		panel2.add(assetNameField);
		panel2.add(numberToOperate);
		panel2.add(numberToOperateField);
		panel2.add(new JLabel());
		panel2.add(continueBtn);
		panel2.setVisible(false);
		panel.add(panel2);
		
		canvas.add(panel);
		
		this.setTitle("Assignment 2C"); // adding a title for the gui
		this.setPreferredSize(new Dimension(1200,1000));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public void displayOutputArea() {
		outputArea.setVisible(true);
		panel2.setVisible(false);
	}
	
	public void displayPayTaxForm() {
		outputArea.setVisible(false);
		panel2.setVisible(true);
		
		assetName.setVisible(false);
		assetNameField.setVisible(false);
		numberToOperate.setVisible(false);
		numberToOperateField.setVisible(false);
	}

	public void displaySellStockForm() {
		outputArea.setVisible(false);
		panel2.setVisible(true);
		assetName.setText("Stock Name");
		assetName.setVisible(true);
		assetNameField.setVisible(true);
		numberToOperate.setText("Number to Sell");
		numberToOperate.setVisible(true);
		numberToOperateField.setVisible(true);
	}

	public void displaySellFundForm() {
		outputArea.setVisible(false);
		panel2.setVisible(true);
		assetName.setText("Mutual Fund Name");
		assetName.setVisible(true);
		assetNameField.setVisible(true);
		numberToOperate.setText("Number to Sell");
		numberToOperate.setVisible(true);
		numberToOperateField.setVisible(true);
	}

	public void displaySellBondForm() {
		outputArea.setVisible(false);
		panel2.setVisible(true);
		assetName.setText("Bond Name");
		assetName.setVisible(true);
		assetNameField.setVisible(true);
		numberToOperate.setText("Number to Sell");
		numberToOperate.setVisible(true);
		numberToOperateField.setVisible(true);
	}
	
	public void displayBuyStockForm() {
		outputArea.setVisible(false);
		panel2.setVisible(true);
		assetName.setText("Stock Name");
		assetName.setVisible(true);
		assetNameField.setVisible(true);
		numberToOperate.setText("Number to Buy");
		numberToOperate.setVisible(true);
		numberToOperateField.setVisible(true);
	}

	public void displayBuyFundForm() {
		outputArea.setVisible(false);
		panel2.setVisible(true);
		assetName.setText("Mutual Fund Name");
		assetName.setVisible(true);
		assetNameField.setVisible(true);
		numberToOperate.setText("Number to Buy");
		numberToOperate.setVisible(true);
		numberToOperateField.setVisible(true);
	}

	public void displayBuyBondForm() {
		outputArea.setVisible(false);
		panel2.setVisible(true);
		assetName.setText("Bond Name");
		assetName.setVisible(true);
		assetNameField.setVisible(true);
		numberToOperate.setText("Number to Buy");
		numberToOperate.setVisible(true);
		numberToOperateField.setVisible(true);
	}
	
	public void clearForm() {
		cusIdField.setText("");
		assetNameField.setText("");
		numberToOperateField.setText("");
	}
	
	public void display() {
		this.pack();
		this.setVisible(true); // need to be true visible
	}
	
	public void setListener(Listener listener)
	{
		stk.addActionListener(listener);
		mf.addActionListener(listener);
		bond.addActionListener(listener);
		cusName.addActionListener(listener);
		
		payTax.addActionListener(listener);
		sellStock.addActionListener(listener);
		sellMF.addActionListener(listener);
		sellBond.addActionListener(listener);
		continueBtn.addActionListener(listener);

		buyStock.addActionListener(listener);
		buyMF.addActionListener(listener);
		buyBond.addActionListener(listener);
	}
	
	public String getCustomerId() {
		return cusIdField.getText();
	}

	public String getAssetName() {
		return assetNameField.getText();
	}

	public String getNumberToOperate() {
		return numberToOperateField.getText();
	}
	
	public JTextArea getOutputArea() {
		return outputArea;
	}
}