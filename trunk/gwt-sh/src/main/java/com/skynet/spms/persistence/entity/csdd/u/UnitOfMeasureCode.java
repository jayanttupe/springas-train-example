package com.skynet.spms.persistence.entity.csdd.u;

/**
 * 计量单位
 * Specifies the type of count, measurement, container or form of the subject part
 * or material.
 * @author 曹宏炜
 * @version 1.0
 * @created 10-三月-2011 11:11:12
 */
public enum UnitOfMeasureCode {
	/**
	 * Each ‐ One unit of an item of supply.
	 */ 
	EA //Each ‐‐ One unit of an item of supply. 每一项一个件
	//TE, // Ten ‐‐ 10 each of an item of supply.
	//AX,// Twenty ‐‐ 20 each of an item of supply.
	//FY, // Fifty ‐‐ Fifty (50) each of an item of supply.
	//HU, // Hundred ‐‐ 100 each of an item of supply.
	//AA,// Two Hundred, Fifty ‐‐ 250 each of an item of supply.
	//VC, // Five Hundred ‐‐ Five hundred (500) each of an item of supply.
	//TH, // Thousand ‐‐ 1000 each of an item of supply.
	//PR, // Pair ‐‐ Two like, corresponding items as gloves, shoes, bearings; or single items fabricated of two corresponding parts as shears, goggles, trousers.
	//BG,// Bag* ‐‐ A flexible container of various sizes and shapes fabricated from materials as paper, plastic or textiles.  Includes "sack" and "pouch".
	//BC,// Block* ‐‐ A piece of material such as wood, stone or metal usually with one or more planed faces.
	//BX,// Box* ‐‐ A rigid three dimensional container of varying size and material.  Includes "tray" and "crate".
	//BR,// Bar* ‐‐ A solid piece or block of material having its length greater than its other dimensions, e.g., solder.  Not applicable to items as soap, beeswax, buffing compound.
	//Bottle, //* ‐‐ A glass, plastic or earthenware container of varying size, shape and  finish with a closure for retention of contents. excludes jars, ampoules, vials and carboys.
	
	//ME, // Meter ‐‐ A metric unit of linear measurement equal to 39.37 inches.
	//F3, //Kilograms per Liter
	//AM,// Ampoule* ‐‐ A small glass or plastic tube sealed by fusion after filling.
	//AT,// Assortment ‐‐ A collection of various items falling into a common category or class and packaged as a small unit constituting a single item of supply.
	//AY,// Assembly ‐‐ A collection of parts assembled to form a complete unit constituting a single item of supply.
	//BA,// Bale* ‐‐ A shaped unit of compressed material bound with cord or metal ties and usually wrapped, e.g., paper and cloth rags.
	//BB,// Bobbin* ‐‐ A cylindrical shaped reel or spool containing thread, yarn, wire.
	//BF,// Board Foot ‐‐ A unit of measure for lumber equal to the volume of a board 12" X 12" X 1" (144 cubic inches, or 2.360 cubic decimeters).
	//Keg,//一个小桶状容器
	//BK,//卷
	//BO //螺栓
	
	
	
