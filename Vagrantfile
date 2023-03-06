# -*- mode: ruby -*-
# vi: set ft=ruby :

VAGRANT_API_VERSION = "2"
VAGRANT_VIRTUAL_BOX = "ubuntu/bionic64"

$apt_update = <<APT_UPDATE_ENDSCRIPT
sudo apt-get update --yes
sudo apt-get upgrade --yes
APT_UPDATE_ENDSCRIPT

$install_java_17 = <<INSTALL_JAVA_17_ENDSCRIPT
sudo apt-get install ca-certificates-java --yes
sudo apt-get install openjdk-17-jdk --yes
INSTALL_JAVA_17_ENDSCRIPT

$install_docker = <<INSTALL_DOCKER_ENDSCRIPT
sudo apt-get install ca-certificates curl gnupg lsb-release --yes
sudo mkdir -m 0755 -p /etc/apt/keyrings
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /etc/apt/keyrings/docker.gpg
echo "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.gpg] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
sudo apt-get update --yes
sudo apt-get install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin docker-compose --yes
sudo apt-get install gnupg2 pass --yes
sudo usermod -a -G docker vagrant
INSTALL_DOCKER_ENDSCRIPT

Vagrant.configure(VAGRANT_API_VERSION) do |config|
  config.vm.box = VAGRANT_VIRTUAL_BOX
  config.vm.provider "virtualbox" do |vbox|
    vbox.memory = 4096
  end
  config.vm.provision "shell", inline: $apt_update
  config.vm.provision "shell", inline: $install_java_17
  config.vm.provision "shell", inline: $install_docker
  config.vm.network "forwarded_port", guest: 8080, host:8080
  config.vm.network "forwarded_port", guest: 8081, host:8081
  config.vm.network "forwarded_port", guest: 8082, host:8082
end
