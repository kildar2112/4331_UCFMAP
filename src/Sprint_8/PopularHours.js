// Include FileSystem
const fs = require('fs')
const filePath = './logs.json'


function jsonReader(filePath, cd);
{
	fs.readFile(filePath, 'utf8', (err, jsonString) =>
	{
		if (err)
			return cb && cb(err);

		try
		{
			const inputArray = JSON.parse(jsonString);
		}
		catch (err) {return cb && cb(err);}
	})
}


jsonReader(filePath, (err, logs) =>
{
	if (err)
	{
		console.log(err);
		return;
	}
	console.log(inputArray.A);
})



//
// async function getHours()
// {
// 	const response = await fetch(filePath);
// 	const data = await response.json();
// 	console.log(data);
// }

//
// console.log(JSON.parse(str).date);
// console.log(JSON.parse(str).A);
// console.log(JSON.parse(str).B);
// console.log(JSON.parse(str).C);
// console.log(JSON.parse(str).D);
// console.log(JSON.parse(str).H);
// console.log(JSON.parse(str).I);
// console.log(JSON.parse(str).L);
//
//
//
// // matrix where row = garage & col = hour of the day
// popHours[][] = new array[numOfGarages][24]
//
//
// for ( garage : popHours[][])
// {
// 	for (i = 0; i < 24; i++)
// 	{
// 		popHours[garage][i] = avgHours(garage, i);
// 	}
// }
//
//
// // acting on logs.json
// avgHours(garage, hour)
// {
// 	sumcounter = 0;
// 	runningsum = 0;
// 	// "date":"2020-03-29 HH:MM:SS.xxxxxx"
// 	for (each line in logs.java where HH == hour)
// 	{
// 		runningsum += (garage:value);
// 		sumcounter++;
// 	}
// 	return (runningsum/sumcounter);
// }
