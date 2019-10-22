(ns path-tools.core-test
  (:require [clojure.test :refer :all]
            [path-tools.core :refer :all]))

(deftest containing-folder-test
  (testing "containing-folder"
    (is (= "/home/nico/desktop"  (containing-folder "/home/nico/desktop/file.txt")))
    (is (= "/home/nico"  (containing-folder "/home/nico/desktop")))
    (is (nil? (containing-folder "file.jpg")))
    (is (= "" (containing-folder "/file.jpg")))))

(deftest file-name-test
  (testing "file-name"
    (is (= "file.txt"  (file-name "/home/nico/desktop/file.txt")))
    (is (= "desktop"  (file-name "/home/nico/desktop")))))


(deftest combine-test
  (testing "combine"
    (is (= "/home/nico/desktop/folder/file.txt"  (combine "/home/nico/" "/desktop"  "folder" "file.txt")))
    (is (= "relative/path/desktop/folder/file.txt"  (combine "relative" "path///" "///desktop"  "folder" "file.txt")))))


(deftest file-extension-test
  (testing "file-extension"
    (is (= ".jpg" (file-extension "my-file.jpg")))
    (is (= ".jpg" (file-extension "my.file.jpg")))
    (is (= ".jpg" (file-extension "/full/path/to/my.file.jpg")))
    (is (= ".hidden-file" (file-extension "/full/path/to/.hidden-file")))
    (is (nil? (file-extension "/full/path/to/my-file")))))

(deftest file-name-without-extension-test
  (testing "file-name-without-extension"
    (is (= "my-file" (file-name-without-extension "my-file.jpg")))
    (is (= "my.file" (file-name-without-extension "my.file.jpg")))
    (is (= "/full/path/t.o/my.file" (file-name-without-extension "/full/path/t.o/my.file.jpg")))
    (is (= "/full/path/to/" (file-name-without-extension "/full/path/to/.hidden-file")))
    (is (= "/full/path/to/my-file" (file-name-without-extension "/full/path/to/my-file")))))

(deftest change-file-name-test
  (testing "change-file-name"
    (is (= "new-file.jpg" (change-file-name "new-file" "my-file.jpg")))
    (is (= "new-file.jpg" (change-file-name "new-file" "my.file.jpg")))
    (is (= "/full/path/t.o/new-file.jpg" (change-file-name "new-file" "/full/path/t.o/my.file.jpg")))
    (is (= "/full/path/to/new-file.hidden-file" (change-file-name "new-file" "/full/path/to/.hidden-file")))
    (is (= "/full/path/to/new-file" (change-file-name "new-file" "/full/path/to/my-file")))))

(deftest change-extension-test
  (testing "change-extension"
    (is (= "my-file.bmp" (change-extension ".bmp" "my-file.jpg")))
    (is (= "my.file.bmp" (change-extension ".bmp" "my.file.jpg")))
    (is (= "/full/path/t.o/my.file.bmp" (change-extension ".bmp" "/full/path/t.o/my.file.jpg")))
    (is (= "/full/path/to/.bmp" (change-extension ".bmp" "/full/path/to/.hidden-file")))
    (is (= "/full/path/to/my-file" (change-extension ".bmp" "/full/path/to/my-file")))))
