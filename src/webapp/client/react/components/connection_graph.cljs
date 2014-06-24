(ns webapp.client.react.components.connection-graph
  (:require
   [om.core          :as om :include-macros true]
   [om.dom           :as dom :include-macros true]
   [clojure.data     :as data]
   [clojure.string   :as string]
   )
  (:use-macros
   [webapp.framework.client.coreclient      :only  [defn-ui-component]])
  (:use
   [webapp.client.helper  :only [amend-record]]
   [webapp.framework.client.system-globals  :only  [touch]]
   ))






;---------------------------------------------------------
(defn-ui-component   text-graph    [companies]
  {:absolute-path [:ui :companies]}
;---------------------------------------------------------
     (dom/div
      #js {:style #js {:height "100%" :width "100%"}}

      (apply
       dom/div
       nil
       (map
        (fn[x]
          (dom/div
           nil
           (dom/div
            #js {
                 :style
                 #js {
                      :width "200px"
                      :display "inline-block"}}
            (get x "company"))
           (dom/a
            #js {:href "#"
                 :onClick

                 #(om/update! companies [:values]
                              (amend-record (into [] (get @companies :values))
                                            "company"
                                            (get @x "company")
                                            (fn[z] (merge z {:clicked true}))
                                            ))


                 }
            (get x "inbound"))
           ))
        (-> companies :values ))
       )

      ))




(defn graph [{:keys [data]} owner]
  (reify

    ;---------------------------------------------------------
    om/IRender
    (render
     [this]
     (dom/div
      #js {:style #js {:height "100%" :width "100%"}}

      (dom/div #js {:style #js {:padding-top "40px"}} "Connections")

      (dom/svg #js {:style #js {:width "200" :height "200"}}
          (dom/circle #js {:cx "60"
                           :cy "60"
                           :r  (get-in data [:width])
                           :stroke "green"
                           :strokeWidth "4"
                           :fill "yellow"} )
      )

))))










;---------------------------------------------------------
(defn-ui-component  latest-endorsements    [endorsements]
  {:absolute-path [:ui :endorsements]}
;---------------------------------------------------------
     (dom/div
      #js {:style #js {:height "100%" :width "100%"}}

      (apply
       dom/div
       nil
       (map
        (fn[x]
          (dom/div
           nil
           (dom/div
            #js {
                 :style
                 #js {
                      :width "200px"
                      :display "inline-block"}}
            (get x "company"))
           (dom/a
            #js {:href "#"
                 :onClick

                 #(om/update! endorsements [:values]
                              (amend-record (into [] (get @endorsements :values))
                                            "company"
                                            (get @x "company")
                                            (fn[z] (merge z {:clicked true}))
                                            ))


                 }
            (get x "inbound"))
           ))
        (-> endorsements :values ))
       )

      ))


