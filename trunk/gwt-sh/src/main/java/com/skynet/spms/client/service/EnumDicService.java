package com.skynet.spms.client.service;

import java.util.LinkedHashMap;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
/**
 * 用于获取枚举类型的字典
 * @author tqc
 *
 */
@RemoteServiceRelativePath("enumDicAction.form")
public interface EnumDicService extends RemoteService {

	public LinkedHashMap<String, String> get(String className);

}
