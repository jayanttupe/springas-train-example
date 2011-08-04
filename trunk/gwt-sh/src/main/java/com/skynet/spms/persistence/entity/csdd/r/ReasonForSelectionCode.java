package com.skynet.spms.persistence.entity.csdd.r;

/**
 * 缩写 RFS 选择备件为原因理由代码
 * Specifies the primary reason for selecting the subject part as a potential
 * spare part.、
 * 0 Not a potential spare (reference item). ‐‐‐ Part will not normally require
 * replacement
 * during the life of the using unit but is included in the provisioning data for
 * continuity and
 * completeness.
 * 1 Wear. ‐‐‐ a) Items which contain moving parts or are themselves moving during
 * their
 * designoperational function (e.g., valve assemblies, actuators, motors, bearings,
 * etc.). b)
 * Nonmoving parts subject to bumping or rubbing by an adjacent part or foreign
 * objectduring normal usage (e.g., carpet, seats, door seals, retainers, turbine
 * buckets,
 * turbine blades, etc.). c) Parts required for replacement due to secondary
 * damage (i.e.,
 * failure of adjacent parts).
 * 2 Maintenance Damage. ‐‐‐ a) Parts accidentally damaged during normal
 * maintenance or
 * overhaul of the using unit or adjacent unit (e.g., nuts, bolts, shims, etc.).
 * b) Parts subject
 * to replacement or expended during overhaul or repair of using unit (e.g.,
 * gaskets, O‐
 * rings, packings, nuts, bolts, cotter pin, etc.). c) Parts subject to damage
 * during normal
 * servicing operational functions (e.g., refueling, passenger and baggage loading,
 * etc.).
 * 3 Loss. ‐‐‐ Parts normally lost during maintenance or overhaul of the using
 * item (e.g., small
 * springs, pins, screws, nuts, etc.).
 * 4 Vibration. ‐‐‐ Parts subject to damage due to vibration
 * 5 Corrosion. ‐‐‐ Parts which, if not maintained by cleaning and/or adequate
 * protective
 * coating, will require replacement because of oxidation or chemical action of a
 * foreign
 * substance.
 * 6 Deterioration. ‐‐‐ Parts which degenerate or have their efficiency impaired
 * as a result of
 * normal functioning other than wear (e.g., parts with cure date, instruments,
 * electrical
 * equipment, etc.).
 * 7 Extreme Temperature. ‐‐‐ Parts installed in areas subject to extreme
 * temperature and
 * those which themselves generate abnormal temperatures.
 * 8 Other.  ‐‐‐ Explained in Explanation Code 54 text.
 * 9 Not an Initial Provisioning Part.  ‐‐‐ Part will not normally require
 * replacement during the
 * first year of the using unit and will therefore not be recommended, but is
 * included in the
 * provisioning data for continuity and completeness.
 * @author 曹宏炜
 * @version 1.0
 * @created 18-四月-2011 16:11:50
 */
public enum ReasonForSelectionCode {

}