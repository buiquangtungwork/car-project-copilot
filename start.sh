#!/bin/bash

PROJECT_DIR="$(cd "$(dirname "$0")" && pwd)"
BE_DIR="$PROJECT_DIR/BE"
FE_DIR="$PROJECT_DIR/FE"

# Colors
GREEN='\033[0;32m'
RED='\033[0;31m'
YELLOW='\033[1;33m'
NC='\033[0m'

echo -e "${GREEN}========================================${NC}"
echo -e "${GREEN}   Car Management System - Starting...  ${NC}"
echo -e "${GREEN}========================================${NC}"

# Stop any existing processes
echo -e "${YELLOW}Stopping any existing servers...${NC}"
pkill -f "car-project.*jar" 2>/dev/null || true
pkill -f "ng serve" 2>/dev/null || true
sleep 1

# Build Backend
echo -e "${YELLOW}Building backend...${NC}"
cd "$BE_DIR"
mvn clean package -DskipTests -q
if [ $? -ne 0 ]; then
  echo -e "${RED}Backend build failed!${NC}"
  exit 1
fi
echo -e "${GREEN}Backend built successfully.${NC}"

# Start Backend in background
echo -e "${YELLOW}Starting backend on port 8081...${NC}"
java -jar "$BE_DIR/target/car-project-0.0.1-SNAPSHOT.jar" > "$PROJECT_DIR/logs/backend.log" 2>&1 &
BE_PID=$!
echo $BE_PID > "$PROJECT_DIR/logs/backend.pid"

# Wait for backend to be ready
echo -e "${YELLOW}Waiting for backend to start...${NC}"
for i in {1..30}; do
  if curl -s http://localhost:8081/api/cars > /dev/null 2>&1; then
    echo -e "${GREEN}Backend is ready! (http://localhost:8081)${NC}"
    break
  fi
  sleep 1
  if [ $i -eq 30 ]; then
    echo -e "${RED}Backend failed to start after 30 seconds. Check logs/backend.log${NC}"
    exit 1
  fi
done

# Install FE dependencies if needed
cd "$FE_DIR"
if [ ! -d "node_modules" ]; then
  echo -e "${YELLOW}Installing frontend dependencies...${NC}"
  npm install -q
fi

# Start Frontend in background
echo -e "${YELLOW}Starting frontend on port 4300...${NC}"
npm start > "$PROJECT_DIR/logs/frontend.log" 2>&1 &
FE_PID=$!
echo $FE_PID > "$PROJECT_DIR/logs/frontend.pid"

# Wait for frontend to be ready
echo -e "${YELLOW}Waiting for frontend to compile...${NC}"
for i in {1..60}; do
  if curl -s http://localhost:4300 > /dev/null 2>&1; then
    echo -e "${GREEN}Frontend is ready! (http://localhost:4300)${NC}"
    break
  fi
  sleep 1
  if [ $i -eq 60 ]; then
    echo -e "${RED}Frontend failed to start. Check logs/frontend.log${NC}"
    exit 1
  fi
done

echo ""
echo -e "${GREEN}========================================${NC}"
echo -e "${GREEN}   All servers running!                 ${NC}"
echo -e "${GREEN}   Frontend: http://localhost:4300      ${NC}"
echo -e "${GREEN}   Backend:  http://localhost:8081      ${NC}"
echo -e "${GREEN}========================================${NC}"
echo ""
echo -e "Run ${YELLOW}./stop.sh${NC} to stop all servers."
echo -e "Logs in: ${YELLOW}$PROJECT_DIR/logs/${NC}"
