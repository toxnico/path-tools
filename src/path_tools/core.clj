(ns path-tools.core
  (:require [clojure.string :as s]))

(defn containing-folder
  "Returns all but the last element of a path"
  [path]
  (when (s/includes? path "/")
    (->> (s/split path #"\/")
         (reverse)
         (rest)
         (reverse)
         (s/join "/"))))

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

(defn file-extension
  "Returns the extension of a file, with the initial dot.
  if there is no extension, returns nil"
  [path]
  (let [file-name (file-name path)]
    (when (s/includes? file-name ".")
      (as-> path %
            (clojure.string/split % #"\.")
            (last %)
            (str "." %)))))

(defn file-name-without-extension
  "Returns the complete path before the extension"
  [path]
  (if (nil? (file-extension path)) ;no extension ?
    path
    (as-> path %
          (s/split % #"\.")
          (drop-last %)
          (s/join "." %))))

(defn change-file-name
  "Changes the file name, keeping the extension"
  [new-name file-path]
  (let [parent (containing-folder file-path)
        extension (file-extension file-path)]
    (if parent
      (combine parent (str new-name extension))
      (str new-name extension))))

(defn change-extension
  "Sets a new extension to a file path, if it already has an extension.
   Otherwise, returns the path as is"
  [new-extension file-path]
  (if-not (file-extension file-path)
    file-path
    (-> (file-name-without-extension file-path)
        (str new-extension))))