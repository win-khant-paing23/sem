USE CASE: 2 Produce a Report on the Salary of Employees in a Department

CHARACTERISTIC INFORMATION

Goal in Context
As an HR advisor I want to produce a report on the salary of employees in a department so that I can support financial reporting of the organisation.

Scope
Company.

Level
Primary task.

Preconditions
We know the department. Database contains current employee salary data.

Success End Condition
A report is available for HR to provide to finance.

Failed End Condition
No report is produced.

Primary Actor
HR Advisor.

Trigger
A request for finance information is sent to HR.

MAIN SUCCESS SCENARIO

Finance requests salary information for a given department.

HR advisor captures the name of the department to get salary information for.

HR advisor extracts current salary information of all employees in the given department.

HR advisor provides report to finance.

EXTENSIONS

Department does not exist:
HR advisor informs finance no such department exists.

SUB-VARIATIONS
None.

SCHEDULE
DUE DATE: Release 1.0