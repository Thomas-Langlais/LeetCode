const CAT_PLAYER = 'C';
const MOUSE_PLAYER = 'M';
const FLOOR = '.';
const WALL = '#';
const FOOD = 'F';

/**
 * @param {string[]} grid
 * @param {number} catJump
 * @param {number} mouseJump
 * @return {boolean}
 */
var canMouseWin = function(grid, catJump, mouseJump) {
    let availableSpaces = 0, cache = {};
    let mouseCoordinates, catCoordinates, fX, fY;
    let directions = [[0,1],[0,-1],[1,0],[-1,0]];

    // find the init coordinates
    for (let i = 0; i < grid.length; i++) {
        for (let j = 0; j < grid[i].length; j++) {
            if (grid[i][j] !== WALL) availableSpaces += 1;
            if (grid[i][j] === CAT_PLAYER) catCoordinates = [i,j];
            else if (grid[i][j] === MOUSE_PLAYER) mouseCoordinates = [i,j];
            else if (grid[i][j] === FOOD) [fX, fY] = [i,j];
        }
    }
    let invalid= ([x,y]) => (x < 0 || x >= grid.length || y < 0|| y >= grid[0].length) ? true : grid[x][y] === WALL;

    let dp = function(turn, [mX, mY], [cX, cY]) {
        let catsTurn = Boolean(turn % 2);
        let state = [turn, [[mX, mY], [cX, cY]]].toString();
        let maxDistance = catsTurn ? catJump : mouseJump;
        
        if (cache[state] === undefined) {
            if ((cX === mX && cY === mY) ||
                (cX === fX && cY === fY) ||
                turn >= 70)
                return cache[state] = catsTurn;
            if (mX === fX && mY === fY)
                return cache[state] = (!catsTurn);
            // try staying still
            if (dp(turn+1, [mX, mY], [cX, cY]) === false)
                return cache[state] = true;
            for (const [dx, dy] of directions) {
                for (let jump = 1; jump <= maxDistance; jump++) {
                    let newState= catsTurn ? [cX + dx * jump, cY + dy * jump] : [mX + dx * jump, mY + dy * jump];
                    if(invalid(newState) || cache[state])
                        break
                    if(dp(
                        turn + 1,
                        catsTurn ? [mX, mY] : newState,
                        catsTurn ? newState : [cX, cY]) === false)
                        return cache[state] = true
                }
            }
            cache[state] = false;
        }
        return cache[state];
    };

    return dp(0, mouseCoordinates, catCoordinates);
};

let grid = ["C...#","...#F","....#","M...."], catJump = 2, mouseJump = 5;
console.table(grid);
console.log(canMouseWin(grid, catJump, mouseJump));
grid = ["####F","#C...","M...."], catJump = 1, mouseJump = 2;
console.table(grid);
console.log(canMouseWin(grid, catJump, mouseJump));
grid = ["CM......","#######.","........",".#######","........","#######.","........","F#######"], catJump = 1, mouseJump = 1;
console.table(grid);
console.log(canMouseWin(grid, catJump, mouseJump));
grid = ["..#.M...","#......#","#F..#...","#..#...#","........","....##C.","..#....#","........"], catJump = 4, mouseJump = 3;
console.table(grid);
console.log(canMouseWin(grid, catJump, mouseJump));