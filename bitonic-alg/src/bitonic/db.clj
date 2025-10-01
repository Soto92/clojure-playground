(ns bitonic.db
  (:require [taoensso.carmine :as car]))

(def conn {:pool {} :spec {:host "127.0.0.1" :port 6379}})

(defmacro wcar* [& body] `(car/wcar conn ~@body))

(defn save-result! [test-name status expected actual]
  (wcar*
    (car/hmset (str "test:" test-name)
               :status status
               :expected (pr-str expected)
               :actual   (pr-str actual)
               :ts       (str (java.time.Instant/now)))))
