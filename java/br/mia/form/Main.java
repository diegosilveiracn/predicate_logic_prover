package br.mia.form;

import br.mia.form.data.SystemProver;

public class Main {

	public static void main(String[] args) {
		SwingApplication swingApplication = new SwingApplication();
		SystemProver.setOut(swingApplication);
		swingApplication.setVisible(Boolean.TRUE);
	}
	
}