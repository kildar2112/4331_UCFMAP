Responsibilities
==

| Name | Responsibility | How it Contributed |
|----|------------|--------|
| Nick P | *Completed* work on SVG, Managed repo structure, Continued updates architecture.md to reflect additional design changes, setup AWS S3 and Lambda, added additional tests | A cleaner SVG means reduced clutter, file size, and improved performance. S3 provided us with a web host that allows for more robust content. Updating our architecture was necessary to more accurately reflect the final project. Lambda allows us to run functions for the website without our own dedicated server. Both AWS services required configuration for group access. |
| Pablo T | Created handler.py to test and run the scraper in Lambda AWS, packaged all the dependencies for the scraper to work in lambda, created the serverless.yml to configure Lambda, and set scraper to run daily using CloudWatch | Our code will be executed using Lambda, so learning to use it and setting it up is important to the project. The scraper also must be run daily to store information about the garages. Importing and configuring the dependencies to Lambda allows the scraper to function. |
| Alexander N | Added modal with Google Maps functionality to website, created Demonstration | Having every garage be clickable with a modal pop-up that displays more detailed information and Google maps is a core feature. |
| Brandon C | UML Diagrams, Automated Testing | Updated UML diagrams for classes and their methods. Updated the automated testing for JavaScript classes. Created a test to validate the data obtained from Scraper.js. Updated Scraper.js to have the most up to date Garage class |
