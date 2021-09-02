package sff;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePlay extends JPanel implements KeyListener,ActionListener {
	
	private int [] snakeXlength = new int[750];
	private int [] snakeYlength = new int[750];
	
	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;
	
	private ImageIcon headLeft;
	private ImageIcon headRight;
	private ImageIcon headUp;
	private ImageIcon headDown;
	
	private int lengthofsnake = 3;
	 private Timer timer;
	 private int delay = 100;
	 
	private ImageIcon tail;
	private int moves = 0;
    private int score = 0;	
	
  private ImageIcon titleImage; 
  
  public void GamePlay() {
	  
	  addKeyListener(this);
	  setFocusable(true);
	  setFocusTraversalKeysEnabled(false);
	  
	  timer = new Timer(delay,this);
	  timer.start();
  }
  
  public void paint (Graphics g) {
	  
	  if(moves == 0) {
		  snakeXlength[0]=100;
		  snakeXlength[1]=75;
		  snakeXlength[2]=50;
		  
		  snakeYlength[0]=100;
		  snakeYlength[1]=100;
		  snakeYlength[2]=100;
	  }
	  
	  
	  
	  
	  //display title
	  titleImage = new ImageIcon("title.png");
	  titleImage.paintIcon(this, g, 25, 5);
	  
	  //display gameplay border
	  g.setColor(Color.DARK_GRAY);
	  g.drawRect(24, 74, 851, 577);
	  
	  //display gameplay background
	  g.setColor(Color.black);
	  g.fillRect(25, 75, 850, 575);
	 
      //initial positions of the head	  
	  headRight = new ImageIcon("headRight.png");
	  headRight.paintIcon(this, g, snakeXlength[0],snakeYlength[0] );
	  
	  for(int i = 0; i<lengthofsnake; i++) {
		  
		  if(i==0 && right) {
			  headRight = new ImageIcon("headRight.png");
			  headRight.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
		  }
		  if(i==0 && left) {
			  headLeft = new ImageIcon("headLeft.png");
			  headLeft.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
		  }
		  if(i==0 && up) {
			  headUp = new ImageIcon("headUp.png");
			  headUp.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
		  }
		  if(i==0 && down) {
			  headDown = new ImageIcon("headDown.png");
			  headDown.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
		  }
		  if(i!=0) {
			  tail = new ImageIcon("tail.png");
			  tail.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
		  }
		  
		  
	  }
	  
	  
	  g.dispose();
	  
  }

@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	timer.restart();
	if(right) {
		for(int n=lengthofsnake-1;n>=0;n--) {
			snakeYlength[n+1]=snakeYlength[n];
		}
		
		for(int n=lengthofsnake;n>=0;n--) {
			if(n==0) {snakeXlength[n]=snakeXlength[n]+25;}
			else {
				snakeXlength[n]=snakeXlength[n-1];
			}
			
			if(snakeXlength[n]>850) {snakeXlength[n]=25;}
		}
		
		repaint();
	}
	
	
	if(left) {
		for(int n=lengthofsnake-1;n>=0;n--) {
			snakeYlength[n+1]=snakeYlength[n];
		}
		
		for(int n=lengthofsnake;n>=0;n--) {
			if(n==0) {snakeXlength[n]=snakeXlength[n]-25;}
			else {
				snakeXlength[n]=snakeXlength[n-1];
			}
			
			if(snakeXlength[n]<25) {snakeXlength[n]=850;}
		}
		
		repaint();
	}
	
	if(up) {
		for(int n=lengthofsnake-1;n>=0;n--) {
			snakeXlength[n+1]=snakeXlength[n];
		}
		
		for(int n=lengthofsnake;n>=0;n--) {
			if(n==0) {snakeYlength[n]=snakeYlength[n]-25;}
			else {
				snakeYlength[n]=snakeYlength[n-1];
			}
			
			if(snakeYlength[n]<75) {snakeYlength[n]=625;}
		}
		
		repaint();
	}
	
	if(down) {
		for(int n=lengthofsnake-1;n>=0;n--) {
			snakeXlength[n+1]=snakeXlength[n];
		}
		
		for(int n=lengthofsnake;n>=0;n--) {
			if(n==0) {snakeYlength[n]=snakeYlength[n]+25;}
			else {
				snakeYlength[n]=snakeYlength[n-1];
			}
			
			if(snakeYlength[n]>625) {snakeYlength[n]=75;}
		}
		
		repaint();
	}
	
	
	
}

@Override
public void keyTyped(KeyEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void keyPressed(KeyEvent e) {
	// TODO Auto-generated method stub
	if(e.getKeyCode() == KeyEvent.VK_RIGHT ) {
		moves++;
		right = true;
		if(!left) {
			right = true;
		} else {
			right = false;
			left = true;
		}
		up = false;
		down = false;
	}
	
	if(e.getKeyCode() == KeyEvent.VK_LEFT ) {
		moves++;
		left = true;
		if(!right) {
			left = true;
		} else {
			left = false;
			right = true;
		}
		up = false;
		down = false;
	}
	
	if(e.getKeyCode() == KeyEvent.VK_UP ) {
		moves++;
		up = true;
		if(!down) {
			up = true;
		} else {
			up = false;
			down = true;
		}
	   left = false;
		right = false;
	}
	
	if(e.getKeyCode() == KeyEvent.VK_DOWN ) {
		moves++;
		down = true;
		if(!up) {
			down = true;
		} else {
			down = false;
			up = true;
		}
		left = false;
		right = false;
	}
}

@Override
public void keyReleased(KeyEvent e) {
	// TODO Auto-generated method stub
	
}
  
  
}
