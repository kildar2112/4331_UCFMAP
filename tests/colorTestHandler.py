import json
import boto3

def decideColor(percent):
    if percent <= 33:
        return "green"
    elif percent <= 66:
        return "yellow"
    elif percent <= 99:
        return "red"
    return "black"

def loadCurrent():
    s3Resource = boto3.resource('s3')

    bucket = "www.ucfparkingmap.com"
    filename = "current.json"
    obj = s3Resource.Object(bucket, filename)
    bodyString = str(obj.get()['Body'].read().decode('UTF-8'))

    return json.loads(bodyString)

def loadColors():
    s3Resource = boto3.resource('s3')

    bucket = "www.ucfparkingmap.com"
    filename = "colors.js"
    obj = s3Resource.Object(bucket, filename)
    bodyString = str(obj.get()['Body'].read().decode('UTF-8'))

    return bodyString

def getColorFromString(colorStr):
    index = colorStr.find(" = ")

    colorStr = colorStr[index+4:]
    endIndex = colorStr.find(";")

    color = colorStr[0:endIndex-1]

    return color


def lambda_handler(event, context):
    colorStr = loadColors()
    currentInfo = loadCurrent()
    garageNames = ['A', 'B', 'C', 'D', 'H', 'I', 'L']
    currentGarageIndex = 0

    index = colorStr.find(";")

    while index > -1:
        expectedColor = getColorFromString(colorStr)
        currentGarageName = garageNames[currentGarageIndex]

        percent = int(currentInfo[currentGarageName])
        actualColor = decideColor(percent)

        if expectedColor != actualColor:
            return False

        colorStr = colorStr[index+1:]
        index = colorStr.find(";")
        currentGarageIndex = currentGarageIndex + 1

    return True;
