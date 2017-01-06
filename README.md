Functions and the Single Responsibility Principle

You're most likely familiar with the Single Responsibility Principle - it's of course the S in the SOLID principles acronym and a cornerstone of many folks' idea of good OO design. If you're like me though, you've pretty much always heard it discussed in relation to modules or classes. It was really interesting for me to read Bob Martin talk about SRP in relation to functions in his Clean Code book - I had started thinking in an SRP way when writing Clojure but I hadn't made the connection until I read Uncle Bob lay it out clearly.

(ns app.core)

(defn construct-win-percentage-data [users]
  (if (empty? users)
    (do
      (println "Use a real logger here maybe")
      :invalid-data)
    (->> users
      (map
        (fn [user]
          (when (:name user)
            (try
              {:name (:name user)
               :win-percentage (/ (:wins user) (:games user))}
              (catch Exception e
                (println "Error processing user" user)
                nil)))))
      (remove nil?))))

(require '[app.core :as ac])

(ac/construct-win-percentage-data
  [
     {:name "Frank" :wins 1 :games 3}
     {:name "Francis" :wins 2 :games 4}
     {:name "Charles" :wins 0 :games 0}
     {:nope :nah}
  ])

1) Invalid paramter check and handling
2) List comprehension logic
3) Determing valid users
4) Logic to create new user data structure



--------------