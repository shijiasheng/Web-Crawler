import numpy as np
result = []
# i = 0
f = open('./movies.txt', 'rb')
while (True):
    line = f.readline()
    if not line:
        break
    line = str(line)
    if ('product/productId:' in line):
        line = line.replace("b'product/productId: ", "").replace("\\n'", "")
        if (len(line) == 10):  # 读取到一个productId
            userId = str(f.readline())  # 下一行一般就是userId
            if ('review/userId: ' not in userId):  # 下一行如果不是userId的话，报错
                print("error" * 100)
            userId = userId.replace("b'review/userId: ", "").replace("\\n'", "")
            # print(userId)
            result.append([str(line), str(userId)])
f.close()
result = np.array(result)
np.savetxt(
    "./productId-userId.csv",
    result,
    delimiter=',',
    fmt='%s',
    header="productId,userId",
)