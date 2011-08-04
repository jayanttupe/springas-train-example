package com.skynet.spms.persistence.entity.csdd.i;

import javax.jws.Oneway;

/**
 * 缩写 INC 长度 1位 N
 * Specifies the interchange relationship between the replaced part (old part
 * number) and the replacing part (new part number).
 * 1 One‐Way Interchangeable.  The old part number may be used as a replacement
 * only  where the old  part number was installed.   The new part number is an
 * acceptable  replacement for either the old or  new part number.
 * 2 Two‐Way Interchangeable.  The old and new part numbers are eachacceptable
 * replacements for both the old and new part numbers.
 * 3 Not Interchangeable.  When the old part is removed, it must be replaced
 * withthe same  part number.  When the new part number is removed, it must be
 * replaced with the new  part number.
 * 4 Interchangeable As A Set.  Replacement requires that the entire quantity of
 * the old part  used in an installation be replaced with a complete set of the
 * new partnumber.  For sets  comprised of more than one part number, Code 5
 * applies.
 * 5 Qualified Interchangeability.  Applies to replacement situations not
 * described by the  above four codes or when not applicable to all aircraft or
 * engines specified by the Model of Applicability Code       (TEI‐ MOA).
 * 6 Part Reidentification (Provisioning V File Only).  Corrects the
 * identification of a replacing/replaced part in an established Replacing Part
 * Data Text (RPD) or Replaced Part  Data Text (RPE).
 * @author 曹宏炜
 * @version 1.0
 * @created 18-四月-2011 16:08:34
 */
public enum InterchangeabilityCode {
	OneWay ,TwoWay
}