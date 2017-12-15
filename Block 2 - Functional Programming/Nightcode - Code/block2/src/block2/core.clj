(ns block2.core
  (:gen-class))
(import '(java.io FileReader))
(import '(java.io File))
(import '(java.io FileNotFoundException))

(comment
  (defmacro safe
    [vector & form]
    (if (empty? form)
      `(try ~vector 
        (catch ArithmeticException e#
         (str "Divide by zero: " (.getMessage e#))))
       
      `(try ~form
        (catch FileNotFoundException e#
         (str "File not found: " (.getMessage e#)))))))

(comment  
 (defmacro safe
   [vector & form]
   (if (empty? form)
     `(try ~vector 
       (catch ArithmeticException e#
         (str "Divide by zero: " (.getMessage e#))))
     
     (let [f (get vector 0) l (get vector 1)]
       `(try ~form
         (catch FileNotFoundException e#
          (str "File not found: " (.getMessage e#))))))))

(defmacro safe [vector & form]
  (if (list? vector)
    `(try 
       ~vector 
      (catch Throwable e# 
       (str "ArithmeticException java.lang.ArithmeticException: " (.getMessage e#))))
    
    (if (empty? vector)
      `(try ~form 
        (catch Throwable e# 
         (str "Error: Empty Vector" (.getMessage e#))))
      
      `(let ~(subvec vector 0 2)
        (try ~@(subvec vector 2) ~@form
         (catch Throwable e# 
          (str "FileNotFoundException java.io.FileNotFoundException:� " (.getMessage e#))))))))



(macroexpand '(safe [s (FileReader. (File. "C:/Users/Peter/Documents/GitHub/PROP-INLUPPAR/Block 2 - Functional Programming/file.txt"))] (.read s)))

(macroexpand '(safe [s (FileReader. (File. "missing-file"))] (. s read)))

(def divide-zero (safe (/ 1 0)))
divide-zero

(def divide-two (safe (/ 10 2)))
divide-two

(def filefound (safe [s (FileReader. (File. "C:/Users/Peter/Documents/GitHub/PROP-INLUPPAR/Block 2 - Functional Programming/file.txt"))] (.read s)))
filefound

(def missing-file (safe [s (FileReader. (File. "missing-file"))] (. s read)))
missing-file
