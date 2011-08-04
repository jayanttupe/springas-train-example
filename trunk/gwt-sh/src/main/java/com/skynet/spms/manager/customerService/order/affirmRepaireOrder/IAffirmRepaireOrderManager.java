package com.skynet.spms.manager.customerService.order.affirmRepaireOrder;
import java.util.List;
import java.util.Map;
import com.skynet.spms.persistence.entity.customerService.order.affirmRepaireOrder.AffirmRepaireOrder;
/**
 * 客户确认修理指令业务接口
 * @author  tqc
 *
 */
public interface IAffirmRepaireOrderManager {
	
	
	public void addAffirmRepaireOrder(AffirmRepaireOrder o);

	public AffirmRepaireOrder updateAffirmRepaireOrder(Map<String, Object> newValues, String itemID);

	public void deleteAffirmRepaireOrder(String itemID);

	public List<AffirmRepaireOrder> queryAffirmRepaireOrderList(int startRow, int endRow);
	
	public AffirmRepaireOrder getAffirmRepaireOrderById(String sheetId);

}
