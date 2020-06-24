package tag;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

import static util.TagValues.*;

public class WeatherTag extends SimpleTagSupport {

	private static final Logger LOGGER = LogManager.getLogger(WeatherTag.class);

	Document page;
	Element mainDiv;
	Element temperature;
	Element description;
	Element wind;
	Element pressure;
	Element humidity;

	public Document getPage() {
		return page;
	}

	public void setPage(Document page) {
		this.page = page;
	}

	public Element getMainDiv() {
		return mainDiv;
	}

	public void setMainDiv(Element mainDiv) {
		this.mainDiv = mainDiv;
	}

	public Element getTemperature() {
		return temperature;
	}

	public void setTemperature(Element temperature) {
		this.temperature = temperature;
	}

	public Element getDescription() {
		return description;
	}

	public void setDescription(Element description) {
		this.description = description;
	}

	public Element getWind() {
		return wind;
	}

	public void setWind(Element wind) {
		this.wind = wind;
	}

	public Element getPressure() {
		return pressure;
	}

	public void setPressure(Element pressure) {
		this.pressure = pressure;
	}

	public Element getHumidity() {
		return humidity;
	}

	public void setHumidity(Element humidity) {
		this.humidity = humidity;
	}

	@Override
	public void doTag() throws IOException {

		try {
			page = Jsoup.connect(WEATHER_URL).get();
			mainDiv = page.select(MAIN_DIV_URI).first();
			temperature = mainDiv.selectFirst(TEMPERATURE_URI);
			description = mainDiv.selectFirst(DESCRIPTION_URI);
			wind = mainDiv.selectFirst(WIND_URI);
			pressure = mainDiv.selectFirst(PRESSURE_URI);
			humidity = mainDiv.selectFirst(HUMIDITY_URI);

			JspWriter writer = getJspContext().getOut();
			StringBuilder sb = new StringBuilder();
			sb.append(TEMPERATURE).append(temperature.text()).append(CELSIUS).append(DELIMITER)
					.append(DESCRIPTION).append(description.text()).append(DELIMITER)
					.append(WIND).append(wind.text()).append(DELIMITER)
					.append(PRESSURE).append(pressure.text()).append(DELIMITER)
					.append(HUMIDITY).append(humidity.text().substring(BEGIN_INDEX));

			writer.print(sb.toString());

		} catch (IOException e) {
			LOGGER.error("ERROR : no connection with weather resources");
		}
	}
}
