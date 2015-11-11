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

(describe "connect-down-left"
          (it "should make the right connections"
              (should= {1 {:connections {4 2}}
                        4 {:connections {1 2}}}
                       (connect-down-left {} 15 1))))

(describe "connect-down-right"
          (it "should make the right connections"
              (should= {3 {:connections {10 6}}
                        10 {:connections {3 6}}}
                       (connect-down-right {} 15 3))))

(def simple-board
  {1 {:connections {6 3, 4 2}, :pegged true}
   4 {:connections {1 2}}
   6 {:connections {1 3}}})

(describe "add-pos"
          (it "should identify the location of the peg"
              (should= simple-board
                       (add-pos {} 15 1))))

(describe "pegged?"
          (it "should return true when the position is pegged"
              (should (pegged? simple-board 1)))
          (it "should return false when the position is not pegged"
              (should-not (pegged? simple-board 4))))

(describe "remove-peg"
          (it "should remove the peg from the specified position"
              (should-not (get-in (remove-peg simple-board 1) [1 :pegged]))))

(describe "place-peg"
          (it "should put a peg in the specified position"
              (should (get-in (place-peg (remove-peg simple-board 1) 4) [4 :pegged]))))

(describe "move-peg"
          (before-all
           (def old-position 1)
           (def new-position 4)
           (def updated-board
             (move-peg simple-board old-position new-position)))
          (it "should remove the peg from the old position"
              (should-not (get-in updated-board [old-position :pegged])))
          (it "should put the peg in the new position"
              (should (get-in updated-board [new-position :pegged]))))

(describe "valid-moves"
          (before-all
           (def board (assoc-in (new-board 5) [4 :pegged] false)))
          (it "should identify a valid move of {4 2} for position one"
              (should= {4 2} (valid-moves board 1)))
          (it "should identify a valid move of {4 5} for position six"
              (should= {4 5} (valid-moves board 6)))
          (it "should identify a valid move of {4 7} for position eleven"
              (should= {4 7} (valid-moves board 11)))
          (it "should identify no valid moves for position five"
              (should= {} (valid-moves board 5)))
          (it "should identify no valid moves for position eight"
              (should= {} (valid-moves board 8))))

(describe "valid-move?"
          (before-all
           (def board (assoc-in (new-board 5) [4 :pegged] false)))
          (it "should return nil if a move is not valid"
              (should-be-nil (valid-move? board 8 4)))
          (it "should return the jumped position when the move is valid"
              (should= 2 (valid-move? board 1 4))))

(run-specs)
