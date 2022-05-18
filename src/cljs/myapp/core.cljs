(ns ^:figwheel-hooks myapp.core
  (:require [reagent.dom :as rd]))


(defn myapp []
  [:div "Hello World!"])


(defn ^:after-load reload []
  (rd/render [myapp]
             (.getElementById js/document "app")))


(defn main []
  (reload))