	/*
	BU,// Bushel ‐‐ A unit of dry measure equal to eight (8) gallons.
	
	CA,// Can* ‐‐ A rigid receptacle made of fiber, metal, plastic.  May be cylindrical or any numbwe of shapes. Includes "cannister".      
	Pail,// ‐‐ A cylindrical container with a handle, as a bucket.        
	Tin,//* ‐‐ A box‐like metal container with a flap or lid cover.
	CB,// Carboy* ‐‐ A large, heavy duty, bottle‐type container used for transportation and storage of liquids.  Usually encased in a rigid protective outer container for shipment.
	CC,// Cubic Centimeter ‐‐ A metric unit of cubic measure equal to 0.000001 cubic meter, 1.0 milliliter or 0.061 cubic inch.
	CE,// Degrees Celsius
	CF,// Cubic Foot ‐‐ A unit of cubic measure equal to 0.037 cubic yard or 0.028 cubic meters.
	CG,// Centigram ‐‐ A unit of metric weight equal to 0.01 gram or 0.000035 ounce.
	CI,// Cubic Inch ‐‐ A unit of cubic measure equal to 0.00058 cubic feet or 16.387 cubic centimeters.
	CK,// Cake* ‐‐ A block of compacted or congealed matter. Applicable to such items as soap, buffing compound.
	CL,// Cylinder* ‐‐ A rigid, cylindrical, metal container designed for transportation and storage of compressed gasses.
	CM,// Centimeters ‐‐ A unit of metric length equal to 0.01 meter or 0.39 inch.
	CN,// Carton* ‐‐ A container, usually of fibreboard or pasteboard, with fixed or collapsible joints and self‐locking or tuck‐in flaps.
	CO,// Container* ‐‐ A general term for a receptacle or covering for shipment of goods.
	CP,// Capsule* ‐‐ A compact metallic or plastic container for liquids or solids.
	CR,// Card* ‐‐ A flat piece of thick paper or pasteboard to which various items can be attached or displayed.
	CS,// Case* ‐‐ A container designed to hold one or more specific items in a fixed position by virtue of conforming dimensions or attachments.
	CT,// Hundred Feet ‐‐ A unit of linear measurement equal to 33.333 yards or 30.48 meters.
	CW,// Hundred Pounds ‐‐ A unit of avoirdupois weight equal to 45.359 kilograms.
	CY,// Cubic Yard ‐‐ Unit of cubic measure equal to 27 cubic feet or 0.765 cubic meter.
	DC,// Cubic Decimeter ‐‐ A metric unit of cubic measure equal to 1.0 liter or 61.02 cubic inches.
	DL,// Deciliter ‐‐ A unit of metric capacity equal to 0.1 liter, 100 cubic centimeters or 6.1 cubic inches.
	DM,// Decimeter ‐‐ A unit of metric length equal to 0.1 meter or 3.94 inches.
	DR,// Dram ‐‐ A unit of avoirdupois weight equal to 1/16 (.0625) ounce or 1.771 grams.
	DZ,// Dozen ‐‐ Twelve each of an item of supply.
	FA, // Degrees Fahrenheit
	FM, // Fathom ‐‐ A unit of length equal to 6.0 feet or 1.829 meters.
	FO, // Fluidounce (U.S.) ‐‐ A liquid unit of measure equal to 1/16 (.0625) pint (U.S.), 29.573 milliliters or 29.573 cubic centimeters.
	FT, // Foot/Foot Run ‐‐ A unit of linear measurement equal to 12 inches or (Linear Foot)  30.480 centimeters.
	
	FZ, // Fluidounce (Imperial) ‐‐ A liquid unit of measure equal to 1/20 (.05) pint (Imperial), 28.416 cubic centimeters or 28.416 milliliters.
	GC, // Gill (Imperial) ‐‐ A unit of liquid or dry measure equal to 5 fluidounces, 8.669 cubic inches or 142.066 cubic centimeters.
	GE, // Pounds per Gallon
	GI, // Gallon (Imperial) ‐‐ A unit of liquid or dry measure equal to 277.420 cubic inches, 4.545 liters or 1.201 gallons (U.S.).
	GL, // Gallon (U.S.) ‐‐ A unit of liquid or dry measure equal to 231 cubic inches, 3.785 liters or 0.833 gallon (Imperial).
	GM, // Gram ‐‐ A small unit of metric mass and weight equal to 0.000001 metric ton or 0.0353 ounce.
	GN, // Grain ‐‐ A small unit of weight equal to 1/480 (.002083) troy ounce, or 0.0648 gram.
	GP, // Group ‐‐ A collection of related items issued as a single unit of supply, e.g., test set group.
	GR, // Gross ‐‐ 144 each of a unit of supply.
	HC, // Hundred Cubic Meters ‐‐ A unit of metric volume equal to 131.0 cubic yards.
	HG, // Hectogram ‐‐ A unit of metric mass and weight equal to 100 grams or 3.527 ounces.
	HK, // Hundred Kilogram (Quintal) ‐‐ A unit of metric mass and weight equal to 0.1 metric ton or 220.46 pounds.
	HL, // Hundred Liters  (Hectoliter) ‐‐ A unit of metric capacity equal to 3.53 cubic feet.
	HM, // Hundred Meters  (Hectometer) ‐‐ A unit of metric length equal to 109.36 yards or 0.062 mile.
	HP, // Half Pint (U.S.) ‐‐ A unit of liquid measure equal to 14.4375 cubic inches or 0.2365 milliliter.
	HS, // Hundred Square Feet ‐‐ A unit of area measurement equal to 11.1 square yards or 9.3 square meters.
	
	HW // Hundred Weight ‐‐ A unit of avoirdupois weight equal to 100 pounds or 45.359 kilograms 
	HY, // Hundred Yards ‐‐ A unit of linear measurement equal to 91.4 meters.
	IN, // Inch  ‐‐ A unit of linear measurement equal to 2.540 centimeters.
	IU, // Unit* ‐‐ A standard or basic quantity into which an item of supply is dispensed or distributed.
	JR, // Jar* ‐‐ A rigid container having a wide mouth and often no neck, typically made of earthen ware or glass.
	
	KC, // Kilograms per Cubic Meter
	KG, // Kilogram ‐‐ A metric unit of weight equal to 1000 grams or 2.2046 pounds.
	KM, // Kilometer ‐‐ A metric unit of length equal to 1000 meters or 0.62 mile.
	KP, // Cop* ‐‐ A cylindrical or conical mass of thread, yarn, cable wound on a quill or tube.
	KT, // Kit ‐‐ A collection of related items issued as a single unit of supply, such as tools, instruments, repair parts, instruction sheets and often supplies typically carried in a box or bag. Also includes selected collections of equipment components, tools and/or materials for the repair, overhaul or modification of equipment.
	LB, // Pound ‐‐ A unit a avoirdupois weight equal to 16 ounces or 0.453 kilogram.
	LG, // Length* ‐‐ A unit of fixed or specific linear measurement.
	LI, // Liter ‐‐ A unit of metric capacity equal to 1.0 cubic decimeter, 61.02 cubic inches, 1.06 quarts (U.S.) or 0.88 quart (Imperial).
	LM, // Linear Meter ‐‐ A term used for measuring preformed piping, insulation.
	LT, // Lot* ‐‐ A number of units of an item of supply offered as a single item.
	MC, // Cubic Meter ‐‐ A metric unit of cubic measure equal to 1.0 kiloliter or 1.31 cubic yards.
	ME, // Meter ‐‐ A metric unit of linear measurement equal to 39.37 inches.
	MF, // Thousand Board Feet ‐‐ A unit of measure for lumber equal to the volume of 1000 board foot units (12" X 12" X 1").
	MG, // Milligram ‐‐ A metric unit of mass and weight equal to .001 gram.
	ML, // Milliliter ‐‐ A metric unit of capacity equal to .001 liter, 0.061 cubic inch or 1 cubic centimeter.
	MM, // Millimeter ‐‐ A metric unit of length equal to .001 meter or 0.04 inch.
	MN, // Square Millimeter ‐‐ A metric unit of area measurement equal to 0.000001 square meter or 0.0016 square inch.
	MT, // Thousand Feet ‐‐ A unit of linear measurement equal to 333.33 yards or 304.8 meters.
	MX, // Thousand Cubic Feet ‐‐ A unit of volume equal to 37.04 cubic yards or 765 cubic meters.
	OT, // Outfit ‐‐ A collection of related items issued as a single item of supply.
	OZ, // Ounce ‐‐ A unit a avoirdupois weight equal to 1/16 (.0625) pound or 28.349 grams.
	PB, // Pint (Imperial) ‐‐ A measure of liquid capacity equal to 1/8 (.125) gallon (Imperial), 34.678 cubic inches, 0.568 liter or 1.201 pints (U.S.).
	PC, // Piece* ‐‐ A portion or quantity of an item, often a specific length.
	PD, // Pad* ‐‐ Multiple sheets of paper that are stacked together and fastened at one end by sealing.
	//PE, // Peck ‐‐ A unit of dry measure equal to 2 gallons.
	//PK, // Bundle* ‐‐ A quantity of an item tied together without compression.
	//Pack, //* ‐‐ A parcel or quantity of an item supplied as a wrapped or tied unit.          
	//Package, //* ‐‐ A quantity of an item supplied in a protective wrapping.
	//PM, // Plate ‐‐ A flat piece of square or rectangular shaped metal of uniform thickness usually 1/4 inch or more.
	//PR, // Pair ‐‐ Two like, corresponding items as gloves, shoes, bearings; or single items fabricated of two corresponding parts as shears, goggles, trousers.
	//PT, // Pint (U.S.) ‐‐ A measure of liquid capacity equal to 1/8 (.125) gallon (U.S.), 28.875 cubic inches, 0.473 liter or 0.833 pint (Imperial).
	//PZ, // Packet* ‐‐ A container used for subsistence items.
//	QI, // Quart (Imperial) ‐‐ A unit of liquid capacity equal to 1/4 (.25) gallon (Imperial), 69.355 cubic inches, 1.136 liters or 1.201 quart (U.S.).
	//QK, // Quarter Kilogram ‐‐ A unit of metric weight equal to 250 grams.
	//QR, // Quire ‐‐ A measure of 24 sheets of paper.
	//QT, // Quart (U.S.) ‐‐ A unit of liquid capacity equal to 1/4 (.25) gallon (U.S.), 57.75 cubic inches, 0.946 liter or 0.833 quart (Imperial).
	//RA, // Ration* ‐‐ Amount of food or supplies allotted to an individual, usually for one day.
	//RL, // Roll* ‐‐ A cylindrical configuration of flexible material which has been rolled on itself as textiles, tape, paper, film and may utilize a core with or without flanges.         
	//Spool, //* ‐‐ A cylindrical form with a flange or rim at each end and an axial hole for a pin or spindle on which material as thread or wire is wound.               
	//Coil, //* ‐‐ An arrangement of material as wire, rope and tubing wound in a circular shape.                      
	//Cone, //* ‐‐ A cone‐shaped mass of material wound on itself as twine or thread wound on a conical core.
	//RM, // Ream ‐‐ A quantity of paper varying from 480 to 516 sheets, depending upon grade.
	//SC, // Square Centimeter ‐‐ A metric unit of area measurement equal to 0.0001 square meter or 0.155 square inch.
	//SD, // Square Decimeter ‐‐ A metric unit of area measurement equal to 0.01 square meter or 15.5 square inches.
	//SF, // Square Foot/Super Foot ‐‐ A unit of area measurement equal to 144 square inches or 0.093 square meter.
	//SH, // Sheet ‐‐ A flat piece of rectangular shaped material of uniform thickness that is very thin in relation to its length and width, such as paper, metal and plywood.
	//SI, // Square Inch ‐‐ A unit of area measurement equal to 0.00694 square feet or 6.4516 square centimeters.
	//SK, // Stick* ‐‐ Material in a relatively long, slendor and often cylindrical form for ease of application, e.g., abrasives.
	//SM, // Square Meter ‐‐ A metric unit of area measurement equal to 10.76 square feet.
	//SN, // Skein ‐‐ A loop of yarn, 120 yards in length, usually wound on a circular core.
	//SO, // Shot ‐‐ A unit of linear measure equal to 15 fathoms or 90 feet, usually applied to anchor chain.
	//SP, // Strip* ‐‐ A relatively narrow, flat length of material, uniform in width, such as paper, wood and metal.
	//ST, // Set ‐‐ A collection of matched or related items issued as a single item of supply,  i.e., tool set, matched set.
	//SY, // Square Yard ‐‐ A unit of area measurement equal to 1296 square inches, 9 square feet or 0.836 square meter.
	//
	//
	//TK, // Ton, Metric (Thousand Kilograms) ‐‐ A metric unit of weight equal to 2204.6 pounds.
	//TL, // Thousand Liter (Kiloliter) ‐‐ A metric unit of capacity equal to 1 cubic meter or 1.31 cubic yards.
	//TN, // Ton, Short ‐‐ A unit of avoirdupois weight equal to 2000 pounds or 0.907 metric ton.
	//TO, // Troy Ounce ‐‐ A unit of troy weight, usually applied to precious metals, equal to 1/12 (.0833) troy pound or 0.373 kilogram.
	//TT, // Tablet* ‐‐ A compressed or molded block of solid material; a collection of sheet paper glued together at one edge.
	//TU, // Tube* ‐‐ A squeeze‐type container used in packaging commodities as adhesives,toothpaste, pharmaceuticals.
	//TX, // Ton, Long ‐‐ A unit of avoirdupois weight equal to 2240 pounds or 1.016 metric tons.
	//VI, // Vial* ‐‐  A small glass container, tubular in shape (generally less than one inch in diameter) having a flat bottom and variety of neck shapes.
	//YD, // Yard ‐‐ A unit of length equal to 3 feet or 0.914 meter.
	//ZV // Syphon* ‐‐ An aerated container from which liquid is forced by gas pressure.
*/}

	