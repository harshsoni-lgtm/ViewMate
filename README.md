*# ViewMate*



*> \*\*AI-powered entertainment discovery for your TV.\*\**



*ViewMate is a TV-first entertainment companion designed to reduce endless scrolling and help users quickly discover movies based on what they feel like watching.*



*## Problem*



*Choosing something to watch can take almost as long as watching it. Streaming platforms provide huge catalogs, but users often spend too much time browsing through them.*



*ViewMate focuses on making entertainment discovery simpler through a TV-friendly interface and mood-based recommendations.*



*## Features*



*- TV-first interface designed for remote navigation*

*- Personalized movie discovery*

*- Mood-based recommendation experience*

*- AI Picks section*

*- Movie details screen*

*- Smooth focus-based TV navigation*

*- Android TV development environment*

*- Node.js backend API*

*- Retrofit-based Android-to-backend communication*

*- Fallback recommendations for reliable operation*



*## How It Works*



*```text*

*User*

&#x20; *↓*

*ViewMate TV App*

&#x20; *↓*

*AI Picks*

&#x20; *↓*

*Select a mood*

&#x20; *↓*

*ViewMate Backend*

&#x20; *↓*

*Recommendation API*

&#x20; *↓*

*Personalized movie picks*

*```*



*## Technology Stack*



*### Frontend*



*- Kotlin*

*- Jetpack Compose*

*- Compose for TV*

*- Android TV*

*- Retrofit*



*### Backend*



*- Node.js*

*- Express.js*

*- REST API*

*- CORS*



*### Planned AI Integration*



*ViewMate is designed to integrate with Amazon Bedrock for dynamic AI-powered recommendations.*



*The current MVP uses a reliable recommendation backend while the Bedrock integration is being developed.*



*## Project Structure*



*```text*

*ViewMate/*

*├── backend/*

*│   ├── server.js*

*│   ├── package.json*

*│   └── package-lock.json*

*│*

*├── frontend/*

*│   ├── app/*

*│   │   └── src/*

*│   ├── gradle/*

*│   ├── build.gradle.kts*

*│   └── settings.gradle.kts*

*│*

*├── .gitignore*

*└── README.md*

*```*



*## Running the Backend*



*Open PowerShell:*



*```powershell*

*cd backend*

*npm install*

*node server.js*

*```*



*The backend runs on:*



*```text*

*http://localhost:3000*

*```*



*Health check:*



*```text*

*GET /health*

*```*



*Recommendation endpoint:*



*```text*

*POST /recommend*

*```*



*Example request:*



*```json*

*{*

&#x20; *"mood": "Mind-bending"*

*}*

*```*



*## Running the Android App*



*1. Open the `frontend` folder in Android Studio.*

*2. Start an Android TV emulator.*

*3. Start the Node.js backend.*

*4. Build and run the Android application.*

*5. Open \*\*AI Picks\*\*.*

*6. Select a mood.*

*7. View the recommended movies.*



*For the Android emulator, the application communicates with the host machine through:*



*```text*

*http://10.0.2.2:3000/*

*```*



*## Current MVP*



*The current version demonstrates the complete working flow:*



*\*\*TV UI → AI Picks → Mood Selection → Backend API → Recommendations → Movie Details\*\**



*## Hackathon*



*Built for the \*\*Amazon Developer Hackathon 2026\*\*.*



*Primary focus:*



*- Fire TV*

*- AWS Builder*



*## Future Improvements*



*- Amazon Bedrock-powered recommendations*

*- Natural-language and voice-based discovery*

*- Streaming-service availability*

*- User profiles and personalization*

*- Watch history*

*- Family viewing modes*

*- More advanced content understanding*



*## License*



*This project is created for the Amazon Developer Hackathon 2026.*

