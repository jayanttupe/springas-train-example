package com.skynet.spms.persistence.entity.csdd.p;

/**
 * 缩写 PRP 长度 1位 AN
 * Specifies the primary procurement source for the subject part number and the
 * relationship of proprietary rights to the originator of the data.
 * In the provisioning fixed length record system, the originator of data in an
 * "S" file is the airframe or engine manufacturer specified by the Transmitter of
 * Data Code in the Header Record; the originator in a "T" file is the End Item
 * Manufacturer noted in the control segment.
 * In the provisioning variable length "V" file and procurement data systems, the
 * originator of data is the supplier referenced by the Supplier Code in the
 * subject Procurement Data Segment and Procurement Data Record.
 * 
 * 1 Part Number assigned by the manufacturer ‐‐‐ Proprietary ‐ Optional Make or
 * Buy Part.‐‐‐ Originator of the data and the part number Manufacturer Code are
 * the same; fabrication of the part by the customer is permitted by the
 * originator of data (manufacturer) who retains proprietary rights.
 * 
 * 2 Part Number assigned by the manufacturer ‐‐‐ Nonproprietary ‐ Supplier
 * Numbered Part. Originator of the data and the part number Manufacturer Code are
 * different; the supplier part is specified for the application and procurement.
 * The originator of the data has no proprietary rights.
 * 
 * 3 Part Number assigned by the manufacturer ‐‐‐ Proprietary ‐ Manufacturer
 * Numbered Part. ‐‐‐ Originator of the data and the part number Manufacturer Code
 * are the same; fabrication of the part by the customer is not permitted;
 * originator of the data (manufacturer) retains proprietary rights.
 * 
 * 4 Part Number assigned by the manufacturer ‐‐‐ Proprietary ‐ Supplier Numbered
 * Part.  ‐‐‐ Originator of the data and the part number Manufacturer Code are
 * different; the supplier designed the item explicity for requirements of the
 * originator of the data who retains proprietary rights and control of the design.
 * 
 * 
 * 5 Part Number assigned by the manufacturer ‐‐‐ Nonproprietary ‐
 * Distributorship/Exclusive Sales Agreement Part. ‐‐‐ Originator of the data and
 * part number Manufacturer Code are different; originator of the data is a
 * distributor and authorized source of supply for the item for which the
 * manufacturer retains proprietary rights.
 * 
 * 7 Part Number assigned by the manufacturer ‐‐‐ Proprietary ‐ Part To Be
 * Purchased From Distributor. ‐‐‐  Originator of the data and part number
 * Manufacturer Code are the same; originator of the data (manufacturer) retains
 * proprietary rights but is not the selling source of supply; parts are sold
 * through a distributor.
 * 
 * J Part Number assigned by the originator of the data ‐‐‐ Proprietary ‐ Optional
 * Make or Buy Part.  ‐‐‐ Originator of the data and the part number Manufacturer
 * Code are the same; fabrication of the part by the customer is permitted by the
 * originator of data (manufacturer) who retains proprietary rights.
 * 
 * K Part Number assigned by the originator of the data ‐‐‐ Nonproprietary ‐
 * Supplier Numbered Part.  ‐‐‐ Originator of the data and the part number
 * Manufacturer Code are different; the supplier part is specified for the
 * application and procurement.  The originator of the data has no proprietary
 * rights.
 * 
 * L Part Number assigned by the originator of the data ‐‐‐ Proprietary ‐
 * Manufacturer Numbered Part.  ‐‐‐ Originator of the data and the part number
 * Manufacturer Code are the same; fabrication of the part by the customer is not
 * permitted; originator of the data (manufacturer) retains proprietary rights.
 * 
 * M Part Number assigned by the originator of the data ‐‐‐ Proprietary ‐ Supplier
 * Numbered Part.  ‐‐‐ Originator of the data and the part number Manufacturer
 * Code are different; the supplier designed the item explicity for requirements
 * of the originator of the data who retains proprietary rights and control of the
 * design.
 * 
 * N Part Number assigned by the originator of the data ‐‐‐ Nonproprietary ‐
 * Distributorship/Exclusive Sales Agreement Part. ‐‐‐ Originator of the data and
 * part number Manufacturer Code are different; originator of the data is a
 * distributor and authorized source of supply for the item for which the
 * manufacturer retains proprietary rights.
 * 
 * P Part Number assigned by the originator of the data ‐‐‐ Proprietary ‐ Part To
 * Be Purchased From Distributor. ‐‐‐  Originator of the data and part number
 * Manufacturer Code are the same; originator of the data (manufacturer) retains
 * proprietary rights but is not the selling source of supply; parts are sold
 * through a distributor
 * @author 曹宏炜
 * @version 1.0
 * @created 18-四月-2011 15:46:07
 */
public enum ProprietaryCode {
   A,B,C
}