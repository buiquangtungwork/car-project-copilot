# FE-BE Integration Troubleshooting Guide

## Current Status

✅ **Backend**: Running on port 8081 with CORS enabled
✅ **API**: Responding and returning data
✅ **Frontend**: Running on port 4200

## Testing Steps

### 1. Check Backend Connectivity
```bash
# Test endpoint directly
curl http://localhost:8081/api/cars

# You should see:
[{"id":1,"make":"Toyota","model":"Camry","year":2023},...] 
```

### 2. Open Browser Console
1. Open Frontend: http://localhost:4200
2. Open DevTools: Press F12
3. Go to **Console** tab
4. Check for any error messages
5. Look for network requests to `http://localhost:8081/api/cars`

### 3. Check Network Tab
1. In DevTools, go to **Network** tab
2. Perform actions (create car, search, etc.)
3. Look for requests to `http://localhost:8081/api/cars`
4. Check response status code
   - ✅ 200: Success
   - ❌ 0: CORS error
   - ❌ 504: Backend not running

### 4. Expected Console Logs
When FE loads, you should see:
```
CarService initialized with API URL: http://localhost:8081/api/cars
Fetching all cars from: http://localhost:8081/api/cars
Successfully fetched cars: (array)
```

## Common Issues & Solutions

### Issue 1: No data on page load
**Symptoms**: Page loads but no cars displayed

**Solutions**:
1. **Hard refresh browser**: Ctrl+Shift+R (Windows) or Cmd+Shift+R (Mac)
2. **Check console logs**: Open DevTools (F12) → Console tab
3. **Verify backend running**: `curl http://localhost:8081/api/cars`
4. **Check Application tab**: 
   - Look for network requests
   - Check response body and status

### Issue 2: CORS Error
**Error**: `Access to XMLHttpRequest has been blocked by CORS policy`

**Causes**: Backend CORS not configured properly

**Solution**: Done ✅ (CORS config now in place)
- Restart backend: `java -jar target/car-project-0.0.1-SNAPSHOT.jar`

### Issue 3: Backend returns 500 error
**Symptoms**: Console shows error from backend

**Check**:
1. Is MySQL running on port 3506?
2. Is database "admin" created?
3. Check backend console for SQL errors

### Issue 4: Blank page or compile errors
**Symptoms**: Blank page, errors in console

**Solution**:
```bash
# Kill and restart frontend
pkill -f "node"
cd /Users/macbook/Documents/Java/car-project/FE
npm install
npm start
```

## Manual Testing

### Test Create Car
```bash
curl -X POST http://localhost:8081/api/cars \
  -H "Content-Type: application/json" \
  -d '{"make":"Tesla","model":"Model 3","year":2025}'
```

### Test Get All Cars
```bash
curl http://localhost:8081/api/cars
```

### Test Search (from FE)
1. Open Frontend: http://localhost:4200
2. Type in search box (e.g., "Toyota")
3. Should filter cars in real-time
4. Check console for search logs

## Frontend Development Mode

### Hot Reload
- Changes to `.ts`, `.html`, `.scss` files auto-reload
- Check browser for updates

### Debug with ng Serve
The server shows compilation status - look for:
```
✔ Compiled successfully
```

## Backend Development Mode

### Restart Backend
```bash
# Kill old process
pkill -f "java -jar"

# Restart
java -jar /Users/macbook/Documents/Java/car-project/BE/target/car-project-0.0.1-SNAPSHOT.jar
```

## Success Checklist

- [ ] Backend running on port 8081
- [ ] Frontend running on port 4200
- [ ] `curl http://localhost:8081/api/cars` returns data
- [ ] No CORS errors in browser console
- [ ] Cars displayed on page load
- [ ] Search feature filters cars
- [ ] Create button opens modal
- [ ] Can add new car successfully
- [ ] Can delete cars
- [ ] Can edit cars

## Debugging Steps

### Step 1: Verify Backend
```bash
curl -v http://localhost:8081/api/cars
# Look for:
# - HTTP/1.1 200 OK
# - Content-Type: application/json
# - Response body with car data
```

### Step 2: Check Frontend Logs
1. Open http://localhost:4200
2. Open DevTools (F12)
3. Console should show:
   - "CarService initialized..."
   - "Fetching all cars..."
   - "Successfully fetched cars..."

### Step 3: Test Create Feature
1. Click "+" button
2. Fill in form
3. Click "Create"
4. Check console for:
   - "Creating car:" log
   - "Successfully created car:" log

### Step 4: Test Search
1. Type in search box
2. Check console for filter logs
3. Cars should appear/disappear

## Log Messages Reference

### Successful Operations
```javascript
// Service initialization
CarService initialized with API URL: http://localhost:8081/api/cars

// Fetching data
Fetching all cars from: http://localhost:8081/api/cars
Successfully fetched cars: Array(3) [...]

// Creating
Creating car: {make: "Toyota", model: "Camry", year: 2023}
Successfully created car: {id: 1, make: "Toyota", ...}
```

### Error Messages
```javascript
// CORS Error
Access to XMLHttpRequest at 'http://localhost:8081/api/cars' from origin 'http://localhost:4200'
has been blocked by CORS policy

// Connection Error
Error: Failed to connect to http://localhost:8081

// HTTP Error
HTTP Error: {status: 500, statusText: "Internal Server Error", ...}
```

## Quick Fix Commands

### Restart Everything
```bash
# Stop all processes
pkill -f "java -jar"
pkill -f "npm start"
pkill -f "node"

# Restart backend
cd /Users/macbook/Documents/Java/car-project/BE
java -jar target/car-project-0.0.1-SNAPSHOT.jar &

# Restart frontend
cd /Users/macbook/Documents/Java/car-project/FE
npm start &
```

### Clean Compilation
```bash
# Frontend
cd /Users/macbook/Documents/Java/car-project/FE
npm cache clean --force
rm -rf node_modules
npm install
npm start

# Backend
cd /Users/macbook/Documents/Java/car-project/BE
mvn clean package -DskipTests
java -jar target/car-project-0.0.1-SNAPSHOT.jar
```

## Need More Help?

1. **Check application logs**:
   - Browser console (F12)
   - Backend terminal output

2. **Check network traffic**:
   - DevTools → Network tab
   - Filter by XHR requests

3. **Verify configurations**:
   - Frontend: `src/app/services/car.service.ts` - Check API URL
   - Backend: `CorsConfig.java` - Check allowed origins
   - Backend: `application.properties` - Check database config

4. **Restart services**:
   - Kill all Java processes
   - Kill all Node processes
   - Rebuild and restart fresh

---

**If you still have issues, please:**
1. Share browser console errors (screenshot or text)
2. Share backend terminal output
3. Share network tab response details
