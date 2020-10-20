from bs4 import BeautifulSoup
import os
import csv
import json

if __name__ == '__main__':
    # 更改工作目录
    question_files = []
    os.chdir('F:\\电影数据\\新建文件夹\\html_0')
    # print(os.listdir())
    directory = os.listdir()
    texts = {}
    # 对每个页面进行处理
    header = ['title', 'Studio', 'Actors', 'ASIN', 'Number of discs', 'Producers', 'Is Discontinued By Manufacturer',
              'Product Dimensions', 'Media Format', 'Subtitles', 'Item model number', 'Director', 'Release date',
              'Language', 'Run time', 'MPAA rating', 'Aspect Ratio', 'Language:', 'Date First Available',
              'Package Dimensions','Writers','Dubbed','Manufacturer','Item Weight','Original Release Date',
              'Label', 'Digital Copy Expiration Date', 'Audio Description', 'Publisher', 'ISBN-13', 'ISBN-10',
              'Manufacturer recommended age', 'Department', 'International Shipping', 'Domestic Shipping']  # 字段名
    with open('test.csv', 'w', newline='', encoding='utf-8')as f:
        f_csv = csv.DictWriter(f, header)
        f_csv.writeheader()
        for file in directory:
            # print(file)
            # 打开html文件进行操作
            with open(file, 'r', encoding='utf-8') as f:
                Soup = BeautifulSoup(f.read(), 'html.parser')
                # 先处理经典情况的select
                select = "#detailBullets_feature_div"
                # 标题是在不同地方的
                titleSelect = "#productTitle"
                # 因为可能有几个匹配的select,所以texts是一个数组
                texts = Soup.select(select)
                titles = Soup.select(titleSelect)
                # 因为我们的select是一段文字,而且只有一处是匹配的(或没有匹配)
                # 所以直接读取数组第一个元素(此处是一段文本),并且去掉空行,转换为数组
                try:
                    text = texts[0].text.splitlines()
                    # print(text)
                    title = titles[0].text.splitlines()
                    # 一个text可能的格式为:
                    # ['', '', '', '', 'Product details', '', '', '', '', '', 'Aspect Ratio', ':', '', '1.33:1, 1.85:1', '', '', 'Is Discontinued By Manufacturer', ':', '', 'No', '', '', 'MPAA rating', ':', '', 's_medPG13 PG-13 (Parents Strongly Cautioned)', '', '', 'Product Dimensions', ':', '', '7.5 x 5.5 x 0.53 inches; 4 Ounces', '', '', 'Item model number', ':', '', '2226213', '', '', 'Director', ':', '', 'David Raynr', '', '', 'Media Format', ':', '', 'Closed-captioned, Color, Dolby, Full Screen, NTSC, Special Edition, Subtitled', '', '', 'Run time', ':', '', '1 hour and 34 minutes', '', '', 'Release date', ':', '', 'August 1, 2000', '', '', 'Actors', ':', '', 'Shane West, James Franco, Marla Sokoloff', '', '', 'Subtitles:', ':', '', 'English', '', '', 'Producers', ':', '', 'Paul Schiff', '', '', 'Language', ':', '', 'Unqualified', '', '', 'Studio', ':', '', 'Sony Pictures Home Entertainment', '', '', 'ASIN', ':', '', 'B00003CXGJ', '', '', 'Number of discs', ':', '', '1', '', '', '', '', '', '', 'Best Sellers Rank:', '', '#76,097 in Movies & TV (See Top 100 in Movies & TV)', '', ' #5,087 in Romance (Movies & TV)', ' #10,283 in Comedy (Movies & TV)', ' #16,980 in Drama DVDs', '', '', '', '', '', '', 'Customer Reviews:', '', '', '', '', '', '', '', '4.3 out of 5 stars', '', '', '', '', '', '', '', '', '253 ratings', '', '', '', '', '', '', '', '', '', '', '', '']

                    while '' in title:
                        title.remove('')
                    while '' in text:
                        text.remove('')

                    while ':' in text:
                        text.remove(':')
                    while 'Product details' in text:
                        text.remove('Product details')
                    while 'Size:One Size' in text:
                        text.remove('Size:One Size')
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
                    for i in range(len(text)):
                        text[i] = text[i].replace('"',"'")

                    for i in range(len(text)):
                        if text[i]!=':':
                            text[i] = text[i].replace(':','')
                    # print(text)
                    for i in range(0, len(text), 2):
                        text[i] = '"' + text[i] + '":'
                    for i in range(2, len(text), 2):
                        text[i] = ',' + text[i]
                    for i in range(1, len(text), 2):
                        text[i] = '"' + text[i] + '"'

                    for i in range(0,len(title)):
                        title[i] = title[i].replace('"',"'")
                    # print(title[0])
                    # title[0] = title[0].replace('"', "'")
                    # print(title[0])
                    text.insert(0, '"title":' + '"' + title[0] + '",')
                    text.insert(0, '{')
                    text.append('}')
                    text = "".join(text)
                    text = text.replace('\\', '\\\\')
                    # print(text)
                    # print(file[0:4])
                    text_dict = json.loads(text)
                    # print(text_dict)
                    # texts.update(text_dict)
                    # dict2.update(dict1)
                    # texts = texts + text_dict
                    f_csv.writerow(text_dict)

                    # 打印
                    # print(title)
                    # print(text)
                    # with open('test.csv', 'w', newline='', encoding='utf-8')as f:
                    #     f_csv = csv.writer(f)
                    #     for data in lst:
                    #         f_csv.writerow(data)
                except IndexError:
                    #     此处说明该页面不是经典的布局,启用第二种情况
                    titleSelect = "#a-page > div.av-page-desktop.avu-retail-page > div.DVWebNode-detail-atf-wrapper.DVWebNode > div > div > div._3KHiTg._2r7Wei.av-dp-container._13P0S3 > div.av-detail-section._1eXZeC > div > h1"
                    select = "#meta-info > div"
                    texts = Soup.select(select)
                    titles = Soup.select(titleSelect)
                    try:
                        text = texts[0].text.splitlines()
                        title = titles[0].text.splitlines()
                    except IndexError:
                        # 此处说明该页面不是经典的布局,启用第三种情况
                        # 未知,先打印文件名,手动查看,编写代码
                        print(file)
                        question_files.append(file)
            # 去除空元素

    print(question_files)