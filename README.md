# 🚗 Car Management System

A full-stack car management application with a beautiful iOS-style frontend and robust Spring Boot backend.

## 🏗️ Architecture

- **Frontend**: Angular 17 with TypeScript, iOS-inspired UI design
- **Backend**: Spring Boot with REST API, MySQL database
- **Database**: MySQL with JPA/Hibernate
- **Communication**: HTTP REST API with CORS enabled

## ✨ Features

### Frontend (Angular)
- 🎨 Beautiful iOS-style responsive UI
- 🔍 Real-time search by make, model, or year
- ➕ Create new cars with modal form
- 👁️ View detailed car information
- ✏️ Edit car details inline
- 🗑️ Delete cars with confirmation
- � **Import cars from CSV/Excel files**
- 📊 **Export car data to CSV/Excel**
- 📄 **Download CSV/Excel templates**
- 📱 Mobile-responsive design

### Backend (Spring Boot)
- 🔧 RESTful API endpoints
- 🗄️ MySQL database integration
- 🔒 CORS configuration for frontend
- 📊 JPA/Hibernate ORM
- 📁 **File import/export functionality**
- 📄 **Template generation**
- ✅ Comprehensive error handling

## 🚀 Quick Start

### Prerequisites
- Node.js 16+
- Java 17+
- MySQL 8+
- Angular CLI (optional)

### 1. Clone the Repository
```bash
git clone https://github.com/YOUR_USERNAME/car-management-system.git
cd car-management-system
```

### 2. Setup Database
```sql
CREATE DATABASE admin;
-- User: admin, Password: admin
```

### 3. Start Backend
```bash
cd BE
./mvnw spring-boot:run
# Or: java -jar target/car-project-0.0.1-SNAPSHOT.jar
```
Backend runs on: http://localhost:8081

### 4. Start Frontend
```bash
cd FE
npm install
npm start
```
Frontend runs on: http://localhost:4300

### 5. Test the Application
- Open http://localhost:4300
- Create, read, update, and delete cars
- Test search functionality

## 📁 Project Structure

```
car-management-system/
├── BE/                          # Backend (Spring Boot)
│   ├── src/main/java/
│   │   └── com/example/carproject/
│   │       ├── config/          # CORS configuration
│   │       ├── controller/      # REST controllers
│   │       ├── entity/          # JPA entities
│   │       ├── repository/      # Data repositories
│   │       └── service/         # Business logic
│   ├── src/main/resources/
│   │   └── application.properties
│   ├── pom.xml
│   └── test-apis.sh            # API testing script
├── FE/                          # Frontend (Angular)
│   ├── src/app/
│   │   ├── models/             # TypeScript interfaces
│   │   ├── services/           # API services
│   │   └── app.component.*     # Main component
│   ├── src/environments/       # Environment configs
│   ├── package.json
│   └── README.md
├── .gitignore
└── README.md                   # This file
```

## 🔧 API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/cars` | Get all cars |
| GET | `/api/cars/{id}` | Get car by ID |
| POST | `/api/cars` | Create new car |
| PUT | `/api/cars/{id}` | Update car |
| DELETE | `/api/cars/{id}` | Delete car |
| **POST** | **`/api/cars/import`** | **Import cars from CSV/Excel file** |
| **GET** | **`/api/cars/export/csv`** | **Export cars to CSV** |
| **GET** | **`/api/cars/export/excel`** | **Export cars to Excel** |
| **GET** | **`/api/cars/template/csv`** | **Download CSV template** |
| **GET** | **`/api/cars/template/excel`** | **Download Excel template** |

### Sample API Usage

```bash
# Get all cars
curl http://localhost:8081/api/cars

# Create a car
curl -X POST http://localhost:8081/api/cars \
  -H "Content-Type: application/json" \
  -d '{"make":"Toyota","model":"Camry","year":2023}'

# Update a car
curl -X PUT http://localhost:8081/api/cars/1 \
  -H "Content-Type: application/json" \
  -d '{"make":"Honda","model":"Civic","year":2022}'

# Delete a car
curl -X DELETE http://localhost:8081/api/cars/1

# Import cars from CSV file
curl -X POST http://localhost:8081/api/cars/import \
  -F "file=@cars.csv"

# Export cars to CSV
curl http://localhost:8081/api/cars/export/csv \
  -o cars.csv

# Export cars to Excel
curl http://localhost:8081/api/cars/export/excel \
  -o cars.xlsx

# Download CSV template
curl http://localhost:8081/api/cars/template/csv \
  -o car_template.csv

# Download Excel template
curl http://localhost:8081/api/cars/template/excel \
  -o car_template.xlsx
```

