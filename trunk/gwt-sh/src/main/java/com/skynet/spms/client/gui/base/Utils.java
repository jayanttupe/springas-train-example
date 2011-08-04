package com.skynet.spms.client.gui.base;

import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Timer;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.customerService.common.DSKey;
import com.skynet.spms.client.vo.PartInfoVO;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.DSDataFormat;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.skynet.spms.client.tools.PartTools;

public class Utils {
	public static String asterisk = "<font color='red'>*</font>";
	public static int index = 0;

	/**
	 * 构建件号
	 * 
	 * @return
	 */
	public static SelectItem getPartNumberList() {
		CustomSelectItem partNumberItem = new CustomSelectItem("partNumber", // selectitem
				// 的name
				"partCatalog.technical", // 备件模块名
				"partIndex_dataSource", // 备件模块数据源
				"partNumber", // selectItem 绑定的value
				"partNumber", // selectItem 显示的value
				"partNumber", // 下拉grid显示 商飞件号
				"manufacturerPartNumber",// 下拉grid显示 原厂商件号
				"m_BasicInformation.keyword_zh");// 显示关键字

		return partNumberItem;
	}

	/***************************************************************************
	 * 构建件号 通过name
	 * 
	 * @param name
	 * @return
	 */
	public static SelectItem getPartNumberListByName(String name) {
		CustomSelectItem partNumberItem = new CustomSelectItem(name, // selectitem
				// 的name
				"partCatalog.technical", // 备件模块名
				"partIndex_dataSource", // 备件模块数据源
				"partNumber", // selectItem 绑定的value
				"partNumber", // selectItem 显示的value
				"partNumber", // 下拉grid显示 商飞件号
				"manufacturerPartNumber",// 下拉grid显示 原厂商件号
				"m_BasicInformation.keyword_zh");// 显示关键字
		return partNumberItem;
	}

	/**
	 * 假数据
	 * 
	 * @return
	 */
	public static DataSource getXmlDataSource() {

		return getXmlDataSourceByFileName("_test.data.xml");
	}

	/**
	 * 根据xml文件名 绑定数据源 文件放于目录： ds/test_data/ 下
	 * 
	 * @param name
	 *            默认文件名为：_test.data.xml
	 * @return
	 */
	public static DataSource getXmlDataSourceByFileName(String name) {
		DataSource dataSource = new DataSource();
		dataSource.setDataFormat(DSDataFormat.XML);
		dataSource.setRecordXPath("/List/record");
		if (name == null || name.equals("")) {
			name = "_test.data.xml";
		}
		dataSource.setDataURL("ds/test_data/" + name);
		DataSourceTextField ds1 = new DataSourceTextField("field1");
		DataSourceTextField ds2 = new DataSourceTextField("field2");
		DataSourceTextField ds3 = new DataSourceTextField("field3");
		DataSourceTextField ds4 = new DataSourceTextField("field4");
		DataSourceTextField ds5 = new DataSourceTextField("field5");
		DataSourceTextField ds6 = new DataSourceTextField("field6");
		DataSourceTextField ds7 = new DataSourceTextField("field7");
		DataSourceTextField ds8 = new DataSourceTextField("field8");
		DataSourceTextField ds9 = new DataSourceTextField("field9");
		DataSourceTextField ds10 = new DataSourceTextField("field10");
		DataSourceTextField ds11 = new DataSourceTextField("field11");
		DataSourceTextField ds12 = new DataSourceTextField("field12");
		DataSourceTextField ds13 = new DataSourceTextField("field13");
		DataSourceTextField ds14 = new DataSourceTextField("field14");
		DataSourceTextField ds15 = new DataSourceTextField("field15");
		DataSourceTextField ds16 = new DataSourceTextField("field16");

		dataSource.setFields(ds1, ds2, ds3, ds4, ds5, ds6, ds7, ds8, ds9, ds10,
				ds11, ds12, ds13, ds14, ds15, ds16);

		return dataSource;
	}

	/**
	 * 清空FormItem的值
	 */
	public static void cleanFormItemValue(DynamicForm df) {
		FormItem[] fis = df.getFields();
		for (FormItem formItem : fis) {
			formItem.setValue("");
		}
	}

	/**
	 * 格式化价格（“币种 价格”）
	 * 
	 * @param field
	 *            列
	 * @param recordName
	 *            绑定的name
	 */
	public static void formatPriceField(ListGridField field) {
		field.setCellFormatter(new CellFormatter() {
			public String format(Object value,
					com.smartgwt.client.widgets.grid.ListGridRecord record,
					int rowNum, int colNum) {
				String internationalCode = record
						.getAttribute("m_InternationalCurrencyCode");
				if (internationalCode == null) {
					internationalCode = "";
				}

				NumberFormat nf = NumberFormat.getFormat("0.00");
				try {
					// 显示 币种 价格
					return internationalCode + " "
							+ nf.format(((Number) value).floatValue());
				} catch (Exception e) {
					return internationalCode + " " + value;
				}

			}
		});
	}

