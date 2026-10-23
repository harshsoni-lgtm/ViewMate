const http = require("http");
const fs = require("fs");
const path = require("path");

const PORT = process.env.PORT || 5173;

const mimeTypes = {
    ".html": "text/html",
    ".css": "text/css",
    ".js": "application/javascript"
};

const server = http.createServer((req, res) => {

    let filePath = req.url === "/"
        ? path.join(__dirname, "index.html")
        : path.join(__dirname, req.url);

    const ext = path.extname(filePath);

    const contentType =
        mimeTypes[ext] || "text/plain";

    fs.readFile(filePath, (error, content) => {

        if (error) {
            res.writeHead(404);
            res.end("404 - File Not Found");
            return;
        }

        res.writeHead(200, {
            "Content-Type": contentType
        });

        res.end(content);
    });

});

server.listen(PORT, "0.0.0.0", () => {
    console.log(`ViewMate web app running on port ${PORT}`);
});