package plugin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

@SuppressWarnings("serial")
public class Window extends JFrame implements PluginListener {
	private JTextPane tp = new JTextPane();
	private JMenu mnPlugins;
	private JTextArea textArea;
	private JMenuItem mntmPropos;
	private JFrame frame;

	/**
	 * Create the frame.
	 */
	public Window() {
		
		setTitle("TP Plugin");
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(301, 6, 68, 29);
		frame.add(menuBar);
		
		mnPlugins = new JMenu("Tools");
		menuBar.add(mnPlugins);
	
		
		textArea = new JTextArea();
		textArea.setTabSize(4);
		JButton button1 = new JButton("open");
		button1.setBackground(UIManager.getColor("Button.shadow"));
		button1.setForeground(Color.BLACK);
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button1.setBounds(6, 6, 68, 29);
		frame.getContentPane().add(button1);
		
		  
		button1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				File file = null;
				String filePath;
		        int result = -1;
		        JFileChooser  fileChooser = new JFileChooser("/Users/shaqianqian/Documents/workspace/tp4/src/e33");
		           fileChooser.setApproveButtonText("确定");
		            fileChooser.setDialogTitle("打开文件");
		            fileChooser.setFileFilter(new FileNameExtensionFilter("text files (*.odt)", "odt"));
		            result = fileChooser.showOpenDialog(frame);
		            if (result == fileChooser.APPROVE_OPTION)
		            {
		                file = fileChooser.getSelectedFile();
		                String path = fileChooser.getSelectedFile().getAbsolutePath();
		    			if(path.substring(path.lastIndexOf(".")+1).equals("odt")){
		    				filePath = fileChooser.getSelectedFile().getAbsolutePath();
		    				try {
		    					BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
		    					tp.setText(bufferedReader.readLine());
		    					bufferedReader.close();
		    					
		    				} catch (IOException e1) {
		    					
		    				}
		    			}
		    			else {
    				System.out.println("ne peux pas ouvrir");
		    			}
		              }  
		           
		            
		        }

		            
					}
			

					);
		
		JButton button3 = new JButton("help");
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button3.setBounds(72, 6, 68, 29);
		frame.getContentPane().add(button3);
		
		JList list = new JList();
		list.setBounds(122, 125, -32, -19);
		frame.getContentPane().add(list);
		
		JList list_1 = new JList();
		list_1.setBounds(16, 46, 1, 1);
		frame.getContentPane().add(list_1);
		
		tp.setBounds(16, 47, 428, 134);
		frame.getContentPane().add(tp);
		
		JButton button4 = new JButton("转成大写");
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		button4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				String s=tp.getText().toUpperCase(); 
				tp.setText(s);
			}

			});
		button4.setBounds(141, 6, 81, 29);
		frame.getContentPane().add(button4);
		
		JButton button5 = new JButton("exit");
		
		button5.setBounds(215, 6, 89, 29);
		button5.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
			

			});
		frame.getContentPane().add(button5);
		JButton Save = new JButton("save");
		Save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = tp.getText();
				String filePath = null;
				System.out.println(text);
				if(filePath == null || filePath.equals("")){
					JFileChooser chooser = new JFileChooser();
					chooser.setDialogTitle("Save");
					if (-1 == JFileChooser.APPROVE_OPTION)
					{
						try {
							write(chooser.getSelectedFile().getAbsolutePath(), text);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						filePath = chooser.getSelectedFile().getAbsolutePath();
					}  
				} else {
					try {
						write(filePath, text);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		Save.setBounds(6, 186, 117, 29);
		frame.getContentPane().add(Save);
	
		frame.setVisible(true);
	}
	private void write(String absolutePath, String text) throws IOException {
		BufferedWriter bufferedWriter;
		
			bufferedWriter = new BufferedWriter(new FileWriter(absolutePath));
			bufferedWriter.write(text,0,text.length());
			bufferedWriter.newLine();
			bufferedWriter.close();
			
		
	}

	@Override
	public void update(List<Plugin> pls) {
		mnPlugins.removeAll();
		for (Plugin p : pls) {
			JMenuItem it = new JMenuItem(p.getLabel());
			it.addActionListener((event) -> {
				textArea.setText(p.transform(textArea.getText()));
			});
			mnPlugins.add(it);
		}
	}

}
