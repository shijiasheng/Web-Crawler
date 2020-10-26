import csv
import difflib


def string_similar(s1, s2):
    return difflib.SequenceMatcher(None, s1, s2).ratio()


def Max(s1, s2):
    if(len(s1)>len(s2)):
        return s1
    else:
        return s2

def JudgeTime(s1,s2):
    if s1==s2:
        return True
    elif s1 == '':
        return True
    elif s2 == '':
        return True
    else:
        return False
def Similarity(s1, s2):
    len1 = len(s1)
    len2 = len(s2)
    dif = [[]]
    for a in range(len1+2):
        dif[a][0] = a
    for a in range(len2+2):
        dif[0][a] = a
    for i in range(1,len1+1):
        for j in range(1,len2+1):
            if s1[i-1] == s2[j-1]:
                temp = 0
            else:
                temp = 1
            dif[i][j] = min(dif[i - 1][j - 1] + temp, dif[i][j - 1] + 1, dif[i - 1][j] + 1)
    similarity = 1 - dif[len1][len2] / max(len(s1), len(s2))
    return similarity


if __name__ == '__main__':
    with open('C:\\Users\\Lenovo\\Desktop\\productData.csv','r')as f:
        reader = csv.DictReader(f, fieldnames=('id','title','release date','genres','director','producers','actor','supporting actors','media format','run time','MPAA rating','subtitles','studio','Item model number','Date First Available','IMDb','audio languages'))
        final=[]
        out_final=[]
        for i in reader:
            final.append(dict(i))
        with open('C:\\Users\\Lenovo\\Desktop\\final_out1.csv', 'w', newline='')as outFile:
            writer = csv.DictWriter(outFile,  fieldnames=('id','title','release date','genres','director','producers','actor','supporting actors','media format','run time','MPAA rating','subtitles','studio','Item model number','Date First Available','IMDb','audio languages','link id','link title'))
            # writer.writerows(final)
            temp1 = final[0]
            temp1['link id'] = ''
            temp1['link title'] = ''
            for i in range(len(final)):
                temp2 = final[i]
                temp2['link id'] = ''
                temp2['link title'] = ''

                min_lenth = min(len(temp1['title']), len(temp2['title']))
                min_lenth = min(min_lenth, 12)
                min_lenth2 = min(min_lenth, 6)
                # if temp1['title'] == temp2['title'] and len(temp1['title']) <= 4 and len(temp2['title']) <= 4:
                #     temp1['director'] = Max(temp1['director'], temp2['director'])
                #     temp1['actor'] = Max(temp1['actor'], temp2['actor'])
                #     temp1['supporting actors'] = Max(temp1['supporting actors'], temp2['supporting actors'])
                #     temp1['genres'] = Max(temp1['genres'], temp2['genres'])
                #     temp1['producers'] = Max(temp1['producers'], temp2['producers'])
                #     temp1['run time'] = Max(temp1['run time'], temp2['run time'])
                #     temp1['Date First Available'] = Max(temp1['Date First Available'], temp2['Date First Available'])
                #     # temp1['director'] = Max(temp1['director'], temp2['director'])
                #     temp1['link id'] = temp1['link id'] + "\n" + temp2['id']
                #     temp1['link title'] = temp1['link title'] + "\n" + temp2['title']
                #     continue
                # elif string_similar(temp1['title'], temp2['title']) > 0.6 and JudgeTime(temp1['release date'],temp2['release date']):
                #     temp1['director'] = Max(temp1['director'], temp2['director'])
                #     temp1['actor'] = Max(temp1['actor'], temp2['actor'])
                #     temp1['supporting actors'] = Max(temp1['supporting actors'], temp2['supporting actors'])
                #     temp1['genres'] = Max(temp1['genres'], temp2['genres'])
                #     temp1['producers'] = Max(temp1['producers'], temp2['producers'])
                #     temp1['run time'] = Max(temp1['run time'], temp2['run time'])
                #     temp1['Date First Available'] = Max(temp1['Date First Available'], temp2['Date First Available'])
                #     # temp1['director'] = Max(temp1['director'], temp2['director'])
                #     temp1['link id'] = temp1['link id'] + "\n" + temp2['id']
                #     temp1['link title'] = temp1['link title'] + "\n" + temp2['title']
                #     continue
                # elif string_similar(temp1['title'][:min_lenth], temp2['title'][:min_lenth]) > 0.9 and len(temp1['title']) >= 8 and len(temp2['title']) >= 8 and len(temp1['title']) <= 18 and len(temp2['title']) <= 18:
                #     temp1['director'] = Max(temp1['director'], temp2['director'])
                #     temp1['actor'] = Max(temp1['actor'], temp2['actor'])
                #     temp1['supporting actors'] = Max(temp1['supporting actors'], temp2['supporting actors'])
                #     temp1['genres'] = Max(temp1['genres'], temp2['genres'])
                #     temp1['producers'] = Max(temp1['producers'], temp2['producers'])
                #     temp1['run time'] = Max(temp1['run time'], temp2['run time'])
                #     temp1['Date First Available'] = Max(temp1['Date First Available'], temp2['Date First Available'])
                #     # temp1['director'] = Max(temp1['director'], temp2['director'])
                #     temp1['link id'] = temp1['link id'] + "\n" + temp2['id']
                #     temp1['link title'] = temp1['link title'] + "\n" + temp2['title']
                #     continue
                # elif string_similar(temp1['title'][:min_lenth2], temp2['title'][:min_lenth2]) > 0.9 and len(temp1['title']) >= 4 and len(temp2['title']) >= 4:
                #     temp1['director'] = Max(temp1['director'], temp2['director'])
                #     temp1['actor'] = Max(temp1['actor'], temp2['actor'])
                #     temp1['supporting actors'] = Max(temp1['supporting actors'], temp2['supporting actors'])
                #     temp1['genres'] = Max(temp1['genres'], temp2['genres'])
                #     temp1['producers'] = Max(temp1['producers'], temp2['producers'])
                #     temp1['run time'] = Max(temp1['run time'], temp2['run time'])
                #     temp1['Date First Available'] = Max(temp1['Date First Available'], temp2['Date First Available'])
                #     # temp1['director'] = Max(temp1['director'], temp2['director'])
                #     temp1['link id'] = temp1['link id'] + "\n" + temp2['id']
                #     temp1['link title'] = temp1['link title'] + "\n" + temp2['title']
                #     continue

                if temp1['title'] == temp2['title'] and len(temp1['title']) <= 4 and len(temp2['title']) <= 4 and JudgeTime(temp1['release date'],temp2['release date']):
                    temp1['director'] = Max(temp1['director'], temp2['director'])
                    temp1['actor'] = Max(temp1['actor'], temp2['actor'])
                    temp1['supporting actors'] = Max(temp1['supporting actors'], temp2['supporting actors'])
                    temp1['genres'] = Max(temp1['genres'], temp2['genres'])
                    temp1['producers'] = Max(temp1['producers'], temp2['producers'])
                    temp1['run time'] = Max(temp1['run time'], temp2['run time'])
                    temp1['Date First Available'] = Max(temp1['Date First Available'], temp2['Date First Available'])
                    # temp1['director'] = Max(temp1['director'], temp2['director'])
                    temp1['link id'] = temp1['link id'] + "\n" + temp2['id']
                    temp1['link title'] = temp1['link title'] + "\n" + temp2['title']
                    continue
                elif string_similar(temp1['title'], temp2['title']) > 0.6 and JudgeTime(temp1['release date'],temp2['release date']):
                    temp1['director'] = Max(temp1['director'], temp2['director'])
                    temp1['actor'] = Max(temp1['actor'], temp2['actor'])
                    temp1['supporting actors'] = Max(temp1['supporting actors'], temp2['supporting actors'])
                    temp1['genres'] = Max(temp1['genres'], temp2['genres'])
                    temp1['producers'] = Max(temp1['producers'], temp2['producers'])
                    temp1['run time'] = Max(temp1['run time'], temp2['run time'])
                    temp1['Date First Available'] = Max(temp1['Date First Available'], temp2['Date First Available'])
                    # temp1['director'] = Max(temp1['director'], temp2['director'])
                    temp1['link id'] = temp1['link id'] + "\n" + temp2['id']
                    temp1['link title'] = temp1['link title'] + "\n" + temp2['title']
                    continue
                elif string_similar(temp1['title'][:min_lenth], temp2['title'][:min_lenth]) > 0.9 and len(temp1['title']) >= 8 and len(temp2['title']) >= 8 and len(temp1['title']) <= 18 and len(temp2['title']) <= 18 and JudgeTime(temp1['release date'],temp2['release date']):
                    temp1['director'] = Max(temp1['director'], temp2['director'])
                    temp1['actor'] = Max(temp1['actor'], temp2['actor'])
                    temp1['supporting actors'] = Max(temp1['supporting actors'], temp2['supporting actors'])
                    temp1['genres'] = Max(temp1['genres'], temp2['genres'])
                    temp1['producers'] = Max(temp1['producers'], temp2['producers'])
                    temp1['run time'] = Max(temp1['run time'], temp2['run time'])
                    temp1['Date First Available'] = Max(temp1['Date First Available'], temp2['Date First Available'])
                    # temp1['director'] = Max(temp1['director'], temp2['director'])
                    temp1['link id'] = temp1['link id'] + "\n" + temp2['id']
                    temp1['link title'] = temp1['link title'] + "\n" + temp2['title']
                    continue
                elif string_similar(temp1['title'][:min_lenth2], temp2['title'][:min_lenth2]) > 0.9 and len(temp1['title']) >= 4 and len(temp2['title']) >= 4 and JudgeTime(temp1['release date'],temp2['release date']):
                    temp1['director'] = Max(temp1['director'], temp2['director'])
                    temp1['actor'] = Max(temp1['actor'], temp2['actor'])
                    temp1['supporting actors'] = Max(temp1['supporting actors'], temp2['supporting actors'])
                    temp1['genres'] = Max(temp1['genres'], temp2['genres'])
                    temp1['producers'] = Max(temp1['producers'], temp2['producers'])
                    temp1['run time'] = Max(temp1['run time'], temp2['run time'])
                    temp1['Date First Available'] = Max(temp1['Date First Available'], temp2['Date First Available'])
                    # temp1['director'] = Max(temp1['director'], temp2['director'])
                    temp1['link id'] = temp1['link id'] + "\n" + temp2['id']
                    temp1['link title'] = temp1['link title'] + "\n" + temp2['title']
                    continue
                writer.writerow(temp1)
                # out_final.append(dict(temp1))
                temp1 = temp2
            writer.writerow(temp1)
            # writer.writerows(out_final)



        # for i in final:
        #     print(i)


