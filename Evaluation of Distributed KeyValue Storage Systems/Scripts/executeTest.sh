clear
pssh -h host1.txt -t -1 -O StrictHostKeyChecking=no -o . 'sh ./DHTPerfTest.sh'
scp -i nikhilnere.pem ubuntu@54.172.158.138:/home/ubuntu/SourceCode/perfLog.log /home/nikhil/AOS/Assignment4/serverlogs/1/
scp -i nikhilnere.pem ubuntu@54.172.158.138:/home/ubuntu/SourceCode/perfLogMongo.log /home/nikhil/AOS/Assignment4/serverlogs/1/
scp -i nikhilnere.pem ubuntu@54.172.158.138:/home/ubuntu/SourceCode/perfLogRedis.log /home/nikhil/AOS/Assignment4/serverlogs/1/
scp -i nikhilnere.pem ubuntu@54.172.158.138:/home/ubuntu/SourceCode/perfLogCouch.log /home/nikhil/AOS/Assignment4/serverlogs/1/


pssh -h host2.txt -t -1 -O StrictHostKeyChecking=no -o . 'sh ./DHTPerfTest.sh'
scp -i nikhilnere.pem ubuntu@54.172.158.138:/home/ubuntu/SourceCode/perfLog.log /home/nikhil/AOS/Assignment4/serverlogs/2/1/
scp -i nikhilnere.pem ubuntu@54.172.158.138:/home/ubuntu/SourceCode/perfLogMongo.log /home/nikhil/AOS/Assignment4/serverlogs/2/1/
scp -i nikhilnere.pem ubuntu@54.172.158.138:/home/ubuntu/SourceCode/perfLogRedis.log /home/nikhil/AOS/Assignment4/serverlogs/2/1/
scp -i nikhilnere.pem ubuntu@54.172.158.138:/home/ubuntu/SourceCode/perfLogCouch.log /home/nikhil/AOS/Assignment4/serverlogs/2/1/
scp -i nikhilnere.pem ubuntu@54.172.124.155:/home/ubuntu/SourceCode/perfLog.log /home/nikhil/AOS/Assignment4/serverlogs/2/2/
scp -i nikhilnere.pem ubuntu@54.172.124.155:/home/ubuntu/SourceCode/perfLogMongo.log /home/nikhil/AOS/Assignment4/serverlogs/2/2/
scp -i nikhilnere.pem ubuntu@54.172.124.155:/home/ubuntu/SourceCode/perfLogRedis.log /home/nikhil/AOS/Assignment4/serverlogs/2/2/
scp -i nikhilnere.pem ubuntu@54.172.124.155:/home/ubuntu/SourceCode/perfLogCouch.log /home/nikhil/AOS/Assignment4/serverlogs/2/2/

