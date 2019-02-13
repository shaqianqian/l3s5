/**
 * @author Nabil Maiz
 * @author Arnaud Cojez
 */

package editor.component;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import editor.Editor;

/**
 * Class defining the Text Area
 * 
 * @author Nabil Maiz
 * @author Arnaud Cojez
 */
public class TextArea extends JTextArea {

	// Fields

	private static final long serialVersionUID = 1L;
	private Editor editor;

	// Methods

	/**
	 * Constructor for the TextArea class
	 * 
	 * @param editor
	 *            the Editor which is the main window class
	 */
	public TextArea(Editor editor) {
		super();
		this.editor = editor;
		this.setLineWrap(true);
		this.getDocument().addDocumentListener(
				new ChangedDocumentListener(this.editor));
	}

	/**
	 * Reads a File and replace the actual content of the textarea with its
	 * content
	 * 
	 * @param file
	 *            the File to read
	 */
	public void readFile(File file) {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(file));
			this.read(reader, null);
			this.getDocument().addDocumentListener(
					new ChangedDocumentListener(this.editor));
		} catch (FileNotFoundException e) {
			System.err.println("Le fichier est introuvable");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Erreur lors de l'ouverture du fichier");
			e.printStackTrace();
		}
	}

	/**
	 * Write the content of the document to File
	 * 
	 * @param file
	 *            the File to write
	 */
	public void writeFile(File file) {
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(file));
			this.write(writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Internal Class used to monitor the changes of the document
	 */
	public class ChangedDocumentListener implements DocumentListener {

		// Fields

		private Editor editor;

		// Methods

		/**
		 * Constructor for the ChangedDocumentListener class
		 * 
		 * @param editor
		 *            the Editor which is the main window class
		 */
		public ChangedDocumentListener(Editor editor) {
			this.editor = editor;
		}

		/*
		 * (non-Javadoc)
		 * @see javax.swing.event.DocumentListener#changedUpdate(javax.swing.event.DocumentEvent)
		 */
		@Override
		public void changedUpdate(DocumentEvent arg0) {
			this.editor.setNotSaved();

		}

		/*
		 * (non-Javadoc)
		 * @see javax.swing.event.DocumentListener#insertUpdate(javax.swing.event.DocumentEvent)
		 */
		@Override
		public void insertUpdate(DocumentEvent arg0) {
			this.editor.setNotSaved();
		}

		/*
		 * (non-Javadoc)
		 * @see javax.swing.event.DocumentListener#removeUpdate(javax.swing.event.DocumentEvent)
		 */
		@Override
		public void removeUpdate(DocumentEvent arg0) {
			this.editor.setNotSaved();
		}

	}
}
