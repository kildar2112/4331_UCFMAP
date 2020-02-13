Program Organization
===
[Architectural Diagram](https://docs.google.com/drawings/d/1c-6kSGmjtPCjN_mEDOcLGAIN6KLTDg-4bDjwdkh7MEM/edit?usp=sharing)

Architectural Description:
In the first level of the diagram, the user, highlighted in green, accesses the website to view parking availability and travel times from the garage the user desires to park in to his or her class. The website, represented by a blue square, indicating it is software being developed, uses two existing software systems: Google Maps and UCF Parking Services. The user is able to view parking availability on the website when that information is retrieved from UCF Parking Services. Additionally, the user is able to view travel times on the website, which uses Google Maps to provide that information.
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


[Related User Stories](https://docs.google.com/spreadsheets/d/1k7q2BV7jlDt9QNjrzUYfFlQOx1ptZoENrk4T_eiTI3E/edit?usp=sharing)

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


[Related User Stories](https://docs.google.com/spreadsheets/d/176kv5p055fJC5ESXw7EtPdeijBrr9F2iLoWR1RY9rsE/edit?usp=sharing)

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
