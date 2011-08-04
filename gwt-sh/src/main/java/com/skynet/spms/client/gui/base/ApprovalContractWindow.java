package com.skynet.spms.client.gui.base;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.skynet.spms.client.entity.BindModInfo;
import com.skynet.spms.client.entity.BindOutcomingInfo;
import com.skynet.spms.client.event.PortalRefreshEvent;
import com.skynet.spms.client.service.ApprovalService;
import com.skynet.spms.client.service.ApprovalServiceAsync;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.skynet.spms.client.vo.approval.ApprovalAvailableVO;
import com.skynet.spms.client.vo.approval.ApprovalRecordVO;
import com.skynet.spms.client.widgets.form.LayoutDynamicForm;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.RadioGroupItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.viewer.DetailViewer;
import com.smartgwt.client.widgets.viewer.DetailViewerField;

/**
 * 审批窗口
 * 
 * @author zhangqiang
 * 
 */
public class ApprovalContractWindow extends BaseWindow {

	private Logger log = Logger.getLogger("approval contract window");

	private LayoutDynamicForm formOne;
	private LayoutDynamicForm formTwo;

	public HLayout btnsView;
	private VLayout twoLayout;

	/** 历史表格 */
	private ListGrid historyGrid;

	/** 审批编号文本框 */
	private TextItem approvalNumberText;
	/** 审批时间文本框 */
	private TextItem approvalTimeText;
	/** 审批优先级文本框 */
	private TextItem approvalPriorityText;
	/** 审批提交人 */
	private TextItem approvalCreateByText;
	/** 审批单据主键文本框 */
	private TextItem approvalSheetNoText;
	/** 审批单据编号文本框 */
	private TextItem approvalItemNumberText;
	/** 审批单据类型文本框 */
	private TextItem approvalSheetTypeText;
	/** 审批意见文本域 */
	private TextAreaItem approvalDescTextArea;
	/** 当前审批用户文本框 */
	private TextItem approvalUserText;
	/** 审批状态单选按钮组 */
	private RadioGroupItem approvalStatusRadioGroup;
	/** 查看单据按钮 */
	private ButtonItem viewSheetBtn;

	private ApprovalServiceAsync approvalService = GWT
			.create(ApprovalService.class);

	public ApprovalContractWindow(JsArray<BindOutcomingInfo> outcommingArray,
			BindModInfo formInfo, String windowTitle, boolean isMax,
			Rectangle srcRect, String iconUrl, final HandlerManager eventBus,
			final String taskID, final String procInstID) {
		super(windowTitle, isMax, srcRect, null, iconUrl);
		initBtnsView(outcommingArray, formInfo, eventBus, taskID);
	}

	@Override
	protected Canvas getLeftLayout(Rectangle srcRect, ListGrid listGrid) {
		/** 主面板 **/
		VLayout vmain = new VLayout();
		vmain.setWidth100();
		vmain.setHeight100();
		vmain.setLayoutMargin(5);

		/** 供应商选择面板 **/
		VLayout listLayout = new VLayout();
		listLayout.setHeight("90%");
		listLayout.setWidth100();

		/** 审批记录 **/
		VLayout oneLayout = new VLayout();
		oneLayout.setGroupTitle("审批记录");
		oneLayout.setIsGroup(true);
		oneLayout.setLayoutMargin(5);
		// 添加From
		oneLayout.addMember(buildFormOne());
		listLayout.addMember(oneLayout);

		/** 面板2 * */
		HLayout twoView = getShowGridView();
		twoView.setHeight(150);
		twoView.setWidth100();
		twoView.setLayoutTopMargin(10);
		listLayout.addMember(twoView);

		/** 审批结果 **/
		twoLayout = new VLayout();
		twoLayout.setGroupTitle("审批结果");
		twoLayout.setIsGroup(true);
		twoLayout.setLayoutMargin(5);
		// 添加From
		twoLayout.addMember(buildFormTwo());
		listLayout.addMember(twoLayout);

		/** 提交按钮 **/
		VLayout btnsLayout = new VLayout();
		btnsLayout.setLayoutMargin(20);
		btnsLayout.setHeight("10%");
		btnsLayout.setWidth100();
		btnsLayout.addMember(getSubmitLayout());
		btnsLayout.setBorder("1px solid #E8E8E8");

		vmain.addMember(listLayout);
		vmain.addMember(btnsLayout);

		setHeight(600);
		return vmain;
	}

