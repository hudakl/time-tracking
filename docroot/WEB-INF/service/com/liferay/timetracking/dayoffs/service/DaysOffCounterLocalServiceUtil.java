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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for DaysOffCounter. This utility wraps
 * {@link com.liferay.timetracking.dayoffs.service.impl.DaysOffCounterLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Laszlo Hudak
 * @see DaysOffCounterLocalService
 * @see com.liferay.timetracking.dayoffs.service.base.DaysOffCounterLocalServiceBaseImpl
 * @see com.liferay.timetracking.dayoffs.service.impl.DaysOffCounterLocalServiceImpl
 * @generated
 */
public class DaysOffCounterLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.timetracking.dayoffs.service.impl.DaysOffCounterLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the days off counter to the database. Also notifies the appropriate model listeners.
	*
	* @param daysOffCounter the days off counter
	* @return the days off counter that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.timetracking.dayoffs.model.DaysOffCounter addDaysOffCounter(
		com.liferay.timetracking.dayoffs.model.DaysOffCounter daysOffCounter)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addDaysOffCounter(daysOffCounter);
	}

	/**
	* Creates a new days off counter with the primary key. Does not add the days off counter to the database.
	*
	* @param dayOffCounterId the primary key for the new days off counter
	* @return the new days off counter
	*/
	public static com.liferay.timetracking.dayoffs.model.DaysOffCounter createDaysOffCounter(
		long dayOffCounterId) {
		return getService().createDaysOffCounter(dayOffCounterId);
	}

	/**
	* Deletes the days off counter with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dayOffCounterId the primary key of the days off counter
	* @return the days off counter that was removed
	* @throws PortalException if a days off counter with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.timetracking.dayoffs.model.DaysOffCounter deleteDaysOffCounter(
		long dayOffCounterId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteDaysOffCounter(dayOffCounterId);
	}

	/**
	* Deletes the days off counter from the database. Also notifies the appropriate model listeners.
	*
	* @param daysOffCounter the days off counter
	* @return the days off counter that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.timetracking.dayoffs.model.DaysOffCounter deleteDaysOffCounter(
		com.liferay.timetracking.dayoffs.model.DaysOffCounter daysOffCounter)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteDaysOffCounter(daysOffCounter);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.timetracking.dayoffs.model.impl.DaysOffCounterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.timetracking.dayoffs.model.impl.DaysOffCounterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.liferay.timetracking.dayoffs.model.DaysOffCounter fetchDaysOffCounter(
		long dayOffCounterId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchDaysOffCounter(dayOffCounterId);
	}

	/**
	* Returns the days off counter with the primary key.
	*
	* @param dayOffCounterId the primary key of the days off counter
	* @return the days off counter
	* @throws PortalException if a days off counter with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.timetracking.dayoffs.model.DaysOffCounter getDaysOffCounter(
		long dayOffCounterId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getDaysOffCounter(dayOffCounterId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the days off counters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.timetracking.dayoffs.model.impl.DaysOffCounterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of days off counters
	* @param end the upper bound of the range of days off counters (not inclusive)
	* @return the range of days off counters
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.timetracking.dayoffs.model.DaysOffCounter> getDaysOffCounters(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getDaysOffCounters(start, end);
	}

	/**
	* Returns the number of days off counters.
	*
	* @return the number of days off counters
	* @throws SystemException if a system exception occurred
	*/
	public static int getDaysOffCountersCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getDaysOffCountersCount();
	}

	/**
	* Updates the days off counter in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param daysOffCounter the days off counter
	* @return the days off counter that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.timetracking.dayoffs.model.DaysOffCounter updateDaysOffCounter(
		com.liferay.timetracking.dayoffs.model.DaysOffCounter daysOffCounter)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateDaysOffCounter(daysOffCounter);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	/**
	* Upon taking a day off, this method registers how many more days can the
	* user take off or throws Exception if there are no more days left.
	*
	* @param userId the id of the user who approves the days off
	* @param workerUserId the id of the user who wants to take the days off
	* @param ruleId the id of the rule which determines the type of the days
	off
	* @param year which year does the user want to take off the days
	* @return the updated registry entry
	* @throws PortalException if a portal exception occurred
	* @throws SystemException if a system exception occurred
	* @throws NoMoreDaysLeftException if the user doesn't have any more days
	to take off
	*/
	public static com.liferay.timetracking.dayoffs.model.DaysOffCounter takeOffDays(
		long userId, long workerUserId, long ruleId, int year, int numberOfDays)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			com.liferay.timetracking.dayoffs.NoMoreDaysLeftException {
		return getService()
				   .takeOffDays(userId, workerUserId, ruleId, year, numberOfDays);
	}

	public static void clearService() {
		_service = null;
	}

	public static DaysOffCounterLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					DaysOffCounterLocalService.class.getName());

			if (invokableLocalService instanceof DaysOffCounterLocalService) {
				_service = (DaysOffCounterLocalService)invokableLocalService;
			}
			else {
				_service = new DaysOffCounterLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(DaysOffCounterLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(DaysOffCounterLocalService service) {
	}

	private static DaysOffCounterLocalService _service;
}