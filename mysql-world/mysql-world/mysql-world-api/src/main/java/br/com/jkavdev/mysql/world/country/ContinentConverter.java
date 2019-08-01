package br.com.jkavdev.mysql.world.country;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ContinentConverter implements AttributeConverter<Continent, String> {

	@Override
	public String convertToDatabaseColumn(Continent continent) {
		return continent.getName();
	}

	@Override
	public Continent convertToEntityAttribute(String dbData) {
		return Continent.from(dbData);
	}

}
