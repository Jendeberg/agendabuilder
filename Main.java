package se.kth.csc.iprog.agendabuilder;

import javax.swing.*;
import java.util.*;

import se.kth.csc.iprog.agendabuilder.controller.StartButtonController;
import se.kth.csc.iprog.agendabuilder.model.*;
import se.kth.csc.iprog.agendabuilder.view.*;

/**
 * The class that starts the AgendaBuilder
 * 
 * @author Daniel
 *
 */
public class Main {
	
	
	public static void main(String[] args){
		AgendaModel model = new AgendaModel();
		StartView view = new StartView(model);
		StartButtonController SBC = new StartButtonController(model,view);
		
	}
}
