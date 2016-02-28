package se.kth.csc.iprog.agendabuilder.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import se.kth.csc.iprog.agendabuilder.model.AgendaModel;

/**
 * A schedule for all days.
 * The detailed plan shows all days with
 * all Activities and all times.
 * All activities can be shown with a description.
 * 
 * This page is none editable.
 *
 */
public class DetailedPlan extends JFrame {
	private static final int DRAWING_SIZE = 700;
	private static final int SUBDIVISIONS_D = 7;
	private static final int SUBDIVISIONS_H = 24;
	private static final int SUBDIVISION_SIZE = 142;
	private static final int SUBDIVISION_SIZE_TWO = 29;
	
    JPanel panel = new JPanel();
	private AgendaModel model;
	
	public DetailedPlan(AgendaModel model){
		this.model =  model;
		this.setLayout(null);
		this.setTitle("Detailed Plan");

	  	setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.SOUTH;
		JPanel panel = new JPanel() {
		   @Override public void paintComponent(Graphics g) {
		      super.paintComponent(g);
		      Graphics2D g2 = (Graphics2D) g;
		      g2.setPaint(Color.GRAY);
		      for (int i = 1; i < SUBDIVISIONS_D; i++) {
		         int x = i * SUBDIVISION_SIZE;
		         g2.drawLine(x, 0, x, getSize().height);
	          }
	          for (int i = 1; i < SUBDIVISIONS_H; i++) {
	             int y = i * SUBDIVISION_SIZE_TWO;
	             g2.drawLine(0, y, getSize().width, y);
	          }
		   }          
		};
	  	panel.setPreferredSize(new Dimension(1000,700));
	  	panel.setBackground(Color.WHITE);
	  	panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	  	gbc.gridy++;
	  	gbc.anchor = GridBagConstraints.CENTER;
	  	this.add(panel, gbc);
		
		this.pack();
		this.setBounds(0,0,1200,800);
		this.setVisible(true);
	}


}