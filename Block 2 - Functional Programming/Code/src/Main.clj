(ns Main
  (:import (java.io File FileReader FileNotFoundException)))

(comment "Test Code"

         (defmacro safe [vector & form]
           (if (list? vector)
             `(try ~vector)
             (catch Throwable e#)
             (str "ArithmeticException java.lang.ArithmeticException: " (.getMessage e#))

             (if (empty? vector)
               `(try ~form)
               (catch Throwable e#)
               (str "Error: Empty Vector" (.getMessage e#))

               `(let ~(subvec vector 0 2))
               (try ~@(subvec vector 2) ~@form)
               (catch java.io.FileNotFoundException e#)
               (str "FileNotFoundException java.io.FileNotFoundException: " (.getMessage e#)))))

         )


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
(println divide-zero)

(def divide-two (safe (/ 10 2)))
divide-two
(println divide-two)

(def filefound (safe [s (FileReader. (File. "C:/Users/Peter/Documents/GitHub/PROP-INLUPPAR/Block 2 - Functional Programming/file.txt"))] (.read s)))
filefound
(println filefound)

(def missing-file (safe [s (FileReader. (File. "missing-file"))] (. s read)))
missing-file
(println missing-file)

