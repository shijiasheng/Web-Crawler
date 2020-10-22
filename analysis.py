from bs4 import BeautifulSoup
import os
import csv
import json

if __name__ == '__main__':
    # 出错页面个数
    errorCount=0
    # 已处理页面
    processCount=0

    # 更改工作目录
    question_files = []
    # os.chdir('F:\\电影数据\\新建文件夹\\html_0')
    os.chdir('C:\\Users\\12549\\Desktop\\数据仓库\\data')
    # print(os.listdir())
    directory = os.listdir()
    texts = {}

    header = ['title', 'Studio', 'Actors', 'ASIN', 'Number of discs', 'Producers', 'Is Discontinued By Manufacturer',
              'Product Dimensions', 'Media Format', 'Subtitles', 'Item model number', 'Director', 'Release date',
              'Language', 'Run time', 'MPAA rating', 'Aspect Ratio', 'Language:', 'Date First Available',
              'Package Dimensions','Writers','Dubbed','Manufacturer','Item Weight','Original Release Date',
              'Label', 'Digital Copy Expiration Date', 'Audio Description', 'Publisher', 'ISBN-13', 'ISBN-10',
              'Manufacturer recommended age', 'Department', 'International Shipping', 'Domestic Shipping',
              'Paperback', 'ColorOne Color', 'Batteries', 'Hardcover', 'SPARS Code', 'DVD Audio', 'Reading level', 'Grade Level']  # 字段名

    header2=['title','isMovie','runTime','year','Directors','Starring','Genres','Supporting actors']
    with open('test.csv', 'w', newline='', encoding='utf-8')as f, \
     open('test2.csv', 'w', newline='', encoding='utf-8') as f2:
        f_csv = csv.DictWriter(f, header)
        f_csv.writeheader()

        f2_csv = csv.DictWriter(f2, header2)
        f2_csv.writeheader()

        for file in directory:
            processCount+=1

            # 查看特定页面问题
            # if file!='603_B007QJAE0O.html':
            #     continue
            # 打开html文件进行操作

            # 避免报错后下一次用上一层的数据,造成混乱
            text= ''
            title=''
            detail=''

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
                try:
                    text = texts[0].text.splitlines()
                    # print(text)
                    title = titles[0].text.splitlines()
                    # print(title)
                    # 一个text可能的格式为:
                    # ['', '', '', '', 'Product details', '', '', '', '', '', 'Aspect Ratio', ':', '', '1.33:1, 1.85:1', '', '', 'Is Discontinued By Manufacturer', ':', '', 'No', '', '', 'MPAA rating', ':', '', 's_medPG13 PG-13 (Parents Strongly Cautioned)', '', '', 'Product Dimensions', ':', '', '7.5 x 5.5 x 0.53 inches; 4 Ounces', '', '', 'Item model number', ':', '', '2226213', '', '', 'Director', ':', '', 'David Raynr', '', '', 'Media Format', ':', '', 'Closed-captioned, Color, Dolby, Full Screen, NTSC, Special Edition, Subtitled', '', '', 'Run time', ':', '', '1 hour and 34 minutes', '', '', 'Release date', ':', '', 'August 1, 2000', '', '', 'Actors', ':', '', 'Shane West, James Franco, Marla Sokoloff', '', '', 'Subtitles:', ':', '', 'English', '', '', 'Producers', ':', '', 'Paul Schiff', '', '', 'Language', ':', '', 'Unqualified', '', '', 'Studio', ':', '', 'Sony Pictures Home Entertainment', '', '', 'ASIN', ':', '', 'B00003CXGJ', '', '', 'Number of discs', ':', '', '1', '', '', '', '', '', '', 'Best Sellers Rank:', '', '#76,097 in Movies & TV (See Top 100 in Movies & TV)', '', ' #5,087 in Romance (Movies & TV)', ' #10,283 in Comedy (Movies & TV)', ' #16,980 in Drama DVDs', '', '', '', '', '', '', 'Customer Reviews:', '', '', '', '', '', '', '', '4.3 out of 5 stars', '', '', '', '', '', '', '', '', '253 ratings', '', '', '', '', '', '', '', '', '', '', '', '']

                    # 去掉title和text所有的空元素
                    while '' in title:
                        title.remove('')
                    while '' in text:
                        text.remove('')
                    # 去掉冒号和一些诸如Product details和Size:One Size的无关信息
                    while ':' in text:
                        text.remove(':')
                    while 'Product details' in text:
                        text.remove('Product details')
                    while 'Size:One Size' in text:
                        text.remove('Size:One Size')
                    # 去掉Customer Reviews:
                    index = 0
                    while index < len(text):
                        if (text[index] == "Customer Reviews:"):
                            break
                        index = index + 1
                    if index != len(text):
                        len_text = len(text)
                        for i in range(len_text - 1, index - 1, -1):
                            del text[i]
                    # 去掉Best Sellers Rank:
                    index = 0
                    while index < len(text):
                        if (text[index] == "Best Sellers Rank:"):
                            break
                        index = index + 1
                    if index != len(text):
                        len_text = len(text)
                        for i in range(len_text - 1, index - 1, -1):
                            del text[i]
                    # 将text里面的双引号“替换成‘单引号（目的是字典处理时只认双引号
                    for i in range(len(text)):
                        text[i] = text[i].replace('"', "'")
                    # 去掉无关紧要的冒号（目的是后续统一加上冒号
                    for i in range(len(text)):
                        if text[i] != ':':
                            text[i] = text[i].replace(':', '')
                    # 将title里面的双引号“替换成‘单引号（目的是字典处理时只认双引号
                    for i in range(0, len(title)):
                        title[i] = title[i].replace('"', "'")
                    for i in range(0, len(text)):
                        text[i] = text[i].replace('"', "'")
                    # 将title里面的逗号换成分号
                    for i in range(0, len(title)):
                        title[i] = title[i].replace(',', ';')
                    for i in range(0, len(text)):
                        text[i] = text[i].replace(',', ';')
                    # 如果这项不在header里面，去掉
                    for i in range(0, len(text), 2):
                        if i < len(text):
                            if (text[i] in header):
                                sjs = 1
                            else:
                                del text[i + 1]
                                del text[i]
                    # 添加冒号，引号，逗号，都是字典查看的标识符
                    for i in range(0, len(text), 2):
                        text[i] = '"' + text[i] + '":'
                    for i in range(2, len(text), 2):
                        text[i] = ',' + text[i]
                    for i in range(1, len(text), 2):
                        text[i] = '"' + text[i] + '"'
                    # 在text最前端加上title
                    text.insert(0, '"title":' + '"' + title[0] + '",')
                    # 在前后加上大括号
                    text.insert(0, '{')
                    text.append('}')
                    # 将数组合并成一个大的字符串
                    text = "".join(text)
                    # 将字符串中的斜杠换掉
                    text = text.replace('\\', '\\\\')
                    # print("情况1:"+text)
                    # print(file[0:5])
                    # 转换成字典
                    text_dict = json.loads(text)
                    # 写入csv文件
                    f_csv.writerow(text_dict)

                except IndexError:
                    result=""

                    #     此处说明该页面不是经典的布局,启用第二种情况
                    titleSelect = "#a-page > div.av-page-desktop.avu-retail-page > div.DVWebNode-detail-atf-wrapper.DVWebNode > div > div > div._3KHiTg._2r7Wei.av-dp-container._13P0S3 > div.av-detail-section._1eXZeC > div > h1"
                    select = "#meta-info > div > dl"
                    detailSelect = "#btf-product-details > div > dl"
                    typeSelect="#dv-action-box > div > div > div > div.abwJ5F.tFxybk._2LF_6p._32Y4AN > div > span._2cx-XY.dv-dp-node-watchlist._1qXS7N > form > input[type=hidden]:nth-child(2)"
                    runTimeSelect="#a-page > div.av-page-desktop.avu-retail-page > div.DVWebNode-detail-atf-wrapper.DVWebNode > div > div > div._3KHiTg._2r7Wei.av-dp-container._13P0S3 > div.av-detail-section._1eXZeC > div > div._3QwtCH._16AW_S._2LF_6p.dv-node-dp-badges.uAeEjV._1qXS7N > span:nth-child(2) > span"
                    yearSelect="#a-page > div.av-page-desktop.avu-retail-page > div.DVWebNode-detail-atf-wrapper.DVWebNode > div > div > div._3KHiTg._2r7Wei.av-dp-container._13P0S3 > div.av-detail-section._1eXZeC > div > div._3QwtCH._16AW_S._2LF_6p.dv-node-dp-badges.uAeEjV._1qXS7N > span:nth-child(3) > span"
                    yearTempSelect="#a-page > div.av-page-desktop.avu-retail-page > div.DVWebNode-detail-atf-wrapper.DVWebNode > div > div > div._3KHiTg._2r7Wei.av-dp-container._13P0S3 > div.av-detail-section._1eXZeC > div > div._3QwtCH._16AW_S._2LF_6p.dv-node-dp-badges.uAeEjV._1qXS7N > span:nth-child(4) > span"
                    try:
                        texts = Soup.select(select)
                        titles = Soup.select(titleSelect)
                        details = Soup.select(detailSelect)
                        types=Soup.select(typeSelect)
                        runTime=Soup.select(runTimeSelect)
                        year=Soup.select(yearSelect)
                        yearTemp=Soup.select(yearTempSelect)



                        # 还可以区分异常情况
                        text = texts[0].text
                        title=titles[0].text

                        result+='{"title":"'+title+'",'

                        if "titleType=movie" in repr(types[0]):
                            # 必然是movie
                            result+='"isMovie":"true",'
                            # movieType=repr(types[0])
                            # index=movieType.find("titleType=")
                            # print(movieType[index:index+20])
                        if len(year)!=0 and "release-year-badge" in repr(year[0]):
                            result += '"year":"' + year[0].text + '",'
                        elif len(runTime) != 0 and "release-year-badge" in repr(runTime[0]):
                            result += '"year":"' + runTime[0].text + '",'
                        elif len(yearTemp) != 0 and "release-year-badge" in repr(yearTemp[0]):
                            result += '"year":"' + yearTemp[0].text + '",'

                        if len(year)!=0 and "runtime-badge" in repr(year[0]):
                            result += '"runTime":"' + year[0].text + '",'
                        elif len(runTime) != 0 and "runtime-badge" in repr(runTime[0]):
                            result += '"runTime":"' + runTime[0].text + '",'
                        elif len(yearTemp) != 0 and "runtime-badge" in repr(yearTemp[0]):
                            result += '"runTime":"' + yearTemp[0].text + '",'



                        # if len(runTime) != 0:
                        #     # 有运行时间
                        #     result += '"runTime":"'+runTime[0].text+'",'
                        #     # print(runTime[0].text)
                        # if len(year)!=0:
                        #     result += '"year":"' + year[0].text + '",'




                        # print(texts[0].text)
                        # print(texts[0].text.split('\n'))
                        # text = texts[0].text.splitlines()
                        # title = titles[0].text.splitlines()
                        # detail=details[0].text.splitlines()

                        for text in texts:
                            text=text.text
                            if "Directors" in text:
                                text=text.replace("Directors","")
                                result += '"Directors":"' + text + '",'
                            if "Starring" in text:
                                text=text.replace("Starring","")
                                result += '"Starring":"' + text + '",'
                            if "Genres" in text:
                                text=text.replace("Genres","")
                                result += '"Genres":"' + text + '",'

                        for detail in details:
                            text = detail.text
                            if "Supporting actors" in text:
                                text=text.replace("Supporting actors","")
                                result += '"Supporting actors":"' + text + '",'
                                print(text)
                        # for detail in details:
                        #     print(detail.text)
                        # temp=texts[0].text
                        # 去掉最后一个字符,并且添加}
                        result=result[:-1]+"}"

                        print("情况2:"+file)

                        # print(result)

                        # 转换成字典
                        result_dict = json.loads(result)
                        # print(result_dict)
                        # 写入csv文件
                        f2_csv.writerow(result_dict)

                    except IndexError:
                        # 此处说明该页面不是经典的布局,启用第三种情况
                        # 未知,先打印文件名,手动查看,编写代码
                        errorCount+=1
                        print(errorCount,processCount,sep='/')
                        # print(file)
                        # question_files.append(file)
            # 去除空元素

    # print(question_files)