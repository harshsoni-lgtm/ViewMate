const express = require("express");
const cors = require("cors");

const app = express();
const PORT = process.env.PORT || 3000;

app.use(cors());
app.use(express.json());

app.get("/health", (req, res) => {
    res.json({
        status: "ok",
        service: "ViewMate Backend"
    });
});

app.post("/recommend", (req, res) => {
    const { mood } = req.body;

    const recommendations = {
        "Mind-bending": [
            "Inception",
            "Interstellar",
            "Blade Runner 2049"
        ],
        "Action": [
            "Mad Max",
            "Gladiator",
            "John Wick",
            "The Batman"
        ],
        "Sci-Fi": [
            "Interstellar",
            "Inception",
            "Dune",
            "Blade Runner 2049"
        ],
        "Family": [
            "The Incredibles",
            "Toy Story",
            "Paddington",
            "The Lion King"
        ]
    };

    const movies = recommendations[mood] || [];

    res.json({
        mood,
        recommendations: movies
    });
});

app.listen(PORT, "0.0.0.0", () => {
    console.log(`ViewMate backend running on http://localhost:${PORT}`);
});