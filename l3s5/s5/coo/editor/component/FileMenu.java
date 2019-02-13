/**
 * @author Nabil Maiz
 * @author Arnaud Cojez
 */
package editor.component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

import editor.Editor;

/**
 * Class defining the File Menu
 * 
 * @author Nabil Maiz
 * @author Arnaud Cojez
 */
public class FileMenu extends JMenu {

	// Fields
	private static final long serialVersionUID = 1L;

	// Methods

	/**
	 * Constructor for the FileMenu class
	 * 
	 * @param editor
	 *            the Editor which is the main window class
	 */
	public FileMenu(Editor editor) {
		super("File");
		JMenuItem newFile = new JMenuItem("New");
		newFile.addActionListener(new NewFileEvent(editor));
		this.add(newFile);

		JMenuItem save = new JMenuItem("Save");
		save.addActionListener(new SaveFileEvent(editor));
		this.add(save);

		JMenuItem saveAs = new JMenuItem("Save As...");
		saveAs.addActionListener(new SaveAsFileEvent(editor));
		this.add(saveAs);

		JMenuItem open = new JMenuItem("Open...");
		open.addActionListener(new OpenFileEvent(editor));
		this.add(open);
		this.add(new JSeparator());
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ExitEvent());
		this.add(exit);
	}

	/**
	 * Internal Class used to manage the app closing when the user chooses the
	 * Exit item in the Menu
	 */
	class ExitEvent implements ActionListener, ItemListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent
		 * )
		 */
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent
		 * )
		 */
		@Override
		public void itemStateChanged(ItemEvent e) {
			// Nothing to do there
		}
	}

	/**
	 * Internal Class used to manage the app reset when the user chooses the New
	 * item in the Menu
	 */
	class NewFileEvent implements ActionListener, ItemListener {

		// Fields
		
		private Editor editor;

		// Methods 
		
		/**
		 * Constructor for the NewFileEvent class
		 * 
		 * @param editor
		 *            the Editor which is the main window class
		 * */
		public NewFileEvent(Editor editor) {
			this.editor = editor;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent
		 * )
		 */
		public void actionPerformed(ActionEvent e) {
			this.editor.reset();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent
		 * )
		 */
		@Override
		public void itemStateChanged(ItemEvent e) {
			// Nothing to do there
		}
	}

	/**
	 * Internal Class used to manage the save of the document when the user
	 * chooses the Save item in the Menu
	 */
	class SaveFileEvent implements ActionListener, ItemListener {

		// Fields
		
		private Editor editor;

		// Methods
		
		/**
		 * 
		 * Constructor for the SaveFileEvent class
		 * 
		 * @param editor
		 *            the Editor which is the main window class
		 */
		public SaveFileEvent(Editor editor) {
			this.editor = editor;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent
		 * )
		 */
		public void actionPerformed(ActionEvent e) {
			this.editor.save();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent
		 * )
		 */
		@Override
		public void itemStateChanged(ItemEvent e) {
			// Nothing to do there
		}
	}

	/**
	 * Internal Class used to manage the save of the document when the user
	 * chooses the Save As... item in the Menu
	 */
	class SaveAsFileEvent implements ActionListener, ItemListener {

		// Fields
		
		private Editor editor;

		//Methods
		
		/**
		 * Constructor for the SaveAsFileEvent class
		 * 
		 * @param editor
		 *            the Editor which is the main window class
		 */
		public SaveAsFileEvent(Editor editor) {
			this.editor = editor;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent
		 * )
		 */
		public void actionPerformed(ActionEvent e) {
			this.editor.saveAs();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent
		 * )
		 */
		@Override
		public void itemStateChanged(ItemEvent e) {
			// Nothing to do there
		}
	}

	/**
	 * Internal Class used to manage the opening of a document when the user
	 * chooses the Open... item in the Menu
	 */
	class OpenFileEvent implements ActionListener, ItemListener {

		//Fields
		
		private Editor editor;

		// Methods
		
		/**
		 * Constructor for the OpenFileEvent class
		 * 
		 * @param editor
		 *            the Editor which is the main window class
		 */
		public OpenFileEvent(Editor editor) {
			this.editor = editor;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent
		 * )
		 */
		public void actionPerformed(ActionEvent e) {
			this.editor.open();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent
		 * )
		 */
		@Override
		public void itemStateChanged(ItemEvent e) {
			// Nothing to do there
		}
	}
}
