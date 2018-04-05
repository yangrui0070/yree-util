#!/usr/bin/env python
# -*- coding: utf-8 -*-
# @Time    : 2017/12/13 下午9:27
# @Author  : YRee
# @File    : file_util.py
# @Software: PyCharm
# @function: 文件读写io操作


def print_file(file_name, context, type):
    fname = 'src/' + type + "/" + file_name
    f = open(fname, 'w')
    f.write(context)
    f.close()


# print_file("a.txt", "modelcon", "model")