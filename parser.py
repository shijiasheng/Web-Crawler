from bs4 import BeautifulSoup
import os
import csv
#雏形

# 文件夹路径
path = 'D:/1111/'
#path = 'H:/数据仓库数据集/html_0/'
header = ['id','title','release date','genres','director','producers','actor','supporting actors','media format','run time','MPAA rating','subtitles','studio','Item model number','Date First Available','IMDb','audio languages']
datas=[]

def get_normal_page_info(soup, movie):
    """
        Get All Info From Normal Pages
    """
    for child in soup.find('div', attrs={'id': 'detailBullets_feature_div'}).ul.find_all('li'):
        text = child.text.strip()
        #在movie  里加actor的信息
        if 'Actor' in text:
            movie['actor'] = ','.join([item.strip() for item in text.split(':')[1].split(',')])
        #在movie  里加director的信息
        elif 'Director' in text:
            movie['director'] = ','.join([item.strip() for item in text.split(':')[1].split(',')])
        #在movie  里加上映时间
        elif 'Release date' in text:
            movie['release date'] = text.split(':')[1].strip() # release date
        elif 'Media Format' in text:
            movie['media format'] = ','.join([item.strip() for item in text.split(':')[1].split(',')])
        elif 'Run time' in text:
            movie['run time'] = text.split(':')[1].strip()
        elif 'Item model number' in text:
            movie['Item model number'] = text.split(':')[1].strip()
        elif 'MPAA rating' in text:
            movie['MPAA rating'] = text.split(':')[1].strip()
        elif 'Studio' in text:
            movie['studio'] = text.split(':')[1].strip()
        elif 'Subtitles' in text:
            movie['subtitles'] = text.split(':')[1].strip()
        elif 'Date First Available' in text:
            movie['Date First Available'] = text.split(':')[1].strip()
    movie['title'] = soup.find(id='productTitle').text.strip() # movie title
    movie['genres'] = soup.find('ul').find_all('li')[-1].span.a.text.strip() # genres

    return movie

def get_prime_page_info(soup, movie):

    movie['title'] = soup.find('h1', attrs={'data-automation-id': 'title'}).text.strip() # movie title
    movie['IMDb'] = soup.find('span', attrs={'data-automation-id':'imdb-rating-badge'}).text.strip() # imdb
    movie['run time'] = soup.find('span', attrs={'data-automation-id':'runtime-badge'}).text.strip()  # run time
    movie['release date'] = soup.find('span', attrs={'data-automation-id':'release-year-badge'}).text.strip()  # release date

    #上半边
    for child in soup.find('div', attrs={'data-automation-id': 'meta-info'}).div.find_all('dl'):
        text = child.text

        if 'Genres' in text:
            text=text.lstrip('Genres')
            movie['genres'] =','.join([item.strip() for item in text.split('\n')[-1].split(',')]) # genre
        elif 'Directors' in text:
            text = text.lstrip('Directors')
            movie['director'] = ','.join([item.strip() for item in text.split('\n')[-1].split(',')]) # director
        elif 'Starring' in text:
            text = text.lstrip('Starring')
            movie['actor'] = ','.join([item.strip() for item in text.split('\n')[-1].split(',')]) # actor
        elif 'Subtitles' in text:
            text = text.lstrip('Subtitles')
            movie['subtitles'] = ','.join([item.strip() for item in text.split('\n')[-1].split(',')])  #Subtitles
        elif 'Audio languages' in text:
            text = text.lstrip('Audio ')
            text = text.lstrip('languages')
            movie['audio languages'] = ','.join([item.strip() for item in text.split('\n')[-1].split(',')])  #Audio languages
    # 下半边
    for child in soup.find('div', attrs={'data-automation-id': 'btf-product-details'}).div.find_all('dl'):
        text = child.text
        if 'Supporting actors' in text:
            text = text.strip('Supporting ')
            text = text.strip('actors')
            movie['supporting actors'] = ','.join([item.strip() for item in text.split('\n')[-1].split(',')])
        elif 'Producers' in text:
            text = text.lstrip('Producers')
            movie['producers'] = ','.join([item.strip() for item in text.split('\n')[-1].split(',')])  # producers
        elif 'Studio' in text:
            text = text.lstrip('Studio')
            movie['studio'] = ','.join([item.strip() for item in text.split('\n')[-1].split(',')])  # Studio
        elif 'MPAA rating' in text:
            text = text.lstrip('MPAA ')
            text = text.lstrip('rating')
            movie['MPAA rating'] = ','.join(
                [item.strip() for item in text.split('\n')[-1].split(',')])  # MPAA rating
        elif 'Format' in text:
            text = text.lstrip('Format')
            movie['media format'] = ','.join(
                [item.strip() for item in text.split('\n')[-1].split(',')])  # media format

    return movie

#raw_movie_id  处理文件的名
def parser(html, asin):
    """
        Check the Sent html
    """
    soup = BeautifulSoup(html, 'lxml')
    element = soup.find(id='productTitle')
    movie = {'id': asin}
    #不是白色的页面
    if element is None:
        element = soup.find('h1', attrs={'data-automation-id': 'title'})
        #空页面
        if element is None: # Error
            print("asin为空文件！")
            return False
        else:
            #黑色的页面
            try:
                movie = get_prime_page_info(soup, movie)
            except Exception:
                pass
    else: # Simple Page
        try:
            movie = get_normal_page_info(soup, movie)
        except Exception:
            pass
    '''
    if 'Director' not in html: # A movie must have a director
        return False
    if 'Fitness' in html: # Not a moive
        return False
    if 'Music Videos' in html:
        return False
    if 'Concerts' in html:
        return False
    if 'title' in movie and 'Season' in movie['title']:
        return False
        '''
    datas.append(movie)

    return True

if __name__=="__main__":
    files = os.listdir(path)

    for file in files:
        filename = path + file

        page_text = open(filename,encoding='utf-8',errors='ignore')
        asin = filename[-15:-5]
        #print(asin)
        parser(page_text,asin)
        # test.csv表示如果在当前目录下没有此文件的话，则创建一个csv文件
        # a表示以“追加”的形式写入，如果是“w”的话，表示在写入之前会清空原文件中的数据
        # newline是数据之间不加空行
        # encoding='utf-8'表示编码格式为utf-8，如果不希望在excel中打开csv文件出现中文乱码的话，将其去掉不写也行。
    with open('test.csv', 'w', newline='', encoding='utf-8') as f:
        writer = csv.DictWriter(f, fieldnames=header)  # 提前预览列名，当下面代码写入数据时，会将其一一对应。
        writer.writeheader()  # 写入列名
        writer.writerows(datas)  # 写入数据


