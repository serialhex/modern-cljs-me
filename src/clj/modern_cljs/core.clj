(ns modern-cljs.core
  (:require [compojure.core :refer [defroutes GET]]
            [compojure.route :refer [resources not-found]]
            [compojure.handler :refer [site]]))

;; defroutes macro defines a function that chains individual route
;; functions together.  The request map is passed to each function in
;; turn, until a non-nil response is returned.
(defroutes app-routes
  ; to serve document root address
  (GET "/" [] "<p>Hello from compojure</p>")
  ; to serve static pages saved in resources/public dir
  (resources "/")
  ; if page is not found
  (not-found "foah-oh-foah: page didnt be found!"))

(def handler
  (site app-routes))
