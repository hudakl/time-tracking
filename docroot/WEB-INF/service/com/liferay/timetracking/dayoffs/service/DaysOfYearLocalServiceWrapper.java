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

package com.liferay.timetracking.dayoffs.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link DaysOfYearLocalService}.
 *
 * @author Laszlo Hudak
 * @see DaysOfYearLocalService
 * @generated
 */
public class DaysOfYearLocalServiceWrapper implements DaysOfYearLocalService,
	ServiceWrapper<DaysOfYearLocalService> {
	public DaysOfYearLocalServiceWrapper(
		DaysOfYearLocalService daysOfYearLocalService) {
		_daysOfYearLocalService = daysOfYearLocalService;
	}

	/**
	* Adds the days of year to the database. Also notifies the appropriate model listeners.
	*
	* @param daysOfYear the days of year
	* @return the days of year that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.timetracking.dayoffs.model.DaysOfYear addDaysOfYear(
		com.liferay.timetracking.dayoffs.model.DaysOfYear daysOfYear)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _daysOfYearLocalService.addDaysOfYear(daysOfYear);
	}

	/**
	* Creates a new days of year with the primary key. Does not add the days of year to the database.
	*
	* @param dayOfYearId the primary key for the new days of year
	* @return the new days of year
	*/
	@Override
	public com.liferay.timetracking.dayoffs.model.DaysOfYear createDaysOfYear(
		long dayOfYearId) {
		return _daysOfYearLocalService.createDaysOfYear(dayOfYearId);
	}

	/**
	* Deletes the days of year with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dayOfYearId the primary key of the days of year
	* @return the days of year that was removed
	* @throws PortalException if a days of year with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.timetracking.dayoffs.model.DaysOfYear deleteDaysOfYear(
		long dayOfYearId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _daysOfYearLocalService.deleteDaysOfYear(dayOfYearId);
	}

	/**
	* Deletes the days of year from the database. Also notifies the appropriate model listeners.
	*
	* @param daysOfYear the days of year
	* @return the days of year that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.timetracking.dayoffs.model.DaysOfYear deleteDaysOfYear(
		com.liferay.timetracking.dayoffs.model.DaysOfYear daysOfYear)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _daysOfYearLocalService.deleteDaysOfYear(daysOfYear);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _daysOfYearLocalService.dynamicQuery();
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
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _daysOfYearLocalService.dynamicQuery(dynamicQuery);
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
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _daysOfYearLocalService.dynamicQuery(dynamicQuery, start, end);
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
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _daysOfYearLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _daysOfYearLocalService.dynamicQueryCount(dynamicQuery);
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
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _daysOfYearLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.timetracking.dayoffs.model.DaysOfYear fetchDaysOfYear(
		long dayOfYearId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _daysOfYearLocalService.fetchDaysOfYear(dayOfYearId);
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
	public com.liferay.timetracking.dayoffs.model.DaysOfYear getDaysOfYear(
		long dayOfYearId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _daysOfYearLocalService.getDaysOfYear(dayOfYearId);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _daysOfYearLocalService.getPersistedModel(primaryKeyObj);
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
	public java.util.List<com.liferay.timetracking.dayoffs.model.DaysOfYear> getDaysOfYears(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _daysOfYearLocalService.getDaysOfYears(start, end);
	}

	/**
	* Returns the number of days of years.
	*
	* @return the number of days of years
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getDaysOfYearsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _daysOfYearLocalService.getDaysOfYearsCount();
	}

	/**
	* Updates the days of year in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param daysOfYear the days of year
	* @return the days of year that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.timetracking.dayoffs.model.DaysOfYear updateDaysOfYear(
		com.liferay.timetracking.dayoffs.model.DaysOfYear daysOfYear)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _daysOfYearLocalService.updateDaysOfYear(daysOfYear);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _daysOfYearLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_daysOfYearLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _daysOfYearLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public DaysOfYearLocalService getWrappedDaysOfYearLocalService() {
		return _daysOfYearLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedDaysOfYearLocalService(
		DaysOfYearLocalService daysOfYearLocalService) {
		_daysOfYearLocalService = daysOfYearLocalService;
	}

	@Override
	public DaysOfYearLocalService getWrappedService() {
		return _daysOfYearLocalService;
	}

	@Override
	public void setWrappedService(DaysOfYearLocalService daysOfYearLocalService) {
		_daysOfYearLocalService = daysOfYearLocalService;
	}

	private DaysOfYearLocalService _daysOfYearLocalService;
}