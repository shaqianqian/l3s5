package plugin;

import java.io.File;
import java.util.Set;



public class mainclass {
	 public static void main(String[] args) {
		 File dir = new File("plugins");
		 PluginFinder finder = new PluginFinder(dir);  
		Window frame = new Window();
		 finder.attach(frame);
		finder.attach(new PluginLogger());   
	        
	        
	    }
}
