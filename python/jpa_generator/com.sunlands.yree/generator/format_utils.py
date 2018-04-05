#!/usr/bin/env python
# -*- coding: utf-8 -*-
# @Time    : 2017/12/13 下午5:52
# @Author  : YRee
# @File    : format_utils.py
# @Software: PyCharm
# @function: 生成java类
import time


def create_model_class(class_name, context):
    now = time.strftime('%Y-%m-%d %H:%M:%S', time.localtime(time.time()))
    context = 'package abc; \n ' \
              '\n' \
              'import lombok.Data; \n\n' \
              '/** \n' \
              '* @author: YRee\n'\
              '* @datatime: ' + now + '\n'\
              '* @function: \n'\
              '*/\n' \
              '@Data \n'\
              'public class ' +class_name +'{\n' \
              '  \n' \
              + context + '\n' \
              '}\n'

    return context


# 要类名和主键类型
def create_jpa_class(class_name,pk_type):
    now = time.strftime('%Y-%m-%d %H:%M:%S', time.localtime(time.time()))
    context_class = "public interface %sRepository extends CrudRepository<%s,%s>{" % (class_name, class_name, pk_type)
    context = 'package abc; \n ' \
              '\n' \
              'import org.springframework.data.repository.CrudRepository; \n'\
              'import org.springframework.stereotype.Repository;\n\n' \
              '/** \n' \
              '* @author: YRee\n'\
              '* @datatime: ' + now + '\n'\
              '* @function: \n'\
              '*/\n' \
              '@Repository \n' + \
              context_class +\
              '  \n' \
              '}\n'

    return context


# 将数据库的那种下划线的变量名，转换为下划线的变量名
def convert(one_string, space_character):  # one_string:输入的字符串；space_character:字符串的间隔符，以其做为分隔标志

    string_list = str(one_string).split(space_character)  # 将字符串转化为list
    first = string_list[0].lower()
    others = string_list[1:]

    others_capital = [word.capitalize() for word in others]  # str.capitalize():将字符串的首字母转化为大写

    others_capital[0:0] = [first]

    hump_string = ''.join(others_capital)  # 将list组合成为字符串，中间无连接符。

    return hump_string


# 类型转换
def convert_type(type):
    switcher = {
        'varchar': 'String',
        'datetime': 'Date',
        'int': 'Integer',
        'bigint': 'Long',
        'tinyint': 'Boolean',
        'decimal': 'Double',
    }
    return switcher.get(type)


def create_filed(filedName, filedType, filedComment):
    context = "   /**\n" \
              "    *\n" \
              "    * " + \
              filedComment + \
              "\n" \
              "    *\n" \
              "    */\n" \
              "    private " + convert_type(filedType) + ' ' + convert(filedName, '_') + ';\n\n'

    return context


def upper_first(word):
    result = ''
    for i in range(len(word)):
        if i == 0:
            result += word[0].upper()
        elif word[i - 1] == ' ':
            result += word[i].upper()
        else:
            result += word[i]
    return result


# create_class("asdasd")
# create_filed("student_name", "varchar", "用户名")

# print create_jpa_class('DepositReceived', 'Long')
# 'class ' + class_name + 'Repository extends CrudRepository<{\n'
