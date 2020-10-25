import re
import csv


def extract_productId(input_file):
    file = open(input_file, 'rb')
    new_file = open('product.txt', 'wb')
    for line in file:
        str = "product/productId:"
        str = str.encode()
        if str in line:
            new_file.write(line)
    file.close()
    new_file.close()
    print('productId is extracted')


def extract_id():
    file = open('product.txt', 'r')
    with open('id.csv', 'w') as csv_file:
        writer = csv.writer(csv_file, lineterminator='\n')
        writer.writerow(["Id"])
        for line in file:
            Id = re.findall(r"product/productId:(.+?)\n",line)
            writer.writerow(Id)
    print('Id is extracted')
    file.close()

def duplicate_id():
    file = 'id.csv'
    data = []
    try:
        with open(file) as f:
            reader = csv.reader(f,dialect=csv.excel_tab);
            data = [row for row in reader]
    except csv.Error as e:
        print(reader.line_num,e);
    data.drop_duplicates(inplace=True)

if __name__ == '__main__':
    data_file = './movies.txt'
    print('**********Start***********')
    extract_productId(data_file)
    extract_id()
    # duplicate_id()
    print('**********Done***********')