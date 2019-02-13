package plugin;
 
import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.Constructor;

 
public class PluginFilter implements FilenameFilter
{
 
    public PluginFilter()
    {
    }
 
    public boolean accept(File dir, String name)
    {
        Class c;
        if (!name.endsWith(".class"))
            return false;
        name = name.substring(0, name.length() - ".class".length());
        c = null;
        try {
			c = Class.forName((new StringBuilder("plugins.")).append(name).toString());
		} catch (ClassNotFoundException e1) {
			
			e1.printStackTrace();
		}
        if(c==null){return false;}
        if (c.isInterface())
            return false;
        if (!c.getPackage().getName().equals("plugins"))
            return false;
     
		Constructor []cons = c.getConstructors();
	   for(Constructor con: cons){if(con.getParameterCount()==0){return true;}
	   else{return false;}
	   }
	
       if( Plugin.class.isAssignableFrom(c)){return true;}
           else{return false;}
           
      
    }
}
