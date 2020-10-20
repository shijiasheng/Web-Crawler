import requests
from bs4 import BeautifulSoup
import csv



#解析网页，并获取帖子的url、标题
def get_data(lst):
    with open('1_B00003CXGJ.html', 'r', encoding='utf-8') as f:
        soup = BeautifulSoup(f.read(),"lxml")
    product_class = soup.find('span').find(class_="a-text-bold")
    print(product_class)
    # for i in soup.find_all("a",attrs="MPAA rating"):
    #     lst.append([i.attrs["href"],i.attrs["MPAA rating"]])

#保存url、标题到csv文件中
def save_to_csv(lst):
    with open('test.csv','w',newline='',encoding='utf-8')as f:
        f_csv = csv.writer(f)
        for data in lst:
            f_csv.writerow(data)

def main():
    lst = []
    get_data(lst)
    print(lst)
    save_to_csv(lst)

main()