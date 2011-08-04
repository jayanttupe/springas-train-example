package com.skynet.spms.manager.stockServiceBusiness.checkAndAcceptBusiness.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.common.gwt.GwtActionHelper;
import com.skynet.spms.manager.helper.SqlHelperTool;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.stockServiceBusiness.checkAndAcceptBusiness.CredentialsManager;
import com.skynet.spms.persistence.entity.spmsdd.CredentialsUseStatus;
import com.skynet.spms.persistence.entity.stockServiceBusiness.checkAndAcceptBusiness.credentials.Credentials;

@Service
@Transactional
public class CredentialsManagerImpl extends CommonManagerImpl<Credentials> implements CredentialsManager{
	@Override
	public void createCredentialsCode(Credentials credentials) {
		Session session = getSession();
		try
		{
			// 备件中心位置
			String strPartCentreLocation = credentials.getPartCentreLocation();
			// 证书存档位置
			String strStackPositionCode = credentials.getStackPositionCode();
			// 序列号(From)
			Integer intPartNumberFrom = Integer.valueOf(credentials.getPartNumberFrom());
			// 序列号(To)
			Integer intPartNumberTo = Integer.valueOf(credentials.getPartNumberTo());
			// 备注
			String strRemark = credentials.getRemark();
			credentials.setCreatedFlg("0");
			
			for(int i = intPartNumberFrom; i <= intPartNumberTo; i++)
			{
				// 证书存档编号
				String strCredentialsCode = strPartCentreLocation
					+ "-"
					+ strStackPositionCode
					+ "-"
					+ String.format("%05d", i);
				
				// 验证是否已经生成过的证书
				String strHql = "select count(*) from Credentials ";
				strHql = strHql + "where credentialsCode = '" + strCredentialsCode + "'";
				List<String> result = session.createQuery(strHql).list();
				if (result != null && result.get(0) != null && Integer.valueOf(String.valueOf(result.get(0))) > 0) {
					credentials.setCreatedFlg("1");
					return;
				}
			}
			
			for(int i = intPartNumberFrom; i <= intPartNumberTo; i++)
			{
				Credentials newCredentials = new Credentials();
				// 证书存档编号
				String strCredentialsCode = strPartCentreLocation
					+ "-"
					+ strStackPositionCode
					+ "-"
					+ String.format("%05d", i);

				// 备件中心位置
				newCredentials.setPartCentreLocation(strPartCentreLocation);
				// 证书存档位置
				newCredentials.setStackPositionCode(strStackPositionCode);
				// 证书存档编号
				newCredentials.setCredentialsCode(strCredentialsCode);
				// 使用状况
				newCredentials.setCredentialsUseStatus(CredentialsUseStatus.NotUsed);
				// 备注
				newCredentials.setRemark(strRemark);
				newCredentials.setCreateBy(GwtActionHelper.getCurrUser());
				newCredentials.setCreateDate(new Date());
				session.save(newCredentials);
			}
		}
		catch (Exception exp) {
			session.getTransaction().rollback();
		}
	}

	@Override
	public List<Credentials> getAllCredentials(Map values, int startRow, int endRow)
	{
		Criteria criteria= getSession().createCriteria(Credentials.class);
		criteria.add(Restrictions.eq("deleted", false));
		criteria.addOrder(Order.asc("credentialsCode"));
		SqlHelperTool.createCriteria(values, criteria, Credentials.class, null);
		 if(endRow>0){
			 criteria.setFirstResult(startRow).setMaxResults(endRow-startRow);
		 }
		 
		 return criteria.list();
	}
}
