const fetch = require("node-fetch");
var express = require("express");
var app = express();

app.listen(3000, () => {
    console.log("Server running on port 3000");
});

app.get("/api/ping", async (req, res) => {
    res.status(200).send({
        success: "true"
    })
});

app.get("/api/posts", async (req, res) => {
    if (!req.query.tags) {
        res.status(400).send({
            error: "Tags parameter is required"
        });
        return;
    }
    var tags = req.query.tags.split(",");
    var sortBy = "id";
    if (req.query.sortBy) {
        sortBy = req.query.sortBy;
        if (sortBy != "id" && sortBy != "reads" && sortBy != "likes" && sortBy != "popularity") {
            res.status(400).send({
                error: "sortBy parameter is invalid"
            });
            return;
        }
    }
    var direction = "asc";
    if (req.query.direction) {
        direction = req.query.direction;
        if (direction != "desc" && direction != "asc") {
            res.status(400).send({
                error: "direction parameter is invalid"
            });
            return;
        }
    }

    var p = [];
    for (var i = 0; i < tags.length; i++) {
        const postsJson = await fetch(`https://api.hatchways.io/assessment/blog/posts?tag=${tags[i]}`);
        var postsResults = await postsJson.json();
        if (p.length != 0) {
            for (var j = 0; j < postsResults["posts"].length; j++) {
                var dup = false;
                for (var k = 0; k < p.length; k++) {
                    if (p[k].id == postsResults["posts"][j].id) {
                        dup = true;
                        break;
                    }
                }
                if (!dup) {
                    console.log(postsResults["posts"][j]);
                    p.push(postsResults["posts"][j]);
                }
            }
        } else {
            p = p.concat(postsResults["posts"]);
        }
    }


    if (sortBy === "id") {
        p.sort((a, b) =>  {
            return parseInt(a.id) - parseInt(b.id);
        });
    } else if (sortBy === "reads") {
        p.sort((a, b) =>  {
            return parseInt(a.reads) - parseInt(b.reads);
        });
    } else if (sortBy === "likes") {
        p.sort((a, b) => {
            return parseInt(a.likes) - parseInt(b.likes);
        });
    } else {
        p.sort((a, b) =>  {
            return parseFloat(a.popularity) - parseFloat(b.popularity);
        });
    }

    if (direction === "desc") {
        p.reverse();
    }

    
    res.status(200).send({
        posts: p
    })
});

module.exports = app;