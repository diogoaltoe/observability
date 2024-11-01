#!/bin/bash
docker-compose up --build -d

sleep 3 && docker-compose ps;