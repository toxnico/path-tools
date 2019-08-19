(ns path-tools.core
  (:require [clojure.string :as s]))

(defn containing-folder
  "Returns all but the last element of a path"
  [path]
  (->> (s/split path #"\/")
       (reverse)
       (rest)
       (reverse)
       (s/join "/")))

(defn file-name
  "Returns the last element of a path"
  [path]
  (->> (s/split path #"\/")
       (last)))

(defn combine
  "Combines path segments, takes care of cleaning the slashes
   in order to get one and only one slash between each segment"
  [& path-coll]
  (let [tail (rest path-coll)
        head (first path-coll)
        clean-head (s/replace head #"/*$" "")]
    (->> tail
         (map #(s/replace % #"^\.*/*|/*$" ""))
         (cons clean-head)
         (s/join "/"))))
