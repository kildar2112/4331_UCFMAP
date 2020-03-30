Responsibilities
==

| Name | Responsibility | How it Contributed |
|----|------------|--------|
| Nick P | Created the popular hours graph, Continued work on popular hours calculator, Managed repo structure for new sprint. | Popular hours graph is ready for insertion into html file once calculator is done. Popular hours calculator logic laid out, pending data-point connections. |
| Pablo T | Updated the scraper to save information to S3 Bucket, configured permissions to let Lambda write to the bucket from a different account, configured permissions to make json files public. Created manual test for bucket and made Lambda function run every ten minutes| Our code will be executed using Lambda and must write to a shared bucket. Making our code write to the bucket and adding the required permissions is essential for the project to work. |
| Alexander N | Added Modals with detailed garage information and Google Maps functionality to the svg map, created demo | Having every garage be clickable with a modal pop-up that displays more detailed information and Google maps is a core feature. |
| Brandon C | Changing Garage colors on embedded map, Recommending the closest Garage | Getting the colors of the garages to change relative to their fullness levels. Embedding the SVG inline with the HTML in order to access the garage objects. Implementing code to recommend the garage closest to whichever building the user selects from the dropdown menu. |