	/**
	 * 格式化金额/单价列
	 * 
	 * @param field
	 *            需要格式化的金额列或单价列
	 * @param currencyKey
	 *            币种列的列名，即列的name
	 */
	public static void formatPriceField(ListGridField field,
			final String currencyKey) {
		field.setCellFormatter(new CellFormatter() {
			public String format(Object value,
					com.smartgwt.client.widgets.grid.ListGridRecord record,
					int rowNum, int colNum) {
				String internationalCode = record.getAttribute(currencyKey);
				if (internationalCode == null) {
					internationalCode = "";
				}

				NumberFormat nf = NumberFormat.getFormat("0.00");
				try {
					// 显示 币种 价格
					return internationalCode + " "
							+ nf.format(((Number) value).floatValue());
				} catch (Exception e) {
					return internationalCode + " " + value;
				}

			}
		});
	}

	/**
	 * 格式化数量（“ 数量 单位”）
	 * 
	 * @param field
	 */
	public static void formatQuantityField(ListGridField field) {
		field.setCellFormatter(new CellFormatter() {
			public String format(Object value,
					com.smartgwt.client.widgets.grid.ListGridRecord record,
					int rowNum, int colNum) {
				String unitOfMeasureCode = record
						.getAttribute("m_UnitOfMeasureCode");
				if (unitOfMeasureCode == null) {
					unitOfMeasureCode = "";
				}
				try {
					// 显示 数量 单位
					return value + " " + unitOfMeasureCode;
				} catch (Exception e) {
					return value + " " + unitOfMeasureCode;
				}

			}
		});
	}

	/**
	 * 格式化数字列
	 * 
	 * @param field
	 *            需要格式化的数字列
	 * @param unitKey
	 *            单位列的列名，即列的name
	 */
	public static void formatQuantityField(ListGridField field,
			final String unitKey) {
		field.setCellFormatter(new CellFormatter() {
			public String format(Object value,
					com.smartgwt.client.widgets.grid.ListGridRecord record,
					int rowNum, int colNum) {
				String unitOfMeasureCode = record.getAttribute(unitKey);
				if (unitOfMeasureCode == null) {
					unitOfMeasureCode = "";
				}
				try {
					// 显示 数量 单位
					return value + " " + unitOfMeasureCode;
				} catch (Exception e) {
					return value + " " + unitOfMeasureCode;
				}

			}
		});
	}

	/***************************************************************************
	 * 让Grid所有行不选中
	 */
	public static void makeAllNotSelectLG(ListGrid lg) {
		lg.selectRecords(lg.getSelection(), false);
	}

	/***************************************************************************
	 * 使Grid所有列可筛选
	 * 
	 * @param lg
	 */
	public static void makeAllCanFilter(ListGrid lg) {
		for (ListGridField listGridField : lg.getFields()) {
			listGridField.setCanFilter(true);
		}
	}

	// public static void deleteSheet(final ListGrid listGrid) {
	// if (ValidateUtil.isRecordSelected(listGrid)) {
	// SC.ask("警告", "你确定要删除所选择的行吗？", new BooleanCallback() {
	// public void execute(Boolean value) {
	// if (value) {
	// ListGridRecord[] records = listGrid.getSelection();
	// for (ListGridRecord listGridRecord : records) {
	// listGrid.removeData(listGridRecord);
	// }
	// }
	// }
	// });
	// }
	// }

	/***************************************************************************
	 * 设置message列宽度
	 * 
	 * @param field
	 */
	public static void setMessageFieldWidth(ListGridField field) {
		field.setWidth(35);
		field.setTitle("留言");
	}

	/**
	 * 设置Grid高度
	 * 
	 * @param gird
	 */
	public static void setListGridHeight(ListGrid gird) {
		gird.setHeight(180);
	}

	/***
	 * 设置项号宽度
	 * 
	 * @param field
	 */
	public static void setItemNumberFieldWidth(ListGridField field) {
		field.setWidth(15);
	}

	/**
	 * 设置数量宽度
	 * 
	 * @param field
	 */
	public static void setItemCountFieldWidth(ListGridField field) {
		field.setWidth(20);
	}

