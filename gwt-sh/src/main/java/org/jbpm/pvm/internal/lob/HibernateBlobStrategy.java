package org.jbpm.pvm.internal.lob;

import java.sql.SQLException;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.engine.jdbc.LobCreator;
import org.jbpm.api.JbpmException;
import org.jbpm.pvm.internal.env.EnvironmentImpl;

public class HibernateBlobStrategy implements BlobStrategy {

	@Override
	public void set(byte[] bytes, Lob lob) {
		if (bytes == null) {
			return;
		}
		
		EnvironmentImpl environment = EnvironmentImpl.getCurrent();
		SessionFactory factory=environment.get(SessionFactory.class);

		LobCreator creator=Hibernate.getLobCreator(factory.getCurrentSession());
		lob.cachedBytes = bytes;
		lob.blob = creator.createBlob(bytes);
		lob.bytes=bytes;
	}

	@Override
	public byte[] get(Lob lob) {
		if (lob.cachedBytes != null) {
			return lob.cachedBytes;
		}

		java.sql.Blob sqlBlob = lob.blob;
		if (sqlBlob != null) {
			try {
				return sqlBlob.getBytes(1, (int) sqlBlob.length());
			} catch (SQLException e) {
				throw new JbpmException("couldn't extract bytes out of blob", e);
			}
		}
		return null;
	}

}
