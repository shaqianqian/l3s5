package design;

public class WeatherData {

	private float rainfall;
	private float temperature;

	public WeatherData(float rainfall, float temp) {
		this.rainfall = rainfall;
		this.temperature = temp;
	}

	/**
	 * @return the precipitation
	 */
	public float getRainfall() {
		return this.rainfall;
	}

	/**
	 * @return the temperature
	 */
	public float getTemperature() {
		return this.temperature;
	}
}
