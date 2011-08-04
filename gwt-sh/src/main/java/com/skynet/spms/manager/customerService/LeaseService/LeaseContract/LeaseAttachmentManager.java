package com.skynet.spms.manager.customerService.LeaseService.LeaseContract;

import java.util.List;
import java.util.Map;

import com.skynet.spms.persistence.entity.base.Attachment;

/**
 * 租赁合同明附件接口
 * 
 * @version 1.0
 * @author 赵小强
 * @date 2011-07-011
 */
public interface LeaseAttachmentManager {

	/**
	 * 添加一条记录
	 * 
	 * @param 附件对象
	 */
	public void addSheet(Attachment item);

	/**
	 * 根据ID查询一条记录
	 * 
	 * @param 附件ID
	 * @return 附件对象
	 */
	public Attachment getById(String id);

	/**
	 * 
	 * 描述这个方法的作用
	 * 
	 * @param 新数据对象
	 * @param 要修改的对象ID
	 * @return 合同附件对象
	 */
	public Attachment update(Map<String, Object> newValues, String itemID);

	/**
	 * 分页查询
	 * 
	 * @param 新的对象数据
	 * @param 首页
	 * @param 尾页
	 * @return 附件集合对象
	 */
	public List<Attachment> doQuery(Map<String, Object> values, int startRow,
			int endRow);

	/**
	 * 分页查询
	 * 
	 * @param 首页
	 * @param 尾页
	 * @return 附件集合对象
	 */
	public List<Attachment> getList(int startRow, int endRow);

	/**
	 * 删除一条记录，这里的删除实际上只是更新操作
	 * 
	 * @param 附件ID
	 */
	public void delete(String itemID);

}
