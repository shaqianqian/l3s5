package generic;

import generic.content.Content;

/**
 * A container that can filled by some specific content.
 * @param <C> the type of content accepted by this container 
 */
public class Container<C extends Content> {

	private int maxVolume;
	private int currentVolume;

	/** Builds an initially empty container of given capacity
	 * @param maxVolume the capacity of this container
	 */
	public Container(int maxVolume) {
		this.maxVolume = maxVolume;
		this.currentVolume = 0;
	}

	/** add content to this container if it has enough free space
	 * @param content the content to add
	 * @throws NotEnoughFreeSpaceException if content's volume is grater than container's free space to
	 */
	public void add(C content) throws NotEnoughFreeSpaceException {
		if (this.maxVolume >= this.currentVolume + content.volume()) {
			this.currentVolume = this.currentVolume + content.volume();
		}
		else throw new NotEnoughFreeSpaceException();
	}

	public int getCurrentVolume() {
		return this.currentVolume;
	}

	/** If possible empty 'other' container into this container by adding its content.
	 * @param other the other container that is emptied in this container
	 * @throws NotEnoughFreeSpaceException if this container has not enough free space
	 */
	public void fillFrom(Container<? extends C> other) throws NotEnoughFreeSpaceException {
		if (this.maxVolume >= this.currentVolume + other.getCurrentVolume()) {
			this.currentVolume = this.currentVolume + other.getCurrentVolume();
		}
		else throw new NotEnoughFreeSpaceException();
	}
	
	/**
	 * empty this container
	 */
	public void empty() {
		this.currentVolume = 0;
	}
}
