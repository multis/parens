(ns server.index
  (:require ["firebase-functions" :as fire-fn]
            ["firebase-admin" :as fire-admin]))

(fire-admin/initializeApp)

(def last-parens
  (.. fire-fn -https
      (onRequest
       (fn [_ res]
         (.. (fire-admin/database) (ref "parens") (orderByKey) (limitToLast 105)
             (on "value"
                 (fn [^js snap]
                   (let [data (-> snap (.val) (js->clj :keywordize-keys true))]
                     (.json res (clj->js (vals data)))))))))))

(def exports #js {:lastParens last-parens})