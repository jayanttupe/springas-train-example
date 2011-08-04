package com.skynet.spms.client.gui.base;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.skynet.spms.client.service.ApprovalService;
import com.skynet.spms.client.service.ApprovalServiceAsync;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * 提交审批点击处理类
 * 
 * @author zhangqiang
 * 
 */
public class ApprovalClickHandler implements ClickHandler {

	/** 关联的表格 */
	private ListGrid listGrid;
	/** 业务类型 */
	private String businessType;
	/** 主键的key值 */
	private String sheetNoKey = "id";
	/**
	 * 单据的编号Key值 这个编号有一定规则性，如销售合同编号都以 SO 开头
	 */
	private String itemNumberKey = "";
	/** 总金额的key值 */
	private String totalAmountKey = "";
	/** 优先级的key值 */
	private String priorityKey = "";
	/** 单据类型 */
	private String sheetType;

	/** 业务状态key值 */
	// private String publishStatusKey =
	// "m_BussinessStatusEntity.m_PublishStatus";
	private String publishStatusKey = "m_BussinessPublishStatusEntity.m_PublishStatus";

	private ApprovalServiceAsync approvalService = GWT
			.create(ApprovalService.class);

	public ApprovalClickHandler(ListGrid listGrid) {
		super();
		this.listGrid = listGrid;
	}

	public ApprovalClickHandler(ListGrid listGrid, String businessType) {
		super();
		this.listGrid = listGrid;
		this.businessType = businessType;
	}

	/**
	 * 
	 * @param listGrid
	 *            当前要操作的表格
	 * @param businessType
	 *            业务类型
	 * @param sheetNoKey
	 *            当前选中数据的主键列名
	 * @param totalAmountKey
	 *            当前选中数据的金额总计列名
	 * @param sheetType
	 *            提交进入工作流的单据类型
	 * @param publishStatusKey
	 *            发布状态列名
	 * */
	public ApprovalClickHandler(ListGrid listGrid, String businessType,
			String sheetNoKey, String totalAmountKey, String sheetType,
			String publishStatusKey) {
		super();
		this.listGrid = listGrid;
		this.businessType = businessType;
		this.sheetNoKey = sheetNoKey;
		this.totalAmountKey = totalAmountKey;
		this.sheetType = sheetType;
		this.publishStatusKey = publishStatusKey;
	}

	/**
	 * 
	 * @param listGrid
	 *            当前要操作的表格
	 * @param businessType
	 *            业务类型
	 * @param sheetNoKey
	 *            当前选中数据的主键列名
	 * @param totalAmountKey
	 *            当前选中数据的金额总计列名
	 * @param sheetType
	 *            提交进入工作流的单据类型
	 * @param publishStatusKey
	 *            发布状态列名
	 * @param priorityKey
	 *            优先级列名
	 */
	public ApprovalClickHandler(ListGrid listGrid, String businessType,
			String sheetNoKey, String itemNumberKey, String totalAmountKey,
			String sheetType, String publishStatusKey, String priorityKey) {
		super();
		this.listGrid = listGrid;
		this.businessType = businessType;
		this.sheetNoKey = sheetNoKey;
		this.itemNumberKey = itemNumberKey;
		this.totalAmountKey = totalAmountKey;
		this.sheetType = sheetType;
		this.publishStatusKey = publishStatusKey;
		this.priorityKey = priorityKey;
	}

	@Override
	public void onClick(ClickEvent event) {
		final ListGridRecord[] selectedRecords = listGrid.getSelection();

		if (null == selectedRecords || selectedRecords.length == 0) {
			SC.say("提示", "请先选择要提交的数据");
			return;
		}

		for (int i = 0; i < selectedRecords.length; i++) {
			// 发布状态
			String publishStatus = selectedRecords[i]
					.getAttribute(publishStatusKey);
			if (!publishStatus.equals("published")) {
				SC.say("提示", "存在未发布的数据，无法提交审批");
				return;
			}
		}

		SC.confirm("操作提醒", "确认提交审批吗？", new BooleanCallback() {
			@Override
			public void execute(Boolean value) {
				if (value != null && value) {

					for (int i = 0; i < selectedRecords.length; i++) {
						// 主键
						String sheetNo = selectedRecords[i]
								.getAttribute(sheetNoKey);
						// 总金额
						String totalAmount = selectedRecords[i]
								.getAttribute(totalAmountKey);
						float amount = 0f;
						try {
							amount = Float.valueOf(totalAmount);
						} catch (Exception e) {
						}
						// 发布状态
						String publishStatus = selectedRecords[i]
								.getAttribute(publishStatusKey);
						// 优先级
						String priority = selectedRecords[i]
								.getAttribute(priorityKey);

						if (null == publishStatus || "".equals(publishStatus)) {
							continue;
						}
						String itemNumber = selectedRecords[i]
								.getAttribute(itemNumberKey);
						if (publishStatus.equals("published")) {
							// 保存审批记录
							approvalService.saveApproval(sheetNo, itemNumber,
									sheetType, Float.valueOf(amount), priority,
									businessType, new AsyncCallback<Void>() {

										@Override
										public void onFailure(Throwable caught) {
											SC.say("系统异常", caught.toString());
										}

										@Override
										public void onSuccess(Void result) {
											SC.say("操作提醒", "提交成功");
										}
									});
						}

					}

				}
			}
		});
	}

	public ListGrid getListGrid() {
		return listGrid;
	}

	public void setListGrid(ListGrid listGrid) {
		this.listGrid = listGrid;
	}

	public void setPublishStatusKey(String publishStatusKey) {
		this.publishStatusKey = publishStatusKey;
	}

	public void setTotalAmountKey(String totalAmountKey) {
		this.totalAmountKey = totalAmountKey;
	}

	public void setSheetNoKey(String sheetNoKey) {
		this.sheetNoKey = sheetNoKey;
	}

	public void setPriorityKey(String priorityKey) {
		this.priorityKey = priorityKey;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getSheetType() {
		return sheetType;
	}

	public void setSheetType(String sheetType) {
		this.sheetType = sheetType;
	}

}
