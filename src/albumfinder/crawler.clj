(ns albumfinder.crawler
  (:require [hickory.core :as hickory]
            [com.rpl.specter :as sp]
            [clj-http.client :as client]
            [cheshire.core :as cheshire]
            [environ.core :refer [env]])
  (:use [clojure.string :only (includes? replace)]))

(def consumer-key (env :discogskey))
(def consumer-secret (env :discogssecret))

(defn search-info
  "Returns a seq containing maps with link and time from all the result of searching the given music string in youtube on the first page"
  [music]   ;; NOTE this function can probably be simplified making better use of SPECTER
  (let [youtube-html (->> (slurp (str "https://www.youtube.com/results?search_query=" music))
                          (hickory/parse)
                          (hickory/as-hiccup))
        links-and-times (sp/select (sp/walker #(and (vector? %) (= (nth % 0) :a) (includes? (:href (nth % 1)) "/watch?") (= (first (last %)) :div))) youtube-html)
        musics-title (sp/select [(sp/walker :class) (sp/if-path [:class #(includes? % "yt-uix-tile-link")] :title)] youtube-html)
        thumbnails (sp/select [(sp/walker :src) (sp/if-path [:src #(includes? % "i.ytimg.com")] :src)] youtube-html)]
    (map #(hash-map :link (first (sp/select [sp/ALL map? :href] %1)), :title (identity %2), :time (last (last (last (last %1))))
                    :thumb (identity %3))
         links-and-times musics-title thumbnails))) 


;;;;;;;;;;;;;;;;;;;; MUSICS functions ;;;;;;;;;;;;;;;;;;;;



(defn filter-best-result-link
  "Filter every music that have less than 20 minutes and return the first one from the results."
  [search-map]
  (when seq search-map
        (first
         (filter
          (fn [music-info]
            (let [time-str-count (count (:time music-info))
                  less-than-five? (< time-str-count 5)]
              (if less-than-five? music-info
                  (and (= time-str-count 5)
                       (< (Character/digit (first (:time music-info)) 10) 2))))) search-map)))) 

(defn format-search-strings [musics]
  (->> musics
       (map #(replace % " " "+"))
       (map #(replace % "&" "%26"))  ;; NOTE Youtube replaces the char & for %26 on search queries, so here we prepare the string for this case.
       (map #(str % "%2C+video"))))  ;; NOTE Here we prepare the search query to ignore playlists and take only full videos.


(defn youtube-musics [musics]
  (when musics
    (let [musics-vec
          (->> (format-search-strings musics)
               (map search-info)
               vec
               (map filter-best-result-link)
               vec)]
      (if (every? nil? musics-vec) nil musics-vec))))



;;;;;;;;;;;;;;;;;;;; ALBUM OR PLAYLIST functions  ;;;;;;;;;;;;;;;;;;;;



(defn format-search-string-album-playlist [album-playlist]
  (->> album-playlist
       (map #(replace % " " "+"))
       (map #(replace % "&" "%26"))  ;; NOTE Youtube replaces the char & for %26 on search queries, so here we prepare the string for this case.
       (map #(str % "&sp=EgIYAg%253D%253D"))))  ;; NOTE Here we prepare the search query to only search videos with more than 20 minutes on youtube.

(defn youtube-album-playlist [album-playlist]
  (->> (format-search-string-album-playlist album-playlist)
       (map search-info)
       (sp/select [sp/ALL seq? sp/FIRST])))


(defn extract-album-tracks-discogs
  "Given a discogs album url, extract it's tracks names in a vector of strings"
  [discogs-album-url]
  (when discogs-album-url
    (let [discogs-api-response (client/get discogs-album-url {:headers {:Authorization (str "Discogs key=" consumer-key ", secret=" consumer-secret)}})
          status (:status discogs-api-response)
          content (when (= status 200) (cheshire/parse-string (:body discogs-api-response) true))]
      (when  content (sp/select [:tracklist sp/ALL :title] content)))))


(defn search-album-discogs
  "Extract the first album discogs url from the given artist, returns the url as a string"
  [artist album]
  (letfn [(discogs-search [artist album release-type-filter?]
            (let [key       consumer-key
                  secret    consumer-secret
                  url       "https://api.discogs.com/"
                  endpoint  "database/search"
                  query-params {:artist artist :release_title album}
                  response  (client/get (str url endpoint)
                                        {:query-params (if release-type-filter? (assoc query-params :type "release" :format "album") query-params) 
                                         :headers {:Authorization (str "Discogs key=" key ", secret=" secret)}})] response))]
    (let [api-call (discogs-search artist album true)
          api-content (if (empty? (:results (cheshire/parse-string (:body api-call) true))) (discogs-search artist album false) api-call) ;; NOTE this is needed for a fallback when the release type of the album searched is not 'album'
          api-parse-content (when (= (:status api-content) 200) (cheshire/parse-string (:body api-content) true))
          found? (not (empty? api-parse-content))]
      (when found?
        (first (sp/select [:results sp/FIRST :resource_url] api-parse-content))))))



(defn album-musics [artist album]
  (->> (search-album-discogs artist album)
       (extract-album-tracks-discogs)
       (sp/transform [sp/ALL] #(str artist " - " %))
       (youtube-musics)))
