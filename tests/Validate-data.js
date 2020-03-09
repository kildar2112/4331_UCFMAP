
function validate(garages) {
	var valid = true;
	for(var i = 0; i < garages.length; i++) {
		if(garages.capacity <= 0) {
			valid = false;
		}
		else if(garages.emptySpots < 0 || garages.emptySpots > garages.capacity) {
			valid = false;
		}
	}
	return valid;
}
