# Blogtastic

Open-source, self-hosted blogging platform for individuals

---
#### Technologies:

**Server:** Kotlin/Spring/Postgres

**UI:** Svelte/TypeScript/Vite/Tailwind

## Development


### Docker

Serves Server + DB + UI

```
docker build . -t blog
```
```
docker run -p 8080:8080 blog
```

### DB

For just running the DB instance, not the whole docker container. Convenient for development.

```
docker compose up blogtastic-db -d
```

### UI

#### npm start

Runs the app in the development mode.
Open http://localhost:8000 to view it in the browser.

The page will reload if you make edits.

#### npm check

Will run svelte-check to validate all types with TypeScript.

#### npm test

Runs unit tests using Vitest.

#### npm run build

### Server

Run 'Start Server' run configuration from IntelliJ

