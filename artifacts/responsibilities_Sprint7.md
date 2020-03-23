Responsibilities
==

| Name | Responsibility | How it Contributed |
|----|------------|--------|
| Nick P | Continued SVG development, Created "walk to" dropdown menu list (approx. 60 items) and added contents to website, Managed repo structure for new sprint | Added labels and coordinates to SVG, opening the way to smooth Google Maps API calls; created building dropdown menu list to minimize coding time. |
| Pablo T | Updated the scraper to save information to S3 Bucket, configured permissions to let Lambda write to the bucket from a different account, configured permissions to make json files public. Created manual test for bucket and made Lambda function run every ten minutes| Our code will be executed using Lambda and must write to a shared bucket. Making our code write to the bucket and adding the required permissions is essential for the project to work. |
| Alexander N | Linked SVG to website (both in S3 bucket), formatted the website for mobile use, created Demonstration | Getting the SVG on the website through S3 means we can edit the SVG without having to re-upload the whole website, and most of our target audience will be using their phones to access the site so mobile functionality is a must-have. |
| Brandon C | Changing Garage colors on embedded map | Getting the colors of the garages to change relative to their fullness levels. Embedding the SVG inline with the HTML in order to access the garage objects |
