package com.skynet.spms.client.gui.base;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.customerService.commonui.BaseListGrid;
import com.skynet.spms.client.service.ApprovalService;
import com.skynet.spms.client.service.ApprovalServiceAsync;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class BaseBusiness {

	private I18n ui = new I18n();

	public final static String PUBLISHSTATUSCONS = "m_BussinessPublishStatusEntity.m_PublishStatus";

	/** 发布 **/
	public final static String PULISHSTATUS_PUBLISHED = "published";

	/** 未发布 **/
	public final static String PULISHSTATUS_UNPUBLISHED = "unpublished";

	/** 变更中 **/
	public final static String PULISHSTATUS_CHANGING = "changing";

	/** 初审 **/
	public final static String APPROVALSTAGE_FIRSTING = "firsting";

	/** 终审 **/
	public final static String APPROVALSTAGE_FINALLYING = "finallying";

	/** 通过 **/
	public final static String AUDITSTATUS_PASS = "pass";

	/** 未通过 **/
	public final static String AUDITSTATUS_NOPASS = "noPass";
	/** 审批中 */
	public final static String AUDITSTATUS_APPROVALING = "approvaling";

	private ApprovalServiceAsync approvalService = GWT
			.create(ApprovalService.class);

	/**
	 * 刷新ListGrid
	 * 
	 * @param grid
	 */
	public void refeshListGrid(ListGrid listGrid) {
		Criteria c = new Criteria();
		c.setAttribute("key", "reload");
		c.setAttribute("r", String.valueOf(Math.random()));
		listGrid.fetchData(c);
	}

	/**
	 * 加入工作流
	 * 
	 * @param contractID
	 *            合同UUID
	 * @param contractNumber
	 *            合同编号
	 * @param sheetType
	 *            业务类型(WorkFlowBusinessType.class)
	 * @param amount
	 *            合同总金额
	 * @param priority
	 *            优先级
	 */
	public void putInFlow(String contractID, String contractNumber,
			String sheetType, Float amount, String priority) {
		approvalService.saveApproval(contractID, contractNumber, sheetType,
				amount, priority, sheetType, new AsyncCallback<Void>() {
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

	/**
	 * 初审合同
	 */
	public void approvalContractFirst(final BaseListGrid grid) {
		if (ValidateUtil.isRecordSelected(grid)) {
			Record target = grid.getSelectedRecord();
			String state = target
					.getAttribute("m_BussinessPublishStatusEntity.m_PublishStatus");
			if (state == null || state.equals("unpublished")) {
				SC.warn("信息尚未发布！请先发布信息！");
				return;
			}
			String approvalStage = target.getAttribute("approvalStage");
			if ("firsting".equals(approvalStage)) {
				SC.warn("合同初审中,请选择正确的当前审批阶段!");
				return;
			} else if ("finallying".equals(approvalStage)) {
				SC.warn("合同终审中,请选择正确的当前审批阶段!");
				return;
			} else {// 去初审
				target.setAttribute("approvalStage", "firsting");
				grid.updateData(target);
				SC.warn("合同初审提交成功，请耐心等待!");
			}
		}
	}

	/**
	 * 终审合同
	 */
	public void approvalContractFinally(final BaseListGrid grid) {
		if (ValidateUtil.isRecordSelected(grid)) {
			Record target = grid.getSelectedRecord();
			String state = target
					.getAttribute("m_BussinessPublishStatusEntity.m_PublishStatus");
			if (state == null || state.equals("unpublished")) {
				SC.warn("信息尚未发布！请先发布信息！");
				return;
			}
			String approvalStage = target.getAttribute("approvalStage");
			String auditStatus = target.getAttribute("auditStatus");
			if ("firsting".equals(approvalStage) && "pass".equals(auditStatus)) {// 去终审
				target.setAttribute("approvalStage", "finallying");
				grid.updateData(target);
				SC.warn("合同终审提交成功，请耐心等待!");
			} else {
				SC.warn("请选择正确的当前审批阶段!");
			}
		}
	}

	/**
	 * 发布
	 * 
	 * @param grid
	 */
	public void publishSheet(final ListGrid grid) {
		if (ValidateUtil.isRecordSelected(grid)) {
			final ListGridRecord[] records = grid.getSelection();
			if (records == null || records.length == 0) {
				SC.say("请选择要操作的信息！");
				return;
			}

			for (ListGridRecord listGridRecord : records) {
				String state = listGridRecord
						.getAttribute("m_BussinessPublishStatusEntity.m_PublishStatus");
				if (state.equals("published")) {
					SC.say("所选择的信息中 包含已发布信息！");
					return;
				}
			}

			SC.confirm("确定要发布所选择的信息吗？", new BooleanCallback() {
				@Override
				public void execute(Boolean value) {
					if (value) {
						for (ListGridRecord listGridRecord : records) {

							listGridRecord
									.setAttribute(
											"m_BussinessPublishStatusEntity.m_PublishStatus",
											"published");// 设置发布状态为 已状态
							listGridRecord.setAttribute(
									"m_BussinessStatusEntity.status",
									"submited");// 设置业务状态为
							// 已提交状态
							grid.updateData(listGridRecord);
						}
						grid.selectRecords(records);
					}
				}
			});

		}
	}

	
	/**
	 * 取消发布
	 * 
	 * @param grid
	 */
	public void cancelPublishSheet(final ListGrid grid) {
		if (ValidateUtil.isRecordSelected(grid)) {
			final ListGridRecord[] records = grid.getSelection();
			if (records == null || records.length == 0) {
				SC.say("请选择要操作的信息！");
				return;
			}

			for (ListGridRecord listGridRecord : records) {
				String state = listGridRecord
						.getAttribute("m_BussinessPublishStatusEntity.m_PublishStatus");
				if (state.equals("unpublished")) {
					SC.say("所选择的信息中 包含未发布信息！");
					return;
				}
			}

			for (ListGridRecord listGridRecord : records) {
				String state = listGridRecord.getAttribute("auditStatus");
				if (AUDITSTATUS_APPROVALING.equals(state)
						|| AUDITSTATUS_PASS.equals(state)) {
					SC.say("只能取消发布 [ 未提交审批 ] 和 [ 未通过 ] 的记录，请核对后再试！");
					return;
				}
			}

			SC.confirm("确定要将所选信息取消发布吗？", new BooleanCallback() {
				@Override
				public void execute(Boolean value) {
					if (value) {
						for (ListGridRecord listGridRecord : records) {
							listGridRecord
									.setAttribute(
											"m_BussinessPublishStatusEntity.m_PublishStatus",
											"unpublished");// 设置发布状态为 已状态
							listGridRecord
									.setAttribute(
											"m_BussinessStatusEntity.status",
											"created");// 设置业务状态为
							// 已提交状态
							grid.updateData(listGridRecord);
							
							
						}
					}
				}
			});
		}
	}

	

	/**
	 * 
	 * @param firstGrid
	 * @param secondGrid
	 * @return -1 第一个grid行数大于第二个，0 相等，1，第一个grid行数小于第二个
	 */
	public static int compareRowNum(BaseListGrid firstGrid,
			BaseListGrid secondGrid) {
		int firstNum = 0;
		int secondNum = 0;
		if (firstGrid != null) {
			firstNum = firstGrid.getRecordList().getLength();
		}
		if (secondGrid != null) {
			secondNum = secondGrid.getRecordList().getLength();
		}
		if (firstNum > secondNum) {
			return -1;
		} else if (firstNum < secondNum) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * 删除记录
	 * 
	 * @param grid
	 */
	public void deleteSheet(final ListGrid grid) {
		if (ValidateUtil.isRecordSelected(grid)) {
			SC.confirm(ui.getB().msgDelWarn(), new BooleanCallback() {
				public void execute(Boolean value) {
					if (value) {
						ListGridRecord[] records = grid.getSelection();
						for (ListGridRecord listGridRecord : records) {
							String publishStatus = listGridRecord
									.getAttribute("m_BussinessPublishStatusEntity.m_PublishStatus");
							if (publishStatus.equals("unpublished")) {
								grid.removeData(listGridRecord);
							} else {
								SC.say("只能删除 [ 未发布 ] 的信息 删除终止！");
								break;
							}
						}
					}
				}
			});
		}
	}
	/**
	 * 
	 * 刷新纪录 
	 *
	 * @param   grid   列表
	 * @return void  
	 * @throws
	 */
	public void refreshSheet( ListGrid grid) {
		grid.invalidateCache();
		Criteria criteria = grid.getCriteria();
		if(criteria!=null){
			grid.fetchData(criteria);
		}else{
			grid.fetchData();
		}
		
	}
	/***
	 * 判断单据是否可修改
	 * 
	 * @param grid
	 * @return 可修改：true，不可修改：false
	 */
	public Boolean canModifiedSheet(final ListGrid grid) {

		if (ValidateUtil.isRecordSelected(grid)) {
			ListGridRecord[] records = grid.getSelection();
			if (records[0].getAttribute(
					"m_BussinessPublishStatusEntity.m_PublishStatus").equals(
					"unpublished")) {
				return true;
			} else {
				SC.say("只能修改 [ 未发布 ] 的信息 操作终止！");
				return false;
			}
		}
		return false;
	}

	/***
	 * 判断选择的单据是否未未发布状态,未发布则不能进行下一步业务，
	 * 
	 * @param grid
	 * @return 未发布返回：false，否则返回：true，可以进行下一步业务
	 */
	public Boolean canDoNextBusiness(final ListGrid grid) {
		if (ValidateUtil.isRecordSelected(grid)) {
			ListGridRecord[] records = grid.getSelection();
			if (records[0].getAttribute(
					"m_BussinessPublishStatusEntity.m_PublishStatus").equals(
					"unpublished")) {
				SC.say("所选择的信息 包含 [ 未发布 ] 信息 不能操作！");
				return false;
			}
			return true;
		}
		return false;
	}

	/**
	 * 合同变更
	 * 
	 * @param grid
	 */
	public void changeContract(final ListGrid grid) {

		if (grid.getSelection().length == 0) {
			return;
		}

		Record target = grid.getSelectedRecord();

		String publishStatus = target.getAttribute(PUBLISHSTATUSCONS);

		if (PULISHSTATUS_PUBLISHED.equals(publishStatus)) {

			target.setAttribute(PUBLISHSTATUSCONS, PULISHSTATUS_CHANGING);

			grid.updateData(target, new DSCallback() {

				@Override
				public void execute(DSResponse response, Object rawData,
						DSRequest request) {
					SC.say("操作成功");
				}

			});

		} else {
			SC.warn("操作错误");
		}

	}

	/**
	 * 发布合同变更
	 * 
	 * @param grid
	 */
	public void publishContractChanged(final ListGrid grid) {
		if (grid.getSelection().length == 0) {
			return;
		}
		Record target = grid.getSelectedRecord();

		String publishStatus = target.getAttribute(PUBLISHSTATUSCONS);

		if (PULISHSTATUS_CHANGING.equals(publishStatus)) {

			target.setAttribute(PUBLISHSTATUSCONS, PULISHSTATUS_PUBLISHED);

			grid.updateData(target, new DSCallback() {
				@Override
				public void execute(DSResponse response, Object rawData,
						DSRequest request) {
					SC.say("操作成功");
				}
			});

		} else {

			SC.warn("操作错误");

		}

	}

	/**
	 * 提交审批
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
	 * @param itemNumberKey
	 *            单据编号(不是主键）
	 * @param publishStatusKey
	 *            发布状态列名
	 * @param priorityKey
	 *            优先级列名
	 */
	public void doApproval(final ListGrid listGrid, final String businessType,
			final String sheetType, final String sheetNoKey,
			final String itemNumberKey, final String totalAmountKey,
			final String publishStatusKey, final String priorityKey) {
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

		for (int i = 0; i < selectedRecords.length; i++) {
			// 审批状态
			String auditStatus = selectedRecords[i].getAttribute("auditStatus");
			if (AUDITSTATUS_APPROVALING.equals(auditStatus)
					|| AUDITSTATUS_PASS.equals(auditStatus)) {
				SC.say("提示", "存在 [ 审批中 ] 或 [ 通过 ] 的数据，无法提交审批");
				return;
			}
		}

		SC.confirm("操作提醒", "确认提交审批吗？", new BooleanCallback() {
			@Override
			public void execute(Boolean value) {
				if (value != null && value) {

					for (int i = 0; i < selectedRecords.length; i++) {
						// 发布状态
						String publishStatus = selectedRecords[i]
								.getAttribute(publishStatusKey);
						if (null == publishStatus || "".equals(publishStatus)) {
							continue;
						}
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
						// 优先级
						String priority = selectedRecords[i]
								.getAttribute(priorityKey);
						// 编号
						String itemNumber = selectedRecords[i]
								.getAttribute(itemNumberKey);
						if (publishStatus.equals("published")) {
							selectedRecords[i].setAttribute("auditStatus",
									AUDITSTATUS_APPROVALING);
							listGrid.updateData(selectedRecords[i]);
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
	
	
	/**
	 * 
	 * 发布
	 * 
	 * @param @param grid 选中ListGrid
	 * @param @param className 要修改的类名
	 * @param @param id 主键
	 * @return void
	 * @throws
	 */
	public void publishSheet(final ListGrid grid, final String className,
			final String id) {
		if (ValidateUtil.isRecordSelected(grid)) {
			final ListGridRecord[] records = grid.getSelection();
			if (records == null || records.length == 0) {
				SC.say("请选择要操作的信息！");
				return;
			}

			for (ListGridRecord listGridRecord : records) {
				String state = listGridRecord
						.getAttribute("m_BussinessPublishStatusEntity.m_PublishStatus");
				if (state.equals("published")) {
					SC.say("所选择的信息中 包含已发布信息！");
					return;
				}
			}

			SC.confirm("确定要发布所选择的信息吗？", new BooleanCallback() {
				@Override
				public void execute(Boolean value) {
					if (value) {
						for (ListGridRecord listGridRecord : records) {

							listGridRecord
									.setAttribute(
											"m_BussinessPublishStatusEntity.m_PublishStatus",
											"published");// 设置发布状态为 已状态
							listGridRecord.setAttribute(
									"m_BussinessStatusEntity.status",
									"submited");// 设置业务状态为
							// 已提交状态
							grid.updateData(listGridRecord);

							if(!"".equals(id)&&id!=null){
								/* 修改业务状态 */
								CodeRPCTool.updateBussinessState(className, id,
										"processed");
							}
							
						}
						grid.selectRecords(records);
					}
				}
			});

		}
	}
	
	/**
	 * 
	 * 取消发布
	 * 
	 * @param @param grid 选中ListGrid
	 * @param @param className 要修改的类名
	 * @param @param id 主键
	 * @return void
	 * @throws
	 */
	public void cancelPublishSheet(final ListGrid grid, final String className,
			final String id) {
		if (ValidateUtil.isRecordSelected(grid)) {
			final ListGridRecord[] records = grid.getSelection();
			if (records == null || records.length == 0) {
				SC.say("请选择要操作的信息！");
				return;
			}

			for (ListGridRecord listGridRecord : records) {
				String state = listGridRecord
						.getAttribute("m_BussinessPublishStatusEntity.m_PublishStatus");
				if (state.equals("unpublished")) {
					SC.say("所选择的信息中 包含未发布信息！");
					return;
				}
			}

			for (ListGridRecord listGridRecord : records) {
				String state = listGridRecord.getAttribute("auditStatus");
				if (AUDITSTATUS_APPROVALING.equals(state)
						|| AUDITSTATUS_PASS.equals(state)) {
					SC.say("只能取消发布 [ 未提交审批 ] 和 [ 未通过 ] 的记录，请核对后再试！");
					return;
				}
			}

			SC.confirm("确定要将所选信息取消发布吗？", new BooleanCallback() {
				@Override
				public void execute(Boolean value) {
					if (value) {
						for (ListGridRecord listGridRecord : records) {
							listGridRecord
									.setAttribute(
											"m_BussinessPublishStatusEntity.m_PublishStatus",
											"unpublished");// 设置发布状态为 已状态
							listGridRecord
									.setAttribute(
											"m_BussinessStatusEntity.status",
											"created");// 设置业务状态为
							// 已提交状态
							grid.updateData(listGridRecord);
							
							if(!"".equals(id)&&id!=null){
								/* 修改业务状态 */
								CodeRPCTool.updateBussinessState(className, id,
										"accepted");
							}
							
						}
					}
				}
			});
		}
	}
	
	
	/**
	 * 删除记录
	 * 
	 * @param grid
	 */
	public void deleteSheet(final ListGrid grid,final String className,
			final String id) {
		if (ValidateUtil.isRecordSelected(grid)) {
			SC.confirm(ui.getB().msgDelWarn(), new BooleanCallback() {
				public void execute(Boolean value) {
					if (value) {
						ListGridRecord[] records = grid.getSelection();
						for (ListGridRecord listGridRecord : records) {
							String publishStatus = listGridRecord
									.getAttribute("m_BussinessPublishStatusEntity.m_PublishStatus");
							if (publishStatus.equals("unpublished")) {
								grid.removeData(listGridRecord);
								if(!"".equals(id)&&id!=null){
									/* 修改业务状态 */
									CodeRPCTool.updateBussinessState(className, id,
											"submited");
								}
								
							} else {
								SC.say("只能删除 [ 未发布 ] 的信息 删除终止！");
								break;
							}
						}
					}
				}
			});
		}
	}

}
