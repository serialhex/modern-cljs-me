(defproject modern-cljs "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  ;; CLJ source code path
  :source-paths ["src/clj"]
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [compojure "1.1.5"]]

  ;; lein-cljsbuild plugin to build a CLJS project
  :plugins [;; cljsbuild plugin
            [lein-cljsbuild "0.3.0"]

            ;;ring plugin
            [lein-ring "0.8.3"]]

  ;; ring tasks config
  :ring {:handler modern-cljs.core/handler}

  ;; cljsbuild options configuration
  :cljsbuild {:builds
              [{;; CLJS source code path
                :source-paths ["src/cljs"]

                ;; Google Clojure (CLS) options configuration
                :compiler {;; CJS generated JS script filename
                           :output-to "resources/public/js/modern.js"

                           ;; minimal js optimization directive
                           :optimizations :whitespace

                           ;; generated JS code prettyfication
                           :pretty-print true}}]})