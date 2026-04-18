#!/bin/bash

echo "=========================================="
echo "Car Management API Test Suite"
echo "=========================================="
echo ""

# Define the base URL
BASE_URL="http://localhost:8081/api/cars"

echo "1. Creating first car (Toyota Camry)..."
RESPONSE1=$(curl -s -X POST $BASE_URL \
  -H "Content-Type: application/json" \
  -d '{"make":"Toyota","model":"Camry","year":2023}')
echo "Response: $RESPONSE1"
echo ""

echo "2. Creating second car (Honda Civic)..."
RESPONSE2=$(curl -s -X POST $BASE_URL \
  -H "Content-Type: application/json" \
  -d '{"make":"Honda","model":"Civic","year":2022}')
echo "Response: $RESPONSE2"
echo ""

echo "3. Creating third car (BMW 3 Series)..."
RESPONSE3=$(curl -s -X POST $BASE_URL \
  -H "Content-Type: application/json" \
  -d '{"make":"BMW","model":"3 Series","year":2024}')
echo "Response: $RESPONSE3"
echo ""

echo "=========================================="
echo "4. Getting all cars..."
echo "=========================================="
curl -s http://localhost:8081/api/cars | jq '.' 2>/dev/null || curl -s http://localhost:8081/api/cars
echo ""
echo ""

echo "=========================================="
echo "5. Getting car by ID (ID: 1)..."
echo "=========================================="
curl -s http://localhost:8081/api/cars/1 | jq '.' 2>/dev/null || curl -s http://localhost:8081/api/cars/1
echo ""
echo ""

echo "=========================================="
echo "6. Updating car ID 1 (Toyota -> Corolla)..."
echo "=========================================="
curl -s -X PUT $BASE_URL/1 \
  -H "Content-Type: application/json" \
  -d '{"make":"Toyota","model":"Corolla","year":2024}' | jq '.' 2>/dev/null || \
curl -s -X PUT $BASE_URL/1 \
  -H "Content-Type: application/json" \
  -d '{"make":"Toyota","model":"Corolla","year":2024}'
echo ""
echo ""

echo "=========================================="
echo "7. Verifying update - Getting car ID 1..."
echo "=========================================="
curl -s http://localhost:8081/api/cars/1 | jq '.' 2>/dev/null || curl -s http://localhost:8081/api/cars/1
echo ""
echo ""

echo "=========================================="
echo "8. Deleting car ID 2 (Honda Civic)..."
echo "=========================================="
curl -s -X DELETE $BASE_URL/2 -v
echo ""
echo ""

echo "=========================================="
echo "9. Verifying deletion - Getting all cars..."
echo "=========================================="
curl -s http://localhost:8081/api/cars | jq '.' 2>/dev/null || curl -s http://localhost:8081/api/cars
echo ""
echo ""

echo "=========================================="
echo "10. Testing invalid ID (ID: 999)..."
echo "=========================================="
curl -s http://localhost:8081/api/cars/999 -w "\nHTTP Status: %{http_code}\n"
echo ""

echo "=========================================="
echo "Test Suite Complete!"
echo "=========================================="
