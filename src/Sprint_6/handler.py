# This is the website scraper for where2park. BeautifulSoup is used
# to read the HTML of the parking website and get the information of each garage, so
# you might have to install it to run this.

import boto3
import json
import datetime
import requests
from bs4 import BeautifulSoup as soup

class Garage:
    def __init__(self, name, empty_spots, max_capacity):
        self.name = name
        self.empty_spots = empty_spots
        self.max_capacity = max_capacity
        self.percent_full = 100 - round(empty_spots / max_capacity * 100)

        if self.percent_full < 0:
            self.percent_full = 0

    def __str__(self):
        return self.name + "\t" + str(self.empty_spots) + "/" + str(self.max_capacity) + "\t" + str(self.percent_full) + "%\n"

# Opens a request to the parking website and gets the HTML
def getPageHTML():
    garageCount_url = 'http://secure.parking.ucf.edu/GarageCount/iframe.aspx';
    page_html = requests.get(garageCount_url)
    return page_html

# Returns an array in which every element contains the HTML for a garage. For example,
# array[0] contains the HTML for Garage A
def getHTMLForAllGarages(page_html):
    page_soup = soup(page_html.content, "html.parser")
    return page_soup.findAll("tr", {"class" : "dxgvDataRow_DevEx"})

# Removes the newlines, spaces, and carriage returns from the garage_space text
# Returns an array with two elements: the empty spots and the max capacity
def formatGarageSpace(garage_space):
    garage_space = garage_space.replace("\r", "")
    garage_space = garage_space.replace("\n", "")
    garage_space = garage_space.replace(" ", "")

    return garage_space.split("/")

# Creates a list of garages, which each contain their name and fullness
def generateGarages(garage_list):
    garages = []
    for garage in garage_list:
        garage_information = garage.findAll("td", {"class" : "dxgv"})

        garage_name = garage_information[0].text
        garage_space = garage_information[1].text
        garage_space = formatGarageSpace(garage_space)

        garages.append(Garage(garage_name, int(garage_space[0]), int(garage_space[1])))

    return garages

# Method that calls all the relevant methods to scrape data
def scrapeGarages():
    page_html = getPageHTML()
    garage_list = getHTMLForAllGarages(page_html)
    garages = generateGarages(garage_list)
    return {
        'date' : str(datetime.datetime.now()),
        'Garage A' : str(garages[0].percent_full),
        'Garage B' : str(garages[1].percent_full),
        'Garage C' : str(garages[2].percent_full),
        'Garage D' : str(garages[3].percent_full),
        'Garage H' : str(garages[4].percent_full),
        'Garage I' : str(garages[5].percent_full),
        'Garage Libra' : str(garages[6].percent_full),
    }

def saveFile(bucket, filename, data):
    s3 = boto3.resource('s3')
    object = s3.Object(bucket, filename)
    object.put(Body = json.dumps(data))

def scrape(event, context):
    data = scrapeGarages()
    filename = f"garageInfo-{data['date']}"
    saveFile('garage_info', filename, data)
