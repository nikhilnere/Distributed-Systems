clear

CLIENT_IP=54.172.170.204

scp -i 'nikhil.pem' -r /home/nikhil/workspace/TaskSchedulerSQS ubuntu@$CLIENT_IP:/home/ubuntu

ssh -i 'nikhil.pem' ubuntu@$CLIENT_IP './TaskSchedulerSQS/runClient.sh'

exit 0