pssh -h host4.txt -t -1 -O StrictHostKeyChecking=no -o . 'sh ./DHTPerfTest.sh'
scp -i nikhilnere.pem ubuntu@54.172.158.138:/home/ubuntu/SourceCode/perfLog.log /home/nikhil/AOS/Assignment4/serverlogs/4/1/
scp -i nikhilnere.pem ubuntu@54.172.158.138:/home/ubuntu/SourceCode/perfLogMongo.log /home/nikhil/AOS/Assignment4/serverlogs/4/1/
scp -i nikhilnere.pem ubuntu@54.172.158.138:/home/ubuntu/SourceCode/perfLogRedis.log /home/nikhil/AOS/Assignment4/serverlogs/4/1/
scp -i nikhilnere.pem ubuntu@54.172.158.138:/home/ubuntu/SourceCode/perfLogCouch.log /home/nikhil/AOS/Assignment4/serverlogs/4/1/
scp -i nikhilnere.pem ubuntu@54.172.124.155:/home/ubuntu/SourceCode/perfLog.log /home/nikhil/AOS/Assignment4/serverlogs/4/2/
scp -i nikhilnere.pem ubuntu@54.172.124.155:/home/ubuntu/SourceCode/perfLogMongo.log /home/nikhil/AOS/Assignment4/serverlogs/4/2/
scp -i nikhilnere.pem ubuntu@54.172.124.155:/home/ubuntu/SourceCode/perfLogRedis.log /home/nikhil/AOS/Assignment4/serverlogs/4/2/
scp -i nikhilnere.pem ubuntu@54.172.124.155:/home/ubuntu/SourceCode/perfLogCouch.log /home/nikhil/AOS/Assignment4/serverlogs/4/2/
scp -i nikhilnere.pem ubuntu@54.208.117.194:/home/ubuntu/SourceCode/perfLog.log /home/nikhil/AOS/Assignment4/serverlogs/4/3/
scp -i nikhilnere.pem ubuntu@54.208.117.194:/home/ubuntu/SourceCode/perfLogMongo.log /home/nikhil/AOS/Assignment4/serverlogs/4/3/
scp -i nikhilnere.pem ubuntu@54.208.117.194:/home/ubuntu/SourceCode/perfLogRedis.log /home/nikhil/AOS/Assignment4/serverlogs/4/3/
scp -i nikhilnere.pem ubuntu@54.208.117.194:/home/ubuntu/SourceCode/perfLogCouch.log /home/nikhil/AOS/Assignment4/serverlogs/4/3/
scp -i nikhilnere.pem ubuntu@54.174.238.152:/home/ubuntu/SourceCode/perfLog.log /home/nikhil/AOS/Assignment4/serverlogs/4/4/
scp -i nikhilnere.pem ubuntu@54.174.238.152:/home/ubuntu/SourceCode/perfLogMongo.log /home/nikhil/AOS/Assignment4/serverlogs/4/4/
scp -i nikhilnere.pem ubuntu@54.174.238.152:/home/ubuntu/SourceCode/perfLogRedis.log /home/nikhil/AOS/Assignment4/serverlogs/4/4/
scp -i nikhilnere.pem ubuntu@54.174.238.152:/home/ubuntu/SourceCode/perfLogCouch.log /home/nikhil/AOS/Assignment4/serverlogs/4/4/

