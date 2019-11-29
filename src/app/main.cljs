
(ns app.main
  (:require [reagent.core :as r]
            ; [app.firebase :as firebase]
            [app.parens :as parens]))

(js/console.log "hiya")

(defn mount! []
  (r/render [parens/view] (js/document.getElementById "coucou")))

(defn main! []
  ; (firebase/init!)
  (parens/init!)
  (mount!))

(defn reload! []
  (mount!))
