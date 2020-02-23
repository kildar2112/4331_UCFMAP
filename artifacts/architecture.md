Program Organization
===

![System_context_diagram](https://github.com/kildar2112/4331_UCFMAP/blob/master/artifacts/imgs/architecture/System_context_diagram.jpeg?raw=true )
In the first level of the diagram, the user, highlighted in green, accesses the website to view parking availability and travel times from the garage the user desires to park in to his or her class. The website, represented by a blue square, indicating it is software being developed, uses two existing software systems: Google Maps and UCF Parking Services. The user is able to view parking availability on the website when that information is retrieved from UCF Parking Services. Additionally, the user is able to view travel times on the website, which uses Google Maps to provide that information.


![Container_diagram](https://github.com/kildar2112/4331_UCFMAP/blob/master/artifacts/imgs/architecture/Container_diagram.jpeg?raw=true)
The second level of the diagram, the user, highlighted in green, visits http://my.ucfparkingmap.wtf/ to view park space and travel times. The software, which is represented by the large, square outline, is represented at this level by two parts. Firstly, the Web Application portion, which is made in HTML, is where the user views parking availability and travel times. This portion also retrieves information from UCF Parking Services. The Web Application makes calls to the Google Maps API, which provides travel times and a route to class.


[Relation to User Stories](https://docs.google.com/spreadsheets/d/1M_ln6ihm26gYYpeJPvi5UznOYSBsz13smrJnPS2PgDU/edit?usp=sharing) based
on the Level 2 of the C4 Diagram above.

Major Classes
===
![Filter and Building class diagram](https://github.com/kildar2112/4331_UCFMAP/blob/master/artifacts/imgs/UML/cd_buiding_processor.jpg?raw=true)
This diagram shows the links between the filter that is put over the UCF map and the buildings, garages, and route that users will be able to see.

CLASS DESCRIPTIONS

Building:
	The class that represents a UCF Building, which contains its location and the    option to enable or disable visibility on the map. It contains two public methods that turn the visibility on or off. Its location is also kept track of by a Point.

Garage:
	A type of building that records how full the garage is, which garage it is, and what permits are needed to park in it. It inherits from the Building class and contains a few private methods, which update the fullness of the garage, calculate the percentage of spots available, and change the color that represents how full a garage is (e.g. a red garage is full). In addition, its fields record what its current color is, how many spots it has, how many are available, and what permits are accepted at that garage.

Processor:
	The class that processes all of the data taken from the UCF Parking Services. It contains arrays that store all of the current data for the garages. This class will also send the data to S3 which is used for storing the data so we can calculate peak hours for the garages. We can also validate the data coming in to make sure that we aren't pulling in garbage data if UCF Parking Services is down for any period of time.

Related User Stories:

| ID | User Story | Related Class | Explanation |
|----|------------|--------|----------|
| 000 | As someone who parks at UCF, I want to see parking availability so I know which garage to park in. | Garage | Garage has a field that says how many spots are full and a method that calculates the percentage of spots taken. |
| 001 | As someone who parks at UCF, I want to see the walk time to my class so that I know when to leave. | Route, Building | Building  has a field that describes its location. Route then uses this information to give a walk time estimate.|
| 002 | As someone who parks at UCF, I want to know the bike time to class so that I know when to leave. | Route, Building | Building has a field that describes its location. Route then uses this information to give a bike time. |
| 003 | As someone who parks at UCF, I want to see a garage's peak hours so I know the best time to park. | Garage | Each garage will keep track of its peak hours. |
| 004 | As someone who parks at UCF, I want to know a route to my classroom so I can reduce my travel time. | Route | The Route class gives finds a route between to locations. |
| 005 | As someone who parks at UCF, I want to see a map of campus that shows me the buildings and garages so I can navigate the campus. | Filter, Building | Filter and Building contribute to what you see on the map. Filter shows how full a garage is by color-coding it and displays the route. Building highlights classrooms on the map which is part of the HTML code. |
| 006 | As someone who parks at UCF, I want to be able to tell a garage's fullness from a color label so that it's more clear from a glance. | Filter | The Filter class is what displays the colors that dictate how full a garage is. |
| 007 | As someone who parks at UCF, I want to know where I parked so that I don't forget. | Garage | The Garage class can store information about where the user parked. |
| 008 | As someone who parks at UCF, I want to know how many spots are left in a garage so I can determine if I can find a spot. | Garage | The Garage class keeps track of how many spots are open and how many spots are occupied, as well as the percentage of spots taken. |
| 009 | As someone who parks at UCF, I want to know which garages I can park in with my specific parking pass so that I don't get a parking ticket. | Garage | The Garage class keeps track of which parking passes are required to park in it. |
| 010 | As someone who parks at UCF, I want to be able to load the website in a web browser so I can check parking from any device. | - | This functionality is dependent on the website and HTML code. |
| 011 | As someone who uses the parking map, I want to be able to select a building or parking garage so that I can see more information and potential interactions. | Building, Garage | The Building and Garage classes provide information about themselves, such as how full it is if it is a garage, and where it is if it is a building. |
| 012 | As someone who uses the parking map, I want to be able to contact the developer so that improvements can be made to the application (or to express my deepest gratitude). | - | This feature will be controlled by the website. |

Data Design
===
It is our current intent to use Heroku as our application's host and to store permanent data logs using Amazon's S3 services. Due to the nature of our host's ephemeral filesystem, we'll need to frequently backup our program's data logs elsewhere. We intend on [reading from a bucket](https://www.ashishpaliwal.com/blog/2015/02/amazon-s3-reading-file-content-from-s3-bucket-in-java/) on program startup and saving logs to it at the end of a data cycle. We intend to setup the data logging in such a way that the most up to date and valid information is available for viewing on the website. In order to accomplish this we must develop the system in such a way that if invalid data is recorded, a halt will be placed on the corrupted data being displayed on the website.

Business Rules
===
There are no known business rules that apply to our project.

User Interface Design
===
![User Interface Diagram](https://github.com/kildar2112/4331_UCFMAP/blob/master/artifacts/imgs/garages/popup.jpg?raw=true)
The Map:
	The map will have clickable icons for each building and garage. The user will be able to click buildings and receive information about them, such as their fullness if it is a garage, or location and distance if it is a classroom. The garages on the map will also be color-coded to represent their fullness, so that the user will know how full a garage is before they click on it.


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
| 007 | As someone who parks at UCF, I want to know where I parked so that I don't forget. | Map | The garage where the user parked will be highlighted on the Map. |
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
Thanks to the nature of Heroku, S3, and the fact that minimal data is stored on the back end, we should be able to scale up the app to handle traffic exceeding the total population of UCF students and staff.

Interoperability
===
While we don't have any plans for the information we produce to be utilized by another party, we will be operating our program based on the information received by [UCF Parking's Garage Availability page](https://parking.ucf.edu/garage-availability/).

Internationalization/Localization
===
Given our project is local to the UCF Orlando campus, there are no plans for supporting anything beyond local usage. Our website's primary supported language will be English, but we do have tentative plans for including an additional language option, Spanish.

Input/Output
===
The system will be receiving input from UCF Parking Services and will be reading and writing to Amazon's S3. At this time we have no intention of storing any data input by a user.

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
Rather than build a server to host our program, we decided to outsource virtually all of our program's hardware requirements. We're using Heroku to run our application and will be using Amazon's S3 service to store our permanent data/files.

Reuse
===
We do not currently have any plans for reusing any part of our project elsewhere. As we develop it, we will do our best to compartmentalize our code to make parts reusable in other projects.

Change Strategy
===
Assuming we embrace the agile mindset, any changes we make should be embraced with minimal friction.
