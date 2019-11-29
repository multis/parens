(ns app.parens
  (:require [re-frame.core :as rf]))

(def init! #(rf/dispatch [::init]))

(rf/reg-event-fx
 ::init
 (fn []
   {:db {}}))

(rf/reg-event-fx
 ::add
 (fn [_ [_ elem]]
   (if (contains? #{"(" ")"} elem)
     {:firebase/write ["parens2" elem]})))

(defn buttons []
  [:div.flex.justify-between
   [:button.f1.grow.br2.ba.bw2.ph4.dark-blue.b--dark-blue.pointer.w-100.mr2
    {:on-click #(rf/dispatch [::add "("])} "("]
   [:button.f1.grow.br2.ba.bw2.ph4.dark-green.b--dark-green.pointer.w-100
    {:on-click #(rf/dispatch [::add ")"])} ")"]])

(rf/reg-sub
 ::last-parens
 (fn [db]
   (sort-by first (::parens db))))

(defn last-parens []
  [:div.mt4.f2
   (for [[id paren] @(rf/subscribe [::last-parens])]
     ^{:key id}
     [:span.dib paren])])

(defn view []
  [:div.mw5.center.pt5
   [buttons]
   [last-parens]])
