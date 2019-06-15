(ns external-function.core
  (:require [pretty.core :as pretty]))

(defprotocol ^:private IExternalFn
  (^clojure.lang.IFn get-impl [this]
   "Get the implementation of this external function, or throw an Exception if none has been provided yet.")

  (provide-impl! [this, ^clojure.lang.IFn impl]
    "Provide an implementation of an external function."))

(deftype ExternalFn [impl]
  pretty/PrettyPrintable
  (pretty [_]
    (list 'external-fn @impl))

  IExternalFn
  (get-impl [_]
    (or @impl (throw (AbstractMethodError. "Implementation has not been provided."))))

  (provide-impl! [_ an-impl]
    (reset! impl an-impl))

  java.util.concurrent.Callable
  (call [this]
    (.call (get-impl this)))

  java.lang.Runnable
  (run [this]
    (.run (get-impl this)))

  clojure.lang.IFn
  (applyTo [this arglist]
    (.applyTo (get-impl this) arglist))

  (invoke [this]
    (.invoke (get-impl this)))

  (invoke [this arg1]
    (.invoke (get-impl this) arg1))

  (invoke [this arg1 arg2]
    (.invoke (get-impl this) arg1 arg2))

  (invoke [this arg1 arg2 arg3]
    (.invoke (get-impl this) arg1 arg2 arg3))

  (invoke [this arg1 arg2 arg3 arg4]
    (.invoke (get-impl this) arg1 arg2 arg3 arg4))

  (invoke [this arg1 arg2 arg3 arg4 arg5]
    (.invoke (get-impl this) arg1 arg2 arg3 arg4 arg5))
  (invoke [this arg1 arg2 arg3 arg4 arg5 arg6]
    (.invoke (get-impl this) arg1 arg2 arg3 arg4 arg5 arg6))

  (invoke [this arg1 arg2 arg3 arg4 arg5 arg6 arg7]
    (.invoke (get-impl this) arg1 arg2 arg3 arg4 arg5 arg6 arg7))

  (invoke [this arg1 arg2 arg3 arg4 arg5 arg6 arg7 arg8]
    (.invoke (get-impl this) arg1 arg2 arg3 arg4 arg5 arg6 arg7 arg8))

  (invoke [this arg1 arg2 arg3 arg4 arg5 arg6 arg7 arg8 arg9]
    (.invoke (get-impl this) arg1 arg2 arg3 arg4 arg5 arg6 arg7 arg8 arg9))

  (invoke [this arg1 arg2 arg3 arg4 arg5 arg6 arg7 arg8 arg9 arg10]
    (.invoke (get-impl this) arg1 arg2 arg3 arg4 arg5 arg6 arg7 arg8 arg9 arg10))

  (invoke [this arg1 arg2 arg3 arg4 arg5 arg6 arg7 arg8 arg9 arg10 arg11]
    (.invoke (get-impl this) arg1 arg2 arg3 arg4 arg5 arg6 arg7 arg8 arg9 arg10 arg11))

  (invoke [this arg1 arg2 arg3 arg4 arg5 arg6 arg7 arg8 arg9 arg10 arg11 arg12]
    (.invoke (get-impl this) arg1 arg2 arg3 arg4 arg5 arg6 arg7 arg8 arg9 arg10 arg11 arg12))

  (invoke [this arg1 arg2 arg3 arg4 arg5 arg6 arg7 arg8 arg9 arg10 arg11 arg12 arg13]
    (.invoke (get-impl this) arg1 arg2 arg3 arg4 arg5 arg6 arg7 arg8 arg9 arg10 arg11 arg12 arg13))

  (invoke [this arg1 arg2 arg3 arg4 arg5 arg6 arg7 arg8 arg9 arg10 arg11 arg12 arg13 arg14]
    (.invoke (get-impl this) arg1 arg2 arg3 arg4 arg5 arg6 arg7 arg8 arg9 arg10 arg11 arg12 arg13 arg14))

  (invoke [this arg1 arg2 arg3 arg4 arg5 arg6 arg7 arg8 arg9 arg10 arg11 arg12 arg13 arg14 arg15]
    (.invoke (get-impl this) arg1 arg2 arg3 arg4 arg5 arg6 arg7 arg8 arg9 arg10 arg11 arg12 arg13 arg14 arg15))

  (invoke [this arg1 arg2 arg3 arg4 arg5 arg6 arg7 arg8 arg9 arg10 arg11 arg12 arg13 arg14 arg15 arg16]
    (.invoke (get-impl this) arg1 arg2 arg3 arg4 arg5 arg6 arg7 arg8 arg9 arg10 arg11 arg12 arg13 arg14 arg15 arg16))

  (invoke [this arg1 arg2 arg3 arg4 arg5 arg6 arg7 arg8 arg9 arg10 arg11 arg12 arg13 arg14 arg15 arg16 arg17]
    (.invoke (get-impl this) arg1 arg2 arg3 arg4 arg5 arg6 arg7 arg8 arg9 arg10 arg11 arg12 arg13 arg14 arg15 arg16
             arg17))

  (invoke [this arg1 arg2 arg3 arg4 arg5 arg6 arg7 arg8 arg9 arg10 arg11 arg12 arg13 arg14 arg15 arg16 arg17 arg18]
    (.invoke (get-impl this) arg1 arg2 arg3 arg4 arg5 arg6 arg7 arg8 arg9 arg10 arg11 arg12 arg13 arg14 arg15 arg16
             arg17 arg18))

  (invoke [this arg1 arg2 arg3 arg4 arg5 arg6 arg7 arg8 arg9 arg10 arg11 arg12 arg13 arg14 arg15 arg16 arg17 arg18
           arg19]
    (.invoke (get-impl this) arg1 arg2 arg3 arg4 arg5 arg6 arg7 arg8 arg9 arg10 arg11 arg12 arg13 arg14 arg15 arg16
             arg17 arg18 arg19))

  (invoke [this arg1 arg2 arg3 arg4 arg5 arg6 arg7 arg8 arg9 arg10 arg11 arg12 arg13 arg14 arg15 arg16 arg17 arg18
           arg19 arg20]
    (.invoke (get-impl this) arg1 arg2 arg3 arg4 arg5 arg6 arg7 arg8 arg9 arg10 arg11 arg12 arg13 arg14 arg15 arg16
             arg17 arg18 arg19 arg20))

  (invoke [this arg1 arg2 arg3 arg4 arg5 arg6 arg7 arg8 arg9 arg10 arg11 arg12 arg13 arg14 arg15 arg16 arg17 arg18
           arg19 arg20 more]
    (.invoke (get-impl this) arg1 arg2 arg3 arg4 arg5 arg6 arg7 arg8 arg9 arg10 arg11 arg12 arg13 arg14 arg15 arg16
             arg17 arg18 arg19 arg20 more)))

(defn ^external_function.core.ExternalFn external-fn
  "Create an function whose implementation will be provided externally. You can invoke this function just like a normal
  function; if an implementation has not been provided yet, it will throw an Exception.

  Provide an impl with `(provide-impl external-fn some-fn)`. You can also use this method to replace impls."
  ([]
   (external-fn nil))

  ([initial-impl]
   (ExternalFn. (atom initial-impl))))

(defmacro defexternal
  "Define an function whose implementation will be provided externally. You can invoke this function just like a normal
  function; if an implementation has not been provided yet, it will throw an Exception.

  Provide an implementation with `(provide-impl external-fn some-fn)`. You can also use this method to replace impls."
  {:arglists '([fn-name docstring? attr-map? arglist-or-arglists])}
  [fn-name & options]
  (let [[docstring & options] (if (string? (first options))
                                options
                                (cons nil options))
        [attr-map arglists]   (if (map? (first options))
                                options
                                [nil (first options)])
        arglists              (if (list? arglists)
                                arglists
                                (list arglists))]
    `(defonce ~(vary-meta
                fn-name
                merge
                {:doc docstring, :arglists `(quote ~arglists) :tag external_function.core.ExternalFn}
                attr-map)
       (external-fn))))
