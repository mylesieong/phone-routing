# Phone Routing Problem Solving

Problem description is provided in `problem_description.txt`. 

## Prequisite
1. Maven
1. JRE7+

## Get Started
1. Compile java code by mvn package
1. Run `java -jar /path/to/phone-routing-0.0.1.jar -f operator1.txt -f operator2.txt --tournament` for tournament mode 
1. Run `java -jar /path/to/phone-routing-0.0.1.jar -f operator1.txt -f operator2.txt --service` for service mode 

## Command Line Options
* -f: read following file as phone operator table
* -t: generate 10000 random 12-digit number and perform search / print result onto stdout and return finish time
* -s: read stdin to read input searchtoken and return result (Ctrl-C to terminated)


## Integrate with other application
1. Create new instance of PhoneOperatorTable and Implement your search or use provided search injected with newly created table with: 
    ```java
    PhoneOperatorTable table = new PhoneOperatorTable();
    CustomSearch cs = new CustomSearch();
    cs.setTable(table);
    String searchToken = readTokenFromSomewhere();
    int result = cs.search(searchToken);
    ```

## Possible reuse/ future change scenario and preparation of design
* New Search Algorithm is introduced

This case can be solved by separating Search and Table. We can instead perform search by injecting a Table into a search agent so that the search agent perform base on the interface of a table and the table's internal operation like loading data from different data source can also be decoupled from search.

* Table should be loaded in another data source/internet

The design mentioned below is also the solution for this case: A table interface.

* New routing policy is introduced say if a operate doesnt carry certain prefix, it can be perform by adding 20% margin on the nearest neighbour

This new feature can be implemented by the future engineer by modifying the search method in the search agent.

## Main Principle
* KISS

Table x Search is simple enough to be understand in a second. 

* SOLID

Single responsiblily [checked] by Table, Search design
Openclose principle [checked] interface design is open to change(when there is new search algorithm just create a new implementation of Search) but close (or say, no need to) change.
Liskov Substitution x not sure, but in order not to over-design, we should take it simple.
Interface segregation [checked] table and search interface is the minimized interface
Dependency injection [checked] that table knowledge is injected into search and data info is injected into table.




