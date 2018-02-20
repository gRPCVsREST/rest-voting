#!/bin/bash

cat <<YAML
apiVersion: apps/v1beta1
kind: Deployment
metadata:
  name: rest-voting
spec:
  replicas: 1
  template:
    metadata:
      labels:
        app: rest-voting
    spec:
      containers:
        - name: voting
          image: gcr.io/$GCP_PROJECT/rest-voting:latest
          imagePullPolicy: Always
          ports:
            - containerPort: 8080
          env:
            - name: leaderboard_url
              value: "http://leaderboard:8080/"
            - name: aggregator_url
              value: "http://rest-aggregator:8080/"
            - name: foobar
              value: "$(date +%s)"
---
apiVersion: v1
kind: Service
metadata:
  name: rest-voting
spec:
  type: NodePort
  selector:
    app: rest-voting
  ports:
   - port: 8080
     targetPort: 8080
     protocol: TCP
---
YAML