	/***
	 * 设置飞机机尾号
	 * 
	 * @param item
	 */
	public static void setAirIdentificationNumberItemDS(final SelectItem item) {
		final DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(DSKey.C_SALESREQUISITIONSHEET,
				DSKey.AIRCRAFTREGISTRATION_SD, new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						item.setOptionDataSource(dataSource);
						item.setDisplayField("aircraftTailNumber");
						item.setValueField("aircraftTailNumber");
					}
				});
	}

	private SelectItem getCHPorductControl() {

		// 构建产品grid
		ListGrid a = new ListGrid();

		DataSource dataSource = new DataSource();
		dataSource.setDataFormat(DSDataFormat.XML);
		dataSource.setRecordXPath("/List/record");
		dataSource.setDataURL("ds/test_data/product.data.xml");
		ListGridField partNumber = new ListGridField("partNumber", "备件描述");
		partNumber.setCanFilter(true);
		ListGridField linkman = new ListGridField("linkman", "备件编号");
		linkman.setCanFilter(true);

		DataSourceTextField dspartNumber = new DataSourceTextField(
				"partNumber", "partNumber", 128, true);
		DataSourceTextField dslinkman = new DataSourceTextField("linkman",
				"linkman", 10, true);
		dataSource.setFields(dspartNumber, dslinkman);

		a.setFields(partNumber, linkman);
		a.setAutoFetchData(true);
		a.setShowFilterEditor(true);
		a.setCellHeight(50);
		a.setDataSource(dataSource);
		a.draw();

		SelectItem item = new SelectItem("partNumber", "1");
		item.setOptionDataSource(dataSource);
		item.setValueField("linkman");
		item.setDisplayField("partNumber");
		item.setPickListWidth(450);
		item.setPickListProperties(a);

		item.setPickListFields(a.getField(0), a.getField(1));
		item.setFilterLocally(false);
		return item;
	}

	/***
	 * 根据件号Item获得件号信息
	 * 
	 * @param partItem
	 * @return
	 */
	public static PartInfoVO getPartInfoVO(FormItem partItem) {
		PartInfoVO partVo = new PartInfoVO();

		// if (index == 0) {
		ListGridRecord selectLGR = partItem.getSelectedRecord();
		String manufactureCodeId = selectLGR
				.getAttribute("m_ManufacturerCode.id");
		String manufactureCode = selectLGR
				.getAttribute("m_ManufacturerCode.code");
		partVo.setManufacturerCodeId(manufactureCodeId);
		partVo.setManufacturerCode(manufactureCode);
		partVo.setKeyword(selectLGR
				.getAttribute("m_BasicInformation.keyword_zh"));
		partVo.setAtaNumber(selectLGR
				.getAttribute("m_BasicInformation.ataNumber"));
		partVo.setUnitOfMeasureCode(selectLGR
				.getAttribute("m_BasicInformation.m_UnitOfMeasureCode"));
		partVo.setSuitableAircraftModel(selectLGR
				.getAttribute("m_BasicInformation.suitableAircraftModel"));
		partVo.setDescription(selectLGR
				.getAttribute("m_BasicInformation.partName_zh"));
		// } else {
		// partVo = PartTools.getPartInfoVOByPartNumber(partItem.getValue()
		// + "");
		// }
		// index++;
		return partVo;
	}

	public static PartInfoVO getPartInfoVOByPartNumber(String partNumber) {
		PartInfoVO partVo = new PartInfoVO();
		partVo = PartTools.getPartInfoVOByPartNumber(partNumber);
		return partVo;
	}

	/**
	 * 启动一个计时器，更新总价格和总项数
	 * 
	 * @param source
	 *            需要统计的表格
	 * @param countItem
	 *            需要更新的項數輸入框
	 * @param priceItem
	 *            需要更新的金额输入框
	 * @param propertyName
	 *            明细中金额列的属性名
	 * @return
	 */
	public static Timer startAmountTimer(final ListGrid source,
			final FormItem countItem, final FormItem priceItem,
			final String propertyName) {
		Timer timer = new Timer() {
			@Override
			public void run() {
				RecordList list = source.getDataAsRecordList();
				int len = list.getLength();
				Float amount = 0F;
				if (priceItem != null) {
					for (int i = 0; i < len; i++) {
						Record record = list.get(i);
						Float price = record.getAttributeAsFloat(propertyName);
						amount += price;
					}
					priceItem.setValue(amount);
				}

				if (countItem != null) {
					countItem.setValue(len);
				}

			}
		};
		return timer;
	}
	
	/**
	 * 使FromItem不可用
	 */
	public static void setReadOnlyForm(DynamicForm form) {
		FormItem[] fis = form.getFields();
		for (FormItem formItem : fis) {
			formItem.setAttribute("readOnly", true);
		}
	}
	
	/**
	 * 
	 * 多选下拉菜单赋值
	 * @param   item  目标多选下拉菜单
	 * @param   value 要赋的值 
	 * @return void  
	 * @throws
	 */
	public static void setMultipleFormItemValue(SelectItem item,Object value){
		if(item.getMultiple()){
			if(value!=null&&!"".equals(value)){
				item.setValues(value.toString().split(","));
			}
		}
	}
	
	/**
	 * 
	 * 多选下拉菜单赋值
	 *
	 * @param  @param item  目标多选下拉菜单
	 * @return void  
	 * @throws
	 */
	public static void setMultipleFormItemValue(SelectItem item){
		if(item.getMultiple()){
			String value=item.getValueAsString();
			if(value!=null&&!"".equals(value)){
				item.setValues(value.split(","));
			}
		}
	}


}
