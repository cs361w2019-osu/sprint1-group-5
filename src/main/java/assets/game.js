var isSetup = true;
var placedShips = 0;
var game;
var shipType;
var vertical;
var sonarUse = 0;
var sonarMode = false;

function makeGrid(table, isPlayer) {
    for (i=0; i<10; i++) {
        let row = document.createElement('tr');
        for (j=0; j<10; j++) {
            let column = document.createElement('td');
            column.addEventListener("click", cellClick);
            row.appendChild(column);
        }
        table.appendChild(row);
    }
}

function sonar()
{
    if (sonarUse < 2)
    {
        sonarMode = true;
        sonarUse++;
    }
    else
    {
        document.getElementById("sonar").style.opacity=0;
        document.getElementById("sonar").removeEventListener("click", sonar);
    }
}

function markHits(board, elementId, surrenderText) {
    board.attacks.forEach((attack) => {
        let className;
        if (attack.result === "MISS")
            className = "miss";
        else if (attack.result === "HIT")
            className = "hit";
        else if (attack.result === "SUNK")
        {
            console.log("HIT!");
            //className = "hit";
            //if ship sunk, make sonar available
            if (sonarUse < 2)
           { document.getElementById("sonar").style.opacity=1;
            document.getElementById("sonar").addEventListener("click", sonar);}
            className = "sunk";
         }
        else if (attack.result === "CAPTAIN")
            className = "captain";
        else if (attack.result === "SURRENDER")
            alert(surrenderText);
        document.getElementById(elementId).rows[attack.location.row-1].cells[attack.location.column.charCodeAt(0) - 'A'.charCodeAt(0)].classList.add(className);
    });
}

function redrawGrid() {
    Array.from(document.getElementById("opponent").childNodes).forEach((row) => row.remove());
    Array.from(document.getElementById("player").childNodes).forEach((row) => row.remove());
    makeGrid(document.getElementById("opponent"), false);
    makeGrid(document.getElementById("player"), true);
    if (game === undefined) {
        return;
    }
        game.playersBoard.ships.forEach((ship) => ship.occupiedSquares.forEach((square) => {
            document.getElementById("player").rows[square.row-1].cells[square.column.charCodeAt(0) - 'A'.charCodeAt(0)].classList.add("occupied");
        }));
        markHits(game.opponentsBoard, "opponent", "You won the game");
        markHits(game.playersBoard, "player", "You lost the game");

}

var oldListener;
function registerCellListener(f) {
    let el = document.getElementById("player");
    for (i=0; i<10; i++) {
        for (j=0; j<10; j++) {
            let cell = el.rows[i].cells[j];
            cell.removeEventListener("mouseover", oldListener);
            cell.removeEventListener("mouseout", oldListener);
            cell.addEventListener("mouseover", f);
            cell.addEventListener("mouseout", f);
        }
    }
    oldListener = f;
}

function cellClick() {
    let row = this.parentNode.rowIndex + 1;
    let col = String.fromCharCode(this.cellIndex + 65);
    if (isSetup) {
        sendXhr("POST", "/place", {game: game, shipType: shipType, x: row, y: col, isVertical: vertical}, function(data) {
            game = data;
            redrawGrid();
            placedShips++;
            document.getElementsByClassName("cancel")[0].style.opacity = 0;
            document.getElementsByClassName("cancel")[1].style.opacity = 0;
            document.getElementsByClassName("cancel")[2].style.opacity = 0;
            if (placedShips == 3) {
                isSetup = false;
                registerCellListener((e) => {});
            }
        });
    }
    //if we click cell while using sonar...
  else if (sonarMode == true)
    {
        var ships = 3;
        var oSquare;
        console.log(row);
        console.log(col);
        var found = false;

        //for all three ships opponent has
        for (var i = 0; i < 3; i++)
        {
            shipSquares = game.opponentsBoard.ships[i].occupiedSquares.length;
            //for all the opponent's occupied squares
            for (var s = 0; s < shipSquares; s++)
            {

                var square = game.opponentsBoard.ships[i].occupiedSquares[s];
                oSquare = square;
                var shipRow = square.row;
                console.log("SHIP");
                console.log(shipRow);
                console.log(shipCol);
                var shipCol = square.column;
                //check if the clicked square matches one of the occupied squares
                if (shipRow == row && shipCol == col)
                {
                     found = true;
                }

            }
            console.log("STATUS:");
            console.log(found);
        }
        //change square's color depending on if the square is occupied
        if (found == true)
                    {
                        document.getElementById("opponent").rows[row-1].cells[col.charCodeAt(0) - 'A'.charCodeAt(0)].classList.add("sonar_occupied");
                    }
          else
                        document.getElementById("opponent").rows[row-1].cells[col.charCodeAt(0) - 'A'.charCodeAt(0)].classList.add("sonar_empty");


        sonarMode = false;
    }
    else
    {
        sendXhr("POST", "/attack", {game: game, x: row, y: col}, function(data) {
            game = data;
            redrawGrid();
        })
    }
}

function sendXhr(method, url, data, handler) {
    var req = new XMLHttpRequest();
    req.addEventListener("load", function(event) {
        if (req.status != 200) {
            alert("Cannot complete the action");
            return;
        }
        handler(JSON.parse(req.responseText));
    });
    req.open(method, url);
    req.setRequestHeader("Content-Type", "application/json");
    req.send(JSON.stringify(data));
    console.log(data)
}

function place(size) {
    return function() {
        let row = this.parentNode.rowIndex;
        let col = this.cellIndex;
        vertical = document.getElementById("is_vertical").checked;
        let table = document.getElementById("player");
        for (let i=0; i<size; i++) {
            let cell;
            if(vertical) {
                let tableRow = table.rows[row+i];
                if (tableRow === undefined) {
                    // ship is over the edge; let the back end deal with it
                    break;
                }
                cell = tableRow.cells[col];
            } else {
                cell = table.rows[row].cells[col+i];
            }
            if (cell === undefined) {
                // ship is over the edge; let the back end deal with it
                break;
            }
            if (size == 2){
                cell.classList.toggle("placed_minesweeper");
                cell.classList.toggle("hover_minesweeper");

            }
            else if (size == 3){
                cell.classList.toggle("placed_destroyer");
                cell.classList.toggle("hover_destroyer");
            }
            else if (size == 4){
                cell.classList.toggle("placed_battleship");
                cell.classList.toggle("hover_battleship");
            }
        }
    }
}

function initGame() {
    makeGrid(document.getElementById("opponent"), false);
    makeGrid(document.getElementById("player"), true);
    document.getElementById("place_minesweeper").addEventListener("click", function(e) {
        shipType = "MINESWEEPER";
        document.getElementById("c1").style.opacity = 1;
       registerCellListener(place(2));
    });
    document.getElementById("place_destroyer").addEventListener("click", function(e) {
        shipType = "DESTROYER";
        document.getElementById("c2").style.opacity = 1;
       registerCellListener(place(3));
    });
    document.getElementById("place_battleship").addEventListener("click", function(e) {
        shipType = "BATTLESHIP";
        document.getElementById("c3").style.opacity = 1;
       registerCellListener(place(4));
    });
    sendXhr("GET", "/game", {}, function(data) {
        game = data;
    });
};