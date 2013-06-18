(ns modern-cljs.login
  (:require-macros [hiccups.core :refer [html]])
  (:require [domina :refer [by-id value by-class html prepend! append! destroy! log attr]]
            [hiccups.runtime :as hiccupsrt]
            [domina.events :refer [listen! prevent-default]]))

;;; 4 to 8, at least one numeric digit.
;(def ^:dynamic *password-re* #"^(?=.*\d).{4,8}$")

;(def ^:dynamic *email-re* #"^[_a-z0-9-]+(\.[_a-z0-9-]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,4})$")

(defn validate-email [email]
  (destroy! (by-class "email"))
  (if (not (re-matches (re-pattern (attr email :pattern)) (value email)))
    (do
      (prepend! (by-id "loginForm") (html [:div.help.email (attr email :title)]))
      false)
    true))

(defn validate-password [password]
  (destroy! (by-class "password"))
  (if (not (re-matches (re-pattern (attr password :pattern)) (value password)))
    (do
      (append! (by-id "loginForm") (html [:div.help.password (attr password :title)]))
      false)
    true))

(defn validate-form [e]
  (let [email        (by-id "email")
        password     (by-id "password")
        email-val    (value email)
        password-val (value password)]
    (if (or (empty? email-val) (empty? password-val))
      (do
        (destroy! (by-class "help"))
        (prevent-default e)
        (append! (by-id "loginForm") (html [:div.help "Please complete the form"])))
      (if (and (validate-email email)
               (validate-password password))
        true
        (prevent-default e)))))

(defn ^:export init []
  (if (and js/document
           (aget js/document "getElementById"))
    (let [email    (by-id "email")
          password (by-id "password")]
        (listen! (by-id "submit") :click (fn [e] (validate-form e)))
        (listen! email :blur (fn [e] (validate-email email)))
        (listen! password :blur (fn [e] (validate-password password))))))
