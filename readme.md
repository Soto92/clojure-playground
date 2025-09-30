# Bitonic Sequence in Clojure

This project implements **bitonic sequence generation** in Clojure and saves test results into **Redis** running in Docker.

---

## 🚀 Requirements

- [Java 11+](https://adoptium.net/) installed
- [Clojure CLI (tools.deps)](https://clojure.org/guides/install_clojure) installed
- [Docker](https://docs.docker.com/get-docker/) installed

---

## 📂 Project Structure

bitonic-alg/
├── deps.edn
├── src/
│ └── bitonic/
│ ├── core.clj ;; bitonic-array implementation
│ └── db.clj ;; Redis connection
└── test/
. └── bitonic/
. └── core_test.clj ;; tests that save results into Redis

````

---

## ▶️ How to Run

### 1. Start Redis in Docker
```bash
docker run --name redis-bitonic -p 6379:6379 -d redis:7
````

This starts Redis on `localhost:6379`.

---

### 2. Run `bitonic-array` manually

Start a REPL:

```bash
clojure -M
```

Load the namespace and run examples:

```clojure
(require '[bitonic.core :refer [bitonic-array]])

(bitonic-array 5 3 10)
;; => [9 10 9 8 7]

(bitonic-array 7 2 5)
;; => [2 3 4 5 4 3 2]

(bitonic-array 15 1 5)
;; => [-1]
```

---

### 3. Run the tests

Run:

```bash
clojure -X:test
```

The tests will:

- Validate the `bitonic-array` function
- Save results into Redis as **hashes**

---

### 4. Check test results in Redis

Open the Redis CLI:

```bash
docker exec -it redis-bitonic redis-cli
```

List keys:

```redis
KEYS test:*
```

Inspect a specific test:

```redis
HGETALL test:case-1
```

Expected output:

```
1) "status"
2) "PASS"
3) "expected"
4) "[9 10 9 8 7]"
5) "actual"
6) "[9 10 9 8 7]"
7) "ts"
8) "2025-09-29T15:40:12.345Z"
```

---

## 🛠️ Clean up Redis container

To remove the container:

```bash
docker rm -f redis-bitonic
```

---

## 📜 License

Educational project — free to use.
