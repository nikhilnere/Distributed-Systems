clear
echo "starting MongoDB"
sudo service mongodb start

echo "starting Redis "
sudo service redis-server start

echo "starting Cassandra"
sudo /etc/init.d/cassandra start

echo "starting CouchDB"
sudo service couchdb start

echo "starting myDHT"
cd /home/ubuntu/SourceCode
ant run

exit 0
