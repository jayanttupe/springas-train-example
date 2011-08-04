package org.jbpm.pvm.internal.lob;

import java.sql.Types;

import org.hibernate.dialect.Oracle10gDialect;

public class FixOracleDialect extends Oracle10gDialect {

	public FixOracleDialect() {
		super();
		registerColumnType(Types.LONGVARCHAR, "clob");
		registerColumnType(Types.LONGNVARCHAR, "clob");
		registerColumnType(Types.VARCHAR, "clob" );
	}

}
