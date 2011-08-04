package com.skynet.spms.client.gui.contractManagement.tag;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class BaseTagPlug {
	
	/** 标签类别 **/
	protected TagEnum tagType;
	
	/** 类别标签集合 **/
	protected List<Tag> moduleTags = new ArrayList<Tag>();

	/**
	 * 加入模块标签
	 * 
	 * @param tag
	 */
	protected void addModuleTag(Tag tag) {
		tag.setTagID(moduleTags.size() + 1);
		tag.setTagType(tagType);
		moduleTags.add(tag);
	}

	/**
	 * 批次加入标签
	 * 
	 * @param tag
	 */
	protected void addModuleTag(Tag... tag) {
		for (int i = 0; i < tag.length; i++) {
			addModuleTag(tag[i]);
		}
	}

	protected abstract void plug(Map<TagEnum, List<Tag>> tags);

}
