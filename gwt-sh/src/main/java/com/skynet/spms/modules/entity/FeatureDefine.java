package com.skynet.spms.modules.entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class FeatureDefine {

	private final String featureName;

	private boolean enable;

	public FeatureDefine(String str) {
		this.featureName = str;
		enable = true;
	}

	public String getFeatureName() {
		return featureName;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(97,131).append(featureName).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return new EqualsBuilder().append(featureName,
				((FeatureDefine) obj).featureName).isEquals();
	}

}
