package com.skynet.spms.persistence.entity.csdd.c;

/**
 * Specifies the type of part certification or conformance document governed by
 * the applicable regulatory agency.
 * 
 * CASA Form 917
 * Certificate of Conformance
 * EASA Form 1
 * FAA Form 8130‐3
 * JAA Form One
 * TCCA 24‐0078
 * Transfer Document
 * @author Administrator
 * @version 1.0
 * @created 10-三月-2011 11:10:38
 */
public enum CertificateType {
	CASAForm917,
	JAAFormOne,
	CAAC_F038,
	TCCA_24_0078,
	EASAForm1,
	certificateOfConformance,
	FAAForm8130_3,
	TransferDocument
}