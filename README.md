# path-tools

A Clojure library designed to help with files and folders paths manipulation.  

## Usage

[![Clojars Project](https://img.shields.io/clojars/v/path-tools.svg)](https://clojars.org/path-tools)

For now, these functions available :

* ```containing-folder``` : Returns all but the last element of a path
* ```file-name``` : Returns the last element of a path
* ```combine``` : Combines path segments, takes care of cleaning the slashes in order to get one and only one slash between each segment
* ```file-extension``` : Returns the extension of a file, with the initial dot. If there is no extension, returns nil
* ```file-name-without-extension``` : Returns the complete path before the extension
* ```change-file-name``` : Changes the file name, keeping the extension
* ```change-extension``` : Sets a new extension to a file path, if it already has an extension. Otherwise, returns the path as is
* ```ls``` : Returns the contents of a folder (not recursively)