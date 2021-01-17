/**
 * @param {number[]} prices
 * @return {number}
 */
var maxProfit = function(prices) {
    let minVal = Number.MAX_VALUE;
    let max = 0;
    for (let i = 0; i < prices.length; i++) {
        if (prices[i] < minVal) {
            minVal = prices[i];
        } else if (prices[i] - minVal > max) {
            max = prices[i] - minVal
        }
    }
    return max;
};


//got too focused with the times that I could have done a one pass
// and looked to find the cheapest price and with that value, find the best profit
let prices = [2,4,1]
console.log(maxProfit(prices));