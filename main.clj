(ns main
  (:require [clojure.string :as str]))

(def input-string "49276d206b696c6c696e6720796f757220627261696e206c696b65206120706f69736f6e6f7573206d757368726f6f6d")

(def result-string "SSdtIGtpbGxpbmcgeW91ciBicmFpbiBsaWtlIGEgcG9pc29ub3VzIG11c2hyb29t")


(def hexlookup {:0 "0000"
                :1 "0001"
                :2 "0010"
                :3 "0011"
                :4 "0100"
                :5 "0101"
                :6 "0110"
                :7 "0111"
                :8 "1000"
                :9 "1001"
                :a "1010"
                :b "1011"
                :c "1100"
                :d "1101"
                :e "1110"
                :f "1111"})

(def base64-keys (->> (for [x (range 64)]
                        (Integer/toString x 2))
                      (map (fn [x] (str "000000" x)))
                      (map #(take-last 6 %))
                      (map #(apply str %))
                      (map keyword)
                      ))

(def base64-values "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/")

(def base64-map (zipmap base64-keys base64-values))

(defn hex->binary [s]
  (map #( (keyword (str %)) hexlookup) s))



(def bin-string(str/join "" (-> input-string
                                hex->binary
                                )))


(def bin2(map #(apply str %) (partition 6 bin-string)))

(defn binary->base64 [s]
  (map (fn [x] ( (keyword x) base64-map) ) s))

(defn encode-base64 [s]
  (let [binary-string (str/join ""(-> input-string
                                      hex->binary))
        sixes (map #(apply str %) (partition 6 binary-string))
        result (str/join "" (-> sixes binary->base64))
        ]
    result
    ))


(str/join ""(-> bin2
                binary->base64))


(defn -main [] (print "HELLO"))
