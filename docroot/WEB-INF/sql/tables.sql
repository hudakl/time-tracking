create table TimeTracking_Activity (
	activityId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	activityName VARCHAR(75) null
);

create table TimeTracking_ActivityAssignment (
	activityAssignmentId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	activityId LONG,
	workDayId LONG,
	startTime DATE null,
	endTime DATE null
);

create table TimeTracking_DaysOfYear (
	dayOfYearId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	dayId DATE null,
	ruleId LONG,
	unitId LONG,
	unitType INTEGER
);

create table TimeTracking_Ruling (
	ruleId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	ruleName VARCHAR(75) null,
	multiplier DOUBLE
);

create table TimeTracking_WorkDay (
	workDayId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	dayOfYearId LONG,
	startTime DATE null,
	endTime DATE null,
	pause INTEGER
);