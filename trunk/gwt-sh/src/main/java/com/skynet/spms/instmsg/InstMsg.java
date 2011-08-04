package com.skynet.spms.instmsg;

import com.skynet.common.prop.PropertyEntity;
import com.skynet.spms.client.entity.MsgEntity;

public interface InstMsg {

	MsgEntity getMsgEntity(PropertyEntity prop);
}
