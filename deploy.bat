@echo off

docker run -d --gpus=all -v ./ollama:/root/.ollama -p 11434:11434 ollama/ollama

docker compose up -d