(ns ^:figwheel-hooks myapp.core
  (:require [reagent.dom :as rd]))


(defn my-app []
  [:div "Hello World!"])


(defn ^:after-load reload []
  (rd/render [my-app]
             (.getElementById js/document "app")))


(defn init! []
  (reload))

(init!)
