(ns main
  (:require [clojure.string :as str]))

(def input-string "49276d206b696c6c696e6720796f757220627261696e206c696b65206120706f69736f6e6f7573206d757368726f6f6d")

(def result-string "SSdtIGtpbGxpbmcgeW91ciBicmFpbiBsaWtlIGEgcG9pc29ub3VzIG11c2hyb29t")


(def hex-binstring-lookup {:0 "0000" :1 "0001" :2 "0010" :3 "0011" :4 "0100" :5 "0101"
                           :6 "0110":7 "0111" :8 "1000" :9 "1001" :a "1010" :b "1011"
                           :c "1100" :d "1101" :e "1110" :f "1111"})
(def hex-binint-lookup {:0 0 :1 1 :2 2 :3 3 :4 4 :5 5 :6 6
                        :7 7 :8 8 :9 9 :a 10 :b 11 :c 12 :d 13
                        :e 14 :f 15})
(def int-hex-char-lookup {
                          :0 "0" :1 "1" :2 "2" :3 "3" :4 "4" :5 "5" :6 "6" :7 "7"
                          :8 "8" :9 "9" :10 "a" :11 "b" :12 "c" :13 "d" :14 "e" :15 "f"})

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
"converts a hex string to a list of strings of binary"
(map #( (keyword (str %)) hex-binstring-lookup ) s))

(defn binary->base64 [s]
"converts a list of 6 bit binary strings to a list of base64 chars"
(map (fn [x] ( (keyword x) base64-map) ) s))

(defn encode-base64 [s]
  "Does not padd out input to ensure size is a multiple of 6 bits"
  (let [binary-string (str/join ""(-> input-string
                                      hex->binary))
        sixes (map #(apply str %) (partition 6 binary-string))
        result (str/join "" (-> sixes binary->base64))
        ]
    result
    ))



(def challenge2-input1 "1c0111001f010100061a024b53535009181c")
(def challenge2-input2 "686974207468652062756c6c277320657965")
(def challenge2-result "746865206b696420646f6e277420706c6179")



(defn challenge2 [s1 s2]
  (let [s1-hex (map  #( (keyword (str %)) hex-binint-lookup ) s1)
        s2-hex (map  #( (keyword (str %)) hex-binint-lookup ) s2)
        newmap (map vector  s1-hex s2-hex)
        xored (map (fn [ [a b] ] (bit-xor a b)) newmap)
        tochar (map #( (keyword (str %)) int-hex-char-lookup ) xored)
        result (apply str tochar)
        ]
    result
    )
  )

(defn -main [] (print "HELLO"))
