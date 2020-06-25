sudo sysctl -w vm.max_map_count=262144

sudo docker run -p 5601:5601 -p 9200:9200 -p 5044:5044 -p 5120:5120 -it -v /vagrant/sharedFolder/10-syslog.conf:/etc/logstash/conf.d/10-syslog.conf -v /vagrant/sharedFolder/02-beats-input.conf:/etc/logstash/conf.d/02-beats-input.conf --name elk sebp/elk