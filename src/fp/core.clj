(ns fp.core
  (:require [clojure.string :as s]))


(defn sum
  ([vals] (sum vals 0))
  ([vals total]
   (if (empty? vals)
     total
     (recur (rest vals) (+ (first vals) total)))))

(defn clean
  [text]
  (s/replace (s/trim text) #"lol" "LOL"))

(def character
  {:name "Smooches McCutes"
   :attributes {:intelligence 10
                :strength 4
                :dexterity 5}})

(def c-intelligence (comp :intelligence :attributes))

(def c-strength (comp :strength :attributes))

(def c-dexterity (comp :dexterity :attributes))

(def spell-slots
  (comp int inc #(/ % 2) c-intelligence))

(defn sleepy-identity
  [x]
  (Thread/sleep 1000)
  x)

(def memo-sleepy-identity (memoize sleepy-identity))
