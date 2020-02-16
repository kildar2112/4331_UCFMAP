Program Organization
===

![System_context_diagram](https://github.com/kildar2112/4331_UCFMAP/blob/master/artifacts/imgs/System_context_diagram.jpeg?raw=true )
In the first level of the diagram, the user, highlighted in green, accesses the website to view parking availability and travel times from the garage the user desires to park in to his or her class. The website, represented by a blue square, indicating it is software being developed, uses two existing software systems: Google Maps and UCF Parking Services. The user is able to view parking availability on the website when that information is retrieved from UCF Parking Services. Additionally, the user is able to view travel times on the website, which uses Google Maps to provide that information.


![Figure 1-2](https://github.com/kildar2112/4331_UCFMAP/blob/master/artifacts/imgs/Container_diagram.jpeg?raw=true )
The second level of the diagram, the user, highlighted in green, visits http://my.ucfparkingmap.wtf/ to view park space and travel times. The software, which is represented by the large, square outline, is represented at this level by two parts. Firstly, the Web Application portion, which is made in HTML, is where the user views parking availability and travel times. This portion also retrieves information from UCF Parking Services. The Web Application makes calls to the Google Maps API, which provides travel times and a route to class.


[Relation to User Stories](https://docs.google.com/spreadsheets/d/1M_ln6ihm26gYYpeJPvi5UznOYSBsz13smrJnPS2PgDU/edit?usp=sharing) based
on the Level 2 of the C4 Diagram above.

Major Classes
===
[Filter and Building class diagram](https://www.lucidchart.com/invitations/accept/ca94d761-6e0c-4333-9405-95fb87ec8580)
This diagram shows the links between the filter that is put over the UCF map and the buildings, garages, and route that users will be able to see.

CLASS DESCRIPTIONS

Building:
	The class that represents a UCF Building, which contains its location and the    option to enable or disable visibility on the map. It contains two public methods that turn the visibility on or off. Its location is also kept track of by a Point.

Garage:
	A type of building that records how full the garage is, which garage it is, and what permits are needed to park in it. It inherits from the Building class and contains a few private methods, which update the fullness of the garage, calculate the percentage of spots available, and change the color that represents how full a garage is (e.g. a red garage is full). In addition, its fields record what its current color is, how many spots it has, how many are available, and what permits are accepted at that garage.

Filter:
 Controls garage colors and routes and can be toggled on or off. Everything is private in this class. Each garage has a color that represents how full it is. Green means relatively empty (0% - 33%], yellow means mediumly full (33% - 66%], red means very full (66-100%), and black means completely full (100%).

Route:
The class that works with the Filter class and that finds the route from a start point to an end point. Its two public methods simply turn it on or off. Its private methods set and change the start and end points, and also find a route between those two points.

Related User Stories:

| ID | User Story | Related Class | Explanation |
|----|------------|--------|----------|
| 000 | As someone who parks at UCF, I want to see parking availability so I know which garage to park in. | Garage | Garage has a field that says how many spots are full and a method that calculates the percentage of spots taken.|
| 001 | As someone who parks at UCF, I want to see the walk time to my class so that I know when to leave. | Route, Building | Building  has a field that describes its location. Route then uses this information to give a walk time estimate.|
| 002 | As someone who parks at UCF, I want to know the bike time to class so that I know when to leave. | Route, Building | Building has a field that describes its location. Route then uses this information to give a bike time.|
| 003 | As someone who parks at UCF, I want to see a garage's peak hours so I know the best time to park. | Garage | Each garage will keep track of its peak hours. |
| 004 | As someone who parks at UCF, I want to know a route to my classroom so I can reduce my travel time. | Route | The Route class gives finds a route between to locations. |
| 005 | As someone who parks at UCF, I want to see a map of campus that shows me the buildings and garages so I can navigate the campus. | Filter, Building | Filter and Building contribute to what you see on the map. Filter shows how full a garage is by color-coding it and displays the route. Building highlights classrooms on the map which is part of the HTML code. |
| 006 | As someone who parks at UCF, I want to be able to tell a garage's fullness from a color label so that it's more clear from a glance. | Filter | The Filter class is what displays the colors that dictate how full a garage is.|
| 007 | As someone who parks at UCF, I want to know where I parked so that I don't forget. | Garage | The Garage class can store information about where the user parked.|
| 008 | As someone who parks at UCF, I want to know how many spots are left in a garage so I can determine if I can find a spot. | Garage | The Garage class keeps track of how many spots are open and how many spots are occupied, as well as the percentage of spots taken.|
| 009 | As someone who parks at UCF, I want to know which garages I can park in with my specific parking pass so that I don't get a parking ticket. | Garage | The Garage class keeps track of which parking passes are required to park in it. |
| 010 | As someone who parks at UCF, I want to be able to load the website in a web browser so I can check parking from any device. | - | This functionality is dependent on the website and HTML code.|
| 011 | As someone who uses the parking map, I want to be able to select a building or parking garage so that I can see more information and potential interactions | Building, Garage | The Building and Garage classes provide information about themselves, such as how full it is if it is a garage, and where it is if it is a building.|
| 012 | As someone who uses the parking map, I want to be able to contact the developer so that improvements can be made to the application (or to express my deepest gratitude) | - | This feature will be controlled by the website.|

Data Design
===
Data may or may not need to be stored, depending if we choose to implement a login, although this might negatively impact how easy the app is to use. If we do decide to include the option to
create accounts, information regarding the user's name, preferred place to park, and where the user parked will be stored in a databse using Firebase.

Business Rules
===

User Interface Design
===
[User Interface Diagram](https://docs.google.com/drawings/d/16DPH29ViC9o1byNa-bV2rYDFJ4SD3fNrLsuwYscEsiA/edit?usp=sharing)

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

The Map:
	The map will have clickable icons for each building and garage. The user will be able to click buildings and receive information about them, such as their fullness if it is a garage, or location and distance if it is a classroom. The garages on the map will also be color-coded to represent their fullness, so that the user will know how full a garage is before they click on it.


Related User Stories:

| ID | User Story | Related UI Component | Explanation |
|----|------------|--------|----------|
| 000 | As someone who parks at UCF, I want to see parking availability so I know which garage to park in. | Garage Availability | This section lists all the garages, along with how many spots are available, unavailable, and what percent full the garage is.|
| 001 | As someone who parks at UCF, I want to see the walk time to my class so that I know when to leave. | Walk to Class | The Walk to Class option gives an estimate, and a route if it is requested, of how long it will take to walk to a chosen location from where you are.|
| 002 | As someone who parks at UCF, I want to know the bike time to class so that I know when to leave. | Bike to Class | The Bike to Class option gives an estimate, and a route if it is requested, of how long it will take to bike to a chosen location from where you are.|
| 003 | As someone who parks at UCF, I want to see a garage's peak hours so I know the best time to park. | Map, Garage Availability | By selecting a garage on the map or in the garage list, the user will be able to see that garage's peak hours. |
| 004 | As someone who parks at UCF, I want to know a route to my classroom so I can reduce my travel time. | Show Route, Go to Google Maps| If Show Route is selected, pressing the Go to Google Maps option will show you a route to the selected location on Google Maps. |
| 005 | As someone who parks at UCF, I want to see a map of campus that shows me the buildings and garages so I can navigate the campus. | Map | The Map part of the website displays a map of the entire campus, including buildings and garages. |
| 006 | As someone who parks at UCF, I want to be able to tell a garage's fullness from a color label so that it's more clear from a glance. | Map| The Map will display all the garages, as well as color them to show how much space is left, with green being relatively empty, yellow being mediumly filled, and red being almost full. A black garage is completely full.|
| 007 | As someone who parks at UCF, I want to know where I parked so that I don't forget. | Map | The garage where the user parked will be highlighted on the Map.|
| 008 | As someone who parks at UCF, I want to know how many spots are left in a garage so I can determine if I can find a spot. | Garage Availability, Map | The Map will display color-coded garages to show which garages are full and which are empty. The Garage Availability section will display a list of how full the garages are.|
| 009 | As someone who parks at UCF, I want to know which garages I can park in with my specific parking pass so that I don't get a parking ticket. | Filter By Parking Permit | The Filter By Parking Permit dropdown menu will allow the user to select a parking permit to filter out the garages that do not accept that permit on the Map and Garage Availability section. |
| 010 | As someone who parks at UCF, I want to be able to load the website in a web browser so I can check parking from any device. | - | This functionality is dependent on the website and HTML code.|
| 011 | As someone who uses the parking map, I want to be able to select a building or parking garage so that I can see more information and potential interactions | Map | The Map will be interactive and display information about the selected building or garage.|
| 012 | As someone who uses the parking map, I want to be able to contact the developer so that improvements can be made to the application (or to express my deepest gratitude) | Contact Developer | By clicking this button, the user can contact the developer.|

Resource Management
===

Security
===

Performance
===

Scalability
===

Interoperability
===

Internationalization/Localization
===

Input/Output
===

Error Processing
===

Fault Tolerance
===

Architectural Feasibility
===

Overengineering
===

Build-vs-Buy Decisions
===

Reuse
===

Change Strategy
===
