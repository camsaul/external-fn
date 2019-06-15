[![Downloads](https://versions.deps.co/camsaul/external-fn/downloads.svg)](https://versions.deps.co/camsaul/external-fn)
[![Dependencies Status](https://versions.deps.co/camsaul/external-fn/status.svg)](https://versions.deps.co/camsaul/external-fn)
[![Circle CI](https://circleci.com/gh/camsaul/external-fn.svg?style=svg)](https://circleci.com/gh/camsaul/external-fn)
[![License](https://img.shields.io/badge/license-Eclipse%20Public%20License-blue.svg)](https://raw.githubusercontent.com/camsaul/external-fn/master/LICENSE)
[![cljdoc badge](https://cljdoc.org/badge/camsaul/external-fn)](https://cljdoc.org/d/camsaul/external-fn/CURRENT)

[![Clojars Project](https://clojars.org/camsaul/external-fn/latest-version.svg)](http://clojars.org/camsaul/external-fn)

A small Clojure utility library for defining abstract functions whose implementation will be provided elsewhere
(perhaps externally), or for defining methods. Useful for dependency injection and otherwise nicely
decoupling different modules of your project from one another.

This library provides a function, `external-fn`, that you can use to create an externally-defined function.
This resulting object works exactly like a normal Clojure function, but will throw an Exception if invoked before
an instance has been provided:

```clj
(require '[external-fn.core :as external-fn])

(def f (external-fn/external-fn))

(f) ;-> AbstractMethodError
```

You can provide an implementation with `provide-impl!`:

```clj
;; provide an implementation
(external-fn/provide-impl! f (constantly :ok))

;; now function will work as expected
(f) ;-> :ok
```

This library also provides a `defexternal` macro to define an external function with almost the exact same syntax as `defn`; the usual
nice-to-have metadata like `:arglists` is provided as well.


```clj
(external-fn/defexternal my-fn
  "You can add docstrings"
  {:can-add-attribute-maps? true}
  [x])

(external-fn/provide-impl! my-fn (fn [x] (+ x x)))

(my-fn 2) ;-> 4
```
