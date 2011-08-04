package com.skynet.spms.persistence.entity.csdd.s;

/**
 * 缩写 SCS  长度 2位 AN
 * The qualification or authority held by the supplier to provide aircraft parts
 * 
 * 01 Production Approval Holder (PAH), as claimed by the supplier (not verified
 * byATA)
 * 02 Parts Manufacturing Approval (PMA), as claimed by the supplier (not verified
 * byATA)
 * 03 Technical Standards Order (TSO), as claimed by the supplier (not verified by
 * ATA)
 * 04 Registered under FAA AC 20‐AIR‐DU, as claimed by the supplier (not verified
 * byATA)
 * 05 Approved under an FAA bilateral agreement, as claimed by the supplier (not
 * verified by ATA)
 * @author 曹宏炜
 * @version 1.0
 * @created 18-四月-2011 15:51:38
 */
public enum SupplierClaimedStatusCode {
  PAH,PMA,TSO
}