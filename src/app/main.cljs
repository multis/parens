
(ns app.main
  (:require [app.lib :as lib]
            [reagent.core :as r]
            [app.parens :as parens]))

(def a 1)

(defonce b 2)

(defn view []
  [:div "Hey hey"])

(defn mount! []
  (r/render [parens/view] (js/document.getElementById "coucou")))

(defn main! []
  (println "[main]: loading")
  (parens/init!)
  (mount!))

(defn reload! []
  (println "[main] reloaded lib:" lib/c lib/d)
  (println "[main] reloaded:" a b)
  (mount!))
