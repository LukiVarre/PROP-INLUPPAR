(ns Main
  (:import (java.io File FileReader)))

(defmacro safe [Variable, Value], (eval ()))

(comment
  (def divide-zero (safe (/ 1 0)))
  divide-zero

  (def divide-two (safe (/ 10 2)))
  divide-two

  (def filefound (safe [s (FileReader. (File. "file.txt"))] (.read s)))
  filefound

  (def missing-file (safe [s (FileReader. (File. "missing-file"))] (. s read)))
  missing-file
  )

(defn -main []
  (println "Hello, World!"))