from bs4 import BeautifulSoup
import os
import csv
import json
import math

if __name__ == '__main__':
    # 更改工作目录
    question_files = []
    os.chdir('F:\\电影数据\\html_0')
    # print(os.listdir())
    directory = os.listdir()
    text_final = []
    # 对每个页面进行处理
    header = ['title', 'Studio', 'Actors', 'ASIN', 'Number of discs', 'Producers', 'Is Discontinued By Manufacturer',
              'Product Dimensions', 'Media Format', 'Subtitles:', 'Item model number', 'Director', 'Release date',
              'Language', 'Run time', 'MPAA rating', 'Aspect Ratio', 'Language:', 'Date First Available',
              'Package Dimensions','Writers','Dubbed:','Manufacturer','Item Weight','Original Release Date', 'Label']  # 字段名
    for file in directory:
        # print(file)
        # 打开html文件进行操作
        with open(file, 'r', encoding='utf-8') as f:
            Soup = BeautifulSoup(f.read(), 'html.parser')
            select = "#detailBullets_feature_div"
            texts = Soup.select(select)
            try:
                text = texts[0].text.splitlines()
                # print(text)
                # 一个text可能的格式为:
                # ['', '', '', '', 'Product details', '', '', '', '', '', 'Aspect Ratio', ':', '', '1.33:1, 1.85:1', '', '', 'Is Discontinued By Manufacturer', ':', '', 'No', '', '', 'MPAA rating', ':', '', 's_medPG13 PG-13 (Parents Strongly Cautioned)', '', '', 'Product Dimensions', ':', '', '7.5 x 5.5 x 0.53 inches; 4 Ounces', '', '', 'Item model number', ':', '', '2226213', '', '', 'Director', ':', '', 'David Raynr', '', '', 'Media Format', ':', '', 'Closed-captioned, Color, Dolby, Full Screen, NTSC, Special Edition, Subtitled', '', '', 'Run time', ':', '', '1 hour and 34 minutes', '', '', 'Release date', ':', '', 'August 1, 2000', '', '', 'Actors', ':', '', 'Shane West, James Franco, Marla Sokoloff', '', '', 'Subtitles:', ':', '', 'English', '', '', 'Producers', ':', '', 'Paul Schiff', '', '', 'Language', ':', '', 'Unqualified', '', '', 'Studio', ':', '', 'Sony Pictures Home Entertainment', '', '', 'ASIN', ':', '', 'B00003CXGJ', '', '', 'Number of discs', ':', '', '1', '', '', '', '', '', '', 'Best Sellers Rank:', '', '#76,097 in Movies & TV (See Top 100 in Movies & TV)', '', ' #5,087 in Romance (Movies & TV)', ' #10,283 in Comedy (Movies & TV)', ' #16,980 in Drama DVDs', '', '', '', '', '', '', 'Customer Reviews:', '', '', '', '', '', '', '', '4.3 out of 5 stars', '', '', '', '', '', '', '', '', '253 ratings', '', '', '', '', '', '', '', '', '', '', '', '']
                print(file[0:4])
                while '' in text:
                    text.remove('')
                while ':' in text:
                    text.remove(':')
                while 'Product details' in text:
                    text.remove('Product details')
                # print(text)
                # print(len(text))
                index = 0
                while index < len(text):
                    if (text[index] == "Customer Reviews:"):
                        break
                    index = index + 1
                if index != len(text):
                    len_text = len(text)
                    for i in range(len_text - 1, index - 1, -1):
                        del text[i]

                index = 0
                while index < len(text):
                    if (text[index] == "Best Sellers Rank:"):
                        break
                    index = index + 1
                if index != len(text):
                    len_text = len(text)
                    for i in range(len_text - 1, index - 1, -1):
                        del text[i]
                # ['Aspect Ratio', 'Is Discontinued By Manufacturer', 'No', 'MPAA rating',
                #  's_medPG13 PG-13 (Parents Strongly Cautioned)', 'Product Dimensions',
                #  '7.5 x 5.5 x 0.53 inches; 4 Ounces', 'Item model number', '2226213', 'Director', 'David Raynr',
                #  'Media Format', 'Closed-captioned, Color, Dolby, Full Screen, NTSC, Special Edition, Subtitled',
                #  'Run time', '1 hour and 34 minutes', 'Release date', 'August 1, 2000', 'Actors',
                #  'Shane West, James Franco, Marla Sokoloff', 'Subtitles:', 'English', 'Producers', 'Paul Schiff',
                #  'Language', 'Unqualified', 'Studio', 'Sony Pictures Home Entertainment', 'ASIN', 'B00003CXGJ',
                #  'Number of discs', '1', 'Best Sellers Rank:', '#76,097 in Movies & TV (See Top 100 in Movies & TV)',
                #  ' #5,087 in Romance (Movies & TV)', ' #10,283 in Comedy (Movies & TV)', ' #16,980 in Drama DVDs',
                #  'Customer Reviews:', '4.3 out of 5 stars', '253 ratings']

                for i in range(1, math.ceil(len(text)//2)):
                    del text[i]
                text_final = text_final + text
                text_final = list(set(text_final))
                print(text_final)
            except IndexError:
                aaaa = 0
