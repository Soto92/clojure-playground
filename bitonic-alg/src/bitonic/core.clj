(ns bitonic.core)

(defn bitonic-array [n l r]
  (if (> n (+ (* 2 (- r l)) 1))
    [-1]
    (let [
          p r
          valid-ks (filter (fn [k] (>= (- p (max (dec k) (- n k))) l))
                           (range 1 (inc n)))
          best-k (first (sort-by (fn [k] [(Math/abs (- k (/ n 2.0))) k])
                                 valid-ks))
          inc-len best-k
          dec-len (- n best-k)
          
          inc-part (range (- p (dec inc-len)) (inc p))
          dec-part (take dec-len (iterate dec (dec p)))]
          
      (vec (concat inc-part dec-part)))))