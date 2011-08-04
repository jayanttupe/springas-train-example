package com.skynet.spms.persistence.entity.stockServiceBusiness.csdd.P;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 15-三月-2011 12:33:16
 */
public enum PeriodicCheckCode {
	// 冲刷
	ReferToTheTechnicalPublica
	// 不适用
	,NotApplicable
	// 参考部件技术手册
	,VisualCheckOrInspection
	// 翻转(如.轮胎、传动装置）
	,TurningOver
	// 更换干燥剂/保持液体
	,ChangeOfDesiccant
	// 更换电池、药筒或者附属填充物
	,ReplacementOfBatteries
	// 重量、压力或电压检测
	,WeightPressureOrVoltageCheck
	// 校准
	,Flushing
	// 清洁/润滑
	,Calibration
	// 目视检查
	,CleaningLubrication
}