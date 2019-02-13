package design.good;

import design.WeatherData;
import design.bad.Operation;

/**
 * Compute the maximal temperature from a list of data
 */
public class MaxTempDataManager extends DataManager {

	public MaxTempDataManager() {
		super();
	}

	/**
	 * @see design.good.DataManager#initProcess()
	 */
	protected float initProcess() {
		return - 1000;
	}

	/**
	 * @see design.good.DataManager#agregateData(float, design.WeatherData)
	 */
	protected float agregateData(float result, WeatherData data) {
		return Math.max(result, data.getTemperature());
	}

}
