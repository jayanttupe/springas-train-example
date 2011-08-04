package com.skynet.spms.client.gui.contractManagement.tag;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.skynet.spms.client.gui.contractManagement.tag.impl.RepairInspectClaimContractTemplateTagPlug;

/**
 * tag 管理
 * 
 * @author tqc
 * 
 */
public class TagManager {

	private static TagManager manager;

	public static Map<TagEnum, List<Tag>> tags = new HashMap<TagEnum, List<Tag>>();

	private TagManager() {
		plug();
	}

	public static TagManager getInstance() {
		if (manager == null) {
			manager = new TagManager();
		}
		return manager;
	}

	/**
	 * 根据标签类别获取标签
	 * 
	 * @param tagType
	 * @return
	 */
	public List<Tag> getTags(TagEnum tagType) {
		return tags.get(tagType);
	}

	/**
	 * 挂载标签
	 */
	private void plug() {
		new RepairInspectClaimContractTemplateTagPlug().plug(tags);
	}

}
