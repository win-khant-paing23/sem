USE CASE: 8 Delete an Employee's Details

CHARACTERISTIC INFORMATION

Goal in Context
As an HR advisor I want to delete an employee's details so that the company is compliant with data retention legislation.

Scope
Company.

Level
Primary task.

Preconditions
Employee has left the company and their data retention period has expired.

Success End Condition
Employee details are completely removed from the database.

Failed End Condition
Employee details are not removed.

Primary Actor
HR Advisor.

Trigger
Data retention period expires for a former employee.

MAIN SUCCESS SCENARIO

HR advisor identifies the employee record required for deletion.

HR advisor requests deletion of the record in the system.

System removes the employee details.

System confirms successful deletion.

EXTENSIONS

Employee does not exist:
System informs HR advisor that no such employee was found.

SUB-VARIATIONS
None.

SCHEDULE
DUE DATE: Release 1.0