(ns external-function.core-test
  (:require [clojure.pprint :as pprint]
            [expectations :refer [expect]]
            [external-function.core :as external-fn]))

;; if you create an external function but don't provide an impl, calling it should throw an Exception
(expect
 AbstractMethodError
 (let [f (external-fn/external-fn)]
   (f)))

;; you should be able to provide an implementation
(expect
 :a
 (let [f (external-fn/external-fn)]
   (external-fn/provide-impl! f (constantly :a))
   (f)))

;; you should be able to swap out the impl for a new one
(expect
 :b
 (let [f (external-fn/external-fn)]
   (external-fn/provide-impl! f (constantly :a))
   (external-fn/provide-impl! f (constantly :b))
   (f)))

;; you should be able to get the impl with `get-impl`
(let [my-fn (fn [])]
  (expect
   my-fn
   (let [f (external-fn/external-fn)]
     (external-fn/provide-impl! f my-fn)
     (external-fn/get-impl f))))

;; you should be able to provide an initial impl
(expect
 "WOW"
 (let [f (external-fn/external-fn (constantly "WOW"))]
   (f)))

;; you should be able to define an external-fn using the helpful macro
(external-fn/defexternal external-fn-1 [])

(expect
 :ok
 (do
   (external-fn/provide-impl! external-fn-1 (constantly :ok))
   (external-fn-1)))

;; should have the expected meta
(expect
 {:doc nil, :arglists '([]), :tag external_function.core.ExternalFn}
 (select-keys (meta #'external-fn-1) [:doc :arglists :tag]))

;; should be able to add a docstring
(external-fn/defexternal external-fn-2 "My docstring" [])

(expect
 {:doc      "My docstring"
  :arglists '([])
  :tag      external_function.core.ExternalFn}
 (select-keys (meta #'external-fn-2) [:doc :arglists :tag]))

;; should be able to use multiple argslists
(external-fn/defexternal external-fn-3 "My docstring" ([] [x] [x y]))

(expect
 {:doc      "My docstring"
  :arglists '([] [x] [x y])
  :tag      external_function.core.ExternalFn}
 (select-keys (meta #'external-fn-3) [:doc :arglists :tag]))

;; should be able to use an attr map
(external-fn/defexternal external-fn-4 {:amazing? true} [])

(expect
 {:doc      nil
  :arglists '([])
  :amazing? true
  :tag      external_function.core.ExternalFn}
 (select-keys (meta #'external-fn-4) [:doc :arglists :amazing? :tag]))

;; external functions should pretty-print nicely
(expect
 "(external-fn nil)\n"
 (with-out-str (pprint/pprint (external-fn/external-fn))))

(expect
 "(external-fn \"#function[some-fn]\")\n"
 ;; not actually a real fn but we just want to be able to check that it pretty prints correctly
 (with-out-str (pprint/pprint (external-fn/external-fn "#function[some-fn]"))))
