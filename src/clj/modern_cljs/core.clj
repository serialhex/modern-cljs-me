(ns modern-cljs.core
  (:require [compojure.core    :refer [defroutes GET POST]]
            [compojure.route   :refer [resources not-found]]
            [compojure.handler :refer [site]]
            [modern-cljs.login :refer [authenticate-user]]))

;; defroutes macro defines a function that chains individual route
;; functions together.  The request map is passed to each function in
;; turn, until a non-nil response is returned.
(defroutes app-routes
  ; to serve document root address
  (GET "/" [] "<p>Hello from compojure</p>")
  (POST "/login" [email password] (authenticate-user email password))
  (resources "/")
  (not-found "foah-oh-foah: page didnt be found!"))

(def handler
  (site app-routes))
