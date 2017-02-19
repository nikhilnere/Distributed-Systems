clear

sudo apt-get update
sudo apt-get install default-jdk
sudo apt-get install ant
cd /home/ubuntu/TaskSchedulerSQS
javac WorkloadGenerationUtil.java
java WorkloadGenerationUtil 10000 "sleep 0" Workload
ant -DinputQ=inputQ -DoutputQ=outputQ -Dw=Workload runclient

exit 0
