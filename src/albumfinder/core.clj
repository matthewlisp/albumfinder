(ns albumfinder.core
  (:require [albumfinder.crawler :as crawler]
            [cheshire.core :as cheshire]))


(defn youtube-musics
  "Given a vector of strings of music names, Returns a vector of maps, containing youtube video links, time, title and thumbnails"
  [musics]
  (crawler/youtube-musics musics))

(defn youtube-album-playlist
  "Given a vector with a single string of album and artist name or a youtube playlist name, Returns a vector of a single map, containing  a youtube album or playlist video link and time"
  [album-playlist]
  (crawler/youtube-album-playlist album-playlist))

(defn album-musics
  "given two strings, an artist and album name, Returns a vector of maps, containing youtube video links and time from all the musics of a given album of a given artist "
  [artist album]
  (crawler/album-musics artist album))


(defn -main
  [artist album]
  (let [search-result (album-musics artist album)]
    (println (cheshire/generate-string search-result {:pretty true}))
    (cheshire/generate-stream search-result (clojure.java.io/writer (str artist "-" album ".json")))
    (println "Generated your json file on current folder.")))
