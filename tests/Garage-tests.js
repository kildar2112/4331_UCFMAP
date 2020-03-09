// test/Garage.js

var expect = require('chai').expect;
var garage = new Garage('test', 0, 100);

describe('#garage.calcPercent()', function() {
	context('without arguments', function() {
		it('should return garage.filledSpots / garage.capacity', function() {
			expect(garage.calcPercent()).to.equal(Math.round(garage.filledSpots/garage.capacity))
		})
	})
})

describe('#garage.updateColor()', function() {
	context('garage at 0% full', function() {
		it('should return the string', function() {
			expect(garage.updateColor()).to.equal('green')
		})
	})

  garage = new Garage('test', 33, 100);
	context('garage at 33% full', function() {
		it('should return the string \'white\'', function() {
			expect(garage.updateColor()).to.equal('green')
		})
	})

  garage = new Garage('test', 66, 100);
	context('garage at 66% full', function() {
		it('should return the string \'green\'', function() {
			expect(garage.updateColor()).to.equal('yellow')
		})
	})

  garage = new Garage('test', 99, 100);
	context('garage at 99% full', function() {
		it('should return the string \'red\'', function() {
			expect(garage.updateColor()).to.equal('red')
		})
	})

  garage = new Garage('test', 100, 100);
	context('garage at 100% full', function() {
		it('should return the string \'black\'', function() {
			expect(garage.updateColor()).to.equal('black')
		})
	})
})
