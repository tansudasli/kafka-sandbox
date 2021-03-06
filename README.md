# kafka-sandbox

Apache Kafka sandbox

- /standalone : Installation scripts for Kafka on GCP
- /kafka-basics : Java app. for producing, consuming w/ kafka-client APIs


## How to Start

check gcloud accounts status on local `gcloud auth list`, then activate right ACCOUNT_NAME if necessary `gcloud config set account ACCOUNT_NAME`.

1. Run `./create-instance-on-GCP.sh` to create a GCP instance on your local. Change some variables!
2. **ssh to instance** and `git clone https://github.com/tansudasli/kafka-sandbox.git`
3. Install kafka w/ one of the **kafka-sandbox/install-kafka-as-standalone.sh** files
4. Format data-disk and mount it. check for <https://cloud.google.com/compute/docs/disks/add-persistent-disk#create_disk>
   - `lsblk`
   - `sudo mkfs.ext4 -m 0 -F -E lazy_itable_init=0,lazy_journal_init=0,discard /dev/sdb`
   - `sudo mount -o discard,defaults /dev/sdb $HOME/kafka_2.11-2.2.1/data`
   - `sudo chown $USER:$USER ~/kafka_2.11-2.2.1/data`
   - `sudo cp /etc/fstab /etc/fstab.backup`
   - `echo UUID=$(sudo blkid -s UUID -o value /dev/sdb) $HOME/kafka_2.11-2.2.1/data ext4 discard,defaults,nofail 0 2 | sudo tee -a /etc/fstab`
5. Configure data folders
   - open config/zookeeper.properties file and set `dataDir=$HOME/kafka_2.11-2.2.1/data/zookeeper`
   - open config/server.properties file and set `log.dirs=$HOME/kafka_2.11-2.2.1/data/kafka`
   - `mkdir $HOME/kafka_2.11-2.2.1/data/zookeeper`
   - `mkdir $HOME/kafka_2.11-2.2.1/data/kafka`
6. to test run, `kafka-topics.sh`
7. to start zookeeper, `zookeeper-server-start.sh kafka_2.11-2.2.1/config/zookeeper.properties &`
8. to start kafka, `kafka-server-start.sh kafka_2.11-2.2.1/config/server.properties &`

