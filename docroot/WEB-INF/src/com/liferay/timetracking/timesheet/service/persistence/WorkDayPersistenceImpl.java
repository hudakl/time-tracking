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

package com.liferay.timetracking.timesheet.service.persistence;

import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.CalendarUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.security.permission.InlineSQLHelperUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.timetracking.timesheet.NoSuchWorkDayException;
import com.liferay.timetracking.timesheet.model.WorkDay;
import com.liferay.timetracking.timesheet.model.impl.WorkDayImpl;
import com.liferay.timetracking.timesheet.model.impl.WorkDayModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * The persistence implementation for the work day service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Laszlo Hudak, Istvan Sajtos
 * @see WorkDayPersistence
 * @see WorkDayUtil
 * @generated
 */
public class WorkDayPersistenceImpl extends BasePersistenceImpl<WorkDay>
	implements WorkDayPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link WorkDayUtil} to access the work day persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = WorkDayImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(WorkDayModelImpl.ENTITY_CACHE_ENABLED,
			WorkDayModelImpl.FINDER_CACHE_ENABLED, WorkDayImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(WorkDayModelImpl.ENTITY_CACHE_ENABLED,
			WorkDayModelImpl.FINDER_CACHE_ENABLED, WorkDayImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(WorkDayModelImpl.ENTITY_CACHE_ENABLED,
			WorkDayModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(WorkDayModelImpl.ENTITY_CACHE_ENABLED,
			WorkDayModelImpl.FINDER_CACHE_ENABLED, WorkDayImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(WorkDayModelImpl.ENTITY_CACHE_ENABLED,
			WorkDayModelImpl.FINDER_CACHE_ENABLED, WorkDayImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
			new String[] { Long.class.getName() },
			WorkDayModelImpl.COMPANYID_COLUMN_BITMASK |
			WorkDayModelImpl.STARTTIME_COLUMN_BITMASK |
			WorkDayModelImpl.ENDTIME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(WorkDayModelImpl.ENTITY_CACHE_ENABLED,
			WorkDayModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the work daies where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching work daies
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WorkDay> findByCompanyId(long companyId)
		throws SystemException {
		return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the work daies where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.timetracking.timesheet.model.impl.WorkDayModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of work daies
	 * @param end the upper bound of the range of work daies (not inclusive)
	 * @return the range of matching work daies
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WorkDay> findByCompanyId(long companyId, int start, int end)
		throws SystemException {
		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the work daies where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.timetracking.timesheet.model.impl.WorkDayModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of work daies
	 * @param end the upper bound of the range of work daies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching work daies
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WorkDay> findByCompanyId(long companyId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID;
			finderArgs = new Object[] { companyId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID;
			finderArgs = new Object[] { companyId, start, end, orderByComparator };
		}

		List<WorkDay> list = (List<WorkDay>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (WorkDay workDay : list) {
				if ((companyId != workDay.getCompanyId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_WORKDAY_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(WorkDayModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (!pagination) {
					list = (List<WorkDay>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<WorkDay>(list);
				}
				else {
					list = (List<WorkDay>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first work day in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching work day
	 * @throws com.liferay.timetracking.timesheet.NoSuchWorkDayException if a matching work day could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkDay findByCompanyId_First(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchWorkDayException, SystemException {
		WorkDay workDay = fetchByCompanyId_First(companyId, orderByComparator);

		if (workDay != null) {
			return workDay;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchWorkDayException(msg.toString());
	}

	/**
	 * Returns the first work day in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching work day, or <code>null</code> if a matching work day could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkDay fetchByCompanyId_First(long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		List<WorkDay> list = findByCompanyId(companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last work day in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching work day
	 * @throws com.liferay.timetracking.timesheet.NoSuchWorkDayException if a matching work day could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkDay findByCompanyId_Last(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchWorkDayException, SystemException {
		WorkDay workDay = fetchByCompanyId_Last(companyId, orderByComparator);

		if (workDay != null) {
			return workDay;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchWorkDayException(msg.toString());
	}

	/**
	 * Returns the last work day in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching work day, or <code>null</code> if a matching work day could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkDay fetchByCompanyId_Last(long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByCompanyId(companyId);

		if (count == 0) {
			return null;
		}

		List<WorkDay> list = findByCompanyId(companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the work daies before and after the current work day in the ordered set where companyId = &#63;.
	 *
	 * @param workDayId the primary key of the current work day
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next work day
	 * @throws com.liferay.timetracking.timesheet.NoSuchWorkDayException if a work day with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkDay[] findByCompanyId_PrevAndNext(long workDayId,
		long companyId, OrderByComparator orderByComparator)
		throws NoSuchWorkDayException, SystemException {
		WorkDay workDay = findByPrimaryKey(workDayId);

		Session session = null;

		try {
			session = openSession();

			WorkDay[] array = new WorkDayImpl[3];

			array[0] = getByCompanyId_PrevAndNext(session, workDay, companyId,
					orderByComparator, true);

			array[1] = workDay;

			array[2] = getByCompanyId_PrevAndNext(session, workDay, companyId,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected WorkDay getByCompanyId_PrevAndNext(Session session,
		WorkDay workDay, long companyId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_WORKDAY_WHERE);

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(WorkDayModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(workDay);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<WorkDay> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the work daies that the user has permission to view where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching work daies that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WorkDay> filterFindByCompanyId(long companyId)
		throws SystemException {
		return filterFindByCompanyId(companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the work daies that the user has permission to view where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.timetracking.timesheet.model.impl.WorkDayModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of work daies
	 * @param end the upper bound of the range of work daies (not inclusive)
	 * @return the range of matching work daies that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WorkDay> filterFindByCompanyId(long companyId, int start,
		int end) throws SystemException {
		return filterFindByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the work daies that the user has permissions to view where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.timetracking.timesheet.model.impl.WorkDayModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of work daies
	 * @param end the upper bound of the range of work daies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching work daies that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WorkDay> filterFindByCompanyId(long companyId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByCompanyId(companyId, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(3 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_WORKDAY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_WORKDAY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_WORKDAY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator, true);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(WorkDayModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(WorkDayModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				WorkDay.class.getName(), _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, WorkDayImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, WorkDayImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			return (List<WorkDay>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the work daies before and after the current work day in the ordered set of work daies that the user has permission to view where companyId = &#63;.
	 *
	 * @param workDayId the primary key of the current work day
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next work day
	 * @throws com.liferay.timetracking.timesheet.NoSuchWorkDayException if a work day with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkDay[] filterFindByCompanyId_PrevAndNext(long workDayId,
		long companyId, OrderByComparator orderByComparator)
		throws NoSuchWorkDayException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByCompanyId_PrevAndNext(workDayId, companyId,
				orderByComparator);
		}

		WorkDay workDay = findByPrimaryKey(workDayId);

		Session session = null;

		try {
			session = openSession();

			WorkDay[] array = new WorkDayImpl[3];

			array[0] = filterGetByCompanyId_PrevAndNext(session, workDay,
					companyId, orderByComparator, true);

			array[1] = workDay;

			array[2] = filterGetByCompanyId_PrevAndNext(session, workDay,
					companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected WorkDay filterGetByCompanyId_PrevAndNext(Session session,
		WorkDay workDay, long companyId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_WORKDAY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_WORKDAY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_WORKDAY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(WorkDayModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(WorkDayModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				WorkDay.class.getName(), _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, WorkDayImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, WorkDayImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(workDay);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<WorkDay> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the work daies where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByCompanyId(long companyId) throws SystemException {
		for (WorkDay workDay : findByCompanyId(companyId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(workDay);
		}
	}

	/**
	 * Returns the number of work daies where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching work daies
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByCompanyId(long companyId) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COMPANYID;

		Object[] finderArgs = new Object[] { companyId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_WORKDAY_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of work daies that the user has permission to view where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching work daies that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int filterCountByCompanyId(long companyId) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByCompanyId(companyId);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_WORKDAY_WHERE);

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				WorkDay.class.getName(), _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "workDay.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_S_E_U = new FinderPath(WorkDayModelImpl.ENTITY_CACHE_ENABLED,
			WorkDayModelImpl.FINDER_CACHE_ENABLED, WorkDayImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByS_E_U",
			new String[] {
				Date.class.getName(), Date.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_S_E_U = new FinderPath(WorkDayModelImpl.ENTITY_CACHE_ENABLED,
			WorkDayModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByS_E_U",
			new String[] {
				Date.class.getName(), Date.class.getName(), Long.class.getName()
			});

	/**
	 * Returns all the work daies where startTime &gt; &#63; and endTime &lt; &#63; and userId = &#63;.
	 *
	 * @param startTime the start time
	 * @param endTime the end time
	 * @param userId the user ID
	 * @return the matching work daies
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WorkDay> findByS_E_U(Date startTime, Date endTime, long userId)
		throws SystemException {
		return findByS_E_U(startTime, endTime, userId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the work daies where startTime &gt; &#63; and endTime &lt; &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.timetracking.timesheet.model.impl.WorkDayModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param startTime the start time
	 * @param endTime the end time
	 * @param userId the user ID
	 * @param start the lower bound of the range of work daies
	 * @param end the upper bound of the range of work daies (not inclusive)
	 * @return the range of matching work daies
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WorkDay> findByS_E_U(Date startTime, Date endTime, long userId,
		int start, int end) throws SystemException {
		return findByS_E_U(startTime, endTime, userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the work daies where startTime &gt; &#63; and endTime &lt; &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.timetracking.timesheet.model.impl.WorkDayModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param startTime the start time
	 * @param endTime the end time
	 * @param userId the user ID
	 * @param start the lower bound of the range of work daies
	 * @param end the upper bound of the range of work daies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching work daies
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WorkDay> findByS_E_U(Date startTime, Date endTime, long userId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_S_E_U;
		finderArgs = new Object[] {
				startTime, endTime, userId,
				
				start, end, orderByComparator
			};

		List<WorkDay> list = (List<WorkDay>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (WorkDay workDay : list) {
				if ((startTime.getTime() >= workDay.getStartTime().getTime()) ||
						(endTime.getTime() <= workDay.getEndTime().getTime()) ||
						(userId != workDay.getUserId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_WORKDAY_WHERE);

			boolean bindStartTime = false;

			if (startTime == null) {
				query.append(_FINDER_COLUMN_S_E_U_STARTTIME_1);
			}
			else {
				bindStartTime = true;

				query.append(_FINDER_COLUMN_S_E_U_STARTTIME_2);
			}

			boolean bindEndTime = false;

			if (endTime == null) {
				query.append(_FINDER_COLUMN_S_E_U_ENDTIME_1);
			}
			else {
				bindEndTime = true;

				query.append(_FINDER_COLUMN_S_E_U_ENDTIME_2);
			}

			query.append(_FINDER_COLUMN_S_E_U_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(WorkDayModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindStartTime) {
					qPos.add(CalendarUtil.getTimestamp(startTime));
				}

				if (bindEndTime) {
					qPos.add(CalendarUtil.getTimestamp(endTime));
				}

				qPos.add(userId);

				if (!pagination) {
					list = (List<WorkDay>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<WorkDay>(list);
				}
				else {
					list = (List<WorkDay>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first work day in the ordered set where startTime &gt; &#63; and endTime &lt; &#63; and userId = &#63;.
	 *
	 * @param startTime the start time
	 * @param endTime the end time
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching work day
	 * @throws com.liferay.timetracking.timesheet.NoSuchWorkDayException if a matching work day could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkDay findByS_E_U_First(Date startTime, Date endTime, long userId,
		OrderByComparator orderByComparator)
		throws NoSuchWorkDayException, SystemException {
		WorkDay workDay = fetchByS_E_U_First(startTime, endTime, userId,
				orderByComparator);

		if (workDay != null) {
			return workDay;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("startTime=");
		msg.append(startTime);

		msg.append(", endTime=");
		msg.append(endTime);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchWorkDayException(msg.toString());
	}

	/**
	 * Returns the first work day in the ordered set where startTime &gt; &#63; and endTime &lt; &#63; and userId = &#63;.
	 *
	 * @param startTime the start time
	 * @param endTime the end time
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching work day, or <code>null</code> if a matching work day could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkDay fetchByS_E_U_First(Date startTime, Date endTime,
		long userId, OrderByComparator orderByComparator)
		throws SystemException {
		List<WorkDay> list = findByS_E_U(startTime, endTime, userId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last work day in the ordered set where startTime &gt; &#63; and endTime &lt; &#63; and userId = &#63;.
	 *
	 * @param startTime the start time
	 * @param endTime the end time
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching work day
	 * @throws com.liferay.timetracking.timesheet.NoSuchWorkDayException if a matching work day could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkDay findByS_E_U_Last(Date startTime, Date endTime, long userId,
		OrderByComparator orderByComparator)
		throws NoSuchWorkDayException, SystemException {
		WorkDay workDay = fetchByS_E_U_Last(startTime, endTime, userId,
				orderByComparator);

		if (workDay != null) {
			return workDay;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("startTime=");
		msg.append(startTime);

		msg.append(", endTime=");
		msg.append(endTime);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchWorkDayException(msg.toString());
	}

	/**
	 * Returns the last work day in the ordered set where startTime &gt; &#63; and endTime &lt; &#63; and userId = &#63;.
	 *
	 * @param startTime the start time
	 * @param endTime the end time
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching work day, or <code>null</code> if a matching work day could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkDay fetchByS_E_U_Last(Date startTime, Date endTime, long userId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByS_E_U(startTime, endTime, userId);

		if (count == 0) {
			return null;
		}

		List<WorkDay> list = findByS_E_U(startTime, endTime, userId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the work daies before and after the current work day in the ordered set where startTime &gt; &#63; and endTime &lt; &#63; and userId = &#63;.
	 *
	 * @param workDayId the primary key of the current work day
	 * @param startTime the start time
	 * @param endTime the end time
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next work day
	 * @throws com.liferay.timetracking.timesheet.NoSuchWorkDayException if a work day with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkDay[] findByS_E_U_PrevAndNext(long workDayId, Date startTime,
		Date endTime, long userId, OrderByComparator orderByComparator)
		throws NoSuchWorkDayException, SystemException {
		WorkDay workDay = findByPrimaryKey(workDayId);

		Session session = null;

		try {
			session = openSession();

			WorkDay[] array = new WorkDayImpl[3];

			array[0] = getByS_E_U_PrevAndNext(session, workDay, startTime,
					endTime, userId, orderByComparator, true);

			array[1] = workDay;

			array[2] = getByS_E_U_PrevAndNext(session, workDay, startTime,
					endTime, userId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected WorkDay getByS_E_U_PrevAndNext(Session session, WorkDay workDay,
		Date startTime, Date endTime, long userId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_WORKDAY_WHERE);

		boolean bindStartTime = false;

		if (startTime == null) {
			query.append(_FINDER_COLUMN_S_E_U_STARTTIME_1);
		}
		else {
			bindStartTime = true;

			query.append(_FINDER_COLUMN_S_E_U_STARTTIME_2);
		}

		boolean bindEndTime = false;

		if (endTime == null) {
			query.append(_FINDER_COLUMN_S_E_U_ENDTIME_1);
		}
		else {
			bindEndTime = true;

			query.append(_FINDER_COLUMN_S_E_U_ENDTIME_2);
		}

		query.append(_FINDER_COLUMN_S_E_U_USERID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(WorkDayModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindStartTime) {
			qPos.add(CalendarUtil.getTimestamp(startTime));
		}

		if (bindEndTime) {
			qPos.add(CalendarUtil.getTimestamp(endTime));
		}

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(workDay);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<WorkDay> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the work daies that the user has permission to view where startTime &gt; &#63; and endTime &lt; &#63; and userId = &#63;.
	 *
	 * @param startTime the start time
	 * @param endTime the end time
	 * @param userId the user ID
	 * @return the matching work daies that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WorkDay> filterFindByS_E_U(Date startTime, Date endTime,
		long userId) throws SystemException {
		return filterFindByS_E_U(startTime, endTime, userId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the work daies that the user has permission to view where startTime &gt; &#63; and endTime &lt; &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.timetracking.timesheet.model.impl.WorkDayModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param startTime the start time
	 * @param endTime the end time
	 * @param userId the user ID
	 * @param start the lower bound of the range of work daies
	 * @param end the upper bound of the range of work daies (not inclusive)
	 * @return the range of matching work daies that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WorkDay> filterFindByS_E_U(Date startTime, Date endTime,
		long userId, int start, int end) throws SystemException {
		return filterFindByS_E_U(startTime, endTime, userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the work daies that the user has permissions to view where startTime &gt; &#63; and endTime &lt; &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.timetracking.timesheet.model.impl.WorkDayModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param startTime the start time
	 * @param endTime the end time
	 * @param userId the user ID
	 * @param start the lower bound of the range of work daies
	 * @param end the upper bound of the range of work daies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching work daies that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WorkDay> filterFindByS_E_U(Date startTime, Date endTime,
		long userId, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByS_E_U(startTime, endTime, userId, start, end,
				orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_WORKDAY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_WORKDAY_NO_INLINE_DISTINCT_WHERE_1);
		}

		boolean bindStartTime = false;

		if (startTime == null) {
			query.append(_FINDER_COLUMN_S_E_U_STARTTIME_1);
		}
		else {
			bindStartTime = true;

			query.append(_FINDER_COLUMN_S_E_U_STARTTIME_2);
		}

		boolean bindEndTime = false;

		if (endTime == null) {
			query.append(_FINDER_COLUMN_S_E_U_ENDTIME_1);
		}
		else {
			bindEndTime = true;

			query.append(_FINDER_COLUMN_S_E_U_ENDTIME_2);
		}

		query.append(_FINDER_COLUMN_S_E_U_USERID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_WORKDAY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator, true);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(WorkDayModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(WorkDayModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				WorkDay.class.getName(), _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, WorkDayImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, WorkDayImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			if (bindStartTime) {
				qPos.add(CalendarUtil.getTimestamp(startTime));
			}

			if (bindEndTime) {
				qPos.add(CalendarUtil.getTimestamp(endTime));
			}

			qPos.add(userId);

			return (List<WorkDay>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the work daies before and after the current work day in the ordered set of work daies that the user has permission to view where startTime &gt; &#63; and endTime &lt; &#63; and userId = &#63;.
	 *
	 * @param workDayId the primary key of the current work day
	 * @param startTime the start time
	 * @param endTime the end time
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next work day
	 * @throws com.liferay.timetracking.timesheet.NoSuchWorkDayException if a work day with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkDay[] filterFindByS_E_U_PrevAndNext(long workDayId,
		Date startTime, Date endTime, long userId,
		OrderByComparator orderByComparator)
		throws NoSuchWorkDayException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByS_E_U_PrevAndNext(workDayId, startTime, endTime,
				userId, orderByComparator);
		}

		WorkDay workDay = findByPrimaryKey(workDayId);

		Session session = null;

		try {
			session = openSession();

			WorkDay[] array = new WorkDayImpl[3];

			array[0] = filterGetByS_E_U_PrevAndNext(session, workDay,
					startTime, endTime, userId, orderByComparator, true);

			array[1] = workDay;

			array[2] = filterGetByS_E_U_PrevAndNext(session, workDay,
					startTime, endTime, userId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected WorkDay filterGetByS_E_U_PrevAndNext(Session session,
		WorkDay workDay, Date startTime, Date endTime, long userId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_WORKDAY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_WORKDAY_NO_INLINE_DISTINCT_WHERE_1);
		}

		boolean bindStartTime = false;

		if (startTime == null) {
			query.append(_FINDER_COLUMN_S_E_U_STARTTIME_1);
		}
		else {
			bindStartTime = true;

			query.append(_FINDER_COLUMN_S_E_U_STARTTIME_2);
		}

		boolean bindEndTime = false;

		if (endTime == null) {
			query.append(_FINDER_COLUMN_S_E_U_ENDTIME_1);
		}
		else {
			bindEndTime = true;

			query.append(_FINDER_COLUMN_S_E_U_ENDTIME_2);
		}

		query.append(_FINDER_COLUMN_S_E_U_USERID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_WORKDAY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(WorkDayModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(WorkDayModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				WorkDay.class.getName(), _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, WorkDayImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, WorkDayImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindStartTime) {
			qPos.add(CalendarUtil.getTimestamp(startTime));
		}

		if (bindEndTime) {
			qPos.add(CalendarUtil.getTimestamp(endTime));
		}

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(workDay);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<WorkDay> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the work daies where startTime &gt; &#63; and endTime &lt; &#63; and userId = &#63; from the database.
	 *
	 * @param startTime the start time
	 * @param endTime the end time
	 * @param userId the user ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByS_E_U(Date startTime, Date endTime, long userId)
		throws SystemException {
		for (WorkDay workDay : findByS_E_U(startTime, endTime, userId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(workDay);
		}
	}

	/**
	 * Returns the number of work daies where startTime &gt; &#63; and endTime &lt; &#63; and userId = &#63;.
	 *
	 * @param startTime the start time
	 * @param endTime the end time
	 * @param userId the user ID
	 * @return the number of matching work daies
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByS_E_U(Date startTime, Date endTime, long userId)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_S_E_U;

		Object[] finderArgs = new Object[] { startTime, endTime, userId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_WORKDAY_WHERE);

			boolean bindStartTime = false;

			if (startTime == null) {
				query.append(_FINDER_COLUMN_S_E_U_STARTTIME_1);
			}
			else {
				bindStartTime = true;

				query.append(_FINDER_COLUMN_S_E_U_STARTTIME_2);
			}

			boolean bindEndTime = false;

			if (endTime == null) {
				query.append(_FINDER_COLUMN_S_E_U_ENDTIME_1);
			}
			else {
				bindEndTime = true;

				query.append(_FINDER_COLUMN_S_E_U_ENDTIME_2);
			}

			query.append(_FINDER_COLUMN_S_E_U_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindStartTime) {
					qPos.add(CalendarUtil.getTimestamp(startTime));
				}

				if (bindEndTime) {
					qPos.add(CalendarUtil.getTimestamp(endTime));
				}

				qPos.add(userId);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of work daies that the user has permission to view where startTime &gt; &#63; and endTime &lt; &#63; and userId = &#63;.
	 *
	 * @param startTime the start time
	 * @param endTime the end time
	 * @param userId the user ID
	 * @return the number of matching work daies that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int filterCountByS_E_U(Date startTime, Date endTime, long userId)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByS_E_U(startTime, endTime, userId);
		}

		StringBundler query = new StringBundler(4);

		query.append(_FILTER_SQL_COUNT_WORKDAY_WHERE);

		boolean bindStartTime = false;

		if (startTime == null) {
			query.append(_FINDER_COLUMN_S_E_U_STARTTIME_1);
		}
		else {
			bindStartTime = true;

			query.append(_FINDER_COLUMN_S_E_U_STARTTIME_2);
		}

		boolean bindEndTime = false;

		if (endTime == null) {
			query.append(_FINDER_COLUMN_S_E_U_ENDTIME_1);
		}
		else {
			bindEndTime = true;

			query.append(_FINDER_COLUMN_S_E_U_ENDTIME_2);
		}

		query.append(_FINDER_COLUMN_S_E_U_USERID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				WorkDay.class.getName(), _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			if (bindStartTime) {
				qPos.add(CalendarUtil.getTimestamp(startTime));
			}

			if (bindEndTime) {
				qPos.add(CalendarUtil.getTimestamp(endTime));
			}

			qPos.add(userId);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _FINDER_COLUMN_S_E_U_STARTTIME_1 = "workDay.startTime > NULL AND ";
	private static final String _FINDER_COLUMN_S_E_U_STARTTIME_2 = "workDay.startTime > ? AND ";
	private static final String _FINDER_COLUMN_S_E_U_ENDTIME_1 = "workDay.endTime < NULL AND ";
	private static final String _FINDER_COLUMN_S_E_U_ENDTIME_2 = "workDay.endTime < ? AND ";
	private static final String _FINDER_COLUMN_S_E_U_USERID_2 = "workDay.userId = ?";

	public WorkDayPersistenceImpl() {
		setModelClass(WorkDay.class);
	}

	/**
	 * Caches the work day in the entity cache if it is enabled.
	 *
	 * @param workDay the work day
	 */
	@Override
	public void cacheResult(WorkDay workDay) {
		EntityCacheUtil.putResult(WorkDayModelImpl.ENTITY_CACHE_ENABLED,
			WorkDayImpl.class, workDay.getPrimaryKey(), workDay);

		workDay.resetOriginalValues();
	}

	/**
	 * Caches the work daies in the entity cache if it is enabled.
	 *
	 * @param workDaies the work daies
	 */
	@Override
	public void cacheResult(List<WorkDay> workDaies) {
		for (WorkDay workDay : workDaies) {
			if (EntityCacheUtil.getResult(
						WorkDayModelImpl.ENTITY_CACHE_ENABLED,
						WorkDayImpl.class, workDay.getPrimaryKey()) == null) {
				cacheResult(workDay);
			}
			else {
				workDay.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all work daies.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(WorkDayImpl.class.getName());
		}

		EntityCacheUtil.clearCache(WorkDayImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the work day.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(WorkDay workDay) {
		EntityCacheUtil.removeResult(WorkDayModelImpl.ENTITY_CACHE_ENABLED,
			WorkDayImpl.class, workDay.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<WorkDay> workDaies) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (WorkDay workDay : workDaies) {
			EntityCacheUtil.removeResult(WorkDayModelImpl.ENTITY_CACHE_ENABLED,
				WorkDayImpl.class, workDay.getPrimaryKey());
		}
	}

	/**
	 * Creates a new work day with the primary key. Does not add the work day to the database.
	 *
	 * @param workDayId the primary key for the new work day
	 * @return the new work day
	 */
	@Override
	public WorkDay create(long workDayId) {
		WorkDay workDay = new WorkDayImpl();

		workDay.setNew(true);
		workDay.setPrimaryKey(workDayId);

		return workDay;
	}

	/**
	 * Removes the work day with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param workDayId the primary key of the work day
	 * @return the work day that was removed
	 * @throws com.liferay.timetracking.timesheet.NoSuchWorkDayException if a work day with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkDay remove(long workDayId)
		throws NoSuchWorkDayException, SystemException {
		return remove((Serializable)workDayId);
	}

	/**
	 * Removes the work day with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the work day
	 * @return the work day that was removed
	 * @throws com.liferay.timetracking.timesheet.NoSuchWorkDayException if a work day with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkDay remove(Serializable primaryKey)
		throws NoSuchWorkDayException, SystemException {
		Session session = null;

		try {
			session = openSession();

			WorkDay workDay = (WorkDay)session.get(WorkDayImpl.class, primaryKey);

			if (workDay == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchWorkDayException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(workDay);
		}
		catch (NoSuchWorkDayException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected WorkDay removeImpl(WorkDay workDay) throws SystemException {
		workDay = toUnwrappedModel(workDay);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(workDay)) {
				workDay = (WorkDay)session.get(WorkDayImpl.class,
						workDay.getPrimaryKeyObj());
			}

			if (workDay != null) {
				session.delete(workDay);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (workDay != null) {
			clearCache(workDay);
		}

		return workDay;
	}

	@Override
	public WorkDay updateImpl(
		com.liferay.timetracking.timesheet.model.WorkDay workDay)
		throws SystemException {
		workDay = toUnwrappedModel(workDay);

		boolean isNew = workDay.isNew();

		WorkDayModelImpl workDayModelImpl = (WorkDayModelImpl)workDay;

		Session session = null;

		try {
			session = openSession();

			if (workDay.isNew()) {
				session.save(workDay);

				workDay.setNew(false);
			}
			else {
				session.merge(workDay);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !WorkDayModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((workDayModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						workDayModelImpl.getOriginalCompanyId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);

				args = new Object[] { workDayModelImpl.getCompanyId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);
			}
		}

		EntityCacheUtil.putResult(WorkDayModelImpl.ENTITY_CACHE_ENABLED,
			WorkDayImpl.class, workDay.getPrimaryKey(), workDay);

		return workDay;
	}

	protected WorkDay toUnwrappedModel(WorkDay workDay) {
		if (workDay instanceof WorkDayImpl) {
			return workDay;
		}

		WorkDayImpl workDayImpl = new WorkDayImpl();

		workDayImpl.setNew(workDay.isNew());
		workDayImpl.setPrimaryKey(workDay.getPrimaryKey());

		workDayImpl.setWorkDayId(workDay.getWorkDayId());
		workDayImpl.setCompanyId(workDay.getCompanyId());
		workDayImpl.setUserId(workDay.getUserId());
		workDayImpl.setUserName(workDay.getUserName());
		workDayImpl.setCreateDate(workDay.getCreateDate());
		workDayImpl.setModifiedDate(workDay.getModifiedDate());
		workDayImpl.setDayOfYearId(workDay.getDayOfYearId());
		workDayImpl.setStartTime(workDay.getStartTime());
		workDayImpl.setEndTime(workDay.getEndTime());
		workDayImpl.setBreak_(workDay.getBreak_());

		return workDayImpl;
	}

	/**
	 * Returns the work day with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the work day
	 * @return the work day
	 * @throws com.liferay.timetracking.timesheet.NoSuchWorkDayException if a work day with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkDay findByPrimaryKey(Serializable primaryKey)
		throws NoSuchWorkDayException, SystemException {
		WorkDay workDay = fetchByPrimaryKey(primaryKey);

		if (workDay == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchWorkDayException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return workDay;
	}

	/**
	 * Returns the work day with the primary key or throws a {@link com.liferay.timetracking.timesheet.NoSuchWorkDayException} if it could not be found.
	 *
	 * @param workDayId the primary key of the work day
	 * @return the work day
	 * @throws com.liferay.timetracking.timesheet.NoSuchWorkDayException if a work day with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkDay findByPrimaryKey(long workDayId)
		throws NoSuchWorkDayException, SystemException {
		return findByPrimaryKey((Serializable)workDayId);
	}

	/**
	 * Returns the work day with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the work day
	 * @return the work day, or <code>null</code> if a work day with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkDay fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		WorkDay workDay = (WorkDay)EntityCacheUtil.getResult(WorkDayModelImpl.ENTITY_CACHE_ENABLED,
				WorkDayImpl.class, primaryKey);

		if (workDay == _nullWorkDay) {
			return null;
		}

		if (workDay == null) {
			Session session = null;

			try {
				session = openSession();

				workDay = (WorkDay)session.get(WorkDayImpl.class, primaryKey);

				if (workDay != null) {
					cacheResult(workDay);
				}
				else {
					EntityCacheUtil.putResult(WorkDayModelImpl.ENTITY_CACHE_ENABLED,
						WorkDayImpl.class, primaryKey, _nullWorkDay);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(WorkDayModelImpl.ENTITY_CACHE_ENABLED,
					WorkDayImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return workDay;
	}

	/**
	 * Returns the work day with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param workDayId the primary key of the work day
	 * @return the work day, or <code>null</code> if a work day with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkDay fetchByPrimaryKey(long workDayId) throws SystemException {
		return fetchByPrimaryKey((Serializable)workDayId);
	}

	/**
	 * Returns all the work daies.
	 *
	 * @return the work daies
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WorkDay> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the work daies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.timetracking.timesheet.model.impl.WorkDayModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of work daies
	 * @param end the upper bound of the range of work daies (not inclusive)
	 * @return the range of work daies
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WorkDay> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the work daies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.timetracking.timesheet.model.impl.WorkDayModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of work daies
	 * @param end the upper bound of the range of work daies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of work daies
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WorkDay> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<WorkDay> list = (List<WorkDay>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_WORKDAY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_WORKDAY;

				if (pagination) {
					sql = sql.concat(WorkDayModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<WorkDay>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<WorkDay>(list);
				}
				else {
					list = (List<WorkDay>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the work daies from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (WorkDay workDay : findAll()) {
			remove(workDay);
		}
	}

	/**
	 * Returns the number of work daies.
	 *
	 * @return the number of work daies
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_WORKDAY);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the work day persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.timetracking.timesheet.model.WorkDay")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<WorkDay>> listenersList = new ArrayList<ModelListener<WorkDay>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<WorkDay>)InstanceFactory.newInstance(
							getClassLoader(), listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(WorkDayImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_WORKDAY = "SELECT workDay FROM WorkDay workDay";
	private static final String _SQL_SELECT_WORKDAY_WHERE = "SELECT workDay FROM WorkDay workDay WHERE ";
	private static final String _SQL_COUNT_WORKDAY = "SELECT COUNT(workDay) FROM WorkDay workDay";
	private static final String _SQL_COUNT_WORKDAY_WHERE = "SELECT COUNT(workDay) FROM WorkDay workDay WHERE ";
	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN = "workDay.workDayId";
	private static final String _FILTER_SQL_SELECT_WORKDAY_WHERE = "SELECT DISTINCT {workDay.*} FROM TimeTracking_WorkDay workDay WHERE ";
	private static final String _FILTER_SQL_SELECT_WORKDAY_NO_INLINE_DISTINCT_WHERE_1 =
		"SELECT {TimeTracking_WorkDay.*} FROM (SELECT DISTINCT workDay.workDayId FROM TimeTracking_WorkDay workDay WHERE ";
	private static final String _FILTER_SQL_SELECT_WORKDAY_NO_INLINE_DISTINCT_WHERE_2 =
		") TEMP_TABLE INNER JOIN TimeTracking_WorkDay ON TEMP_TABLE.workDayId = TimeTracking_WorkDay.workDayId";
	private static final String _FILTER_SQL_COUNT_WORKDAY_WHERE = "SELECT COUNT(DISTINCT workDay.workDayId) AS COUNT_VALUE FROM TimeTracking_WorkDay workDay WHERE ";
	private static final String _FILTER_ENTITY_ALIAS = "workDay";
	private static final String _FILTER_ENTITY_TABLE = "TimeTracking_WorkDay";
	private static final String _ORDER_BY_ENTITY_ALIAS = "workDay.";
	private static final String _ORDER_BY_ENTITY_TABLE = "TimeTracking_WorkDay.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No WorkDay exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No WorkDay exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(WorkDayPersistenceImpl.class);
	private static WorkDay _nullWorkDay = new WorkDayImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<WorkDay> toCacheModel() {
				return _nullWorkDayCacheModel;
			}
		};

	private static CacheModel<WorkDay> _nullWorkDayCacheModel = new CacheModel<WorkDay>() {
			@Override
			public WorkDay toEntityModel() {
				return _nullWorkDay;
			}
		};
}