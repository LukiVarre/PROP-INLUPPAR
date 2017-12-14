(ns Main
  (:import (java.io File FileReader FileNotFoundException)))

(comment
  (defmacro safe
    [vector & form]
    (if (empty? form)
      `(try ~vector
            (catch ArithmeticException e#
              (str "Divide by zero: " (.getMessage e#))))

      `(try ~form
            (catch FileNotFoundException e#
              (str "File not found: " (.getMessage e#))))))
  )


(defmacro safe
  [vector & form]
  (if (empty? form)
    `(try ~vector
      (catch ArithmeticException e#
       (str "Divide by zero: " (.getMessage e#))))

     (let [f (get vector 0) l (get vector 1)]
       `(try ~form
         (catch FileNotFoundException e#
          (str "File not found: " (.getMessage e#)))))))

(def divide-zero (safe (/ 1 0)))
divide-zero
(println divide-zero)

(def divide-two (safe (/ 10 2)))
divide-two
(println divide-two)

(def filefound (safe [s (FileReader. (File. "file.txt"))] (.read s)))
filefound
(println filefound)

(def missing-file (safe [s (FileReader. (File. "missing-file"))] (. s read)))
missing-file
(println missing-file)

