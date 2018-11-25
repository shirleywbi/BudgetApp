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
	- expenseTotal income
	- expenseTotal money spent
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

If user types in "1", they will be asked "Enter your income:". The number would be added to the expenseTotal balance.
If user types in "2", they will be asked the following:
	"Enter name of expense:"
	"Cost:"
	The expense and cost will be saved.
	Print the name of the expense and its cost.
If user types in "3", show summary report of expenseTotal income, expenseTotal expenses, list of expense names and costs, and current balance.
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
Stage 4: Types, Interfaces and Saving

1. Added load and save feature.
2. Added two interfaces: Reports and Expenses
3. Added ability to categorize expenses to pre-built categories.
4. Updated report to provide a expenseTotal of each expense category.

*********************************************************************
Stage 5:

1. Changed interfaces to abstract class.
2. Entertainment and Health categories added.
3. Added Expense percentages to report.

*********************************************************************
Stage 6: Robustness

1. Added exceptions:
    - SavingsBeingUsedExceptionTest - in BalanceReport and ReportPrinter
    - NegativeAmountExceptionTest, NumberFormatExceptionTest, and InvalidEntryExceptionTest - in BudgetTracker
    Exceptions allowed for system to not crash when invalid input was entered.
2. Rearranged Balances and Expenses

TO-DO:
- Merge object for list of expenses
- Possibly create a set of all categories - pending HashSet execution
- To allow editing of expense categories
- Fix bugs:
	- Spacing in expense names when saving/loading
- Pull up information from the file.
- Add option to go back to main
- Add dates to expense log.
- Create visuals for the report (e.g., charts and graphs)

*********************************************************************
Stage 8: Data Relationships

1. Set up and maintain a reflexive relationship: syncing add, remove (Persons and Income)
2. Introduce a comparison, and override hashcode and equals to make it work (Persons and Income)
- run via tests, not in main
3. Changed design to reduce coupling by removing categories and changing it to a list
4. Added hash map of categories for expense menu option
5. Created ExpenseItem object to merge lists of categories, costs, and expense names

*********************************************************************
Stage 9: Design Principles

1. Created new class ExpenseCategory and moved method/field from BudgetTracker and Expense
2. Refactored methods in BudgetTracker and Expense into smaller methods

*********************************************************************
Stage 11: GUI

1. Added GUI but bugs have been introduced.
2. Refactored SelectionPanel into separate panels to increase cohesion.
3. Numbers have been formatted to two decimal places.
4. Exceptions updated to prevent invalid input.

TO-DO:
- Integrate load/save
- Write out any missing REQUIRES/MODIFIES/EFFECTS
- Clean up semantic coupling, create enumeration for anything with Strings in UI
- Add tests for model

Nice Additions:
- Add dates to expense log.
- Create visuals for the report (e.g., charts and graphs)
- To allow editing of expense categories
- Reset button

*********************************************************************
PROJECT SUMMARY:
Challenges:
- Trying to make the UI look "nice"
    - Didn't know what colors to use
        Solution:
        - Searched for other people's projects to see what colours worked (often didn't)
        - Played around with different shades
    - Had to translate idea to code
        Solution:
        - Broke idea up into different components (functions): expense, income, summary, and reports
        - Implemented and refactored afterwards (not necessarily a good idea because some things broke when refactoring)
- Tests: Difficult to write for Scanner and GUI
- Writing maintainable code and balancing coupling and cohesion
    - Did not consider maintainability when first creating the project,
    resulted in refactoring multiple times to include class requirements
- Decided to use Java Swing because TAs were more familiar even though JavaFX may have been easier to implement
    - Next time: Figure out what you want out of your GUI before implementation

What did I learn?
- LSP - Determining whether a subclass is substitutable.
    - EX. Panels had extended UIFormat but panels does not have an is-a relationship so instantiated instead.
- Exceptions - Hiding non-fatal errors from users by throwing and catching exceptions
- Bugs, bugs and more bugs
    - Solutions are everywhere so look up what worked for other people
    - Read your code carefully, shrink if needed
    - Draw a class diagram, structural diagram, type hierarchy
    - Think about possible causes for the issues
        - Why is something repeating? Is there duplication somewhere?
            - EX. Duplication when trying to print out report due to multiple notifyObservers in different methods
        - Why are values not changing? Is it referring to the correct object?