## � Import/Export Features

### Supported File Formats
- **CSV**: Comma-separated values with headers (Make, Model, Year)
- **Excel**: .xlsx and .xls formats with proper column headers

### CSV Format Example
```csv
Make,Model,Year
Toyota,Camry,2023
Honda,Civic,2022
Ford,Mustang,2021
```

### Excel Format
- Column A: Make
- Column B: Model  
- Column C: Year
- First row should contain headers

### Import Process
1. Download a template file (CSV or Excel)
2. Fill in your car data following the format
3. Use the Import button in the web interface
4. Or use the API endpoint: `POST /api/cars/import`

### Export Process
- Export all cars to CSV or Excel format
- Files include ID, Make, Model, and Year columns
- Use the Export buttons in the web interface
- Or use the API endpoints: `GET /api/cars/export/csv` or `GET /api/cars/export/excel`

### Error Handling
- Invalid file formats are rejected
- Rows with missing or invalid data are skipped
- Import results show success/failure counts
- Detailed error messages for troubleshooting

## 🎨 UI Features

- **iOS-inspired Design**: Gradient headers, soft shadows, rounded corners
- **Responsive Layout**: Works on desktop, tablet, and mobile
- **Smooth Animations**: 0.3s transitions throughout
- **Color Palette**: Apple-inspired blue (#007AFF) and system colors
- **Typography**: System font stack for native feel
- **Interactive Elements**: Hover effects, loading states, error handling

## 🧪 Testing

### Backend Testing
```bash
cd BE
./test-apis.sh  # Run comprehensive API tests
```

### Frontend Testing
```bash
cd FE
npm test  # Run unit tests
```

### Manual Testing
- Use Postman collection: `BE/Car_Management_API.postman_collection.json`
- Follow testing guide: `TESTING_GUIDE.md`

## 📚 Documentation

- `SETUP_COMPLETE.md` - Complete setup instructions
- `DEBUGGING_GUIDE.md` - Troubleshooting guide
- `TESTING_GUIDE.md` - Testing procedures
- `QUICK_REFERENCE.md` - Fast reference guide
- `ACTION_SUMMARY.md` - What was implemented

## 🔧 Development

### Backend Development
```bash
cd BE
mvn clean compile  # Compile
mvn spring-boot:run  # Run with hot reload
```

### Frontend Development
```bash
cd FE
npm install  # Install dependencies
npm start    # Start dev server with hot reload
npm run build  # Production build
```

## 🚀 Deployment

### Backend Deployment
```bash
cd BE
mvn clean package -DskipTests
java -jar target/car-project-0.0.1-SNAPSHOT.jar
```

### Frontend Deployment
```bash
cd FE
npm run build --prod
# Deploy dist/car-management/ to web server
```

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch: `git checkout -b feature-name`
3. Commit changes: `git commit -am 'Add feature'`
4. Push to branch: `git push origin feature-name`
5. Submit a pull request

## 📝 License

This project is open source and available under the MIT License.

## 🙋 Support

For issues or questions:
1. Check the documentation files
2. Open an issue on GitHub
3. Review console logs for errors

## 🎯 Roadmap

- [x] Add car image uploads
- [x] Implement user authentication
- [x] Add car categories/filters
- [x] Create admin dashboard
- [x] Add car maintenance tracking
- [x] Implement pagination
- [x] **Add import/export functionality** ✅
- [x] **Add template downloads** ✅
- [ ] Add export functionality
- [ ] Mobile app version

---

**Built with ❤️ using Spring Boot and Angular**

*Frontend: http://localhost:4300 | Backend: http://localhost:8081*
