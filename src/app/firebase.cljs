(ns app.firebase
  (:require [re-frame.core :as rf]
            ["firebase/app" :as firebase]
            ["firebase/database"]))

(def config
  #js {:apiKey "AIzaSyAgKh_Z2JF0rh3quDpZ0q8tugpvno21NtU"
       :authDomain "parens-2eb63.firebaseapp.com"
       :databaseURL "https://parens-2eb63.firebaseio.com"
       :projectId "parens-2eb63"
       :storageBucket "parens-2eb63.appspot.com"
       :messagingSenderId "243574292481"
       :appId "1:243574292481:web:b39e738eb5e624358c4000"})

(defn init! []
  (firebase/initializeApp config))

(rf/reg-fx
 :firebase-read
 (fn [[path limit on-value]]
   (.. (firebase/database) (ref path) (orderByKey) (limitToLast limit)
       (on "value"
           (fn [snap]
             (let [res (-> snap (.val) (js->clj :keywordize-keys true))]
               (rf/dispatch [on-value res])))))))

(rf/reg-fx
 :firebase-write
 (fn [[path value]]
   (.. (firebase/database) (ref path) (push) (set value))))




