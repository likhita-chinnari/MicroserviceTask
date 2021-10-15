# MicroserviceTask

This repository contains the solution code for the given problem statement in a folder named "SuperheroesAssemble". I built this Spring Web MVC project using Eclipse.

The src folder contains a controllers module which has HomeControllers and InputsController java classes.

HomeController is a RestController responsible for  calling the 3 APIs parallely for every 10 seconds, fetching the data from the APIs and updating the latest values in a map.
The HashMap takes parameters of type String and FictionalCharacter class as key value pairs. The FictionalCharacetr class is present in com.app.model package which stores the
name and power level of each character fetched from the APIs.

InputsController is reponsible for recieving the requests from the client and displaying the latest power level of that input character by fecthing it from map present in 
HomeController. (HomeController is autowired inside InputsController). InputsController also contains a priority Queue which is used to implement the business rules.

PriorityQueue takes BusinessRuleModel class objects. BusinessRuleModel class is present in com.app.model package , it contains chaarcter name, requestCount(the number of times
the character is requested from clients till present) and its latest power level. Here BusinessRuleModel class implements Comparable interface, such that priority of the 
objects is decided based on the logic given in compareTo method in BusinessRuleModel class.

The logic given is to compare the objects based on the requestCount, if any two objects have equal requestCount then compare using their power levels.
