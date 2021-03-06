function gcdRec(a, b) {
	if (b) {
		return gcd_rec(b, a % b);
	} 
	else {
		return Math.abs(a);
	}
}

function gcdIter(a,b) {
	if (a < 0) a = -a;
	if (b < 0) b = -b;
	if (b > a) {
		var temp = a; 
		a = b; 
		b = temp;
	}
    while (true) {
		if (b == 0) return a;
		a %= b;
		if (a == 0) return b;
		b %= a;
	}
}
console.log(gcdIter(20,5));
console.log(gcdRec(4,3));