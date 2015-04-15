/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.timetracking.dayoffs.model;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import com.liferay.timetracking.dayoffs.service.ClpSerializer;
import com.liferay.timetracking.dayoffs.service.RulingLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author László Hudák
 */
public class RulingClp extends BaseModelImpl<Ruling> implements Ruling {
	public RulingClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return Ruling.class;
	}

	@Override
	public String getModelClassName() {
		return Ruling.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _ruleId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setRuleId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _ruleId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("ruleId", getRuleId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("ruleName", getRuleName());
		attributes.put("multiplier", getMultiplier());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long ruleId = (Long)attributes.get("ruleId");

		if (ruleId != null) {
			setRuleId(ruleId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String ruleName = (String)attributes.get("ruleName");

		if (ruleName != null) {
			setRuleName(ruleName);
		}

		Double multiplier = (Double)attributes.get("multiplier");

		if (multiplier != null) {
			setMultiplier(multiplier);
		}
	}

	@Override
	public long getRuleId() {
		return _ruleId;
	}

	@Override
	public void setRuleId(long ruleId) {
		_ruleId = ruleId;

		if (_rulingRemoteModel != null) {
			try {
				Class<?> clazz = _rulingRemoteModel.getClass();

				Method method = clazz.getMethod("setRuleId", long.class);

				method.invoke(_rulingRemoteModel, ruleId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_groupId = groupId;

		if (_rulingRemoteModel != null) {
			try {
				Class<?> clazz = _rulingRemoteModel.getClass();

				Method method = clazz.getMethod("setGroupId", long.class);

				method.invoke(_rulingRemoteModel, groupId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;

		if (_rulingRemoteModel != null) {
			try {
				Class<?> clazz = _rulingRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_rulingRemoteModel, companyId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;

		if (_rulingRemoteModel != null) {
			try {
				Class<?> clazz = _rulingRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_rulingRemoteModel, userId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
	}

	@Override
	public void setUserUuid(String userUuid) {
		_userUuid = userUuid;
	}

	@Override
	public String getUserName() {
		return _userName;
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;

		if (_rulingRemoteModel != null) {
			try {
				Class<?> clazz = _rulingRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_rulingRemoteModel, userName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;

		if (_rulingRemoteModel != null) {
			try {
				Class<?> clazz = _rulingRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_rulingRemoteModel, createDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;

		if (_rulingRemoteModel != null) {
			try {
				Class<?> clazz = _rulingRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_rulingRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getRuleName() {
		return _ruleName;
	}

	@Override
	public void setRuleName(String ruleName) {
		_ruleName = ruleName;

		if (_rulingRemoteModel != null) {
			try {
				Class<?> clazz = _rulingRemoteModel.getClass();

				Method method = clazz.getMethod("setRuleName", String.class);

				method.invoke(_rulingRemoteModel, ruleName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public double getMultiplier() {
		return _multiplier;
	}

	@Override
	public void setMultiplier(double multiplier) {
		_multiplier = multiplier;

		if (_rulingRemoteModel != null) {
			try {
				Class<?> clazz = _rulingRemoteModel.getClass();

				Method method = clazz.getMethod("setMultiplier", double.class);

				method.invoke(_rulingRemoteModel, multiplier);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getRulingRemoteModel() {
		return _rulingRemoteModel;
	}

	public void setRulingRemoteModel(BaseModel<?> rulingRemoteModel) {
		_rulingRemoteModel = rulingRemoteModel;
	}

	public Object invokeOnRemoteModel(String methodName,
		Class<?>[] parameterTypes, Object[] parameterValues)
		throws Exception {
		Object[] remoteParameterValues = new Object[parameterValues.length];

		for (int i = 0; i < parameterValues.length; i++) {
			if (parameterValues[i] != null) {
				remoteParameterValues[i] = ClpSerializer.translateInput(parameterValues[i]);
			}
		}

		Class<?> remoteModelClass = _rulingRemoteModel.getClass();

		ClassLoader remoteModelClassLoader = remoteModelClass.getClassLoader();

		Class<?>[] remoteParameterTypes = new Class[parameterTypes.length];

		for (int i = 0; i < parameterTypes.length; i++) {
			if (parameterTypes[i].isPrimitive()) {
				remoteParameterTypes[i] = parameterTypes[i];
			}
			else {
				String parameterTypeName = parameterTypes[i].getName();

				remoteParameterTypes[i] = remoteModelClassLoader.loadClass(parameterTypeName);
			}
		}

		Method method = remoteModelClass.getMethod(methodName,
				remoteParameterTypes);

		Object returnValue = method.invoke(_rulingRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			RulingLocalServiceUtil.addRuling(this);
		}
		else {
			RulingLocalServiceUtil.updateRuling(this);
		}
	}

	@Override
	public Ruling toEscapedModel() {
		return (Ruling)ProxyUtil.newProxyInstance(Ruling.class.getClassLoader(),
			new Class[] { Ruling.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		RulingClp clone = new RulingClp();

		clone.setRuleId(getRuleId());
		clone.setGroupId(getGroupId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setRuleName(getRuleName());
		clone.setMultiplier(getMultiplier());

		return clone;
	}

	@Override
	public int compareTo(Ruling ruling) {
		int value = 0;

		value = getRuleName().compareTo(ruling.getRuleName());

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof RulingClp)) {
			return false;
		}

		RulingClp ruling = (RulingClp)obj;

		long primaryKey = ruling.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	public Class<?> getClpSerializerClass() {
		return _clpSerializerClass;
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{ruleId=");
		sb.append(getRuleId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", ruleName=");
		sb.append(getRuleName());
		sb.append(", multiplier=");
		sb.append(getMultiplier());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(31);

		sb.append("<model><model-name>");
		sb.append("com.liferay.timetracking.dayoffs.model.Ruling");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>ruleId</column-name><column-value><![CDATA[");
		sb.append(getRuleId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>ruleName</column-name><column-value><![CDATA[");
		sb.append(getRuleName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>multiplier</column-name><column-value><![CDATA[");
		sb.append(getMultiplier());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _ruleId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _ruleName;
	private double _multiplier;
	private BaseModel<?> _rulingRemoteModel;
	private Class<?> _clpSerializerClass = com.liferay.timetracking.dayoffs.service.ClpSerializer.class;
}