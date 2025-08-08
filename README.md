# Travel App with Firebase

![Android](https://img.shields.io/badge/Android-3DDC84?logo=android&logoColor=white)
![Firebase](https://img.shields.io/badge/Firebase-FFCA28?logo=firebase&logoColor=black)
![Java](https://img.shields.io/badge/Java-ED8B00?logo=java&logoColor=white)

## Travel App Demo Video
https://github.com/tanvir-ahamed04/Travel-app-with-firebase/blob/master/travel-app.mp4

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

| Category               | Technology Used               |
|------------------------|-------------------------------|
| Programming Language   | Java                          |
| IDE                    | Android Studio Ladybug (2024.2.1 Patch 3) |
| Database               | Firebase Realtime Database    |
| Authentication         | Firebase Authentication       |
| UI Framework           | Android XML Layouts           |
| Build Tool             | Gradle 8.9                    |

## Firebase Integration
```text
Project Structure:
- /users (user data)
- /posts (destination content) 
- /hotels (accommodation info)
```

## Testing & Quality Assurance

### Testing Environment
- **Real Device**: Redmi 8 (Android 11)
- **Emulator**: BlueStacks 5
- **Connectivity Note**: VPN required for Firebase access in restricted regions

### Functional Testing Results

| Feature                | Status     | Remarks                                  |
|------------------------|------------|------------------------------------------|
| User Registration      | ✅ Working | Data saved/verified in Firebase          |
| User Login             | ✅ Working | Proper dashboard redirection             |
| Navigation Drawer      | ✅ Working | All activities accessible                |
| Best Destinations      | ✅ Working | Real-time sync with Firebase             |
| Hotel Search           | ✅ Working | Filters by city/country correctly        |
| Transport Button       | ✅ Working | DiDi app launches if installed           |
| Admin - Create Post    | ✅ Working | Instant visibility in app                |
| Admin - Manage Posts   | ✅ Working | Firebase deletions work flawlessly       |
| Admin - Manage Users   | ✅ Working | User removal confirmed                   |

## Installation
1. Clone the repository:
```bash
git clone https://github.com/tanvir-ahamed04/Travel-app-with-firebase.git
```

2. Add your Firebase configuration:
- Download `google-services.json` from Firebase Console
- Place in: `app/google-services.json`

3. Sync Gradle dependencies

## Build Requirements
- Android Studio (2024.2.1+ recommended)
- Gradle 8.9
- JDK 17

## Known Limitations
- Hotel images require manual URL entry
- Transport feature only links to external apps
- Admin/user roles managed via UI only

## Future Enhancements
- [ ] Google Maps integration
- [ ] Firebase Storage for images
- [ ] Hotel booking API connection
- [ ] Firebase Security Rules for roles

## Screenshots
| Feature | Preview |
|---------|---------|
| Login | ![Login Screen](screenshots/login.png) |
| Destinations | ![Destinations](screenshots/destinations.png) |
| Hotel Search | ![Hotels](screenshots/hotels.png) |

## License
MIT License - See [LICENSE](LICENSE) for details.
```

Key improvements:
1. Added **Technical Stack** as a clean markdown table
2. Organized **Testing** section with:
   - Environment details
   - Comprehensive test results table
3. Maintained consistent formatting throughout
4. Kept all technical details while removing personal information
5. Preserved all original functionality documentation
