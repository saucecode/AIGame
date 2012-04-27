package net.sauce.mapper;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Game extends JPanel implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1213064266196997267L;
	Timer timer = new Timer(50,this);
	World world = new World(this);
	JFrame frame;
	
	public static void main(String[] args){
		new Game();
	}
	
	public Game(){
		frame = new JFrame("Artificial Intellegence Test");
		frame.setVisible(true);
		frame.setSize(800,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		frame.addKeyListener(this);
		
		System.out.println("started");
		
		timer.start();
		addKeyListener(this);
		this.setFocusable(true);
	}
	
	public void update(){
		world.onTick();
	}
	
	public void paint(Graphics gg){
		super.paint(gg);
		Graphics2D g = (Graphics2D)gg;
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		world.draw(g);
	}

	public void actionPerformed(ActionEvent arg0) {
		update();
		repaint();
	}

	public void keyPressed(KeyEvent arg0) {
		world.keyPressed(arg0.getKeyCode());
	}

	public void keyReleased(KeyEvent arg0) {
		world.keyReleased(arg0.getKeyCode());
	}

	public void keyTyped(KeyEvent arg0) {
		//world.keyTyped(arg0.getKeyCode());
	}
}
