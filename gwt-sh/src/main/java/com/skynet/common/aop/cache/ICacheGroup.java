package com.skynet.common.aop.cache;

/**
 * 缓存对象接口，如果缓存对象需要刷新逻辑，则继承该接口
 * 返回一个或一组字符串名称作为该对象关联的group
 * 
 * 刷新缓存时将以group为单位刷新
 * 
 * @Example 
 * 某一批实体对象来自数据源userinfo.xml，则可以将group绑定为[userinfo]
 * 当该组实体对应的数据源更新时，只需要直接以group=userinfo重置缓存
 * 就能重置所有和此group绑定的缓存对象，而其他对象不受影响
 * 
 * 一个实体可以绑定多个group，如一个实体数据与多个数据源关联
 * 则可以一次绑定多个组，从而确保任一上游相关数据源更新都能及时触发所有关联的实体缓存刷新
 * 
 * * @author jiang
 *
 */
public interface ICacheGroup {

	public String[] getCacheGroups();
}
