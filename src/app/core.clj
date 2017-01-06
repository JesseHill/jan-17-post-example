(ns app.core)

(declare handle-empty-users-param
         construct-data-from-user-collection
         remove-empty-items
         construct-data-for-user
         valid-user?
         safely-build-win-percentage-data
         build-win-percentage-data)

(defn construct-win-percentage-data [users]
  (if (empty? users)
    (handle-empty-users-param)
    (construct-data-from-user-collection users)))

(defn- handle-empty-users-param []
  (println "Use a real logger here - Invalid users")
  :invalid-data)

(defn- construct-data-from-user-collection [users]
  (->> users
    (map construct-data-for-user)
    remove-empty-items))

(defn- remove-empty-items [coll]
  (remove nil? coll))

(defn- construct-data-for-user [user]
  (when (valid-user? user)
    (safely-build-win-percentage-data user)))

(defn- valid-user? [user]
  (some? (:name user)))

(defn- handle-build-win-percentage-data-error [e]
  (println "Do some real logging to print the error: " (.getMessage e))
  nil)

(defn- safely-build-win-percentage-data [user]
  (try
    (build-win-percentage-data user)
    (catch Exception e
      (handle-build-win-percentage-data-error e))))

(defn- build-win-percentage-data [user]
  {:name (:name user)
   :win-percentage (/ (:wins user) (:games user))})




