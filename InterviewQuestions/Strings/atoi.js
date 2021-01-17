/**
 * @param {string} s
 * @return {number}
 */
var old = function(s) {
    let integer = 0;
    let applyArithmetics;
    let limit = s.length;
    // min = -(2**31), max = (2**31) - 1
    let min = -2147483648, max = 2147483647;
    // parse the number until we reach a non numeric character
    for (let i = 0; i < limit; i++) {
        // iterate over the initial whitespaces
        if (!traversedWhitespace) {
            // code 32 is the white space in utf-16
            if (s.charCodeAt(i) == 32) continue;
            // set the flag to not enter this condition again if it was not a whitespace
            traversedWhitespace = true;
            // configure teh arithmetics for whether there is a signed component on the number
            if (s[i] === '-') {
                applyArithmetics = num => -num;
                i++;
            } else {
                applyArithmetics = num => num;
                if (s[i] === '+') i++;
            }
            // get the next whitespace from the current index
            limit = s.indexOf(' ', i);
            if (limit == -1) limit = s.length;
        }
        // find the char code and check if it's a valid charater aka 0-9
        // 48 is the utf-16 code for 0
        let code = s.charCodeAt(i);
        // it was a number character so push to the cleaned string
        if (!(48 <= code && code <= 57)) break;
        // take the code equivalent decimal number
        // and add it to the resulting number
        // add the decimal exponent as we are starting from the left
        integer += applyArithmetics((code - 48) * 10 ** (limit - 1 - i));
        // clamp the integer if they exceed the min and max and values
        if (integer > max) return max;
        if (integer < min) return min;
    }
    return integer;
};

// maxExit = (2**31) / 10, max = (2**31) - 1, 7 because of the digit in the 10**0 position of max
let maxExit = 214748364, max = 2147483647, newDigitExit = 7;

/**
 * @param {string} s
 * @return {number}
 */
var myAtoi = function(s) {
    let integer = 0;
    let sign;
    let i = 0;
    // edge cases
    if (s.length == 0) return integer
    // iterate over the leading whitespace
    while (i < s.length && s[i] === ' ') i++;
    // figure out the resulting sign
    if (s[i] == '-') {
        sign = -1;
        i++;
    } else {
        sign = 1;
        if (s[i] == '+') i++;
    }
    while (i < s.length) {
        // not chars we are looking for
        if (!('0' <= s[i] && s[i] <= '9')) break;
        // it overflows past teh 32bit signed int
        if (integer > maxExit ||
            (integer === maxExit && s[i] - '0' > newDigitExit)) {
            integer = max * sign;
            // the min is one larger than the max unsigned. adjusting
            if (sign == -1) integer -= 1;
            return integer;
        }
        // shift the number and add the new digit in the 10**0 slot
        integer = integer * 10 + (s[i++] - '0');
    }
    return integer * sign;
}

/**
 * notes.
 * I used the decimal shift in a way that made parsing decimal places difficult
 * i used a index to where the number would end and use it to calculate the 10**n addition.
 * This would fail because of the no understanding of the decimal break
 * 
 * What I shouild have done was shift the current number by multilpying by 10. (essentially shifting 4 to 40)
 * and then add the current index to the result.
 * this would not break at decimals because of shifting the number left each time, not starting at the highest decimal location
 * and working downwards.
 */

let str = "  987 and words"
console.log(myAtoi(str));
str = "-987"
console.log(myAtoi(str));
str = "words and 987"
console.log(myAtoi(str));
str = "+1953"
console.log(myAtoi(str));
str = "-2147483648";
console.log(myAtoi(str));
str = "2147483647";
console.log(myAtoi(str));
str = "-2147483649";
console.log(myAtoi(str));
str = "2147483648";
console.log(myAtoi(str));
str = "3.1415";
console.log(myAtoi(str));