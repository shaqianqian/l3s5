package design.good;

import design.WeatherData;
import design.bad.Operation;

/**
 * Compute the minimal temperature from a list of data
 */
public class MinTempDataManager extends DataManager {

	public MinTempDataManager() {
		super();
	}

	/**
	 * @see design.good.DataManager#initProcess()
	 */
	protected float initProcess() {
		return + 1000;
	}

	/**
	 * @see design.good.DataManager#agregateData(float, design.WeatherData)
	 */
	protected float agregateData(float value, WeatherData data) {
		return Math.min(value, data.getTemperature());
	}

}
