(ns block2.core
  (:gen-class))
(import '(java.io FileReader))
(import '(java.io File))
(import '(java.io FileNotFoundException))

(comment 
  Grupp 10
  Peter Yakob
  Lukas Varli)

(defmacro safe
  [vector & form]
  (if (empty? form)
    `(try ~vector 
      (catch ArithmeticException e#
        (str "ArithmeticException java.lang.ArithmeticException: " (.getMessage e#))))
    
    (if (not= (count vector) 2)
       `(try ~form
         (catch Throwable e#
           (str "Error: " (.getMessage e#))))
        `(try 
          (let ~(subvec vector 0 2)
           (try ~@(get vector 2) ~@form))
          (catch java.io.FileNotFoundException e# 
           (str "FileNotFoundException java.io.FileNotFoundException: " (.getMessage e#)))))))

(def divide-zero (safe (/ 1 0)))
divide-zero

(def divide-two (safe (/ 10 2)))
divide-two

(def filefound (safe [s (FileReader. (File. "C:/Users/Peter/Documents/GitHub/PROP-INLUPPAR/Block 2 - Functional Programming/file.txt"))] (.read s)))
filefound

(def missing-file (safe [s (FileReader. (File. "missing-file"))] (. s read)))
missing-file


(macroexpand '(safe (/ 1 0)))

(macroexpand '(safe (/ 10 2)))

(macroexpand '(safe [s (FileReader. (File. "C:/Users/Peter/Documents/GitHub/PROP-INLUPPAR/Block 2 - Functional Programming/file.txt"))] (.read s)))

(macroexpand '(safe [s (FileReader. (File. "missing-file"))] (. s read)))

