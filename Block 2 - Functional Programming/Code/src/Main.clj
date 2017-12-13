(ns Main
  (:import (java.io File FileReader FileNotFoundException)))


(defmacro safe
  [code & vector]
  (if (empty? vector)
    `(try ~code (catch ArithmeticException e# (str "Divide by zero: " (.getMessage e#))))

    `(try ~vector (catch FileNotFoundException e# (str "File not found: " (.getMessage e#))))))


(def divide-zero (safe (/ 1 0)))
divide-zero
(println divide-zero)

(def divide-two (safe (/ 10 2)))
divide-two
(println divide-two)

(def filefound (safe [s (FileReader. (File. "file.txt"))] (.read s)))

filefound

(macroexpand '(safe [s (FileReader. (File. "file.txt"))] (.read s)))

(def missing-file (safe [s (FileReader. (File. "missing-file"))] (. s read)))

missing-file
