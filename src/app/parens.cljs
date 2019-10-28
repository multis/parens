(ns app.parens
  (:require [re-frame.core :as rf]))

(def init! #(rf/dispatch [::init]))

(rf/reg-event-fx
 ::init
 (fn []
   {:db {::parens ["(" ")"]}}))

(rf/reg-event-fx
 ::add
 (fn [{db :db} [_ elem]]
   (if (contains? #{"(" ")"} elem)
     {:db (update db ::parens (fn [parens] (conj parens elem)))}
     nil)))

(rf/reg-sub
 ::last-parens
 (fn [db]
   (::parens db)))

(defn last-parens []
  [:div.mt4
   (map-indexed (fn [i elem] ^{:key i} [:span elem])
                @(rf/subscribe [::last-parens]))])

(defn buttons []
  [:div.flex.justify-between
   [:button.f1.dim.br2.ba.bw2.ph4.dark-blue.b--dark-blue.pointer.w-100.mr2
    {:on-click #(rf/dispatch [::add "("])} "("]
   [:button.f1.dim.br2.ba.bw2.ph4.dark-green.b--dark-green.pointer.w-100
    {:on-click #(rf/dispatch [::add ")"])} ")"]])

(defn view []
  [:div.mw5.center.pt5
   [buttons]
   [last-parens]])