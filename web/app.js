// =========================
// API CONFIGURATION
// =========================

// Local backend:
// const API_URL = "http://localhost:3000";

// Production backend:
// Replace this with your deployed backend URL.
const API_URL = "http://localhost:3000";


// =========================
// MOVIE DATA
// =========================

const recommendedMovies = [
    {
        title: "Inception",
        genre: "Mind-bending · Sci-Fi",
        tag: "VIEWMATE PICK",
        light: "#7465a8",
        dark: "#181522"
    },

    {
        title: "Interstellar",
        genre: "Sci-Fi · Adventure",
        tag: "HIGHLY RATED",
        light: "#526b9d",
        dark: "#111722"
    },

    {
        title: "Dune",
        genre: "Sci-Fi · Adventure",
        tag: "EPIC",
        light: "#b17d4f",
        dark: "#21150e"
    },

    {
        title: "The Batman",
        genre: "Action · Crime",
        tag: "DARK & GRITTY",
        light: "#6c3941",
        dark: "#170d10"
    },

    {
        title: "Blade Runner 2049",
        genre: "Sci-Fi · Mystery",
        tag: "FUTURE",
        light: "#8b704e",
        dark: "#19130e"
    }
];


const actionMovies = [
    {
        title: "Mad Max",
        genre: "Action · Adventure",
        tag: "ADRENALINE",
        light: "#b66c43",
        dark: "#21130d"
    },

    {
        title: "Gladiator",
        genre: "Action · Drama",
        tag: "CLASSIC",
        light: "#8b6847",
        dark: "#1c140d"
    },

    {
        title: "John Wick",
        genre: "Action · Thriller",
        tag: "INTENSE",
        light: "#465b7e",
        dark: "#0d1118"
    },

    {
        title: "The Batman",
        genre: "Action · Crime",
        tag: "DARK & GRITTY",
        light: "#6c3941",
        dark: "#170d10"
    }
];


const movieDescriptions = {

    "Inception":
        "A skilled thief who steals secrets through dream-sharing technology is given a chance to erase his past by planting an idea in someone's mind.",

    "Interstellar":
        "A team of explorers travels through a wormhole in space in search of a new home for humanity.",

    "Dune":
        "Paul Atreides travels to a dangerous desert world where political conflict and destiny collide.",

    "The Batman":
        "Batman investigates a series of crimes that reveal a deeper conspiracy inside Gotham City.",

    "Blade Runner 2049":
        "A young blade runner uncovers a long-buried secret that leads him to search for a legendary former officer.",

    "Mad Max":
        "In a dangerous post-apocalyptic world, a lone warrior joins a daring escape across the wasteland.",

    "Gladiator":
        "A betrayed Roman general fights his way through the gladiator arena while seeking justice.",

    "John Wick":
        "A legendary assassin is forced back into the criminal underworld after an attack on his quiet life."
};


// =========================
// NAVIGATION
// =========================

function showHome() {

    document
        .getElementById("homeScreen")
        .classList.remove("hidden");

    document
        .getElementById("aiPicksScreen")
        .classList.add("hidden");

    document
        .getElementById("detailsScreen")
        .classList.add("hidden");

    document
        .getElementById("homeNav")
        .classList.add("active");

    document
        .getElementById("aiNav")
        .classList.remove("active");

    window.scrollTo({
        top: 0,
        behavior: "smooth"
    });
}


function showAiPicks() {

    document
        .getElementById("homeScreen")
        .classList.add("hidden");

    document
        .getElementById("aiPicksScreen")
        .classList.remove("hidden");

    document
        .getElementById("detailsScreen")
        .classList.add("hidden");

    document
        .getElementById("homeNav")
        .classList.remove("active");

    document
        .getElementById("aiNav")
        .classList.add("active");

    window.scrollTo({
        top: 0,
        behavior: "smooth"
    });
}


// =========================
// SCROLL
// =========================

function scrollToRecommendations() {

    const section =
        document.getElementById(
            "recommendationsSection"
        );

    if (!section) {
        return;
    }

    section.scrollIntoView({
        behavior: "smooth"
    });
}


// =========================
// MOVIE CARD
// =========================

function createMovieCard(movie) {

    const card =
        document.createElement("article");

    card.className = "movie-card";

    const poster =
        document.createElement("div");

    poster.className = "movie-poster";

    poster.style.setProperty(
        "--poster-light",
        movie.light || "#555"
    );

    poster.style.setProperty(
        "--poster-dark",
        movie.dark || "#202024"
    );

    poster.innerHTML = `

        <div class="movie-info">

            <span class="movie-tag">
                ${movie.tag || "RECOMMENDED"}
            </span>

            <h3>
                ${movie.title}
            </h3>

            <p>
                ${movie.genre || "Movie recommendation"}
            </p>

        </div>

    `;

    card.appendChild(poster);

    card.addEventListener(
        "click",
        () => showMovieDetails(movie)
    );

    return card;
}


// =========================
// LOAD MOVIE ROW
// =========================

