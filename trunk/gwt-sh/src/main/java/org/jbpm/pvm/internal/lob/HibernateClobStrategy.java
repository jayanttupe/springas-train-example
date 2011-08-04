package org.jbpm.pvm.internal.lob;

import java.sql.SQLException;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.engine.jdbc.LobCreator;
import org.jbpm.api.JbpmException;
import org.jbpm.pvm.internal.env.EnvironmentImpl;

public class HibernateClobStrategy implements ClobStrategy {
	
	public void set(char[] chars, Lob lob) {
		if (chars != null) {
			EnvironmentImpl environment = EnvironmentImpl.getCurrent();
			SessionFactory factory=environment.get(SessionFactory.class);
			
			LobCreator creator=Hibernate.getLobCreator(factory.getCurrentSession());

			lob.clob = creator.createClob(new String(chars));
		}
	}

	public char[] get(Lob lob) {
		java.sql.Clob sqlClob = lob.clob;
		if (sqlClob != null) {
			try {
				int length = (int) sqlClob.length();
				String text = sqlClob.getSubString(1, length);
				return text.toCharArray();
			} catch (SQLException e) {
				throw new JbpmException("couldn't extract chars out of clob", e);
			}
		}
		return null;
	}
}
