clear
echo "Installing Java"
sudo apt-get update
sudo apt-get install openjdk-7-jdk
sudo apt-get install -y mongodb-org-server mongodb-org-shell mongodb-org-tools
sudo rm /var/lib/dpkg/lock

echo "Installing MongoDb"
sudo apt-get install mongodb
sudo service mongod start
sudo apt-get install mongodb-clients

echo "Installing Ant"
sudo apt-get install ant

echo "Installing radis"
sudo apt-get install -y python-software-properties
sudo add-apt-repository -y ppa:rwky/redis
sudo apt-get update
sudo apt-get install -y redis-server
sudo service redis-server start

echo "Installing Cassandra"
echo "deb http://www.apache.org/dist/cassandra/debian 22x main" | sudo tee -a /etc/apt/sources.list.d/cassandra.sources.list
gpg --keyserver pgp.mit.edu --recv-keys F758CE318D77295D
gpg --export --armor F758CE318D77295D | sudo apt-key add -
gpg --keyserver pgp.mit.edu --recv-keys 2B5C1B00
gpg --export --armor 2B5C1B00 | sudo apt-key add -
gpg --keyserver pgp.mit.edu --recv-keys 0353B12C
gpg --export --armor 0353B12C | sudo apt-key add -
sudo apt-get update
sudo apt-get install cassandra
sudo /etc/init.d/cassandra start

echo "Installing CouchDB"
sudo apt-get install couchdb
sudo service couchdb start

exit 0
