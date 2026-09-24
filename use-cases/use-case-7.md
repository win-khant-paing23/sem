USE CASE: 7 Update an Employee's Details

CHARACTERISTIC INFORMATION

Goal in Context
As an HR advisor I want to update an employee's details so that employee's details are kept up-to-date.

Scope
Company.

Level
Primary task.

Preconditions
Employee exists in the database.

Success End Condition
Employee details are updated in the database.

Failed End Condition
Employee details remain unchanged.

Primary Actor
HR Advisor.

Trigger
An employee reports a change in personal or role details.

MAIN SUCCESS SCENARIO

HR advisor receives updated details.

HR advisor searches for the employee record.

HR advisor modifies the details in the system.

System saves the updated record.

EXTENSIONS

Employee does not exist:
System informs HR advisor that no such employee was found.

Invalid data entered:
System prompts HR advisor to correct the entered data.

SUB-VARIATIONS
None.

SCHEDULE
DUE DATE: Release 1.0