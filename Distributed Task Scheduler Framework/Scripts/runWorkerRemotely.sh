clear

pscp -h workers.txt -O StrictHostKeyChecking=no -r /home/nikhil/workspace/TaskSchedulerSQS /home/ubuntu
pssh -h workers.txt -t -1 -O StrictHostKeyChecking=no -o . './TaskSchedulerSQS/runWorker.sh'

exit 0
