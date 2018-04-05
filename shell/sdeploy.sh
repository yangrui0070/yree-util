#!/bin/bash
set -e

#没有参数默认退出
if test $# = '0'
then
    echo "您未选择要执行的bro模块..."
    exit
fi

if  [[ ${1} == '-h' ]] || [[ ${1} == '-help' ]]
then
   echo "无参数+模块名           默认打包主分支"
   echo "-b+分支名+模块名        打包选择的远程分支的模块"
   echo "-h/-help                帮助"
   exit
fi


#项目路径
bro_path="/home/test/YRee/bro"
wep_path="/data/jetty/webapps"
#备份路径
bk_path="/data/bro_bk"
#git远程分支(默认主分支)
git_branch="remotes/origin/"

cd $bro_path

if test ${1} = '-b'
 then
   echo "您选择了${2}分支"
   git_branch=${git_branch}${2}
 else 
   echo "您未选择分支，默认选取master分支" 
   git_branch="${git_branch}master"
fi

echo "将要拉取${git_branch}的代码"

git fetch --all
git reset --hard ${git_branch}

#获取所有的模块名
modules=$(ls) 

modules_str=''
tag_int=0
#验证输入的参数是否合法
for i in $@
do  
  if  [[ $1 == '-b'  ]] && [[ ${tag_int} -eq 0 ]] || [[ ${tag_int} -eq 1 ]]
    then 
        tag_int=$[tag_int+1]
        continue   
  fi
 
  #如果子模块中包含
  if echo "${modules[@]}" | grep -w $i  &>/dev/null;
    then
      echo "您选择了打包bro下的${i}模块..."
      #拼接模块为字符串
      modules_str=${modules_str}','${i}
    else 
      echo "您所输入的模块名: ${i} 不对，请检查后再重新使用该命令"
      exit
  fi
done
modules_str=${modules_str#*,}

echo ${modules_str}
if [[ ${modules_str} == '' ]]
 then 
    echo "您未选择要打包的模块"
    exit
fi


mvn clean package -pl $modules_str  -am -DskipTests=true

tag_int=0;
#将war包复制到webapp下
for i in $@
do
   if  [[ $1 == '-b'  ]] && [[ ${tag_int} -eq 0 ]] || [[ ${tag_int} -eq 1 ]]
     then
         tag_int=$[tag_int+1]
         continue
   fi

  cd ${bro_path}
  cd  ./${i}/target
  war_name=` find . -name $i*.war|head -n 1`
  #备份
  CURTIME=$(date +%Y%m%d%H%M%S)
  bk_name=${war_name%.war}_${CURTIME}
  bk_name=${bk_name#*/}.war
  echo "正在备份到: ${bk_path}/${bk_name}" 
  cp ${war_name} ${bk_path}/$bk_name
 
  echo "正在部署${i}.war"
  cp -f ${war_name} ${wep_path}/$i.war
done


#service jetty restart 
sleep 5s
service jetty stop
sleep 5s
service jetty stop 
sleep 5s
service jetty start
#sleep 
#sleep 
