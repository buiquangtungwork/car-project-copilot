# Car Management FE - Setup Guide

## Quick Start

### Prerequisites
- Node.js v16+ installed
- npm or yarn
- Backend running on port 8081

### Step-by-Step Setup

#### 1. Navigate to FE Directory
```bash
cd /Users/macbook/Documents/Java/car-project/FE
```

#### 2. Install Dependencies
```bash
npm install
```

This will install all required packages:
- Angular 17
- RxJS
- TypeScript
- Build tools

**Installation time:** ~5-10 minutes (depends on connection)

#### 3. Verify Backend
Ensure your Spring Boot backend is running:
```bash
cd /Users/macbook/Documents/Java/car-project/BE
java -jar target/car-project-0.0.1-SNAPSHOT.jar
```

Expected output: Application started on port 8081

#### 4. Start Frontend Development Server
```bash
npm start
```

Expected output:
```
✔ Compiled successfully.
...
Local:            http://localhost:4200
```

The application will automatically open in your default browser at `http://localhost:4200`

## Detailed Features

### 1. Main Screen - Car List
- Displays all cars in a responsive grid
- Shows car make, model, year
- Displays car ID
- Features 3 action buttons: View, Edit, Delete

### 2. Search Box
```
Search by: Make, Model, or Year
Example: "Toyota", "Camry", "2023"
Results update in real-time
```

### 3. Create New Car
- Click the "+" button (Floating Action Button)
- Fill in the form:
  - Make (required)
  - Model (required)
  - Year (required, must be > 0)
- Click "Create"

### 4. View Details
- Click "View" on any car card
- Modal shows all car information
- Options to Edit or Delete from detail view

### 5. Update Car
- Option 1: Click "Edit" on car card
- Option 2: View details → Click "Edit"
- Update fields in modal
- Click "Update"

### 6. Delete Car
- Click "Delete" button
- Confirm deletion
- Car is removed from list

## UI/UX Features

### iOS-Like Design
- Smooth animations
- Gradient headers
- Soft shadows and depth
- Rounded corners throughout
- Clean typography

### Responsive Layout
- **Mobile (< 480px):** Single column, optimized buttons
- **Tablet (480-768px):** 2-column grid
- **Desktop (> 768px):** 3-column grid

### User Feedback
- Loading spinner while fetching data
- Error messages with close button
- Empty state when no cars found
- Smooth transitions

## File Structure Explained

```
FE/
├── src/
│   ├── app/
│   │   ├── models/
│   │   │   └── car.model.ts              Interface defining Car object
│   │   ├── services/
│   │   │   └── car.service.ts            Handles all API calls
│   │   ├── app.component.ts              Main component with logic
│   │   ├── app.component.html            UI template
│   │   ├── app.component.scss            Styling (1000+ lines!)
│   │   └── app.module.ts                 Angular module setup
│   ├── environments/
│   │   ├── environment.ts                Dev config
│   │   └── environment.prod.ts           Production config
│   ├── index.html                        HTML entry point
│   ├── main.ts                           Angular bootstrap
│   └── styles.scss                       Global styles
├── angular.json                          Build config
├── tsconfig.json                         TypeScript config
├── package.json                          Dependencies
└── README.md                             Full documentation
```

## API Integration

### Service Configuration
File: `src/app/services/car.service.ts`

```typescript
private apiUrl = 'http://localhost:8081/api/cars';
```

### Available Methods
- `getAllCars()` - Fetch all cars
- `getCarById(id)` - Fetch specific car
- `createCar(car)` - Create new car
- `updateCar(id, car)` - Update existing car
- `deleteCar(id)` - Delete car

### API Response Format
```json
{
  "id": 1,
  "make": "Toyota",
  "model": "Camry",
  "year": 2023
}
```

## Development Commands

### Start Dev Server
```bash
npm start
```

### Build for Production
```bash
npm run build
```
Output: `dist/car-management/`

### Watch Mode (without serve)
```bash
npm run watch
```

### Run Tests
```bash
npm test
```

## Styling Details

### Colors Used
```scss
$primary-color: #007AFF       // Apple Blue
$secondary-color: #34C759     // Apple Green
$danger-color: #FF3B30        // Apple Red
$warning-color: #FF9500       // Apple Orange
$info-color: #00C7FF          // Apple Cyan
$light-gray: #F2F2F7          // Background
$medium-gray: #E5E5EA         // Borders
$dark-gray: #8E8E93           // Text secondary
```

### Typography
- Font Family: System fonts (Apple system font stack)
- Header: 32px, Weight 700
- Card Title: 20px, Weight 700
- Body Text: 16px, Weight 400-600

### Spacing
- Standard Padding: 16px, 20px, 24px
- Border Radius: 12px (main), 6-8px (smaller)
- Box Shadow: Soft shadows for depth

## Common Issues & Solutions

### Issue: Backend connection error
**Solution:**
1. Verify backend is running: `curl http://localhost:8081/api/cars`
2. Check port 8081 is not blocked
3. Review browser console (F12) for network errors

### Issue: Port 4200 already in use
**Solution:**
```bash
npm start -- --port 4201
```

### Issue: npm install fails
**Solution:**
```bash
npm cache clean --force
rm -rf node_modules
npm install
```

### Issue: Blank page on localhost:4200
**Solution:**
1. Open Developer Console (F12)
2. Check for errors
3. Refresh page (Ctrl+R or Cmd+R)
4. Try hard refresh (Ctrl+Shift+R or Cmd+Shift+R)

## Performance Tips

### For Development
- Use `npm start` for hot module reloading
- Use Chrome DevTools for debugging
- Keep console open for errors

### For Production
- Run `npm run build`
- Serves are smaller bundle sizes
- Minified and optimized code

## Browser Compatibility

✅ **Fully Supported:**
- Chrome 90+
- Firefox 88+
- Safari 14+
- Edge 90+

## Next Steps

1. **Install dependencies:** `npm install`
2. **Start backend:** Java Spring Boot app on port 8081
3. **Start frontend:** `npm start`
4. **Test operations:** Create, read, update, delete cars
5. **Customize:** Modify colors, add features as needed

## Customization

### Change Primary Color
File: `src/app/app.component.scss`
```scss
$primary-color: #YOUR_COLOR;
```

### Add New Field
1. Update `Car` interface in `models/car.model.ts`
2. Add input in `app.component.html`
3. Update backend entity and API

### Modify Grid Layout
File: `src/app/app.component.scss`
```scss
.cars-grid {
  grid-template-columns: repeat(auto-fill, minmax(250px, 1fr)); // Change to your preference
}
```

## Support

For detailed information, see:
- [README.md](README.md) - Full documentation
- [Angular Docs](https://angular.io/docs)
- Backend API docs in BE folder

---

**Happy coding! 🎉**
