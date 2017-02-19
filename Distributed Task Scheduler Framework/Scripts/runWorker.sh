clear

sudo apt-get update
sudo apt-get install default-jdk
sudo apt-get install ant
cd /home/ubuntu/TaskSchedulerSQS

ant -DinputQ=inputQ -DoutputQ=outputQ -Dt=1 runworker

exit 0
