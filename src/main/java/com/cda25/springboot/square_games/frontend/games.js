const square_gamesAPI = "http://localhost:8080/games";
$.getJSON(square_gamesAPI, {
    format: "json"
})
    .done(function (data) {
        if (!$("#games").length) {
            let items = [];
            $.each(data, function (key, val) {
                for (let v = 0; v < val.length; v++) {
                    items.push("<a id='" + val[v] + "'" +
                        " class='text-white h1 bg-primary border border-2 rounded p-3 text-center'" +
                        " type='button'" +
                        " onclick='createGame(" + val[v] + ")'" +
                        ">" +
                        "<span>" + val[v] + "</span>" +
                        "</a>");
                }
            });

            $("<div/>", {
                "id": "games",
                "class": "text-center",
                html: items.join("")
            }).appendTo("#gamesCatalog");
        }
    });

function createGame(gameId) {
    $("<div/>").change(function () {
        $('#newGame').removeAttr('hidden');
    });
    const square_gamesAPI_create_game = "http://localhost:8080/games";
    $("#gamesCatalog").hide();
    $.post(square_gamesAPI_create_game, {
        game: gameId,
        playerCount: "2",
        boardSize: "3"
    })
        .done(function (data) {
            if (!$("#newGame").length) {
                let item;
                $.each(data, function (key, val) {
                    items.push("<p id='" + key + "'" +
                        " class='text-white h1 bg-primary border border-2 rounded p-3 text-center'>" +
                        "<span>" + val + "</span>" +
                        "</p>");
                });

                $("<div/>", {
                    "id": "gameIn",
                    "class": "text-center",
                    html: items.join("")
                }).appendTo("#game");
            }
        });
}