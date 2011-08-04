package com.skynet.spms.persistence.entity.csdd.t;

/**
 * This indicator, when applied to a specified quantity of a particular part in a
 * request for quote,  provides the ability to request from a supplier all
 * applicable documentation certifying the origin and condition of a particular
 * part. As response, the Traceability
 * Data Indicator can be used to indicate that part documentation is not available
 * or is not applicable to the particular part.
 * @author 曹宏炜
 * @version 1.0
 * @created 10-三月-2011 11:11:11
 */
public enum TraceabilityDataIndicator {
	NAP,
	NAV,
	YES
}