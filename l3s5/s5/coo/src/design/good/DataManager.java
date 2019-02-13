package design.good;

import java.util.List;

import design.WeatherData;
import design.Logger;
import design.bad.Operation;

/**
 * Provide a way to processa list aof weather data to compute a value.
 *
 */
public abstract class DataManager {

	private Logger logger;

	public DataManager() {
		this.logger = new Logger();
	}

	/** provide the initial value for the process
	 * @return the initial value for the process
	 */
	protected abstract float initProcess();
	/** consider a new data and aggregate it with current value
	 * @param current current value
	 * @param data the data to consider : must be aggregate with current
	 * @return the new value computed from current and data
	 */
	protected abstract float agregateData(float current, WeatherData data);
	
	/** apply a process on a list of WeatherData 
	 * @param datas the list of weather datas to process
	 * @return the result of the process
	 */
	public float processData(List<WeatherData> datas) {
		this.logger.register(datas.size() + " data received");
		float result = this.initProcess();
		long start = System.currentTimeMillis();
		for (WeatherData data : datas) {
			result = this.agregateData(result,data);
		}
		long end = System.currentTimeMillis();
		this.logger.register(" data processed in " + (end - start) + "ms");
		return result;
	}

}
