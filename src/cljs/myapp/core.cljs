(ns ^:figwheel-hooks myapp.core
  (:require [reagent.core :as r]))


(defn my-app []
  [:div "Hello World!"])


(defn ^:after-load reload []
  (r/render [my-app]
            (.getElementById js/document "app")))


(defn init! []
  (reload))


(init!)
