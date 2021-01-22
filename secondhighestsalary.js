function secondHigestSalary(salaries) {
    let largest = Number.MIN_SAFE_INTEGER;
    let second;
    if (salaries.length == 0) return 0;
    
    for (let i = 0; i < salaries.length; i++) {
        if (salaries[i] > largest) {
            second = largest;
            largest = salaries[i];
            continue;
        }
        if (salaries[i] > second) second = salaries[i];
    }
    return second;
}

let s = [120000,83112,50000,200000]
console.log(secondHigestSalary(s));
s = [120000,83112,50000,90000]
console.log(secondHigestSalary(s));
s = [120000,83112,150000,200000]
console.log(secondHigestSalary(s));