#  install kafka as standalone 

sudo apt update
sudo apt install openjdk-8-jdk -y

wget http://ftp.itu.edu.tr/Mirror/Apache/kafka/2.2.1/kafka_2.11-2.2.1.tgz

tar -xvf kafka_2.11-2.2.1.tgz
cd kafka_2.11-2.2.1

echo 'PATH="$PATH:/your/path/to/your/kafka/bin"' >> ~/.profile
source  ~/.profile

echo "to test run, kafka_2.11-2.2.1/bin/kafka-topics.sh"


