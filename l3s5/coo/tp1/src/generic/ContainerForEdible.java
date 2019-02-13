package generic;

import generic.content.Content;
import generic.content.Edible;

/**
 *  Containers for edible content.
 * @param <C> the edible content's type
 */
public class ContainerForEdible<C extends Content & Edible> extends Container<C> {

	public ContainerForEdible(int maxVolume) {
		super(maxVolume);
	}

}
