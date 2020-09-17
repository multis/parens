
(ns app.main
  (:require [reagent.dom :as rd]
            [app.firebase :as firebase]
            [app.parens :as parens]))

(js/console.log "hiya")

(defn mount! []
  (rd/render [parens/view] (js/document.getElementById "coucou")))

(defn main! []
  (firebase/init!)
  (parens/init!)
  (mount!))

(defn reload! []
  (mount!))
