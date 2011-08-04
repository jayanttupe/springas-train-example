package com.skynet.spms.persistence.entity.csdd.m;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.skynet.spms.persistence.entity.base.baseCodeEntity.BaseCodeEntity;

/**
 * MFR 制造商代码
 * Identifies the manufacturer, government agency or other organization
 * controlling the design, production and part number assignment of the subject
 * part.
 * 
 * 5位字符
 * @author 曹宏炜
 * @version 1.0
 * @created 18-三月-2011 13:34:49
 */
@Entity
@DiscriminatorValue("Manufactourer")
public class ManufacturerCode extends BaseCodeEntity {
  
}