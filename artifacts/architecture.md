Program Organization
===
Level 1: System Context
![System_context_diagram](https://github.com/kildar2112/4331_UCFMAP/blob/master/artifacts/imgs/C4%20Diagrams/System%20Context.jpeg?raw=true)
The user checks the website, called where2park, and sees an interactive map. Here the user can view garage availability, peak hours, and which permits are accepted in which garages. Furthermore, the website will make API calls to Google Maps to provide walk times and to display a route to get to a desired building. The website will also retrieve information about peak hours via calls to our DynamoDB database.


Level 2: Container
![Container_diagram](https://github.com/kildar2112/4331_UCFMAP/blob/master/artifacts/imgs/C4%20Diagrams/Container.jpeg?raw=true)
The software is made up of two major parts: the web scraper and the single-page web application. The web scraper will be a function hosted on Amazon's Lambda, which will pull from the [inline frame used on UCF's website](http://secure.parking.ucf.edu/GarageCount/iframe.aspx). This information is validated and then sent to the Dynamo database, where it will be used for updating the data displayed our map and for calculations regarding popular hours. The single-page application provides an interface for all of the functionality the user will need. It is here the user can view garage saturation, popular/peak hours, valid parking permits, and walk times, etc. The website will also allow the user to invoke API calls to Google Maps to display a route and estimate walk times.

Level 3: Component
![Component_diagram](https://github.com/kildar2112/4331_UCFMAP/blob/master/artifacts/imgs/C4%20Diagrams/Component.jpeg?raw=true)
The single-page application has several components. Firstly, the contact developer component allows a user to send an email to us and is independent from any other part of the program. The Map component displays all the color-coded garages, which will be interactive. Upon clicking a garage, a pop-up window that displays information about the garage will appear. When invoked by the user, this window makes API calls to Google Maps to display a route and to estimate walk times. This window will also retrieve peak hours from the Dynamo database. The Select Classroom Component will be a dropdown menu with all the classroom buildings. Upon clicking one of these classrooms, a list of the nearest garages will appear, along with their current saturation level. The items in the dropdown will be encoded with coordinate information for Google Maps to find a route, since the buildings will not be interactive on the map.

Level 4: Class
![Class_diagram](https://github.com/kildar2112/4331_UCFMAP/blob/master/artifacts/imgs/C4%20Diagrams/Class%20Diagram%20C4.jpeg?raw=true)
This diagram shows the classes we will be using for the software. The website scraper class returns an array of Garage objects, which contain how full they are. That information is then sent to the processor class, which validates the information and formats it. Then, the processor class sends the processed data to the Database Updater class, which sends that information to the database. The processed data is then pulled from DynamoDB and pushed as the most up to date parking data on our website.


Related User Stories:

| ID | User Story | Related Class | Explanation |
|----|------------|--------|----------|
| 000 | As someone who parks at UCF, I want to see parking availability so I know which garage to park in. | Map, Garage, Garage List, Website scraper | Garages appear color-coded on the map. The garage list also displays the fullness of a garage. The website scraper gets that information from UCF Parking Services |
| 001 | As someone who parks at UCF, I want to see the walk time to my class so that I know when to leave. | Pop-Up Window | The Pop-Up Window makes API calls to Google Maps, showing the walk time. |
| 002 | As someone who parks at UCF, I want to know the bike time to class so that I know when to leave. | Pop-up window | The Pop-Up Window makes API calls to Google Maps, showing the bike time. |
| 003 | As someone who parks at UCF, I want to see a garage's peak hours so I know the best time to park. | Pop-Up Window, Database | The pop-up window shows peak hours. The peak hours are stored in the database |
| 004 | As someone who parks at UCF, I want to know a route to my classroom so I can reduce my travel time. | Pop-Up Window | The pop-up window displays an embedded link to Google Maps |
| 005 | As someone who parks at UCF, I want to see a map of campus that shows me the buildings and garages so I can navigate the campus. | Map | The map shows all the relevant garages and buildings |
| 006 | As someone who parks at UCF, I want to be able to tell a garage's fullness from a color label so that it's more clear from a glance. | Map, Garage | The Garages displayed on the map will be color-coded to show fullness. |
| ~007~ | ~As someone who parks at UCF, I want to know where I parked so that I don't forget.~ | ~Pop-up, Map~ | ~This info might be displayed on the pop-up or the map~ |
| 008 | As someone who parks at UCF, I want to know how many spots are left in a garage so I can determine if I can find a spot. | Garage List, Map, Garage, Pop-up Window | The Garage List shows a list of which garages are closest to a selected building and their fullness. Garages on the map will be color-coded to represent fullness. The Pop-Up Window details what percent full a garage is and how many spots are available.|
| 009 | As someone who parks at UCF, I want to know which garages I can park in with my specific parking pass so that I don't get a parking ticket. | Pop-Up Window | The Pop-Up Window displays all the accepted parking permits for a garage. |
| 010 | As someone who parks at UCF, I want to be able to load the website in a web browser so I can check parking from any device. | - | This functionality is dependent on the website and HTML code. |
| 011 | As someone who uses the parking map, I want to be able to select a building or parking garage so that I can see more information and potential interactions. | Map, Pop-Up Window, Garage | The map shows all the interactive garages, which can be clicked to open a pop-up window. This window shows all the relevant information |
| 012 | As someone who uses the parking map, I want to be able to contact the developer so that improvements can be made to the application (or to express my deepest gratitude). | Contact Developer | The contact developer option allows the user to send an email to the developers. |



Major Classes
===
![Filter and Building class diagram](https://github.com/kildar2112/4331_UCFMAP/blob/master/artifacts/imgs/UML/cd_building_processor.jpg?raw=true)

This diagram shows the links between the buildings, garages, and the processor that is used to process and check the data coming from UCF Parking Services.

CLASS DESCRIPTIONS

Building:
	The class that represents a UCF Building, which contains its location and the option to enable or disable visibility on the map. It contains two public methods that turn the visibility on or off. Its location is also kept track of by a Point.

Garage:
	A type of building that records how full the garage is, which garage it is, and what permits are needed to park in it. It inherits from the Building class and contains a few private methods, which update the saturation levels of the garage, calculate the percentage of spots available, and changes the color that represents how full a garage is (e.g. a red garage is nearly full).

Processor:
	The class that processes all of the data taken from the UCF Parking Services. It contains arrays that store all of the current data for the garages. This class will also send the data to DynamoDB which is used for storing the data so we can calculate peak hours for the garages. We can also validate the data coming in to make sure that we aren't pulling in garbage data if UCF Parking Services is down for any period of time.

Related User Stories:

| ID | User Story | Related Class | Explanation |
|----|------------|--------|----------|
| 000 | As someone who parks at UCF, I want to see parking availability so I know which garage to park in. | Garage | Garage has a field that says how many spots are full and a method that calculates the percentage of spots taken. |
| 001 | As someone who parks at UCF, I want to see the walk time to my class so that I know when to leave. | Building | Building  has a field that describes its location which is used to give a walk time estimate.|
| 002 | As someone who parks at UCF, I want to know the bike time to class so that I know when to leave. | Building | Building has a field that describes its location which will be used when finding the route. |
| 003 | As someone who parks at UCF, I want to see a garage's peak hours so I know the best time to park. | Garage | Each garage will keep track of its peak hours. |
| 004 | As someone who parks at UCF, I want to know a route to my classroom so I can reduce my travel time. | Building | The Building class gives locations for which will be used when finding a route between two locations. |
| 005 | As someone who parks at UCF, I want to see a map of campus that shows me the buildings and garages so I can navigate the campus. | Building | Building contribute to what you see on the map. Garages store how full they are by color-coding. Building highlights classrooms on the map which is part of the HTML code. |
| 006 | As someone who parks at UCF, I want to be able to tell a garage's fullness from a color label so that it's more clear from a glance. | Garage | The Garage class stores the color it should display that dictate how full a garage is. |
| ~007~ | ~As someone who parks at UCF, I want to know where I parked so that I don't forget.~ | ~Garage~ | ~The Garage class can store information about where the user parked.~ |
| 008 | As someone who parks at UCF, I want to know how many spots are left in a garage so I can determine if I can find a spot. | Garage | The Garage class keeps track of how many spots are open and how many spots are occupied, as well as the percentage of spots taken. |
| 009 | As someone who parks at UCF, I want to know which garages I can park in with my specific parking pass so that I don't get a parking ticket. | Garage | The Garage class keeps track of which parking passes are required to park in it. |
| 010 | As someone who parks at UCF, I want to be able to load the website in a web browser so I can check parking from any device. | - | This functionality is dependent on the website and HTML code. |
| 011 | As someone who uses the parking map, I want to be able to select a building or parking garage so that I can see more information and potential interactions. | Building, Garage | The Building and Garage classes provide information about themselves, such as how full it is if it is a garage, and where it is if it is a building. |
| 012 | As someone who uses the parking map, I want to be able to contact the developer so that improvements can be made to the application (or to express my deepest gratitude). | - | This feature will be controlled by the website. |

Data Design
===
It is our current intent to use AWS's Lambda as our application's host and to store permanent data logs using Amazon's DynamoDB service. We intend to setup the data logging in such a way that the most up to date and valid information is available for viewing on the website. In order to accomplish this we must develop the system in such a way that if invalid data is recorded, a halt will be placed on the corrupted data and prevent it from being displayed on the website.

Business Rules
===
There are no known business rules that apply to our project.

User Interface Design
===
![User Interface Diagram](https://github.com/kildar2112/4331_UCFMAP/blob/master/artifacts/imgs/garages/popup.jpg?raw=true)
The Map:
	The map will have clickable icons for each garage. The user will be able to click garages and receive detailed information about them such as their overall saturation, accepted permit types, popular hours, etc. The garages on the map will also be color-coded to represent their fullness, so that the user will know roughly how full a garage is simply by glancing at the map as a whole.


---
![User Interface Diagram](https://github.com/kildar2112/4331_UCFMAP/blob/master/artifacts/imgs/garages/C.jpg?raw=true)

User Interface Description:

Garage Availability:
	The user will be able to view how many parking spots are open and what percentage of spots are available by simply looking at this section. It contains a list of all the garages, and when the user clicks on a garage, that garage will be highlighted on the map.

Filter by Parking Permit:
	The user will be able to see where he or she can park by filtering the garages by permits. In other words, they will know what permits are accepted in which garages. The user will select the permit they have and a list of valid garages will be displayed.

Walk to Class / Bike to Class:
	The user will select one of these two options before they receive a travel time and route. The user will simply click the box corresponding to their travel method, which will let Google Maps know how the user wishes to get to class.

Select Classroom Building:
	The user will select the classroom building they wish to go to from a dropdown menu. This will let Google Maps know where the user’s destination is.

Show route:
	The user will click  the box next to this if they wish to see a route to get to their chosen building. If the user does not click this, the user will receive instead only an estimated travel time.

Go to Google Maps:
	The user will click this and will be shown an estimated travel time and route depending on the options they chose above.




Related User Stories:

| ID | User Story | Related UI Component | Explanation |
|----|------------|--------|----------|
| 000 | As someone who parks at UCF, I want to see parking availability so I know which garage to park in. | Garage Availability | This section lists all the garages, along with how many spots are available, unavailable, and what percent full the garage is. |
| 001 | As someone who parks at UCF, I want to see the walk time to my class so that I know when to leave. | Walk to Class | The Walk to Class option gives an estimate, and a route if it is requested, of how long it will take to walk to a chosen location from where you are. |
| 002 | As someone who parks at UCF, I want to know the bike time to class so that I know when to leave. | Bike to Class | The Bike to Class option gives an estimate, and a route if it is requested, of how long it will take to bike to a chosen location from where you are. |
| 003 | As someone who parks at UCF, I want to see a garage's peak hours so I know the best time to park. | Map, Garage Availability | By selecting a garage on the map or in the garage list, the user will be able to see that garage's peak hours. |
| 004 | As someone who parks at UCF, I want to know a route to my classroom so I can reduce my travel time. | Show Route, Go to Google Maps | If Show Route is selected, pressing the Go to Google Maps option will show you a route to the selected location on Google Maps. |
| 005 | As someone who parks at UCF, I want to see a map of campus that shows me the buildings and garages so I can navigate the campus. | Map | The Map part of the website displays a map of the entire campus, including buildings and garages. |
| 006 | As someone who parks at UCF, I want to be able to tell a garage's fullness from a color label so that it's more clear from a glance. | Map | The Map will display all the garages, as well as color them to show how much space is left, with green being relatively empty, yellow being mediumly filled, and red being almost full. A black garage is completely full. |
| ~007~ | ~As someone who parks at UCF, I want to know where I parked so that I don't forget.~ | ~Map~ | ~The garage where the user parked will be highlighted on the Map.~ |
| 008 | As someone who parks at UCF, I want to know how many spots are left in a garage so I can determine if I can find a spot. | Garage Availability, Map | The Map will display color-coded garages to show which garages are full and which are empty. The Garage Availability section will display a list of how full the garages are. |
| 009 | As someone who parks at UCF, I want to know which garages I can park in with my specific parking pass so that I don't get a parking ticket. | Filter By Parking Permit | The Filter By Parking Permit dropdown menu will allow the user to select a parking permit to filter out the garages that do not accept that permit on the Map and Garage Availability section. |
| 010 | As someone who parks at UCF, I want to be able to load the website in a web browser so I can check parking from any device. | - | This functionality is dependent on the website and HTML code. |
| 011 | As someone who uses the parking map, I want to be able to select a building or parking garage so that I can see more information and potential interactions. | Map | The Map will be interactive and display information about the selected building or garage. |
| 012 | As someone who uses the parking map, I want to be able to contact the developer so that improvements can be made to the application (or to express my deepest gratitude). | Contact Developer | By clicking this button, the user can contact the developer. |

Resource Management
===
At this time we do not have any concerns with resource management. Should we encounter issues regarding resource management during development, we will update this section.

Security
===
For our own security, we have decided to buy our hosting platform as to avoid making ourselves vulnerable to malicious activity.

Performance
===
We have not foreseen any performance issues in our design mockups yet. We'll keep you updated.

Scalability
===
Thanks to the nature of Lambda, DynamoDB, and the fact that minimal data is stored on the back end, we should be able to scale up the app to handle traffic exceeding the total population of UCF students and staff.

Interoperability
===
While we don't have any plans for the information we produce to be utilized by another party, we will be operating our program based on the information received by [UCF Parking's Garage Availability page](https://parking.ucf.edu/garage-availability/).

Internationalization/Localization
===
Given our project is local to the UCF Orlando campus, there are no plans for supporting anything beyond local usage. Our website's primary supported language will be English, but we do have tentative plans for including an additional language option, Spanish.

Input/Output
===
The system will be receiving input from UCF Parking Services and will be reading and writing to Amazon's DynamoDB. At this time we have no intention of storing any data input by a user.

Fault Tolerance
===
Since we're using 3rd party data, we must insure that we have a very narrow fault tolerance in respects to the data we allow our system to digest. If any changes to the source of our data is made, we have to be able to verify that the data we are receiving is still valid. If a change has been made that breaks the system, our error processing needs to be able to handle it and notify us, the development team.

Error Processing
===
If the system receives invalid data from a 3rd party, it cannot commit them to a valid log file. It must be processed and stored for debugging. If at some point the incoming data stream changes is such a way, the system must go into a safety/lockdown state that prevents valid logs from corruption and invalid data from being displayed to the user.


Architectural Feasibility
===
As we near a completed architecture, we will evaluate our architecture as a whole and update this section.

Overengineering
===
We currently do not have plans to overengineer our product as a whole. The intent for the popular hours calculator is to build it in such a way that it can be functional independent of input source... In other words, it should functional outside of the scope of our project with minimal modifications.

Build-vs-Buy Decisions
===
Rather than build a server to host our program, we decided to outsource virtually all of our program's hardware requirements. We're using Lambda to run our application and will be using Amazon's DynamoDB service to store our permanent data/files.

Reuse
===
We do not currently have any plans for reusing any part of our project elsewhere. As we develop it, we will do our best to compartmentalize our code to make parts reusable in other projects.

Change Strategy
===
Assuming we embrace the agile mindset, any changes we make should be embraced with minimal friction.
