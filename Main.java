package se.kth.csc.iprog.agendabuilder;

import javax.swing.*;
import java.util.*;

import se.kth.csc.iprog.agendabuilder.model.*;
import se.kth.csc.iprog.agendabuilder.view.*;

public class Main {
	
	
	public static void main(String[] args){
		AgendaModel model = new AgendaModel();
		JFrame view = new AddActivity(model);
	}
}
