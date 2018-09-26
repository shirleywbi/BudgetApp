# Personal Project
:arrow_forward: Please refer to [edX][1] for description and instructions.

[1]: https://edge.edx.org/courses/course-v1:UBC+CPSC210+2018W1/courseware/a4d49b3ef5fa4fe2bd9496e76d72dc48/e2887456a15a48dbb040ecdac313168f/1?activate_block_id=block-v1%3AUBC%2BCPSC210%2B2018W1%2Btype%40vertical%2Bblock%40ff793bbcd5544e82bb5052f0dffe5d71

*********************************************************************
Goal: To create a budget tracker to input your income and expenses

How to run: Program can be run in the ui package, main class

Possible Capabilities (TBD):
- inputting your income and expenses
- categorizing your expenses
- print report to indicate:
	- total income
	- total money spent
	- breakdown of expenses

*********************************************************************
Stage 1: To create a budget tracker to produce an income and expenses

1. Create ui package > main class to start the project.
2. Create 2 methods: 
   (1) to print the income
   (2) to print the expenses

*********************************************************************
Stage 2: User Interaction

1. Insert interaction loop:

Main interaction screen:

"What would you like to do?"
[1] Add income
[2] Add expense
[3] Show report
[4] Exit

If user types in "1", they will be asked "Enter your income:". The number would be added to the total balance. 
If user types in "2", they will be asked the following:
	"Enter name of expense:"
	"Cost:"
	The expense and cost will be saved.
	Print the name of the expense and its cost.
If user types in "3", show summary report of total income, total expenses, list of expense names and costs, and current balance. 
If user types in "4", exit program.

*********************************************************************
Stage 3: Data Abstraction

1. Divided BudgetTracker into smaller helper functions to make it easier to read/work with.
2. Created tests for Balance to ensure income, expenses, expense entries were being added correctly.
	***No tests were written for Reports as it only pulls and prints content. As per Piazza @292, it may be a bit redundant.
	***No tests were written for BudgetTracker as per Piazza @237 and @337 where 
	- (@237) it is stated that we are not expected to test them in class and 
	- (@337) "You can't test console input/output, but probably you should test the methods being called from within your interaction loop"

*********************************************************************
TO-DO:
- Categorize expenses into various categories.
- Add dates to expense log.
- Save and update file with expenses.
- Create visuals for the report (e.g., charts and graphs)
- Add filter for string inputs into incomeEntry.