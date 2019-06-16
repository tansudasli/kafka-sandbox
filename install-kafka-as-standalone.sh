#  install kafka as standalone 

wget https://www.apache.org/dyn/closer.cgi?path=/kafka/2.2.1/kafka_2.11-2.2.1.tgz

tar -xvf kafka_2.11-2.2.1.tgz
cd kafka_2.11-2.2.1

sudo apt install openjdk-8-jdk -y

echo 'PATH="$PATH:/your/path/to/your/kafka/bin"' >> ~/.profile
source  ~/.profile

echo "to test run, kafka_2.11-2.2.1/bin/kafka-topics.sh"


