clear
pscp -h host16.txt -O StrictHostKeyChecking=no -r /home/nikhil/AOS/Assignment4/SourceCode /home/ubuntu/
pscp -h host16.txt -O StrictHostKeyChecking=no DHTPerfTest.sh /home/ubuntu/
pscp -h host16.txt -O StrictHostKeyChecking=no ServerScript.sh /home/ubuntu/

pssh -h host16.txt -t -1 -O StrictHostKeyChecking=no -o . 'chmod +x ServerScript.sh'
pssh -h host16.txt -t -1 -O StrictHostKeyChecking=no -o . 'chmod +x DHTPerfTest.sh'
pssh -h host16.txt -t -1 -O StrictHostKeyChecking=no -o . 'sh ./ServerScript.sh'

exit 0
