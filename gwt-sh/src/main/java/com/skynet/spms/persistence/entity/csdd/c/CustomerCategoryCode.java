package com.skynet.spms.persistence.entity.csdd.c;

/**
 * Customer Category Code
 * 缩写 CCC 长度 3 位 AN
 * A supplier‐assigned designator to a particular customer, for use in the
 * variable pricing functionality.
 * 
 * This information is applied against the viewing rights file and is submitted to
 * the Discount Matrix Table as part of a Product Category Code (PCC)/Customer
 * Category Code (CCC) combination. It is not included on output to the customer.
 * The maximum number of occurrences in the Discount Matrix Table is 70.Only one
 * CCC can be assigned to each customer by a supplier.
 * @author 曹宏炜
 * @version 1.0
 * @created 18-四月-2011 16:04:59
 */
public enum CustomerCategoryCode {



   A,B,C

}