from bs4 import BeautifulSoup
import os
import csv
import json


if __name__ == '__main__':
    # 更改工作目录
    os.chdir('C:\\Users\\12549\\Desktop\\数据仓库\\data')
    directory = os.listdir()

    for file in directory:
        if file == 'test.csv' or file == 'test2.csv':
            continue
        if file != '9384_B0009MAO32.html':
            continue

        # 对每个页面进行处理
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
            # print(texts[0])
            # testSelect="html"
            # tests=Soup.select(testSelect)
            # print(tests)
            # print(file)
            Soup.find('div', attrs={'id': 'detailBullets_feature_div'})