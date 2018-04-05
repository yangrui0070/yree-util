#!/bin/bash

gitdirname=$1

cd /home/git/repositories

pathdirname=/home/git/repositories/$gitdirname

#[ -d $pathdirname ] && rm -fr $pathdirname
[ -d $pathdirname ] && { echo "$pathdirname 已经存在,请先确认后手动删除才能新建.";exit 1; }

mkdir -p $pathdirname
cd $pathdirname

git init --bare

chown git:git -R $pathdirname

echo "git@172.16.10.183:/home/git/repositories/$gitdirname"