/**
 * @param {number[]} prices
 * @return {number}
 */
var maxProfit = function(prices) {
    let total = 0;
    for (let i = 1; i< prices.length; i++){
        if(prices[i] > prices[i-1]){
            total += prices[i]-prices[i-1];
        }
    }
    return total;
};

let s = [2,1,2,1,0,1,2]
console.log(maxProfit(s));
