up:
	docker-compose --env-file .env -f infrastructure/docker-compose.yaml up --build

down:
	docker-compose --env-file .env -f infrastructure/docker-compose.yaml down -v --rmi all