	/** 布局二* */
	private HLayout getShowGridView() {
		HLayout h = new HLayout();
		h.setWidth100();
		h.setHeight100();
		h.setMembersMargin(5);
		h.setLayoutTopMargin(3);

		VLayout leftLayout = new VLayout();
		leftLayout.setWidth100();
		leftLayout.setHeight100();
		Label leftLb = new Label("审批历史");
		leftLb.setHeight("20");
		leftLayout.addMember(leftLb);// 先放label
		leftLayout.addMember(getLeftGrid());// 再放Grid

		h.addMember(leftLayout);
		return h;
	}

	/**
	 * 构建可发送的供应商列表
	 */
	private DynamicForm buildFormOne() {
		formOne = new LayoutDynamicForm();
		formOne.setNumCols(4);

		// 审批编号
		approvalNumberText = new TextItem();
		approvalNumberText.setName("approvalNumber");
		approvalNumberText.setTitle("审批编号");
		approvalNumberText.setDisabled(true);

		// 递交时间
		approvalTimeText = new TextItem();
		approvalTimeText.setName("createDate");
		approvalTimeText.setTitle("递交时间");
		approvalTimeText.setDisabled(true);

		// 优先级
		approvalPriorityText = new TextItem();
		approvalPriorityText.setName("m_Priority");
		approvalPriorityText.setTitle("优先级");
		approvalPriorityText.setDisabled(true);

		// 提交人
		approvalCreateByText = new TextItem();
		approvalCreateByText.setName("createBy");
		approvalCreateByText.setTitle("提交人");
		approvalCreateByText.setDisabled(true);

		// 单据主键
		approvalSheetNoText = new TextItem();
		approvalSheetNoText.setName("sheetNo");
		approvalSheetNoText.setTitle("单据主键");
		approvalSheetNoText.setDisabled(true);
		approvalSheetNoText.setVisible(false);

		approvalItemNumberText = new TextItem();
		approvalItemNumberText.setName("itemNumber");
		approvalItemNumberText.setTitle("单据编号");
		approvalItemNumberText.setDisabled(true);

		// 查看单据
		viewSheetBtn = new ButtonItem("查看单据");
		viewSheetBtn.setStartRow(false);
		viewSheetBtn.setEndRow(true);
		viewSheetBtn
				.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {

					@Override
					public void onClick(
							com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
						Object t = approvalSheetTypeText.getValue();
						Object o = approvalSheetNoText.getValue();
						if (null != t && null != o) {
							String type = t.toString();
							String no = o.toString();
							log.info("approvalSheetTypeText:" + type
									+ "\t approvalSheetNoText:" + no);
							// 根据类型和单据编号获取详细窗口
							log.info("显示单据详细面板");
							Window win = ApprovalSheetDetailWinFactory
									.getApprovalSheetDetailWin(no, type);
							if (null != win) {
								ShowWindowTools.showWindow(
										viewSheetBtn.getPageRect(), win, 200);
							} else {
								SC.say("系统错误", "未找到该单据的详细面板");
							}

						}

					}
				});

		// 单据类型
		approvalSheetTypeText = new TextItem();
		approvalSheetTypeText.setName("sheetType");
		approvalSheetTypeText.setTitle("单据类型");
		approvalSheetTypeText.setDisabled(true);

		formOne.setFields(approvalNumberText, approvalTimeText,
				approvalPriorityText, approvalCreateByText,
				approvalSheetNoText, approvalItemNumberText, viewSheetBtn,
				approvalSheetTypeText);

