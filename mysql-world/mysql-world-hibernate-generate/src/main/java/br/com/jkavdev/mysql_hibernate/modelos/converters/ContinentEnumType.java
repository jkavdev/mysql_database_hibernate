package br.com.jkavdev.mysql_hibernate.modelos.converters;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.EnumType;

import br.com.jkavdev.mysql_hibernate.modelos.Continent;

public class ContinentEnumType extends EnumType<Continent> {

	private static final long serialVersionUID = 1L;

	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session)
			throws HibernateException, SQLException {

		if (value == null) {
			st.setNull(index, Types.OTHER);
		} else {
			st.setObject(index, ((Continent) value).getName(), Types.CHAR);
		}
	}

}
