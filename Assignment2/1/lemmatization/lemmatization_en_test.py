from nltk.stem import WordNetLemmatizer
from nltk.corpus import wordnet
from nltk import word_tokenize, pos_tag
import nltk
def get_wordnet_pos(tag):
    if tag.startswith('J'):
        return wordnet.ADJ
    elif tag.startswith('V'):
        return wordnet.VERB
    elif tag.startswith('N'):
        return wordnet.NOUN
    elif tag.startswith('R'):
        return wordnet.ADV
    else:
        return None

def change(word):
    # tagged_sent = pos_tag(word)
    # print(tagged_sent)
    # wordnet_pos = get_wordnet_pos(word) or wordnet.NOUN
    # print(wordnet_pos)
    lemmatizer = WordNetLemmatizer()
    word = lemmatizer.lemmatize(word, 'n')
    word = lemmatizer.lemmatize(word, 'v')
    word = lemmatizer.lemmatize(word, 'a')
    # print(word)
    # text_in = open('F:\\lemmatization-en.txt', 'rb')
    # for line_in in text_in:
    #     line_in = line_in.split()
    #     if line_in[1] == word:
    #         word = line_in[0]
    #         break
    # print(word)
    return word

    # text_out.write(line_in[0])
    # enter = '\n'
    # enter = bytes(enter, encoding='utf-8')
    # text_out.write(enter)
    # print(line_in[0])

if __name__ == '__main__':
    file_in = open('F:\\standard_review.txt', 'rb')
    # file_in = open('E:\\大三上\\数据仓库\\movies.txt', 'rb')
    file_out = open('F:\\output.txt', 'wb')
    for line in file_in.readlines():
        words = line.decode("utf-8")
        words = words.split()
        # print(words)
        for word in words:
            word = change(word)
            word = bytes(word, encoding='utf-8')
            file_out.write(word)
            enter = ' '
            enter = bytes(enter, encoding='utf-8')
            file_out.write(enter)