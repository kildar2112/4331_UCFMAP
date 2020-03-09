// This program reads the garage information form UCF Parking Services (http://secure.parking.ucf.edu/GarageCount/iframe.aspx)
// and writes the information to a .csv file

// Cheerio and request are used here to access and read the parking website.
// Run npm i cheerio and nmp i request to install them if you do not have them already.

const request = require('request')
const cheerio = require('cheerio')
const fs = require('fs')
const writeStream = fs.createWriteStream("ScrapedData.csv")

// Headers for Output
writeStream.write(`Garage,Spaces Available,Percent Full \n`)

// A garage has a name, empty spots, capacity, and percent full
class Garage {
  constructor(name, emptySpots,capacity) {
		this.name = name;
		this.capacity = capacity;
		this.filledSpots = capacity-emptySpots;
		this.percentage = Math.round(this.filledSpots/capacity);
		this.acceptedPermits = ['B','C','D'];
    if(this.percentage <= 33) {
			this.color = 'green';
		}
		else if(this.percentage <= 66) {
			this.color = 'yellow';
		}
		else if(this.percentage <= 99) {
			this.color = 'red';
		}
		else if(this.percentage == 100) {
			this.color = 'black';
		}
	}

	calcPercent() {
		this.percentage = Math.round(this.filledSpots/this.capacity);
		return this.percentage;
	}

	updateColor() {
		var currentPercent = this.percentage;
		if(currentPercent <= 33) {
			this.color = 'green';
		}
		else if(currentPercent <= 66) {
			this.color = 'yellow';
		}
		else if(currentPercent <= 99) {
			this.color = 'red';
		}
		else if(currentPercent == 100) {
			this.color = 'black';
		}

		return this.color;
	}

  updateSpots() {
    scrape();
    this.updateColor();
  }
}

// Use cheerio to load the html from the website.
function fillGarageInfo(html) {
  const $ = cheerio.load(html)
  var garages = []
  var name = ''

  // Loop through every class with a dxgv tag, each of which
  // contains a name or parking data for a specific garage.
  $('.dxgv').each((index, element) => {
    const garageInfo = $(element).text()

    // garageInfo returns an array with the text inside each dxgv class. However,
    // every text in garageInfo where index % 3 == 2 is empty. For example: garage[2] is empty.
    if (index % 3 == 0)
      name = garageInfo
    else if (index % 3 == 1) {
      var fullnessInfo = garageInfo.trim().replace("\n","").split("/")
      var emptySpots = parseInt(fullnessInfo[0])
      var capacity = parseInt(fullnessInfo[1])

      garages.push(new Garage(name, emptySpots, capacity))
    }

  })

  return garages
}

// Opens the request and gets a list of garages from fillGarageInfo()
// It then writes all the information into ScrapedData
function scrape() {
  request('http://secure.parking.ucf.edu/GarageCount/iframe.aspx', (error, response, html) => {
    if (!error && response.statusCode == 200) {
      var garages = fillGarageInfo(html)

      for (var i = 0; i < garages.length; i++)
        writeStream.write(`${garages[i].name}, ${garages[i].emptySpots} / ${garages[i].capacity}, ${garages[i].percentFull}%\n`)
    }
  })
}

scrape()
