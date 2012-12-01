rmdir -Force -Recur target
lein uberjar
java -jar .\target\chitty-0.1.0-SNAPSHOT-standalone.jar