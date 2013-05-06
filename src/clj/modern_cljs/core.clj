(ns modern-cljs.core
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]))

;; defroutes macro defines a function that chains individual route
;; functions together.  The request map is passed to each function in
;; turn, until a non-nil response is returned.
(defroutes app-routes
  ; to serve document root address
  (GET "/" [] "<p>Hello from compojure</p>")
  ; to serve static pages saved in resources/public dir
  (route/resources "/")
  ; if page is not found
  (route/not-found "foah-oh-foah: page didnt be found!"))

(def handler
  (handler/site app-routes))