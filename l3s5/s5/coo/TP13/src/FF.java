import java.io.File;
import java.io.FilenameFilter;

public class FF implements FilenameFilter {

	   String str;
	   
	   // constructor takes string argument
	   public FF(String ext)
	   {
	      str="."+ext;
	   }

	@Override
	 public boolean accept(File dir, String name) {
	      return name.endsWith(str);
	   }
}
