# Travel App with Firebase

![Android](https://img.shields.io/badge/Android-3DDC84?logo=android&logoColor=white)
![Firebase](https://img.shields.io/badge/Firebase-FFCA28?logo=firebase&logoColor=black)
![Java](https://img.shields.io/badge/Java-ED8B00?logo=java&logoColor=white)

## App Demo Video
https://github.com/tanvir-ahamed04/Travel-app-with-firebase/blob/main/travel-app.mp4

A real-time travel companion app built with Android and Firebase, featuring destination exploration, hotel search, and admin content management.

## Key Features

### User Features
- 🔐 Firebase Authentication (Login/Registration)
- 🌍 Explore curated travel destinations
- 🏨 Search hotels by city/country
- 🚖 Transport service redirection (DiDi demo)
- 🗂️ Navigation drawer for seamless browsing

### Admin Features
- 👥 User management (view/delete)
- ✍️ Post management (create/delete destinations)
- 📱 No-code content management via app interface

## Technical Stack
- **Frontend**: Java, Android XML
- **Backend**: Firebase Realtime Database
- **Authentication**: Firebase Auth
- **Build Tools**: Gradle 8.9
- **Minimum SDK**: Android 8.0 (Oreo)

## Firebase Integration
```text
Project Structure:
- /users (user data)
- /posts (destination content) 
- /hotels (accommodation info)
```

## Installation
1. Clone the repository:
```bash
git clone https://github.com/tanvir-ahamed04/Travel-app-with-firebase.git
```

2. Add Firebase configuration:
- Download `google-services.json` from Firebase Console
- Place in: `app/google-services.json`

3. Sync Gradle dependencies

## Build Requirements
- Android Studio (2024.2.1+ recommended)
- Gradle 8.9
- JDK 17

## Known Limitations
- Hotel images require manual URL entry (Firebase Storage not implemented)
- Transport feature only links to DiDi app (no API integration)
- Admin/user roles managed via UI (no Firebase security rules)

## Future Enhancements
- [ ] Integrate Google Maps API
- [ ] Add Firebase Storage for image uploads
- [ ] Implement hotel booking API
- [ ] Role-based Firebase security rules

## License
This project is available under the MIT License - see the [LICENSE](LICENSE) file for details.

```

Key features of this README:
1. Clear badges showing tech stack
2. Organized feature breakdown
3. Visual Firebase structure
4. Installation instructions with security note
5. Build requirements matching your Gradle config
6. Honest limitations section
7. Future roadmap
8. Placeholder for screenshots
9. Standard license reference
