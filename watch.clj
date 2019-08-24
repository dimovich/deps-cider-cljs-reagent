(require '[figwheel.main.watching :as fww]
         '[clojure.tools.namespace.reload :as nr]
         '[clojure.tools.namespace.file :as nf]
         '[clojure.tools.namespace.track :as nt])



(def clj-paths ["src/clj"])

(println "watching clj: " (pr-str clj-paths))

(fww/add-watch!
 :my-clj-watcher
 {:paths clj-paths
  :filter (fww/suffix-filter #{"clj" "cljc"})
  :handler (fww/throttle
            100
            (fn [evts]
              (when-let [files (->> evts (mapv :file)
                                    set vec not-empty)]
                (doseq [file files]
                  (let [err (-> (nt/tracker)
                                (nf/add-files [file])
                                (nr/track-reload)
                                ::nr/error)]
                    (if err
                      (println (-> err ex-message)
                               "\n" (-> err ex-cause ex-message))
                      (println "reloaded " (.getPath file))))))))})



(comment
  (fww/remove-watch! :my-clj-watcher))
