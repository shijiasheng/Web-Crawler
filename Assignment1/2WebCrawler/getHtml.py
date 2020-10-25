from random import randint
headers = {
    "accept": 'text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9',
    "accept-encoding": "gzip, deflate, br",
    "accept-language": "zh-CN,zh;q=0.9,en;q=0.8",
    "cache-control": "max-age=0",
    "downlink": "0.85",
    "ect": "3g",
    "rtt": "400",
    "sec-fetch-dest": "document",
    "sec-fetch-mode": "navigate",
    "sec-fetch-site": "none",
    "sec-fetch-user": "?1",
    "upgrade-insecure-requests": "1",
    "user-agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.75 Safari/537.36",
}


def get_cookies():
    import requests
    from selenium import webdriver
    import time
    i = 0
    cookies_list = []
    # 获取10个cookies
    for _ in range(10):
        url = 'https://www.amazon.com/dp/6301976975/'
        # 需要下载chromedriver
        driver = webdriver.Chrome("C:\\Users\\cheng fu\\Desktop\\chromedriver.exe")
        driver.get(url=url)
        driver.refresh()
        c = driver.get_cookies()
        # print(c)
        cookies = {}
        # 获取cookie中的name和value,转化成requests可以使用的形式
        for cookie in c:
            # print(cookie)
            cookies[cookie['name']] = cookie['value']
        # print(cookies)
        driver.quit()
        cookies_list.append(cookies)
        i = i + 1
        # print(i)
    return cookies_list


def getHtml():
    import requests
    import time
    import random
    from bs4 import BeautifulSoup
    # 获取cookies
    cookies_list = get_cookies()
    i = 1
    error_num = 0
    for id in open("./productIDResult.txt"):
        url = "https://www.amazon.com/dp/" + id
        random_cookie = random.randint(0, len(cookies_list) - 1)  # 随机获取一个cookie
        r = requests.get(url=url, headers=headers, cookies=cookies_list[random_cookie])
        # print(r)
        soup = BeautifulSoup(r.text, "lxml")
        if ((str(r) != "<Response [200]>") | (str(soup.title) == '<title dir="ltr">Amazon.com</title>')):
            error_num = error_num + 1
            print(error_num)
        else:
            pass
        file_name = "E:/html_" + str(int(i / 10000)) + "/" + str(i) + "_" + str(id)[:-1] + ".html"
        with open(file_name, "w", encoding='utf-8') as f:
            f.write(r.text)
        i = i + 1

getHtml()