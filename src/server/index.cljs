(ns server.index
  (:require ["firebase-functions" :as fire-fn]))

(def last-parens
  (.. fire-fn -https
      (onRequest (fn [_ res] (.send res "heyheyheyi")))))

(def exports #js {:lastParens last-parens})