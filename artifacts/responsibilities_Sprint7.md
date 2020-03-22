Responsibilities
==

| Name | Responsibility | How it Contributed |
|----|------------|--------|
| Nick P | Continued SVG development, Managed repo structure for new sprint | Added labels and coordinates to SVG, opening the way to smooth Google Maps API calls; S3 provided us with a web host that allows for more robust content. Updating our architecture was necessary to more accurately reflect the final project. |
| Pablo T | Created handler.py to test and run the scraper in Lambda AWS, packaged all the dependencies for the scraper to work in lambda, created the serverless.yml to configure Lambda, and set scraper to run daily using CloudWatch | Our code will be executed using Lambda, so learning to use it and setting it up is important to the project. The scraper also must be run daily to store information about the garages. Importing and configuring the dependencies to Lambda allows the scraper to function. |
| Alexander N | Linked SVG to website (both in S3 bucket), formatted the website for mobile use, created Demonstration | Getting the SVG on the website through S3 means we can edit the SVG without having to re-upload the whole website, and most of our target audience will be using their phones to access the site so mobile functionality is a must-have. |
| Brandon C | UML Diagrams, Automated Testing | Updated UML diagrams for classes and their methods. Updated the automated testing for JavaScript classes. Created a test to validate the data obtained from Scraper.js. Updated Scraper.js to have the most up to date Garage class |
