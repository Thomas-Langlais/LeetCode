let arr = [1,2,1,3,3,2,4,5,6,5,8,6,7,7,8,4];
// take an element out at random
arr.splice(6, 1); // 4 gets taken out
let missingDup;
// how do we find the element that we took out
// let lkp = {};
// for (let i = 0; i < arr.length; i++) {
//     if (lkp[arr[i]] == undefined) {
//         lkp[arr[i]] = 1
//     }
//     else {
//         delete lkp[arr[i]];
//     }
// }
// missingDup = Object.keys(lkp)[0]

// arr.sort((a,b) => a - b);
// let counter = 0;
// for (let i = 0; i < arr.length - 1; i++) {
//     counter++;
//     if (arr[i] != arr[i+1]) {
//         if (counter == 1) {
//             missingDup = arr[i];
//             break;
//         } else {
//             counter = 0;
//         }
//     }
// }

// let sum = [1,2,1,3,3,2,4,5,6,5,8,6,7,7,8,4].reduce((a, b) => a + b, 0);
// for (let i = 0; i < arr.length; i++) {
//     sum -= arr[i];
// }
// missingDup = sum; 

missingDup = 0;
for (let i = 0; i < arr.length; i++) {
    missingDup ^= arr[i];
}

console.log(missingDup);