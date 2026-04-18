# Car Management Frontend - Angular

A beautiful, iOS-style car management application built with Angular. Features a responsive UI for managing your car collection with CRUD operations.

## Features

✨ **Beautiful iOS-like UI Design**
- Clean, modern interface with smooth animations
- Responsive grid layout for car listings
- Gradient headers and soft shadows

🔍 **Search Functionality**
- Real-time search across make, model, and year
- Instant filtering results

🚗 **CRUD Operations**
- **Create**: Add new cars via modal form
- **Read**: View all cars in beautiful card layout & detailed view
- **Update**: Edit car details with easy-to-use form
- **Delete**: Remove cars with confirmation dialog

📱 **Responsive Design**
- Works perfectly on desktop, tablet, and mobile
- Floating Action Button (FAB) for adding new cars
- Modal dialogs for viewing details and editing

🎨 **UI Components**
- Search bar with icon
- Car cards with year badges
- Detail modals with car information
- Loading spinner
- Error messages
- Empty state handling

## Technology Stack

- **Angular 17**
- **TypeScript**
- **SCSS** with advanced styling
- **RxJS** for reactive programming
- **HttpClientModule** for API calls

## Prerequisites

- Node.js (v16 or higher)
- npm or yarn
- Angular CLI (optional but recommended)

## Installation

### 1. Navigate to the FE directory
```bash
cd /Users/macbook/Documents/Java/car-project/FE
```

### 2. Install dependencies
```bash
npm install
```

### 3. Verify Backend is Running
Ensure the Spring Boot backend is running on `http://localhost:8081`
```bash
# In a separate terminal
cd /Users/macbook/Documents/Java/car-project/BE
java -jar target/car-project-0.0.1-SNAPSHOT.jar
```

## Running the Application

### Development Server
```bash
npm start
```

This will:
- Start the development server on `http://localhost:4200/`
- Automatically open the application in your browser
- Enable hot module reloading for instant updates

### Build for Production
```bash
npm run build
```

The production-ready files will be in the `dist/` directory.

## Project Structure

```
FE/
├── src/
│   ├── app/
│   │   ├── models/
│   │   │   └── car.model.ts          # Car interface
│   │   ├── services/
│   │   │   └── car.service.ts        # API service for car operations
│   │   ├── app.component.ts          # Main component logic
│   │   ├── app.component.html        # UI template
│   │   ├── app.component.scss        # Component styles (iOS design)
│   │   └── app.module.ts             # Angular module configuration
│   ├── index.html                    # Application entry point
│   ├── main.ts                       # Bootstrap file
│   └── styles.scss                   # Global styles
├── angular.json                      # Angular CLI configuration
├── tsconfig.json                     # TypeScript configuration
├── package.json                      # Dependencies
└── README.md                         # This file
```

## API Configuration

The frontend is configured to communicate with the backend at:
```
Base URL: http://localhost:8081/api/cars
```

### API Endpoints Used

| Method | Endpoint | Purpose |
|--------|----------|---------|
| GET | `/api/cars` | Fetch all cars |
| GET | `/api/cars/{id}` | Fetch specific car |
| POST | `/api/cars` | Create new car |
| PUT | `/api/cars/{id}` | Update car |
| DELETE | `/api/cars/{id}` | Delete car |

## Usage Guide

### Adding a New Car
1. Click the **"+"** button (FAB) in the bottom-right corner
2. Fill in the Make, Model, and Year fields
3. Click **"Create"**

### Searching for Cars
1. Use the search box at the top
2. Type make, model, or year
3. Results update in real-time

### Viewing Car Details
1. Click **"View"** button on any car card
2. Modal opens showing full car information

### Editing a Car
1. Click **"Edit"** button on a car card, or
2. View details and click **"Edit"** in the detail modal
3. Update the fields
4. Click **"Update"**

### Deleting a Car
1. Click **"Delete"** button on a car card, or
2. View details and click **"Delete"** in the detail modal
3. Confirm the deletion

## Styling & Theming

The application uses an iOS-inspired design system:

### Color Palette
- Primary Blue: `#007AFF`
- Success Green: `#34C759`
- Danger Red: `#FF3B30`
- Warning Orange: `#FF9500`
- Info Cyan: `#00C7FF`
- Light Background: `#F2F2F7`

### Design Elements
- Rounded corners: 12px (border-radius)
- Soft shadows for depth
- Gradient headers
- Smooth transitions (0.3s)
- Responsive typography

## Responsive Breakpoints

- **Mobile**: < 480px (single column)
- **Tablet**: 480px - 768px (optimized layout)
- **Desktop**: > 768px (full grid layout)

## Browser Support

- Chrome (latest)
- Firefox (latest)
- Safari (latest)
- Edge (latest)

## Troubleshooting

### Backend Connection Error
If you see "Failed to load cars" error:
1. Verify the backend is running on port 8081
2. Check the API URL in `car.service.ts`
3. Ensure MySQL is running on port 3506

### Port Already in Use
If port 4200 is in use:
```bash
ng serve --port 4201
```

### Dependency Issues
```bash
rm -rf node_modules package-lock.json
npm install
```

## Development Tips

### Hot Module Reloading
Changes to files are automatically reloaded. Just save your file and the browser updates instantly.

### Angular DevTools
Install Angular DevTools Chrome Extension for better debugging:
https://angular.io/guide/devtools

### Console Debugging
Open DevTools (F12) to see:
- Network requests to backend
- Application errors
- Component state

## Performance Optimization

- Tree-shaking enabled in production build
- Lazy loading ready (can be added)
- OnPush change detection ready
- Small bundle size (~150KB gzipped)

## Future Enhancements

- [ ] Add car image uploads
- [ ] Add filtering by make/year
- [ ] Add sorting options
- [ ] Add pagination
- [ ] Add dark mode
- [ ] Add car statistics dashboard
- [ ] Add export to CSV/PDF

## Contributing

Feel free to modify and extend the application:
1. Create new components in `src/app/components/`
2. Add new services in `src/app/services/`
3. Update styles in `*.scss` files

## License

This project is open source and available for personal use.

## Support

For issues or questions:
1. Check the troubleshooting section
2. Review the console for error messages
3. Verify backend connectivity

---

**Happy Car Managing! 🚗**
