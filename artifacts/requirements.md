# Requirements

| Requirement ID | User Story ID | Requirement | Acceptance Criteria | Effort | Priority | Status |
|----------------|---------------|-------------|---------------------|--------|----------|--------|
| 000 | 000 | Website must pull parking information from "UCF Campus Map and Parking Guide" | Data on our website must match up-to-date data from "UCF Campus Map and Parking Guide" | 8 | Necessary | Working |
| 001 | 001 | Upon request, the time to walk from a garage to a user-selected building should be displayed | From a garage's details menu, the user should be able to select "walk to" and a building, and the time to walk should be displayed | 13 | Desirable | Planned |
| 002 | 002 | Upon request, the time to bike from a garage to a user-selected building should be displayed | From a garage's details menu, the user should be able to select "bike to" and a building, and the time to bike should be displayed | 13 | Desirable | Planned |
| 003 | 005 | Website must display a map of UCF and its parking garages | The UCF map must be visible and show all of the non-residential garages | 5 | Necessary | Working |
| 004 | 006 | Garages on the map should change color relative to the their current saturation levels | At any point in time, an individual garage's color should reflect the color assigned to its saturation level | 8 | Important | Planned |
| 005 | 008 | Garages details must display how many spots are available in that garage | The garage details will display the same availability data as defined in requirement #000. | 5 | Necessary | Planned |
| 006 | 010 | Website must to be able to open from a web browser | Given the appropriate URL, the website loads without incident from testing browser | 3 | Necessary | Verified |
| 007 | 010 | Website should be formatted for Desktop and Mobile | The website functions are available for use on both Desktop and Mobile devices | 3 | Desirable | Planned |
| 008 | 003 | System shall generate alerts when data cannot be processed. | If system rejects incoming data or an internal calculation results in an out of bounds solution, the developers will receive and alert | 13 | Necessary | Planned |
| 009 | 003 | System shall display saturation over time graph on an individual garages' page. | When the user views the page of an individual garage, they shall see a saturation over time graph. | 13 | Necessary | Working |
| 010 | 003 | System must reject incoming input that is verifiably invalid (ex: data out of predefined  bounds, wrong type, etc.) | A system pause shall be conducted and requirement #008 activated when invalid data is passed to system. | 21 | Necessary | Planned |
| 011 | 003 | [Internal] System should update saturation graph via adjustable time interval | System test verifies successful update flag | 5 | Important | Working |
| 012 | 003 | System must store incoming data long term for analysis/computations | Data is stored in such a manner that it can be retrieved by system functions and read by developers | 21 | Necessary | Working |
| 013 | 009 | Garages must store the permit types for which it has parking spaces available | Given a driver viewing the homepage, when they select a garage, then the permit types permitted in the garage are displayed. | 3 | Necessary | Working |
| 014 | 004 | Upon request, the user shall be given a route from the garage to the building they selected | From a garage's details menu, the user should be able to select "walk to" and a building, and the path to that building should be displayed | 13 | Necessary | Planned |
| 015 | 011 | The user should be able to select a garage to gain more information about that garage | When the user clicks or taps on a garage, the details window for that garage is shown. | 13 | Necessary | Planned |
| 016 | 012 | The user should be able to email the developers to provide feedback | When the user is viewing the map, a "contact developer" email link is clickable and working | 4 | Desirable | Satisfied |
| 017 | 013 | The user should be reminded that using their phone while driving is dangerous | When the user loads the website, they are forced to agree to not operate their phones while driving before using the map | 1 | Important | Satisfied | 
