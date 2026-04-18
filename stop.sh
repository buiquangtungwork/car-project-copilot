#!/bin/bash

PROJECT_DIR="$(cd "$(dirname "$0")" && pwd)"

GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m'

echo -e "${YELLOW}Stopping all servers...${NC}"

# Stop by PID files if they exist
if [ -f "$PROJECT_DIR/logs/backend.pid" ]; then
  kill $(cat "$PROJECT_DIR/logs/backend.pid") 2>/dev/null || true
  rm -f "$PROJECT_DIR/logs/backend.pid"
fi

if [ -f "$PROJECT_DIR/logs/frontend.pid" ]; then
  kill $(cat "$PROJECT_DIR/logs/frontend.pid") 2>/dev/null || true
  rm -f "$PROJECT_DIR/logs/frontend.pid"
fi

# Fallback: kill by process name
pkill -f "car-project.*jar" 2>/dev/null || true
pkill -f "ng serve" 2>/dev/null || true

echo -e "${GREEN}All servers stopped.${NC}"