		return formOne;
	}

	/**
	 * 构建等待提交的供应商列表
	 */
	private DynamicForm buildFormTwo() {
		formTwo = new LayoutDynamicForm();
		formTwo.setNumCols(2);
		formTwo.setTitleAlign(Alignment.LEFT);

		// 审批操作
		approvalStatusRadioGroup = new RadioGroupItem();
		approvalStatusRadioGroup.setName("approvalStatus");
		approvalStatusRadioGroup.setTitle("审批结果");
		approvalStatusRadioGroup.setVertical(false);
		approvalStatusRadioGroup.setRequired(true);
		approvalStatusRadioGroup.setRequiredMessage("请选择审批结果");

		// 审批意见
		approvalDescTextArea = new TextAreaItem();
		approvalDescTextArea.setName("approvalDesc");
		approvalDescTextArea.setTitle("审批意见");
		approvalDescTextArea.setTitleVAlign(VerticalAlignment.TOP);
		approvalDescTextArea.setLength(255);

		// 审批人
		approvalUserText = new TextItem();
		approvalUserText.setName("approvalUser");
		approvalUserText.setTitle("审批人");
		approvalUserText.setDisabled(true);

		formTwo.setFields(approvalStatusRadioGroup, approvalDescTextArea,
				approvalUserText);

		return formTwo;
	}

	/**
	 * 表单提交操作按钮
	 * 
	 * @return
	 */
	private HLayout getSubmitLayout() {
		btnsView = new BtnsHLayout();
		btnsView.setWidth100();
		btnsView.setMembersMargin(5);

		return btnsView;
	}

	/***
	 * 构建按钮
	 * 
	 * @param buttons
	 */
	public void initBtnsView(JsArray<BindOutcomingInfo> outcommingArray,
			final BindModInfo formInfo, final HandlerManager eventBus,
			final String taskID) {
		Button submitBtn = new Button();
		submitBtn.setTitle("提交");
		submitBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				boolean result = formTwo.validate(false);
				if (!result) {
					return;
				}
				// 保存审批数据，提交审批结果
				Object approvalDesc = getApprovalDescTextArea().getValue();
				Object approvalStatus = getApprovalStatusRadioGroup()
						.getValue();
				String id = formInfo.getBusinessKey();
				String desc = null;
				String status = null;

				if (null == id || "".equals(id)) {
					SC.say("提示", "主键ID为空，无法提交数据");
					return;
				}

				if (null != approvalDesc) {
					desc = approvalDesc.toString();
				}
				if (null != approvalStatus) {
					status = approvalStatus.toString();
				}

				log.info("approvalDesc:" + desc + "\t approvalStatus:" + status
						+ "\t id:" + id);
				log.info("保存审批操作");

				String outcoming = "";

				// 获取审批去向
				for (ApprovalResult re : ApprovalResult.values()) {
					String key = re.getKey();
					// String value = re.getValue();
					if (key.equals(status)) {
						outcoming = key;
						break;
					}
				}

				ApprovalAvailableVO vo = new ApprovalAvailableVO();
				vo.setApprovalDesc(desc);
				vo.setApprovalStatus(status);
				vo.setTaskID(taskID);

				approvalService.saveApprovalAvailable(id, vo, taskID,
						outcoming, new AsyncCallback<Void>() {
							@Override
							public void onFailure(Throwable caught) {
								SC.say("系统异常", caught.getMessage());
								log.warning(caught.getMessage());
							}

							@Override
							public void onSuccess(Void result) {
								SC.say("提示", "操作成功");
								close();
								eventBus.fireEvent(new PortalRefreshEvent()); // 刷新列表
								log.info("commit success");
							}

						});

			}
		});

		btnsView.addMember(submitBtn);

		Button cancelBtn = new Button();
		cancelBtn.setTitle("关闭");
		cancelBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				close();
			}
		});
		btnsView.addMember(cancelBtn);

		// 根据审批流向绑定值
		bindApprovalStatusValue(outcommingArray, submitBtn);
	}

	private void bindApprovalStatusValue(
			JsArray<BindOutcomingInfo> outcommingArray, Button submitBtn) {
		LinkedHashMap<String, String> values = new LinkedHashMap<String, String>();
		if (null != outcommingArray && outcommingArray.length() > 0) {
			for (int i = 0; i < outcommingArray.length(); i++) {
				BindOutcomingInfo info = outcommingArray.get(i);
				log.info("forminfo outcoming:" + info.getOutcoming());
				for (ApprovalResult result : ApprovalResult.values()) {
					String key = result.getKey();
					String value = result.getValue();
					if (key.equals(info.getOutcoming())) {
						values.put(key, value);
						break;
					}
				}
			}
			approvalStatusRadioGroup.setValueMap(values);
			approvalStatusRadioGroup.setRequired(true);
		} else {
			// 没有去向 隐藏审批表单
			twoLayout.setVisible(false);
			submitBtn.setVisible(false);
		}
	}

	/**
	 * 填充值
	 * 
	 * @param vo
	 */
	public void setApprovalValue(ApprovalRecordVO vo) {
		approvalNumberText.setValue(vo.getApprovalNumber());
		approvalTimeText.setValue(vo.getCreateDate());
		approvalPriorityText.setValue(vo.getM_Priority());
		approvalCreateByText.setValue(vo.getCreateBy());
		approvalSheetNoText.setValue(vo.getSheetNo());
		approvalItemNumberText.setValue(vo.getItemNumber());
		approvalSheetTypeText.setValue(vo.getSheetType());
		approvalUserText.setValue(vo.getCurrentApprovalUser());

		// 填充历史审批数据
		List<ApprovalAvailableVO> list = vo.getApprovalAvaibleVoList();
		if (null != list && list.size() > 0) {
			ListGridRecord[] records = new ListGridRecord[list.size()];
			for (int i = 0; i < list.size(); i++) {
				ApprovalAvailableVO approvalAvailableVO = list.get(i);
				ListGridRecord record = new ListGridRecord();
				record.setAttribute("approvalUser",
						approvalAvailableVO.getApprovalUser());
				record.setAttribute("approvalTime",
						approvalAvailableVO.getApprovalTime());
				for (ApprovalResult result : ApprovalResult.values()) {
					String key = result.getKey();
					String value = result.getValue();
					String status = approvalAvailableVO.getApprovalStatus();
					if (result.toString().equals(status)) {
						record.setAttribute("approvalStatus", value);
						break;
					}
				}
				record.setAttribute("approvalDesc",
						approvalAvailableVO.getApprovalDesc());
				records[i] = record;
			}

			historyGrid.setData(records);

		}
	}

	// 新询价单信息
	private ListGrid getLeftGrid() {
		historyGrid = new ListGrid() {
			@Override
			protected Canvas getCellHoverComponent(Record record,
					Integer rowNum, Integer colNum) {
				DetailViewer detailViewer = new DetailViewer();
				detailViewer.setWidth(200);
				detailViewer.setData(new Record[] { record }); // 数据源

				DetailViewerField descField = new DetailViewerField(
						"approvalDesc", "审批意见");
				detailViewer.setFields(descField);

				return detailViewer;
			}
		};

		ListGridField field1 = new ListGridField("approvalUser", "审批人");
		ListGridField field2 = new ListGridField("approvalTime", "审批时间");
		ListGridField field3 = new ListGridField("approvalStatus", "审批结果");
		ListGridField field4 = new ListGridField("approvalDesc", "审批意见");

		historyGrid.setFields(field1, field2, field3, field4);

		historyGrid.setCanHover(true);
		historyGrid.setShowHover(true);
		historyGrid.setShowHoverComponents(true);
		return historyGrid;
	}

	public TextAreaItem getApprovalDescTextArea() {
		return approvalDescTextArea;
	}

	public RadioGroupItem getApprovalStatusRadioGroup() {
		return approvalStatusRadioGroup;
	}

}
