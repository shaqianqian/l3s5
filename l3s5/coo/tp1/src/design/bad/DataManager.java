package design.bad;

import java.util.List;

import design.WeatherData;
import design.Logger;

public class DataManager {

	private Logger logger;
	private Operation operation;

	public DataManager(Operation op) {
		this.operation = op;
		this.logger = new Logger();
	}

	public float processData(List<WeatherData> datas) {
		this.logger.register(datas.size() + " data received");
		float result;
		switch (this.operation) {
		case totalRainfall:
			result = 0;
			break;
		case maxTemperature:
			result = -1000;
			break;
		default: //  == minTemperature
			result = +1000;
			break;
		}
		long start = System.currentTimeMillis();
		for (WeatherData data : datas) {

			switch (this.operation) {
			case totalRainfall:
				result = result + data.getRainfall();
				break;
			case maxTemperature:
				result = Math.max(result, data.getTemperature());
				break;
			case minTemperature:
				result = Math.min(result, data.getTemperature());
				break;
			}
		}
		long end = System.currentTimeMillis();
		this.logger.register(" data processed in " + (end - start) + "ms");
		return result;
	}
}
