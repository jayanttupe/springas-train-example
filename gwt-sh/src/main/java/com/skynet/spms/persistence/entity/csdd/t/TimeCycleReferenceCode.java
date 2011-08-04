package com.skynet.spms.persistence.entity.csdd.t;

/**
 * 缩写 TRF
 * Designates the operating time and cycles accumulated since a part was installed,
 * or since it was installed as new, last overhauled, last repaired, last checked,
 * or since last shop visit.
 * 
 * C Time/Cycles accumulated since last check。 自上次定检。
 * I Time/Cycles accumulated since last installation of the part 自上次安装。
 * N Time/Cycles accumulated since installation as a new part
 * O Time/Cycles accumulated since last overhaul
 * R Time/Cycles accumulated since last repair
 * V Time/Cycles accumulated since last in repair shop visit
 * X Time/Cycles accumulated since last inspection 自上次检验。
 * @author 曹宏炜
 * @version 1.0
 * @created 18-四月-2011 16:13:26
 */
public enum TimeCycleReferenceCode {
	C,I,N,O,R,V,X
}