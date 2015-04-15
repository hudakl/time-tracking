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

package com.liferay.timetracking.dayoffs.service.base;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.bean.IdentifiableBean;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.PersistedModel;
import com.liferay.portal.service.BaseLocalServiceImpl;
import com.liferay.portal.service.PersistedModelLocalServiceRegistryUtil;
import com.liferay.portal.service.persistence.UserPersistence;

import com.liferay.timetracking.dayoffs.model.DaysOfYear;
import com.liferay.timetracking.dayoffs.service.DaysOfYearLocalService;
import com.liferay.timetracking.dayoffs.service.persistence.DaysOfYearPersistence;
import com.liferay.timetracking.dayoffs.service.persistence.RulingPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the days of year local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.timetracking.dayoffs.service.impl.DaysOfYearLocalServiceImpl}.
 * </p>
 *
 * @author LĂˇszlĂł HudĂˇk
 * @see com.liferay.timetracking.dayoffs.service.impl.DaysOfYearLocalServiceImpl
 * @see com.liferay.timetracking.dayoffs.service.DaysOfYearLocalServiceUtil
 * @generated
 */
public abstract class DaysOfYearLocalServiceBaseImpl
	extends BaseLocalServiceImpl implements DaysOfYearLocalService,
		IdentifiableBean {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.timetracking.dayoffs.service.DaysOfYearLocalServiceUtil} to access the days of year local service.
	 */

	/**
	 * Adds the days of year to the database. Also notifies the appropriate model listeners.
	 *
	 * @param daysOfYear the days of year
	 * @return the days of year that was added
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public DaysOfYear addDaysOfYear(DaysOfYear daysOfYear)
		throws SystemException {
		daysOfYear.setNew(true);

		return daysOfYearPersistence.update(daysOfYear);
	}

	/**
	 * Creates a new days of year with the primary key. Does not add the days of year to the database.
	 *
	 * @param dayOfYearId the primary key for the new days of year
	 * @return the new days of year
	 */
	@Override
	public DaysOfYear createDaysOfYear(long dayOfYearId) {
		return daysOfYearPersistence.create(dayOfYearId);
	}

	/**
	 * Deletes the days of year with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param dayOfYearId the primary key of the days of year
	 * @return the days of year that was removed
	 * @throws PortalException if a days of year with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public DaysOfYear deleteDaysOfYear(long dayOfYearId)
		throws PortalException, SystemException {
		return daysOfYearPersistence.remove(dayOfYearId);
	}

	/**
	 * Deletes the days of year from the database. Also notifies the appropriate model listeners.
	 *
	 * @param daysOfYear the days of year
	 * @return the days of year that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public DaysOfYear deleteDaysOfYear(DaysOfYear daysOfYear)
		throws SystemException {
		return daysOfYearPersistence.remove(daysOfYear);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(DaysOfYear.class,
			clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return daysOfYearPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.timetracking.dayoffs.model.impl.DaysOfYearModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return daysOfYearPersistence.findWithDynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.timetracking.dayoffs.model.impl.DaysOfYearModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return daysOfYearPersistence.findWithDynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	 * Returns the number of rows that match the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows that match the dynamic query
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery)
		throws SystemException {
		return daysOfYearPersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows that match the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows that match the dynamic query
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection) throws SystemException {
		return daysOfYearPersistence.countWithDynamicQuery(dynamicQuery,
			projection);
	}

	@Override
	public DaysOfYear fetchDaysOfYear(long dayOfYearId)
		throws SystemException {
		return daysOfYearPersistence.fetchByPrimaryKey(dayOfYearId);
	}

	/**
	 * Returns the days of year with the primary key.
	 *
	 * @param dayOfYearId the primary key of the days of year
	 * @return the days of year
	 * @throws PortalException if a days of year with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DaysOfYear getDaysOfYear(long dayOfYearId)
		throws PortalException, SystemException {
		return daysOfYearPersistence.findByPrimaryKey(dayOfYearId);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException, SystemException {
		return daysOfYearPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the days of years.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.timetracking.dayoffs.model.impl.DaysOfYearModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of days of years
	 * @param end the upper bound of the range of days of years (not inclusive)
	 * @return the range of days of years
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DaysOfYear> getDaysOfYears(int start, int end)
		throws SystemException {
		return daysOfYearPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of days of years.
	 *
	 * @return the number of days of years
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int getDaysOfYearsCount() throws SystemException {
		return daysOfYearPersistence.countAll();
	}

	/**
	 * Updates the days of year in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param daysOfYear the days of year
	 * @return the days of year that was updated
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public DaysOfYear updateDaysOfYear(DaysOfYear daysOfYear)
		throws SystemException {
		return daysOfYearPersistence.update(daysOfYear);
	}

	/**
	 * Returns the days of year local service.
	 *
	 * @return the days of year local service
	 */
	public com.liferay.timetracking.dayoffs.service.DaysOfYearLocalService getDaysOfYearLocalService() {
		return daysOfYearLocalService;
	}

	/**
	 * Sets the days of year local service.
	 *
	 * @param daysOfYearLocalService the days of year local service
	 */
	public void setDaysOfYearLocalService(
		com.liferay.timetracking.dayoffs.service.DaysOfYearLocalService daysOfYearLocalService) {
		this.daysOfYearLocalService = daysOfYearLocalService;
	}

	/**
	 * Returns the days of year remote service.
	 *
	 * @return the days of year remote service
	 */
	public com.liferay.timetracking.dayoffs.service.DaysOfYearService getDaysOfYearService() {
		return daysOfYearService;
	}

	/**
	 * Sets the days of year remote service.
	 *
	 * @param daysOfYearService the days of year remote service
	 */
	public void setDaysOfYearService(
		com.liferay.timetracking.dayoffs.service.DaysOfYearService daysOfYearService) {
		this.daysOfYearService = daysOfYearService;
	}

	/**
	 * Returns the days of year persistence.
	 *
	 * @return the days of year persistence
	 */
	public DaysOfYearPersistence getDaysOfYearPersistence() {
		return daysOfYearPersistence;
	}

	/**
	 * Sets the days of year persistence.
	 *
	 * @param daysOfYearPersistence the days of year persistence
	 */
	public void setDaysOfYearPersistence(
		DaysOfYearPersistence daysOfYearPersistence) {
		this.daysOfYearPersistence = daysOfYearPersistence;
	}

	/**
	 * Returns the ruling local service.
	 *
	 * @return the ruling local service
	 */
	public com.liferay.timetracking.dayoffs.service.RulingLocalService getRulingLocalService() {
		return rulingLocalService;
	}

	/**
	 * Sets the ruling local service.
	 *
	 * @param rulingLocalService the ruling local service
	 */
	public void setRulingLocalService(
		com.liferay.timetracking.dayoffs.service.RulingLocalService rulingLocalService) {
		this.rulingLocalService = rulingLocalService;
	}

	/**
	 * Returns the ruling remote service.
	 *
	 * @return the ruling remote service
	 */
	public com.liferay.timetracking.dayoffs.service.RulingService getRulingService() {
		return rulingService;
	}

	/**
	 * Sets the ruling remote service.
	 *
	 * @param rulingService the ruling remote service
	 */
	public void setRulingService(
		com.liferay.timetracking.dayoffs.service.RulingService rulingService) {
		this.rulingService = rulingService;
	}

	/**
	 * Returns the ruling persistence.
	 *
	 * @return the ruling persistence
	 */
	public RulingPersistence getRulingPersistence() {
		return rulingPersistence;
	}

	/**
	 * Sets the ruling persistence.
	 *
	 * @param rulingPersistence the ruling persistence
	 */
	public void setRulingPersistence(RulingPersistence rulingPersistence) {
		this.rulingPersistence = rulingPersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.service.CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.service.CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.service.ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.service.ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.service.UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.service.UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user remote service.
	 *
	 * @return the user remote service
	 */
	public com.liferay.portal.service.UserService getUserService() {
		return userService;
	}

	/**
	 * Sets the user remote service.
	 *
	 * @param userService the user remote service
	 */
	public void setUserService(
		com.liferay.portal.service.UserService userService) {
		this.userService = userService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	public void afterPropertiesSet() {
		Class<?> clazz = getClass();

		_classLoader = clazz.getClassLoader();

		PersistedModelLocalServiceRegistryUtil.register("com.liferay.timetracking.dayoffs.model.DaysOfYear",
			daysOfYearLocalService);
	}

	public void destroy() {
		PersistedModelLocalServiceRegistryUtil.unregister(
			"com.liferay.timetracking.dayoffs.model.DaysOfYear");
	}

	/**
	 * Returns the Spring bean ID for this bean.
	 *
	 * @return the Spring bean ID for this bean
	 */
	@Override
	public String getBeanIdentifier() {
		return _beanIdentifier;
	}

	/**
	 * Sets the Spring bean ID for this bean.
	 *
	 * @param beanIdentifier the Spring bean ID for this bean
	 */
	@Override
	public void setBeanIdentifier(String beanIdentifier) {
		_beanIdentifier = beanIdentifier;
	}

	@Override
	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		if (contextClassLoader != _classLoader) {
			currentThread.setContextClassLoader(_classLoader);
		}

		try {
			return _clpInvoker.invokeMethod(name, parameterTypes, arguments);
		}
		finally {
			if (contextClassLoader != _classLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	protected Class<?> getModelClass() {
		return DaysOfYear.class;
	}

	protected String getModelClassName() {
		return DaysOfYear.class.getName();
	}

	/**
	 * Performs an SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) throws SystemException {
		try {
			DataSource dataSource = daysOfYearPersistence.getDataSource();

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql, new int[0]);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = com.liferay.timetracking.dayoffs.service.DaysOfYearLocalService.class)
	protected com.liferay.timetracking.dayoffs.service.DaysOfYearLocalService daysOfYearLocalService;
	@BeanReference(type = com.liferay.timetracking.dayoffs.service.DaysOfYearService.class)
	protected com.liferay.timetracking.dayoffs.service.DaysOfYearService daysOfYearService;
	@BeanReference(type = DaysOfYearPersistence.class)
	protected DaysOfYearPersistence daysOfYearPersistence;
	@BeanReference(type = com.liferay.timetracking.dayoffs.service.RulingLocalService.class)
	protected com.liferay.timetracking.dayoffs.service.RulingLocalService rulingLocalService;
	@BeanReference(type = com.liferay.timetracking.dayoffs.service.RulingService.class)
	protected com.liferay.timetracking.dayoffs.service.RulingService rulingService;
	@BeanReference(type = RulingPersistence.class)
	protected RulingPersistence rulingPersistence;
	@BeanReference(type = com.liferay.counter.service.CounterLocalService.class)
	protected com.liferay.counter.service.CounterLocalService counterLocalService;
	@BeanReference(type = com.liferay.portal.service.ResourceLocalService.class)
	protected com.liferay.portal.service.ResourceLocalService resourceLocalService;
	@BeanReference(type = com.liferay.portal.service.UserLocalService.class)
	protected com.liferay.portal.service.UserLocalService userLocalService;
	@BeanReference(type = com.liferay.portal.service.UserService.class)
	protected com.liferay.portal.service.UserService userService;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private String _beanIdentifier;
	private ClassLoader _classLoader;
	private DaysOfYearLocalServiceClpInvoker _clpInvoker = new DaysOfYearLocalServiceClpInvoker();
}