

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;


public class FourInArrowGui  implements ActionListener{
	
	
	public static void main(String[] args) {
		FourInArrowGui game = new FourInArrowGui(); // default dimensions 7x7
		//ArrayList

	}
	 private int dimensions;
	 private JFrame window;
	 private Container contentPane;
	 private JButton [][] arrButtons;
	 private char[][] cTable;
	 char player = 'O';
	 
	public FourInArrowGui() throws HeadlessException {
		this.dimensions = 7;
		window = new JFrame("4 In Arow");
		window.setSize(870,880);
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		contentPane = window.getContentPane();
		contentPane.setLayout(new GridLayout(dimensions,dimensions));
		
		arrButtons = new JButton[dimensions][dimensions];
		cTable= new char[dimensions][dimensions];
	
		for( int r = 0; r < dimensions; r++) {
			for( int c = 0; c < dimensions; c++) {
				arrButtons[r][c] = new JButton();
				arrButtons[r][c].addActionListener(this);
				arrButtons[r][c].setActionCommand("" + c);
				arrButtons[r][c].setText(".");
				contentPane.add(arrButtons[r][c]);
				cTable[r][c] = '.';		
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int c = Integer.parseInt(e.getActionCommand());
		int r = emptyRow(c);
		if(r !=-1) {
			arrButtons[r][c].setText("" + player);
			cTable[r][c] = player ;
			if(winner(r, c)) {
				result(0);
			} else if(boardIsFull()) {
				result(1);
			}
			
		}			
		nextPlayer(); // updates the player
	}
		
	public void nextPlayer() {
		if(this.player == 'O') {
			this.player = 'X';
		} else if(this.player == 'X'){
			this.player = 'O';
		}
	}
	
	public int emptyRow(int c) {
		for( int r = dimensions -1; r >= 0; r--) {
			if((cTable[r][c] == '.')) {
				return r;
				
			}
		}
		return -1; // returns -1 if there are no empty rows in chosen column
	}
	
	// prints who is the winner on a separate window
	public void result(int p) { 
		JFrame messageWindow = new JFrame("Who won!!");
		messageWindow.setLocationRelativeTo(null);
		messageWindow.setSize(200,60);
		messageWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		messageWindow.setVisible(true);
		Container mcontentPane = messageWindow.getContentPane();
		mcontentPane.setLayout(new FlowLayout());
		JLabel message;
		if(p == 0 ) {
			message = new JLabel("The winner is " + player);
			mcontentPane.add(message);
		}
		else if(p == 1 ) {
			message = new JLabel("Is a tie");
			mcontentPane.add(message);}
		}
	
	public boolean boardIsFull() {
		
		for( int i = 0; i < dimensions ; i++) {
			if(cTable[0][i] != 'O' || cTable[0][i] != 'X'){
			return false; 
			}
		}
		return true;
	}

	public boolean winner(int row, int col) {
		
		// diagonal BLR
		int tmpr = row;
		int tmpc = col;
		// check left 
		int w = 1;
		while(tmpr < 6 && tmpc > 0) {
			tmpr += 1;
			tmpc -= 1;
			if(cTable[tmpr][tmpc] == player) {
				w++;
		} else { break;
			}
		}	
			
		tmpr = row;
		tmpc = col;
	
		// check right 
		while(tmpr > 0 &&  tmpc < 6) {
			tmpr -= 1;
			tmpc += 1;
			if(cTable[tmpr][tmpc] == player) {
				w++;
			} else { break;}
		}
	
		if( w > 3) return true;
	
		//Diagonal TLR
		w = 1;
		tmpr = row;
		tmpc = col;
		// check left 
		while(tmpr > 0 && tmpc > 0) {
			tmpr -= 1;
			tmpc -= 1;
			if(cTable[tmpr][tmpc] == player) {
			w++;
			} else { break;}
		}
		
		tmpr = row;
		tmpc = col;
	
		// check right 
		while(tmpr < 6  && tmpc < 6) {
			tmpr += 1;
			tmpc += 1;
			if(cTable[tmpr][tmpc] == player) {
				w++;
			} else { break;}
		}
	
		if( w > 3) return true;
	
		w =1;
		// Horizontal check
		// check left
		tmpr = row;
		tmpc = col;
	
		while( tmpc > 0 ) {
			tmpc  -= 1;
			if (cTable[tmpr][tmpc] == player) {
				w++;
			} else { break;}
		}
		tmpc = col;
		// check right 
		while( tmpc < 6) {
			tmpc += 1;
			if(cTable[tmpr][tmpc] == player) {
				w++;
			} else { break;}
		}
	
		if( w > 3) return true;
		
		tmpr = row;
		tmpc = col;
	
		// check down vertical check 
		w = 1;
		while(tmpr < 6 ) {
			tmpr += 1;
			if(cTable[tmpr][tmpc] == player) {
				w++;
			} else { break;}
		}
		
		if( w > 3) {return true;}
		else return false;
	}	
}