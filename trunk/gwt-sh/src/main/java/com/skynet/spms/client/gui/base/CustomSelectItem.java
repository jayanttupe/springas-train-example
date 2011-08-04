package com.skynet.spms.client.gui.base;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.events.FilterEditorSubmitEvent;
import com.smartgwt.client.widgets.grid.events.FilterEditorSubmitHandler;

public class CustomSelectItem extends SelectItem {
	ListGrid dropListGrid;

	/**
	 * @param name
	 *            实体属性名
	 * @param moduleName
	 *            模块名
	 * @param dsName
	 *            数据源名
	 * @param valueField
	 *            对应于数据源中有的属性，用于作为SelectItem的Value值
	 * @param displayField
	 *            用于SelectItem的显示值
	 * @param gridFieldNames
	 *            ListGrid中的列名，来自于ds中的属性
	 */
	public CustomSelectItem(String name, String moduleName, String dsName,
			final String valueField, final String displayField,
			final String... gridFieldNames) {
		super(name);
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(moduleName, dsName,
				new PostDataSourceInit() {

					@Override
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						dropListGrid = new ListGrid();
						dropListGrid.setDataSource(dataSource);
						dropListGrid.setAutoFetchData(true);
						dropListGrid.setShowFilterEditor(true);
						dropListGrid.setShowAllRecords(false);
						dropListGrid.setFilterOnKeypress(true);
						dropListGrid
								.addFilterEditorSubmitHandler(new FilterEditorSubmitHandler() {
									@Override
									public void onFilterEditorSubmit(
											FilterEditorSubmitEvent event) {
										Criteria c = event.getCriteria();
										setPickListCriteria(c);
										fetchData();
									}
								});

						ListGridField[] fields = new ListGridField[gridFieldNames.length];
						for (int i = 0; i < gridFieldNames.length; i++) {
							fields[i] = new ListGridField(gridFieldNames[i]);
							fields[i].setCanFilter(true);
						}
						dropListGrid.setFields(fields);
						dropListGrid.fetchData();

						setOptionDataSource(dataSource);
						setValueField(valueField);
						setDisplayField(displayField);
						setPickListProperties(dropListGrid);
						// setFilterLocally(true);
						setPickListFields(fields);
					}
				});
	}

	/**
	 * @param name
	 *            实体属性名
	 * @param moduleName
	 *            模块名
	 * @param dsName
	 *            数据源名
	 * @param valueField
	 *            对应于数据源中有的属性，用于作为SelectItem的Value值
	 * @param displayField
	 *            用于SelectItem的显示值
	 * @param criteria
	 *            筛选条件
	 * @param gridFieldNames
	 *            ListGrid中的列名，来自于ds中的属性
	 */
	public CustomSelectItem(String name, String moduleName, String dsName,
			final String valueField, final String displayField,
			final Criteria criteria, final String... gridFieldNames) {
		super(name);
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(moduleName, dsName,
				new PostDataSourceInit() {

					@Override
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						dropListGrid = new ListGrid();
						dropListGrid.setDataSource(dataSource);
						dropListGrid.setAutoFetchData(true);
						dropListGrid.setShowFilterEditor(true);
						dropListGrid.setShowAllRecords(false);
						dropListGrid.setFilterOnKeypress(true);
						dropListGrid
								.addFilterEditorSubmitHandler(new FilterEditorSubmitHandler() {
									@Override
									public void onFilterEditorSubmit(
											FilterEditorSubmitEvent event) {
										Criteria c = event.getCriteria();
										setPickListCriteria(c);
										fetchData();
									}
								});

						ListGridField[] fields = new ListGridField[gridFieldNames.length];
						for (int i = 0; i < gridFieldNames.length; i++) {
							fields[i] = new ListGridField(gridFieldNames[i]);
							fields[i].setCanFilter(true);
						}
						dropListGrid.setFields(fields);
						dropListGrid.setCriteria(criteria);
						dropListGrid.fetchData(criteria);

						setOptionDataSource(dataSource);
						setValueField(valueField);
						setDisplayField(displayField);
						setPickListProperties(dropListGrid);
						// setFilterLocally(true);
						setPickListFields(fields);
					}
				});
	}
}
