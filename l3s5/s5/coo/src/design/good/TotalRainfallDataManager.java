package design.good;

import design.WeatherData;
import design.bad.Operation;

/**
 * Sum the rainfall from a list of data
 */
public class TotalRainfallDataManager extends DataManager {

	public TotalRainfallDataManager() {
		super();
	}

	/**
	 * @see design.good.DataManager#initProcess()
	 */
	protected float initProcess() {
		return 0;
	}

	/**
	 * @see design.good.DataManager#agregateData(float, design.WeatherData)
	 */
	protected float agregateData(float value, WeatherData data) {
		return value + data.getRainfall();
	}

}
