# Phone Routing Problem Solving

Problem description is provided in `problem_description.txt`. In order to solve the problem, 2 search algorithms is implemented in this project shared the same interface `Search`. 

There is also a command line tools provided in this project. For usage, please refers to the *Command Line Options* part  

## Prequisite for build

1. Maven
1. JDK(1.4+)

## Get Started

1. Get the project code by `git clone https://github.com/mylesieong/phone-routing`

1. Navigate to project root and compile with maven command `mvn package`

1. To search for query(s) in given operator text file(s), run command as below example:

```
$ java -cp target/phone-routing-1.0-SNAPSHOT.jar com.myles.alatest.PhoneRoutingCommandLineRunner search -f operator_a.txt -f operator_b.txt  -f countryprefix.txt  -q 4656311335 -q 85328344674 -q 8617817723875 -q 8810233
Search 4656311335 in input operators:
  In operator operator_a.txt, cost is 0.17
  In operator operator_b.txt, cost is 0.2
  In operator countryprefix.txt, cost is 1.19
------------------------------------
Search 85328344674 in input operators:
  In operator operator_a.txt, cost is -1.0
  In operator operator_b.txt, cost is -1.0
  In operator countryprefix.txt, cost is 1.06
------------------------------------
Search 8617817723875 in input operators:
  In operator operator_a.txt, cost is -1.0
  In operator operator_b.txt, cost is -1.0
  In operator countryprefix.txt, cost is 1.09
------------------------------------
Search 8810233 in input operators:
  In operator operator_a.txt, cost is -1.0
  In operator operator_b.txt, cost is -1.0
  In operator countryprefix.txt, cost is 1.14
------------------------------------
```

1. To launch an interesting tournament among 2 search algorithms, run command as below example:

```
$ java -cp target/phone-routing-1.0-SNAPSHOT.jar com.myles.alatest.PhoneRoutingCommandLineRunner tournament -f countryprefix.txt
com.myles.alatest.BruteSearch starts at: 2017-06-30T11:07:28.785+08:00
com.myles.alatest.BruteSearch stops at:  2017-06-30T11:07:42.094+08:00
com.myles.alatest.BruteSearch comsumes:  13309
------------------------------------
com.myles.alatest.AlphabeticSearch starts at: 2017-06-30T11:07:44.403+08:00
com.myles.alatest.AlphabeticSearch stops at:  2017-06-30T11:07:44.808+08:00
com.myles.alatest.AlphabeticSearch comsumes:  405
------------------------------------
```

1. To load the help page, simply pass no input parameters.

## Command Line Options

```
NAME:       Phone Routing Problem Command Line Runner

SYNOPSIS:   java com.myles.alatest.PhoneRoutingCommandLineRunner <mode> <file-args>
            [<file-args>] [<query-args>]

MODE:       Two working mode is available. They are: tournament and search. 

            Tournament mode will launch a performance comparison among 2 search 
            algorithm implementation based on same operator file and 1,000,000 
            random generated numbers. Note that tournament mode only accepts one 
            set of <file-args>.
            
            Search mode will search through a given list of operators for each 
            queries. Note that search mode accepts at least one set of <file-args>
            and at least one set of <query-args>.
            
ARGGS:      <file-args> composes an identifier '-f' followed by a path to an operator 
            file. The operator file should contains a list of lines of prefix-cost 
            pairs by which should be separated by a <tab> character between the prefix 
            and the cost.
            
            <query-args> composes an identifier '-q' followed by a number to be 
            searched.

EXAMPLE:    

*   To search the cost of number 4656311335 in operator_a and operator_b:
    java com.myles.alatest.PhoneRoutingCommandLineRunner search -f operator_a.txt -f operator_b.txt -q 4656311335 

*   To launch an tournament among 2 search algorithms based on operator_x:
    java com.myles.alatest.PhoneRoutingCommandLineRunner tournament -f operator_x.txt
```
    
## Integrate with other application

To use the API power provided in this project, create new instance of PhoneOperatorTable and implement your search or use provided search: 

```java
Map table = new PhoneOperatorMap();
table.load(filename);

Search cs = new AlphabeticSearch();   // Any custom impl of Search is applicable
cs.index(table);

Double result = cs.search(searchString);
```

## Algorithm Implementations in this Project

* BruteSearch: Read the input file and store as list. Search query number with iterating all elements in the list.

* AlphabeticSearch: Parse input map into a tree structure with every tree node represent a digit. Travel the tree from root to leave according to query number.
    
## Adapt future changes

There is many possible requirement changes or new requirement in the future. Here is suggestion for different most possible future changes adaption.

* When new search algorithm is invented

    We can implement the Search interface to the class NewSearch. Since we perform search by injecting a Map into a search agent so that the search interface is decoupled from the Operator Map.

* Operator information moved to new data source/format

    Simple override the PhoneOperatorMap's load method by adding new data source fetching logic. The search algorithms are still valid without any changes.

* New routing policy is introduced 
    
    Say a new routing polic is introduced that if a operator doesn't carry certain prefix, it can be dialed with 20% extra cost of the nearest prefix. For this case, unfortunately, we need to implement new Search implementation since our current search agent has coupled the routing policy into it search method.
