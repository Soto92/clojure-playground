(ns bitonic.core)

(defn bitonic-array [n l r]
  (if (> n (+ (* 2 (- r l)) 1))
    [-1]
    (let [
          start [(dec r)]
          dec-part (take-while #(>= % l) (iterate dec r))
          dec-seq (concat start dec-part)
          inc-part (take-while #(>= % l) (iterate dec (- r 2)))]
      (vec
        (take n
              (concat (reverse inc-part) dec-seq))))))
