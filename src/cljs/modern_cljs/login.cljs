(ns modern-cljs.login
  (:require-macros [hiccups.core :as h])
  (:require [domina :refer [by-id value]]
            [hiccups.runtime :as hiccupsrt]
            [domina.events :refer [listen!]]))

(defn validate-form []
  (let [email    (by-id "email")
        password (by-id "password")]
    (if (and (> (count (value email)) 0)
             (> (count (value password)) 0))
      true
      (do (js/alert "Please, complete the form!")
          false))))

(defn ^:export init []
  (if (and js/document
           (aget js/document "getElementById"))
    (listen! (by-id "submit") :click validate-form)))
