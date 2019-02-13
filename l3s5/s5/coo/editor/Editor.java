package editor;

/**
 * @author Nabil Maiz
 * @author Arnaud Cojez
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import plugin.PluginEventListener;
import plugin.PluginFinder;
import editor.component.FileMenu;
import editor.component.HelpMenu;
import editor.component.TextArea;
import editor.component.ToolsMenu;

/**
 * Class defining the Editor
 * @author Nabil Maiz
 * @author Arnaud Cojez
 */
public class Editor {

	// Fields
	protected JFrame frame;
	protected TextArea textarea;
	protected File file;
	protected boolean saved;
	protected static final String VERSION = "eXditor 1.0";
	protected final File dropins = new File("./dropins/plugins");
	protected final PluginFinder finder = new PluginFinder(dropins);

	// Methods
	
	/**
	 * Constructor for the Editor class
	 */
	public Editor() {

		JMenuBar menubar = new JMenuBar();
		JMenu tools = new ToolsMenu(this);
		JMenu helpMenu = new HelpMenu(this);
		this.frame = new JFrame();
		this.frame.addWindowListener(new CloseWindowEvent());
		this.frame.setLocation(100, 300);

		/* Menu Bar*/
		this.frame.add(menubar, BorderLayout.NORTH);

		/* Menu File */
		menubar.add(new FileMenu(this));

		/* Menu Tools */
		this.finder.addListener((PluginEventListener) tools);
		menubar.add(tools);

		/* Menu Help */
		this.finder.addListener((PluginEventListener) helpMenu);
		menubar.add(helpMenu);

		/* TextArea */
		this.textarea = new TextArea(this);
		JScrollPane sp = new JScrollPane(this.textarea);
		this.frame.add(sp);

		this.frame.setPreferredSize(new Dimension(500, 300));
		this.saved = true;
		if (!this.dropins.exists())
			this.dropins.mkdir();
		this.updateTitle();
	}

	/**
	 * Runs the Application until the user stops it
	 */
	public void run() {
		this.frame.pack();
		this.frame.setVisible(true);
		this.finder.start();
	}

	/**
	 * Updates the title of the window
	 */
	public void updateTitle() {
		StringBuilder sb = new StringBuilder();
		if (!this.saved)
			sb.append('*');
		if (this.file == null)
			sb.append("untitled");
		else
			sb.append(this.file.getName());
		sb.append(" | ");
		sb.append(Editor.VERSION);
		this.frame.setTitle(sb.toString());
	}

	/**
	 * Resets the editor to work on a new Document
	 */
	public void reset() {
		this.textarea.setText(null);
		this.file = null;
		this.saved = true;
		this.updateTitle();
	}

	/**
	 * Saves the Document to the output if it is known. If the output file is
	 * unknown, makes a call to saveAs
	 */
	public void save() {
		if (this.file == null)
			this.chooseFile();
		if (this.file != null) {
			if (!this.file.exists())
				try {
					this.file.createNewFile();
				} catch (IOException e) {
					System.out.println("erreur lors de la création du fichier"
							+ this.file.getAbsolutePath());
					e.printStackTrace();
				}
			this.textarea.writeFile(this.file);
			this.saved = true;
		}
		this.updateTitle();
	}

	/**
	 * Asks the user to choose an output file and saves to it
	 */
	public void saveAs() {
		this.chooseFile();
		if (this.file != null) {
			if (!this.file.exists())
				try {
					this.file.createNewFile();
				} catch (IOException e) {
					System.out.println("erreur lors de la création du fichier"
							+ this.file.getAbsolutePath());
					e.printStackTrace();
				}
			this.textarea.writeFile(this.file);
			this.saved = true;
		}
		this.updateTitle();
	}

	/**
	 * Chooses and opens an existing file and fills textArea with the content of this file
	 */
	public void open() {
		this.chooseFile();
		if (this.file != null)
			this.loadFile();
	}
	
	/**
	 * Loads this.file in textArea if this.file isn't null
	 */
	public void loadFile() {
			if (this.file.exists()) {
				this.textarea.readFile(this.file);
				this.saved = true;
				this.updateTitle();
			}
		}

	/**
	 * Chooses an existing file or creates one thanks to a FileChooser
	 */
	public void chooseFile() {
		JFileChooser fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(fc);

		if (returnVal == JFileChooser.APPROVE_OPTION)
			this.file = fc.getSelectedFile();
	}

	/**
	 * Sets [saved] to false
	 */
	public void setNotSaved() {
		this.saved = false;
		this.updateTitle();
	}

	/**
	 * Internal Class used to manage the app closing when the user closes the
	 * window
	 */
	class CloseWindowEvent extends WindowAdapter {
		public void windowClosing(java.awt.event.WindowEvent e) {
			System.exit(0);
		}
	}

	/**
	 * Main method of the editor
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Editor editor = new Editor();
		editor.run();

	}

	/**
	 * Returns the text selected in the TextArea
	 * @return the text selected in the TextArea
	 */
	public String getSelectedText() {
		return this.textarea.getSelectedText();
	}

	/**
	 * Replaces the text selected in the TextArea by <code>transform</code>
	 * @param transform the text to write
	 */
	public void replaceSelection(String transform) {
		this.textarea.replaceSelection(transform);
	}

	/**
	 * Shows a confirm message with a title
	 * @param title
	 * @param message
	 */
	public void confirm(String title, String message) {
		JOptionPane.showMessageDialog(this.frame, message, title, JOptionPane.INFORMATION_MESSAGE);
	}
}