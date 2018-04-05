#!/usr/bin/env python
# -*- coding: utf-8 -*-
# @Time    : 2017/12/13 下午4:52
# @Author  : YRee
# @File    : generate.py
# @Software: PyCharm
# @function: 接收数据库配置

import MySQLdb
import format_utils
import file_util
import sys

reload(sys)
sys.setdefaultencoding('utf-8')
# conn = MySQLdb.connect(
#     host='127.0.0.1',
#     port=3306,
#     user='root',
#     passwd='root',
#     db='test',
#     charset="utf8"
# )

# conn = MySQLdb.connect(
#     host='172.16.117.219',
#     port=3306,
#     user='crm',
#     passwd='crm',
#     db='clearing',
#     charset="utf8"
# )

conn = MySQLdb.connect(
    host='172.16.116.81',
    port=3306,
    user='finance_all',
    passwd='Finance@Income0901',
    db='finance-income',
    charset="utf8"
)

cur = conn.cursor()

#全表查
# tableNames = cur.execute("show tables")
# tableList = cur.fetchmany(tableNames)

tableList = {'details_info'}

for tableName in tableList:
    print tableName
    querySql = "SELECT column_name,data_type,column_comment FROM information_schema.columns WHERE table_name= '" + \
               tableName[0] + "'"
    cur.execute(querySql)
    fileds = cur.fetchall()

    fileds_context = ''

    # print fileds
    pkttpe = fileds[0][1]
    for filed in fileds:
        fileds_context = fileds_context + format_utils.create_filed(filed[0], filed[1], filed[2])
    model_cls_name = format_utils.convert(tableName[0], '_')
    model_cls_name = format_utils.upper_first(model_cls_name)
    model_class_context = format_utils.create_model_class(model_cls_name, fileds_context)
    file_util.print_file(model_cls_name + '.java', model_class_context, "model")

    pkttpe = format_utils.convert_type(pkttpe)
    jpa_class_name = model_cls_name + "Repository"
    mjpa_class_context = format_utils.create_jpa_class(model_cls_name, pkttpe)
    file_util.print_file(jpa_class_name + '.java', mjpa_class_context, "jpa")
    # print class_context

cur.close()
conn.commit()
conn.close()

