#!/bin/bash

#成功的标识
success="succeeded"

ip_list=("127.0.0.1")
port_list=(80 81 8080 8888) 


for i in ${ip_list[@]} 
do 
  for j in ${port_list[@]}
  do
     #获得系统当前时间 
     time=`date +%Y-%m-%d.%H:%M:%S`

     #将错误输出到临时目录,并且错误不中断
     nc -z -v -n $i $j > ./tmp 2>&1
     result=`cat ./tmp`

     if [[ $result =~ $success  ]]
     then
       #echo "$time 连接$i:$j成功"
       continue
     else 
       echo  "$time 连接$i:$j失败" >> ./port_monitor_error.log
     fi
   done
done
