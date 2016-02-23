package se.kth.csc.iprog.agendabuilder.view;

import java.util.*;
import javax.swing.*;

import se.kth.csc.iprog.agendabuilder.model.*;
import se.kth.csc.iprog.agendabuilder.util.*;

/**
 * The window to add an activity from.
 * @author Daniel
 *
 */
public class AddActivity extends JFrame{
	private final int x = 10;
	private final int height = 25;
	private final int width = 200;
	
	private AgendaModel model;
	public JTextField name = new JTextField();
	public JTextField length = new JTextField();
	public JComboBox type;
	public JTextArea description = new JTextArea();
	public JButton cancel = new JButton();
	public JButton save = new JButton();
	
	public AddActivity(AgendaModel model){
		this.model =  model;
		this.setLayout(null);
		this.setTitle("Add Activity");
		
		//name
		name.setBounds(x, x, width, height);
		this.add(name);
		
		//length
		length.setBounds(x, 2*x+height, width/2, height);
		this.add(length);
		JLabel mins = new JLabel();
		mins.setBounds(2*x+(width/2), (2*x+height), width/2, height);
		mins.setText("min");
		this.add(mins);
		
		//type
		String[] types = {"Presentation", "Discussion", "Group Work", "Break"};
		type = new JComboBox(types);
		type.setBounds(x, 3*x+2*height, width, height);
		this.add(type);
		
		//Description
		description.setBounds(x, 4*x+3*height, width, 4*height);
		this.add(description);
		
		//Buttons
		cancel.setBounds(x, 225, width/2, height);
		cancel.setText("Cancel");
		this.add(cancel);
		save.setBounds(x+(width/2), 225, width/2, height);
		save.setText("Save");
		this.add(save);
		
		this.pack();
		this.setBounds(0,0,250,300);
		this.setVisible(true);
	}
	
	
}
