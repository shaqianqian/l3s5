package plugin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimerTask;

import javax.swing.Timer;



public class PluginFinder implements ActionListener{
    private  File dir;
	private  List<PluginListener> pluginlistners = new ArrayList<PluginListener>();
	private Timer timer;
	private Set<File> knownFiles = new HashSet<File>();
	private PluginFilter filter;
    private ClassLoader classloader;
	private boolean hasChanged;
	public PluginFinder(File directory) {
		super();
		this.dir = dir;
		this.timer = new Timer(1000,this);
	}
	public void start() {
		timer.start();
	}
  /**
     * @param observer    观察者对象
     */
	public Set<File> listFiles() {
		return new HashSet<File>(Arrays.asList(dir.listFiles(filter)));
	}
	public List<Plugin> toListPlugin(Set<File> files) {
		List<Plugin> plugins = new ArrayList<>();
		for(File f : files) {
			Class c=null;
			Plugin plugin = null;
			String classname = f.getName().substring(0, f.getName().length() - ".class".length());
           try {
			plugin= (Plugin) Class.forName((new StringBuilder("plugins.")).append(classname).toString()).newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   plugins.add(plugin);
		    
	}return plugins;}
    public void attach(PluginListener pluginlistener){
        
    	pluginlistners.add(pluginlistener);
        System.out.println("Attached an observer");
    }
    /**
     * 删除观察者对象
     * @param observer    观察者对象
     */
    public void detach(PluginListener pluginlistener){
        
    	pluginlistners.remove(pluginlistener);
    	System.out.println("removed an observer");
    }
    /**
     * 通知所有注册的观察者对象
     */
    public void nodifyObservers(){
        
        for(PluginListener pluginlistener : pluginlistners){
        	List<Plugin> allPlugins = toListPlugin(knownFiles); 
        	pluginlistener.update(allPlugins);

    		}
    		hasChanged = false;
        	
        }

   protected void setFiles(Set<File> newFiles){
		if( !knownFiles.equals(newFiles)) {
			knownFiles =  newFiles;
			hasChanged = true;
		}
	}



	public void actionPerformed() {
		List<Plugin> allPlugins = toListPlugin(knownFiles); 
		for(PluginListener pl: pluginlistners){
			pl.update(allPlugins);
		}
		hasChanged = false;
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}







	
}
