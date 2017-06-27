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
* --tournament: generate 10000 random 12-digit number and perform search / print result onto stdout and return finish time
* --service: read stdin to read input searchtoken and return result (Ctrl-C to terminated)


## Integrate with other application
1. Create new instance of PhoneOperatorTable and Implement your search or use provided search injected with newly created table with: 
    ```java
    PhoneOperatorTable table = new PhoneOperatorTable();
    CustomSearch cs = new CustomSearch();
    cs.setTable(table);
    String searchToken = readTokenFromSomewhere();
    int result = cs.search(searchToken);
    ```
