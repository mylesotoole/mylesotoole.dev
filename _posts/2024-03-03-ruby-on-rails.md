---
title: "Installing Ruby on Rails"
categories:
  - Linux
tags:
 - Ubuntu
---

## Installing RVM:

1. [Add Repo](https://github.com/rvm/ubuntu_rvm/blob/531e67a9903a593d2c9c1f3cb9b6ee4ec332fad2/README.md):
``` 
sudo apt-add-repository -y ppa:rael-gc/rvm
```

2. [Add Key Import Utility](https://rvm.io/):
```
sudo apt install gnupg2
```

3. [Add GPG Keys](https://rvm.io/):
```
gpg2 --keyserver keyserver.ubuntu.com --recv-keys 409B6B1796C275462A1703113804BB82D39DC0E3 7D2BAF1CF37B13E2069D6956105BD0E739499BDB
```

4. Install RVM:
```
sudo apt-get install rvm -y
```

5. Add your user to `rvm` group (`$USER` will automatically insert your username):
```term
sudo usermod -a -G rvm $USER
```    

6. Now, in order to always load rvm, change the Gnome Terminal to always perform a login.

- At terminal window, open the application menu, then `Preferences`. Click on `Unnamed` profile, select the `Command` tab and check `Run command as login shell`.

![Terminal Screenshot](/assets/images/TERMINAL.png)

7. Open a new terminal window, then fix permissions and reboot:
```
rvmsudo rvm cleanup all && rvm fix-permissions && sudo reboot
```

## [Installing Ruby](https://www.railstutorial.org/book)

9. Configure RVM, Download, and Set Default Ruby:
``` 
rvm get stable
```

```
rvm install 3.1.2
```

```
rvm --default use 3.1.2
```

10. Check Version:
```
ruby -v
```

## Installing Rails

11. Configure the __.gemrc__ file to skip the installation of Ruby documentation
```
echo "gem: --no-document" >> ~/.gemrc
```

12. Install Rails with a specific version number:
```
gem install rails -v 7.0.4
```

13. Check Version:
```
rails -v
```

## Install Bundler:

14.  Installing Bundler with a specific version number: 
```
gem install bundler -v 2.3.14
```