(require '[figwheel.main.watching :as fww]
         '[clojure.tools.namespace.repl :as nr])



(def clj-paths ["src/clj"])



(defn watch! []
  (println "watching clj: " (pr-str clj-paths))

  (apply nr/set-refresh-dirs clj-paths)

  (fww/add-watch!
   :my-clj-watcher
   {:paths clj-paths
    :filter (fww/suffix-filter #{"clj" "cljc"})
    :handler (fww/throttle
              100
              (bound-fn [_]
                (let [result (nr/refresh)]
                  (when (not= :ok result)
                    (println (ex-message result) "\n"
                             (ex-message (ex-cause result)))))))}))


(watch!)



(comment
  (fww/remove-watch! :my-clj-watcher))
