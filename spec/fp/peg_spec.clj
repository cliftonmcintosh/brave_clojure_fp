(ns fp.peg-spec
  (:require [speclj.core :refer :all]
            [fp.peg :refer :all]))

(describe "tri*"
          (it "should create triangular numbers"
              (should= '(1 3 6 10 15) (take 5 (tri*)))))

(describe "triangular?"
          (it "should return true for a triangular number"
              (should (triangular? 15)))
          (it "should return false for a non-triangular number"
              (should-not (triangular? 16))))

(describe "row-tri"
          (it "should return the right number for a row"
              (should= 6 (row-tri 3))))

(describe "row-num"
          (it "should return the correct row for a given number"
              (should= 3 (row-num 5))))

(describe "connect"
          (it "should identify mutual connections between two positions"
              (should= { 1 {:connections {4 2}} 4 {:connections {1 2}}}
                       (connect {} 15 1 2 4))))

(run-specs)
