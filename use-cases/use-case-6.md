USE CASE: 6 View an Employee's Details

CHARACTERISTIC INFORMATION

Goal in Context
As an HR advisor I want to view an employee's details so that the employee's promotion request can be supported.

Scope
Company.

Level
Primary task.

Preconditions
Employee exists in the database.

Success End Condition
HR advisor can view the employee's current details.

Failed End Condition
Employee details cannot be accessed.

Primary Actor
HR Advisor.

Trigger
A promotion request or general inquiry requires employee information.

MAIN SUCCESS SCENARIO

HR advisor captures employee ID or name.

HR advisor queries the system for the employee.

System retrieves and displays employee details.

EXTENSIONS

Employee does not exist:
System informs HR advisor that no such employee was found.

SUB-VARIATIONS
None.

SCHEDULE
DUE DATE: Release 1.0