pssh -h host8.txt -t -1 -O StrictHostKeyChecking=no -o . 'sh ./DHTPerfTest.sh'
scp -i nikhilnere.pem ubuntu@54.172.158.138:/home/ubuntu/SourceCode/perfLog.log /home/nikhil/AOS/Assignment4/serverlogs/8/1/
scp -i nikhilnere.pem ubuntu@54.172.158.138:/home/ubuntu/SourceCode/perfLogMongo.log /home/nikhil/AOS/Assignment4/serverlogs/8/1/
scp -i nikhilnere.pem ubuntu@54.172.158.138:/home/ubuntu/SourceCode/perfLogRedis.log /home/nikhil/AOS/Assignment4/serverlogs/8/1/
scp -i nikhilnere.pem ubuntu@54.172.158.138:/home/ubuntu/SourceCode/perfLogCouch.log /home/nikhil/AOS/Assignment4/serverlogs/8/1/
scp -i nikhilnere.pem ubuntu@54.172.124.155:/home/ubuntu/SourceCode/perfLog.log /home/nikhil/AOS/Assignment4/serverlogs/8/2/
scp -i nikhilnere.pem ubuntu@54.172.124.155:/home/ubuntu/SourceCode/perfLogMongo.log /home/nikhil/AOS/Assignment4/serverlogs/8/2/
scp -i nikhilnere.pem ubuntu@54.172.124.155:/home/ubuntu/SourceCode/perfLogRedis.log /home/nikhil/AOS/Assignment4/serverlogs/8/2/
scp -i nikhilnere.pem ubuntu@54.172.124.155:/home/ubuntu/SourceCode/perfLogCouch.log /home/nikhil/AOS/Assignment4/serverlogs/8/2/
scp -i nikhilnere.pem ubuntu@54.208.117.194:/home/ubuntu/SourceCode/perfLog.log /home/nikhil/AOS/Assignment4/serverlogs/8/3/
scp -i nikhilnere.pem ubuntu@54.208.117.194:/home/ubuntu/SourceCode/perfLogMongo.log /home/nikhil/AOS/Assignment4/serverlogs/8/3/
scp -i nikhilnere.pem ubuntu@54.208.117.194:/home/ubuntu/SourceCode/perfLogRedis.log /home/nikhil/AOS/Assignment4/serverlogs/8/3/
scp -i nikhilnere.pem ubuntu@54.208.117.194:/home/ubuntu/SourceCode/perfLogCouch.log /home/nikhil/AOS/Assignment4/serverlogs/8/3/
scp -i nikhilnere.pem ubuntu@54.174.238.152:/home/ubuntu/SourceCode/perfLog.log /home/nikhil/AOS/Assignment4/serverlogs/8/4/
scp -i nikhilnere.pem ubuntu@54.174.238.152:/home/ubuntu/SourceCode/perfLogMongo.log /home/nikhil/AOS/Assignment4/serverlogs/8/4/
scp -i nikhilnere.pem ubuntu@54.174.238.152:/home/ubuntu/SourceCode/perfLogRedis.log /home/nikhil/AOS/Assignment4/serverlogs/8/4/
scp -i nikhilnere.pem ubuntu@54.174.238.152:/home/ubuntu/SourceCode/perfLogCouch.log /home/nikhil/AOS/Assignment4/serverlogs/8/4/
scp -i nikhilnere.pem ubuntu@54.175.242.243:/home/ubuntu/SourceCode/perfLog.log /home/nikhil/AOS/Assignment4/serverlogs/8/5/
scp -i nikhilnere.pem ubuntu@54.175.242.243:/home/ubuntu/SourceCode/perfLogMongo.log /home/nikhil/AOS/Assignment4/serverlogs/8/5/
scp -i nikhilnere.pem ubuntu@54.175.242.243:/home/ubuntu/SourceCode/perfLogRedis.log /home/nikhil/AOS/Assignment4/serverlogs/8/5/
scp -i nikhilnere.pem ubuntu@54.175.242.243:/home/ubuntu/SourceCode/perfLogCouch.log /home/nikhil/AOS/Assignment4/serverlogs/8/5/
scp -i nikhilnere.pem ubuntu@54.208.237.210:/home/ubuntu/SourceCode/perfLog.log /home/nikhil/AOS/Assignment4/serverlogs/8/6/
scp -i nikhilnere.pem ubuntu@54.208.237.210:/home/ubuntu/SourceCode/perfLogMongo.log /home/nikhil/AOS/Assignment4/serverlogs/8/6/
scp -i nikhilnere.pem ubuntu@54.208.237.210:/home/ubuntu/SourceCode/perfLogRedis.log /home/nikhil/AOS/Assignment4/serverlogs/8/6/
scp -i nikhilnere.pem ubuntu@54.208.237.210:/home/ubuntu/SourceCode/perfLogCouch.log /home/nikhil/AOS/Assignment4/serverlogs/8/6/
scp -i nikhilnere.pem ubuntu@54.175.250.171:/home/ubuntu/SourceCode/perfLog.log /home/nikhil/AOS/Assignment4/serverlogs/8/7/
scp -i nikhilnere.pem ubuntu@54.175.250.171:/home/ubuntu/SourceCode/perfLogMongo.log /home/nikhil/AOS/Assignment4/serverlogs/8/7/
scp -i nikhilnere.pem ubuntu@54.175.250.171:/home/ubuntu/SourceCode/perfLogRedis.log /home/nikhil/AOS/Assignment4/serverlogs/8/7/
scp -i nikhilnere.pem ubuntu@54.175.250.171:/home/ubuntu/SourceCode/perfLogCouch.log /home/nikhil/AOS/Assignment4/serverlogs/8/7/
scp -i nikhilnere.pem ubuntu@54.209.9.217:/home/ubuntu/SourceCode/perfLog.log /home/nikhil/AOS/Assignment4/serverlogs/8/8/
scp -i nikhilnere.pem ubuntu@54.209.9.217:/home/ubuntu/SourceCode/perfLogMongo.log /home/nikhil/AOS/Assignment4/serverlogs/8/8/
scp -i nikhilnere.pem ubuntu@54.209.9.217:/home/ubuntu/SourceCode/perfLogRedis.log /home/nikhil/AOS/Assignment4/serverlogs/8/8/
scp -i nikhilnere.pem ubuntu@54.209.9.217:/home/ubuntu/SourceCode/perfLogCouch.log /home/nikhil/AOS/Assignment4/serverlogs/8/8/


