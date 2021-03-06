#  install kafka as standalone 

sudo apt update
sudo apt install openjdk-8-jdk -y

wget http://ftp.itu.edu.tr/Mirror/Apache/kafka/2.2.1/kafka_2.11-2.2.1.tgz

tar -xvf kafka_2.11-2.2.1.tgz

echo 'PATH="$PATH:$HOME/kafka_2.11-2.2.1/bin"' >> ~/.profile
echo 'PATH="$PATH:$HOME/kafka_2.11-2.2.1/bin"' >> ~/.bash_profile
source  ~/.profile
source  ~/.bash_profile

mkdir ~/kafka_2.11-2.2.1/data




echo "format attached disk and mount as data folder"
echo "look for details: https://cloud.google.com/compute/docs/disks/add-persistent-disk#create_disk"

echo "--------run below commands ----------"
echo "lsblk"
echo "sudo mkfs.ext4 -m 0 -F -E lazy_itable_init=0,lazy_journal_init=0,discard /dev/sdb"
echo "sudo mount -o discard,defaults /dev/sdb $HOME/kafka_2.11-2.2.1/data"
echo "sudo chown $USER:$USER ~/kafka_2.11-2.2.1/data"
echo "sudo cp /etc/fstab /etc/fstab.backup"
echo "echo UUID=`sudo blkid -s UUID -o value /dev/sdb` $HOME/kafka_2.11-2.2.1/data ext4 discard,defaults,nofail 0 2 | sudo tee -a /etc/fstab"

echo "open config/zookeeper.properties file and set dataDir=$HOME/kafka_2.11-2.2.1/data/zookeeper"
echo "open config/server.properties file and set log.dirs=$HOME/kafka_2.11-2.2.1/data/kafka"

echo "mkdir $HOME/kafka_2.11-2.2.1/data/zookeeper"
echo "mkdir $HOME/kafka_2.11-2.2.1/data/kafka"

echo "to test run, $HOME/kafka_2.11-2.2.1/bin/kafka-topics.sh"