package br.com.jkavdev.mysql_hibernate.modelos.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.jkavdev.mysql_hibernate.modelos.h2.Continent;

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