function loadMovieSection(
    elementId,
    movies
) {

    const container =
        document.getElementById(elementId);

    if (!container) {
        return;
    }

    container.innerHTML = "";

    movies.forEach(movie => {

        container.appendChild(
            createMovieCard(movie)
        );

    });
}


// =========================
// API MOVIE CONVERSION
// =========================

function convertApiMovie(movieName) {

    const knownMovie =
        [
            ...recommendedMovies,
            ...actionMovies
        ].find(
            movie => movie.title === movieName
        );

    if (knownMovie) {
        return knownMovie;
    }

    return {

        title: movieName,

        genre: "ViewMate recommendation",

        tag: "VIEWMATE PICK",

        light: "#555b78",

        dark: "#12131a"

    };
}


// =========================
// GET RECOMMENDATIONS
// =========================

async function getRecommendations(mood) {

    const area =
        document.getElementById(
            "recommendationArea"
        );

    const title =
        document.getElementById(
            "recommendationTitle"
        );

    const moviesContainer =
        document.getElementById(
            "recommendationMovies"
        );

    if (!area || !title || !moviesContainer) {
        console.error(
            "Recommendation elements not found in HTML."
        );

        return;
    }

    area.classList.remove("hidden");

    title.textContent =
        `${mood} recommendations`;

    moviesContainer.innerHTML = `

        <div class="loading">

            <div class="loading-dots">

                <span></span>
                <span></span>
                <span></span>

            </div>

            Finding your next watch...

        </div>

    `;

    area.scrollIntoView({
        behavior: "smooth",
        block: "start"
    });


    try {

        const response =
            await fetch(
                `${API_URL}/recommend`,
                {
                    method: "POST",

                    headers: {
                        "Content-Type":
                            "application/json"
                    },

                    body: JSON.stringify({
                        mood: mood
                    })
                }
            );


        if (!response.ok) {

            throw new Error(
                `Backend returned ${response.status}`
            );

        }


        const data =
            await response.json();


        moviesContainer.innerHTML = "";


        if (
            !data.recommendations ||
            data.recommendations.length === 0
        ) {

            moviesContainer.innerHTML = `

                <p style="color:#777">
                    No recommendations found.
                </p>

            `;

            return;
        }


        data.recommendations
            .map(convertApiMovie)
            .forEach(movie => {

                moviesContainer.appendChild(
                    createMovieCard(movie)
                );

            });

    }

    catch (error) {

        console.error(
            "ViewMate API error:",
            error
        );


        moviesContainer.innerHTML = `

            <div>

                <p style="color:#aaa; margin-bottom:10px">

                    ViewMate couldn't connect to
                    the recommendation engine.

                </p>

                <p style="color:#666; font-size:12px">

                    Make sure the ViewMate backend
                    is running correctly.

                </p>

            </div>

        `;

    }
}


// =========================
// CLEAR RECOMMENDATIONS
// =========================

function clearRecommendations() {

    const area =
        document.getElementById(
            "recommendationArea"
        );

    if (!area) {
        return;
    }

    area.classList.add("hidden");

    window.scrollTo({
        top: 0,
        behavior: "smooth"
    });
}


// =========================
// MOVIE DETAILS
// =========================

function showMovieDetails(movie) {

    document
        .getElementById("homeScreen")
        .classList.add("hidden");

    document
        .getElementById("aiPicksScreen")
        .classList.add("hidden");

    document
        .getElementById("detailsScreen")
        .classList.remove("hidden");

    document
        .getElementById("homeNav")
        .classList.remove("active");

    document
        .getElementById("aiNav")
        .classList.remove("active");


    const container =
        document.getElementById(
            "movieDetails"
        );


    if (!container) {
        return;
    }


    const description =
        movieDescriptions[movie.title] ||
        "Explore this recommendation with ViewMate and discover why it might be your next great watch.";


    container.innerHTML = `

        <div class="details-content">

            <div
                class="details-poster"
                style="
                    --poster-light:
                        ${movie.light || "#555"};

                    --poster-dark:
                        ${movie.dark || "#202024"};
                "
            >

                <div class="details-poster-title">
                    ${movie.title}
                </div>

            </div>


            <div class="details-copy">

                <span class="section-kicker">
                    VIEWMATE RECOMMENDATION
                </span>


                <h1>
                    ${movie.title}
                </h1>


                <div class="details-meta">

                    <span>
                        ${movie.genre || "Movie"}
                    </span>

                    <span>
                        ViewMate Pick
                    </span>

                    <span>
                        Recommended
                    </span>

                </div>


                <p>
                    ${description}
                </p>


                <button
                    class="primary-btn"
                    onclick="showAiPicks()"
                >

                    <span>✦</span>

                    Find another movie

                </button>

            </div>

        </div>

    `;


    window.scrollTo({
        top: 0,
        behavior: "smooth"
    });
}


// =========================
// INITIALIZE
// =========================

loadMovieSection(
    "recommendedMovies",
    recommendedMovies
);


loadMovieSection(
    "actionMovies",
    actionMovies
);


showHome();