(ns app.spec
  (:require [cljs.spec.alpha :as s]
            [re-frame.core :as rf]))

(defn check-and-throw
  [a-spec db]
  (when-not (s/valid? a-spec db)
    (throw (ex-info (str "spec check faild: " (s/explain-str a-spec db)) {}))))

(s/def ::db (s/keys :req [:app.parens/parens]))

(def check-spec-interceptor (rf/after (partial check-and-throw ::db)))

