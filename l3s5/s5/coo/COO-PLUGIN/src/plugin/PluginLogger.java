package plugin;

import java.util.List;


public class PluginLogger implements PluginListener{

	@Override
	public void update(List<Plugin> plugins) {
		System.out.println("faire un update");
	}

	
}
