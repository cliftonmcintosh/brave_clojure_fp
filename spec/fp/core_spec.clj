(ns fp.core-spec
  (:require [speclj.core :refer :all]
            [fp.core :refer :all]))

(describe "sum"
  (it "should give back the one number if it is only given one number"
      (should= 1 (sum [1])))
  (it "should give zerio if the arguments is empty"
      (should= 0 (sum [])))
  (it "should sum up the number it is given"
      (should= 3 (sum [1 2])))
  (it "should add to its accumulator when it is given an accumulator"
      (should= 6 (sum [1 2] 3))))

(describe "clean"
          (it "should replace lower-case \"lol\" with uppercase \"LOL\""
              (should= "Hey, LOL!" (clean "Hey, lol!"))))

(run-specs)
