# FlightFilter
Run the program: ~/src/com.gridline.testing.Main.java
1. after start up program run into infinite while loop and program shows user available options for flight filtering
2. User should type one of the available options and get filtered flights according to option requirement. If user types wrong option, program will ask user to type again. Typing is case not sensitive.
3. In order to quite the program user should type 'exit'

Short classes description:

#Main.java:
1. contains only main entry method main

#FilterRun.java:
1. contains run method where logic of the filtering options is written
2. At the while loop program waits user input in order to start filter flights according to called filter

#FlightFilterAPI.java
1. Interface where all flight filters are decleared

#FlightFilter.java
1. Class contains logic of the all flight filters - implements FlightFilterAPI methods, each method is shortly described into interface class 

#UtilFunctions.java
1. Class contains all auxiliary methods needed for filter implementation, such as Segment time count - time calculation between two dates.
2. Based on time calculation, custom flight comparator was created in order to quickly compare two flights by flight duration
3. Other methods quickly described inside this class

#FlightFilterHelpMethodsTest.java
1. Most important thing is to make sure that SegmentTimeCount method counts flight time correctly for all cases, even unrealistic cases when flight time can be lasted for years, anyways it should count right amount of hours.

#FlightFilterTest.java
1. Testing all filter method using FlightsRepoTest.java as data source

#FlightsRepo.java
1. flights collected in one place, like 'in memory db'

#FlightsRepoTest.java
1. flights collected in one place, like 'in memory db', used for test cases only!
