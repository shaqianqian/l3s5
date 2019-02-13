/**
 * @author Nabil Maiz
 * @author Arnaud Cojez
 */
package editor.component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import plugin.Plugin;
import plugin.PluginAddedEvent;
import plugin.PluginEventListener;
import editor.Editor;

/**
 * Class defining the Tools Menu
 * 
 * @author Nabil Maiz
 * @author Arnaud Cojez
 */
public class ToolsMenu extends JMenu implements PluginEventListener {

	// Fields

	private static final long serialVersionUID = 1L;
	private Editor editor;

	// Methods

	/**
	 * Constructor for the ToolsMenu class
	 * 
	 * @param editor
	 *            the Editor which is the main window class
	 * */
	public ToolsMenu(Editor editor) {
		super("Tools");
		this.editor = editor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see plugin.PluginEventListener#pluginAdded(plugin.PluginAddedEvent)
	 */
	@Override
	public void pluginAdded(PluginAddedEvent plugin) {
		JMenuItem menuItem = new JMenuItem(plugin.getLabel());
		menuItem.addActionListener(new ToolListener(plugin, this.editor));
		this.add(menuItem);
	}

	/**
	 * Internal Class used to manage the addition of a plugin to the Tools menu
	 */
	public class ToolListener implements ActionListener {

		// Fields

		private Editor editor;
		private Plugin plugin;

		// Methods

		/**
		 * 
		 * Constructor for the ToolListener class
		 * 
		 * @param plugin
		 *            the added Plugin
		 * @param editor
		 *            the Editor which is the main window class
		 */
		public ToolListener(Plugin plugin, Editor editor) {
			this.editor = editor;
			this.plugin = plugin;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent
		 * )
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			String selection = this.editor.getSelectedText();
			if (selection != null)
				this.editor.replaceSelection(this.plugin.transform(selection));
		}
	}
}
