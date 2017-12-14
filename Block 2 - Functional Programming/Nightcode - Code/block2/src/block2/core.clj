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

(macroexpand '(safe [s (FileReader. (File. "file.txt"))] (.read s)))

(def divide-zero (safe (/ 1 0)))
divide-zero

(def divide-two (safe (/ 10 2)))
divide-two

(def filefound (safe [s (FileReader. (File. "file.txt"))] (.read s)))
filefound

(def missing-file (safe [s (FileReader. (File. "missing-file"))] (. s read)))
missing-file
 
