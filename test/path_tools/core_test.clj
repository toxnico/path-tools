(ns path-tools.core-test
  (:require [clojure.test :refer :all]
            [path-tools.core :refer :all]))

(deftest containing-folder-test
  (testing "containing-folder"
    (is (= "/home/nico/desktop"  (containing-folder "/home/nico/desktop/file.txt")))
    (is (= "/home/nico"  (containing-folder "/home/nico/desktop")))))


(deftest containing-folder-test
  (testing "containing-folder"
    (is (= "/home/nico/desktop"  (containing-folder "/home/nico/desktop/file.txt")))
    (is (= "/home/nico"  (containing-folder "/home/nico/desktop")))))

(deftest file-name-test
  (testing "file-name"
    (is (= "file.txt"  (file-name "/home/nico/desktop/file.txt")))
    (is (= "desktop"  (file-name "/home/nico/desktop")))))


(deftest combine-test
  (testing "combine"
    (is (= "/home/nico/desktop/folder/file.txt"  (combine "/home/nico/" "/desktop"  "folder" "file.txt")))
    (is (= "relative/path/desktop/folder/file.txt"  (combine "relative" "path///" "///desktop"  "folder" "file.txt")))))