pssh -h host16.txt -t -1 -O StrictHostKeyChecking=no -o . 'sh ./DHTPerfTest.sh'
scp -i nikhilnere.pem ubuntu@54.172.158.138:/home/ubuntu/SourceCode/perfLog.log /home/nikhil/AOS/Assignment4/serverlogs/16/1/
scp -i nikhilnere.pem ubuntu@54.172.158.138:/home/ubuntu/SourceCode/perfLogMongo.log /home/nikhil/AOS/Assignment4/serverlogs/16/1/
scp -i nikhilnere.pem ubuntu@54.172.158.138:/home/ubuntu/SourceCode/perfLogRedis.log /home/nikhil/AOS/Assignment4/serverlogs/16/1/
scp -i nikhilnere.pem ubuntu@54.172.158.138:/home/ubuntu/SourceCode/perfLogCouch.log /home/nikhil/AOS/Assignment4/serverlogs/16/1/
scp -i nikhilnere.pem ubuntu@54.172.124.155:/home/ubuntu/SourceCode/perfLog.log /home/nikhil/AOS/Assignment4/serverlogs/16/2/
scp -i nikhilnere.pem ubuntu@54.172.124.155:/home/ubuntu/SourceCode/perfLogMongo.log /home/nikhil/AOS/Assignment4/serverlogs/16/2/
scp -i nikhilnere.pem ubuntu@54.172.124.155:/home/ubuntu/SourceCode/perfLogRedis.log /home/nikhil/AOS/Assignment4/serverlogs/16/2/
scp -i nikhilnere.pem ubuntu@54.172.124.155:/home/ubuntu/SourceCode/perfLogCouch.log /home/nikhil/AOS/Assignment4/serverlogs/16/2/
scp -i nikhilnere.pem ubuntu@54.208.117.194:/home/ubuntu/SourceCode/perfLog.log /home/nikhil/AOS/Assignment4/serverlogs/16/3/
scp -i nikhilnere.pem ubuntu@54.208.117.194:/home/ubuntu/SourceCode/perfLogMongo.log /home/nikhil/AOS/Assignment4/serverlogs/16/3/
scp -i nikhilnere.pem ubuntu@54.208.117.194:/home/ubuntu/SourceCode/perfLogRedis.log /home/nikhil/AOS/Assignment4/serverlogs/16/3/
scp -i nikhilnere.pem ubuntu@54.208.117.194:/home/ubuntu/SourceCode/perfLogCouch.log /home/nikhil/AOS/Assignment4/serverlogs/16/3/
scp -i nikhilnere.pem ubuntu@54.174.238.152:/home/ubuntu/SourceCode/perfLog.log /home/nikhil/AOS/Assignment4/serverlogs/16/4/
scp -i nikhilnere.pem ubuntu@54.174.238.152:/home/ubuntu/SourceCode/perfLogMongo.log /home/nikhil/AOS/Assignment4/serverlogs/16/4/
scp -i nikhilnere.pem ubuntu@54.174.238.152:/home/ubuntu/SourceCode/perfLogRedis.log /home/nikhil/AOS/Assignment4/serverlogs/16/4/
scp -i nikhilnere.pem ubuntu@54.174.238.152:/home/ubuntu/SourceCode/perfLogCouch.log /home/nikhil/AOS/Assignment4/serverlogs/16/4/
scp -i nikhilnere.pem ubuntu@54.175.242.243:/home/ubuntu/SourceCode/perfLog.log /home/nikhil/AOS/Assignment4/serverlogs/16/5/
scp -i nikhilnere.pem ubuntu@54.175.242.243:/home/ubuntu/SourceCode/perfLogMongo.log /home/nikhil/AOS/Assignment4/serverlogs/16/5/
scp -i nikhilnere.pem ubuntu@54.175.242.243:/home/ubuntu/SourceCode/perfLogRedis.log /home/nikhil/AOS/Assignment4/serverlogs/16/5/
scp -i nikhilnere.pem ubuntu@54.175.242.243:/home/ubuntu/SourceCode/perfLogCouch.log /home/nikhil/AOS/Assignment4/serverlogs/16/5/
scp -i nikhilnere.pem ubuntu@54.208.237.210:/home/ubuntu/SourceCode/perfLog.log /home/nikhil/AOS/Assignment4/serverlogs/16/6/
scp -i nikhilnere.pem ubuntu@54.208.237.210:/home/ubuntu/SourceCode/perfLogMongo.log /home/nikhil/AOS/Assignment4/serverlogs/16/6/
scp -i nikhilnere.pem ubuntu@54.208.237.210:/home/ubuntu/SourceCode/perfLogRedis.log /home/nikhil/AOS/Assignment4/serverlogs/16/6/
scp -i nikhilnere.pem ubuntu@54.208.237.210:/home/ubuntu/SourceCode/perfLogCouch.log /home/nikhil/AOS/Assignment4/serverlogs/16/6/
scp -i nikhilnere.pem ubuntu@54.175.250.171:/home/ubuntu/SourceCode/perfLog.log /home/nikhil/AOS/Assignment4/serverlogs/16/7/
scp -i nikhilnere.pem ubuntu@54.175.250.171:/home/ubuntu/SourceCode/perfLogMongo.log /home/nikhil/AOS/Assignment4/serverlogs/16/7/
scp -i nikhilnere.pem ubuntu@54.175.250.171:/home/ubuntu/SourceCode/perfLogRedis.log /home/nikhil/AOS/Assignment4/serverlogs/16/7/
scp -i nikhilnere.pem ubuntu@54.175.250.171:/home/ubuntu/SourceCode/perfLogCouch.log /home/nikhil/AOS/Assignment4/serverlogs/16/7/
scp -i nikhilnere.pem ubuntu@54.209.9.217:/home/ubuntu/SourceCode/perfLog.log /home/nikhil/AOS/Assignment4/serverlogs/16/8/
scp -i nikhilnere.pem ubuntu@54.209.9.217:/home/ubuntu/SourceCode/perfLogMongo.log /home/nikhil/AOS/Assignment4/serverlogs/16/8/
scp -i nikhilnere.pem ubuntu@54.209.9.217:/home/ubuntu/SourceCode/perfLogRedis.log /home/nikhil/AOS/Assignment4/serverlogs/16/8/
scp -i nikhilnere.pem ubuntu@54.209.9.217:/home/ubuntu/SourceCode/perfLogCouch.log /home/nikhil/AOS/Assignment4/serverlogs/16/8/
scp -i nikhilnere.pem ubuntu@54.208.24.253:/home/ubuntu/SourceCode/perfLog.log /home/nikhil/AOS/Assignment4/serverlogs/16/9/
scp -i nikhilnere.pem ubuntu@54.208.24.253:/home/ubuntu/SourceCode/perfLogMongo.log /home/nikhil/AOS/Assignment4/serverlogs/16/9/
scp -i nikhilnere.pem ubuntu@54.208.24.253:/home/ubuntu/SourceCode/perfLogRedis.log /home/nikhil/AOS/Assignment4/serverlogs/16/9/
scp -i nikhilnere.pem ubuntu@54.208.24.253:/home/ubuntu/SourceCode/perfLogCouch.log /home/nikhil/AOS/Assignment4/serverlogs/16/9/
scp -i nikhilnere.pem ubuntu@54.208.1.95:/home/ubuntu/SourceCode/perfLog.log /home/nikhil/AOS/Assignment4/serverlogs/16/10/
scp -i nikhilnere.pem ubuntu@54.208.1.95:/home/ubuntu/SourceCode/perfLogMongo.log /home/nikhil/AOS/Assignment4/serverlogs/16/10/
scp -i nikhilnere.pem ubuntu@54.208.1.95:/home/ubuntu/SourceCode/perfLogRedis.log /home/nikhil/AOS/Assignment4/serverlogs/16/10/
scp -i nikhilnere.pem ubuntu@54.208.1.95:/home/ubuntu/SourceCode/perfLogCouch.log /home/nikhil/AOS/Assignment4/serverlogs/16/10/
scp -i nikhilnere.pem ubuntu@54.208.2.183:/home/ubuntu/SourceCode/perfLog.log /home/nikhil/AOS/Assignment4/serverlogs/16/11/
scp -i nikhilnere.pem ubuntu@54.208.2.183:/home/ubuntu/SourceCode/perfLogMongo.log /home/nikhil/AOS/Assignment4/serverlogs/16/11/
scp -i nikhilnere.pem ubuntu@54.208.2.183:/home/ubuntu/SourceCode/perfLogRedis.log /home/nikhil/AOS/Assignment4/serverlogs/16/11/
scp -i nikhilnere.pem ubuntu@54.208.2.183:/home/ubuntu/SourceCode/perfLogCouch.log /home/nikhil/AOS/Assignment4/serverlogs/16/11/
scp -i nikhilnere.pem ubuntu@54.152.190.82:/home/ubuntu/SourceCode/perfLog.log /home/nikhil/AOS/Assignment4/serverlogs/16/12/
scp -i nikhilnere.pem ubuntu@54.152.190.82:/home/ubuntu/SourceCode/perfLogMongo.log /home/nikhil/AOS/Assignment4/serverlogs/16/12/
scp -i nikhilnere.pem ubuntu@54.152.190.82:/home/ubuntu/SourceCode/perfLogRedis.log /home/nikhil/AOS/Assignment4/serverlogs/16/12/
scp -i nikhilnere.pem ubuntu@54.152.190.82:/home/ubuntu/SourceCode/perfLogCouch.log /home/nikhil/AOS/Assignment4/serverlogs/16/12/
scp -i nikhilnere.pem ubuntu@54.85.56.252:/home/ubuntu/SourceCode/perfLog.log /home/nikhil/AOS/Assignment4/serverlogs/16/13/
scp -i nikhilnere.pem ubuntu@54.85.56.252:/home/ubuntu/SourceCode/perfLogMongo.log /home/nikhil/AOS/Assignment4/serverlogs/16/13/
scp -i nikhilnere.pem ubuntu@54.85.56.252:/home/ubuntu/SourceCode/perfLogRedis.log /home/nikhil/AOS/Assignment4/serverlogs/16/13/
scp -i nikhilnere.pem ubuntu@54.85.56.252:/home/ubuntu/SourceCode/perfLogCouch.log /home/nikhil/AOS/Assignment4/serverlogs/16/13/
scp -i nikhilnere.pem ubuntu@54.209.27.2:/home/ubuntu/SourceCode/perfLog.log /home/nikhil/AOS/Assignment4/serverlogs/16/14/
scp -i nikhilnere.pem ubuntu@54.209.27.2:/home/ubuntu/SourceCode/perfLogMongo.log /home/nikhil/AOS/Assignment4/serverlogs/16/14/
scp -i nikhilnere.pem ubuntu@54.209.27.2:/home/ubuntu/SourceCode/perfLogRedis.log /home/nikhil/AOS/Assignment4/serverlogs/16/14/
scp -i nikhilnere.pem ubuntu@54.209.27.2:/home/ubuntu/SourceCode/perfLogCouch.log /home/nikhil/AOS/Assignment4/serverlogs/16/14/
scp -i nikhilnere.pem ubuntu@54.208.228.119:/home/ubuntu/SourceCode/perfLog.log /home/nikhil/AOS/Assignment4/serverlogs/16/15/
scp -i nikhilnere.pem ubuntu@54.208.228.119:/home/ubuntu/SourceCode/perfLogMongo.log /home/nikhil/AOS/Assignment4/serverlogs/16/15/
scp -i nikhilnere.pem ubuntu@54.208.228.119:/home/ubuntu/SourceCode/perfLogRedis.log /home/nikhil/AOS/Assignment4/serverlogs/16/15/
scp -i nikhilnere.pem ubuntu@54.208.228.119:/home/ubuntu/SourceCode/perfLogCouch.log /home/nikhil/AOS/Assignment4/serverlogs/16/15/
scp -i nikhilnere.pem ubuntu@54.172.252.144:/home/ubuntu/SourceCode/perfLog.log /home/nikhil/AOS/Assignment4/serverlogs/16/16/
scp -i nikhilnere.pem ubuntu@54.172.252.144:/home/ubuntu/SourceCode/perfLogMongo.log /home/nikhil/AOS/Assignment4/serverlogs/16/16/
scp -i nikhilnere.pem ubuntu@54.172.252.144:/home/ubuntu/SourceCode/perfLogRedis.log /home/nikhil/AOS/Assignment4/serverlogs/16/16/
scp -i nikhilnere.pem ubuntu@54.172.252.144:/home/ubuntu/SourceCode/perfLogCouch.log /home/nikhil/AOS/Assignment4/serverlogs/16/16/

exit 0
