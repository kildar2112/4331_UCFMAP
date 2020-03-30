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
        self.percent_full = round((max_capacity - empty_spots) / max_capacity * 100)
        self.color = "black"

        if self.percent_full < 0:
            self.percent_full = 0

        if self.percent_full <= 33:
            self.color = "green"
        elif self.percent_full <= 66:
            self.color = "yellow"
        elif self.percent_full <= 99:
            self.color = "red"

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
    return garages

def getJSON(garages):
    return {
        'date' : str(datetime.datetime.now()),
        'A' : str(garages[0].percent_full),
        'B' : str(garages[1].percent_full),
        'C' : str(garages[2].percent_full),
        'D' : str(garages[3].percent_full),
        'H' : str(garages[4].percent_full),
        'I' : str(garages[5].percent_full),
        'L' : str(garages[6].percent_full),
    }


def getColors(garages):
    retval = ""

    garageNames = ["A", "B", "C", "D", "H", "I", "L"]

    for i in range(0, 7):
        objectId = "\"obj_Garage_" + garageNames[i] + "\""
        line = "document.getElementById(" + objectId + ").style.fill = \"" + garages[i].color + "\";\n"
        retval = retval + line

    return retval

def pushCurrent(garages):
    s3 = boto3.client('s3')

    bucket = 'www.ucfparkingmap.com'
    dataCurrent = getJSON(garages)
    filename = 'current.json'
    uploadByteStream = bytes(json.dumps(dataCurrent).encode('UTF-8'))

    s3.put_object(Bucket=bucket, Key=filename, Body=uploadByteStream, ACL='bucket-owner-full-control')

def pushColors(garages):
    s3 = boto3.client('s3')

    bucket = 'www.ucfparkingmap.com'
    dataColors = getColors(garages)
    filename = "colors.js"
    uploadByteStream = bytes(dataColors.encode('UTF-8'))

    s3.put_object(Bucket=bucket, Key=filename, Body=uploadByteStream, ACL='bucket-owner-full-control')

def pushLogs(garages):
    s3Resource = boto3.resource('s3')
    s3 = boto3.client('s3')

    bucket = "www.ucfparkingmap.com"
    filename = "logs.json"
    obj = s3Resource.Object(bucket, filename)
    dataCurrent = getJSON(garages)

    bodyString = str(obj.get()['Body'].read().decode('UTF-8'))

    if len(bodyString) == 0:
        bodyString = "[\n" + json.dumps(dataCurrent) + "\n]"
    else:
        bodyString = bodyString[:-2]
        bodyString = bodyString + ",\n" + json.dumps(dataCurrent) + "\n]"

    uploadByteStream = bytes(bodyString.encode('UTF-8'))

    s3.put_object(Bucket=bucket, Key=filename, Body=uploadByteStream, ACL='bucket-owner-full-control')



def scrape(event, context):
    garages = scrapeGarages();

    pushCurrent(garages)
    pushColors(garages)
    pushLogs(garages)

    return "done"
